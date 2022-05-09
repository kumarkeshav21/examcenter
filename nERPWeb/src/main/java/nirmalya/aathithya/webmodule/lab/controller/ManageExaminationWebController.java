package nirmalya.aathithya.webmodule.lab.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.AddCenterAddMoreWebModel;
import nirmalya.aathithya.webmodule.lab.model.AddInstructionModel;
import nirmalya.aathithya.webmodule.lab.model.AddMoreExamCourseWebModel;
import nirmalya.aathithya.webmodule.lab.model.EditExaminationWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageCenterWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageExaminationWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageInstructionWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class ManageExaminationWebController {
	Logger logger = LoggerFactory.getLogger(ManageExaminationWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("manage-examination")
	public String manageExamination(Model model, HttpSession session) {

		logger.info("Method : manageExamination starts");
	
		logger.info("Method : manageExamination ends");
		return "lab/manage-examination";
	}
	
	//for manage examination  
	@SuppressWarnings("unchecked")
	
	@GetMapping("manage-examination-table")
	public @ResponseBody JsonResponse<List<ManageExaminationWebModel>> viewManageExamination(Model model,HttpSession session){
		logger.info("Method :viewManageExamination starts");
		String userid = (String)session.getAttribute("USER_ID");
		String userRole = (String)session.getAttribute("USER_ROLETYPE");
		String userRoles = (String)session.getAttribute("USER_ROLES_STRING");
		JsonResponse<List<ManageExaminationWebModel>> resp = new JsonResponse<List<ManageExaminationWebModel>>();

		try {
			resp = 
			restTemplate.getForObject(env.getLabUrl() + "rest-manage-examination-table?userid="+userid+"&userrole="+userRoles, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ManageExaminationWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ManageExaminationWebModel>>() {
				});
		String drProfDoc=null;
		for(ManageExaminationWebModel i : quotationNewModel) {
		if (i.getLogo() != null && i.getLogo() != ""
				&& ! quotationNewModel.get(0).getLogo().equals("null")) {
			
			drProfDoc = env.getBaseURL() + "document/profile/"+i.getLogo();
			i.setDrProfDoc(drProfDoc);
			System.out.println("Image"+drProfDoc);
			
		}}
		
		
		
	
	  for (ManageExaminationWebModel m : quotationNewModel) {
	 
	 if (m.getStatus().equals("1")) { m.setStatus("Active"); } else {
	  m.setStatus("InActive"); }
	 
	 }
	 
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}
		
		
		System.out.println("########"+resp);
		logger.info("Method :viewManageExamination ends");
		// System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	/*
	 * Edit Exam details
	 */
	@SuppressWarnings("unchecked")
	///@PostMapping("manage-examination-details-save")
	@RequestMapping(value = "manage-examination-edit-details-save", method = { RequestMethod.POST })
	public @ResponseBody JsonResponse<Object> editExaminationDetails(Model model, @RequestBody EditExaminationWebModel editExaminationWebModel,
			HttpSession session) {
		System.out.println("########"+editExaminationWebModel);
		logger.info("Method : editExaminationDetails starts");
		
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		@SuppressWarnings("unused")
		String userId = (String) session.getAttribute("USER_ID");

		System.out.println("########"+userId);
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				editExaminationWebModel.setUploadLogo(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("keshav"+imageName);
		try {

			resp = restTemplate.postForObject(env.getLabUrl() + "edit-examination-details?userId="+userId, editExaminationWebModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		
		System.out.println("@@@======>>>"+resp);
		logger.info("Method : editExaminationDetails starts");
		return resp;
	}


	@SuppressWarnings("unchecked")
	@GetMapping("/manage-examination-delete")
	public @ResponseBody JsonResponse<Object> deleteExamination(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteExamination starts");
		String userId = (String) session.getAttribute("USER_ID");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "deleteExamination?id=" + id+"&userId="+userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteExamination ends");
		return resp;
	}

	
/*************************************************************************/
	@PostMapping("edit-exam-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");
		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("quotationPFile", inputFile);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : uploadFile controller function 'post-mapping' ends");
		return response;
	}

	@SuppressWarnings("unused")
	private String saveAllImage(byte[] imageBytes, String ext) {
		logger.info("Method : saveAllImage starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName = nowTime + ".jpg";
				} else {
					imageName = nowTime + "." + ext;
				}

			}
             System.out.println(env.getFileUploadProcurement() + imageName);
			Path path = Paths.get(env.getFileUploadProcurement() + imageName);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;
		
	}
	
	/**************add more exam manage *******************/
	@SuppressWarnings("unchecked")
	@GetMapping("manage-examination-addmore")
	public @ResponseBody JsonResponse<List<AddMoreExamCourseWebModel>> viewManageExamCourse(HttpSession session,Model model, @RequestParam String id) {
		logger.info("Method :viewManageExamCourse starts");
		String userid = (String) session.getAttribute("USER_ID");

		System.out.println("########################" + userid);
		JsonResponse<List<AddMoreExamCourseWebModel>> resp = new JsonResponse<List<AddMoreExamCourseWebModel>>();
		
		 List<DropDownModel> courseList = new ArrayList<DropDownModel>();
		 /* List<DropDownModel> getshiftList = new ArrayList<DropDownModel>();
		 */
		try {
			DropDownModel[] courseType = restTemplate.getForObject(env.getLabUrl() + "get-exam-Course-id?id="+id,
					DropDownModel[].class);

			courseList = Arrays.asList(courseType);
			model.addAttribute("courseList", courseList);
			System.out.println("############"+courseList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-manage-exam-addmore?id=" + id,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
      
      
      
		ObjectMapper mapper = new ObjectMapper();

		List<AddMoreExamCourseWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<AddMoreExamCourseWebModel>>() {
				});
		for (AddMoreExamCourseWebModel m : quotationNewModel) {
			m.setCourseList(courseList);
			
		}
		
	      
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewManageExamCourse ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
}
