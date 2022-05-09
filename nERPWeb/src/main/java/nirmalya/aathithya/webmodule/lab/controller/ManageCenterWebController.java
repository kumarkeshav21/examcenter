package nirmalya.aathithya.webmodule.lab.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.AddCenterAddMoreWebModel;
import nirmalya.aathithya.webmodule.lab.model.CandidateListWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageCenterWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageInstructionWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class ManageCenterWebController {
	Logger logger = LoggerFactory.getLogger(ManageCenterWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("manage-center")
	public String manageCenter(Model model, HttpSession session) {

		logger.info("Method : manageCenter starts");
		
		try {
			DropDownModel[] locList = restTemplate.getForObject(env.getLabUrl() + "get-loc-center-list",
					DropDownModel[].class);
			List<DropDownModel> getLocList = Arrays.asList(locList);

			model.addAttribute("locList", getLocList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		//examlist drop down
		
				try {
					DropDownModel[] examlist = restTemplate.getForObject(env.getLabUrl() + "get-exam-center-list",
							DropDownModel[].class);
					List<DropDownModel> getExamList = Arrays.asList(examlist);

					model.addAttribute("examList", getExamList);
				} catch (RestClientException e) {
					e.printStackTrace();
				}
				
			
		logger.info("Method : manageCenter ends");
		return "lab/manage-center";
	}


	@SuppressWarnings("unchecked")
	@GetMapping("manage-center-table")
	public @ResponseBody JsonResponse<List<ManageCenterWebModel>> viewManageCenter(Model model,HttpSession session) {
		logger.info("Method :viewManageCenter starts");
		String userid = (String) session.getAttribute("USER_ID");
		String userRoles = (String)session.getAttribute("USER_ROLES_STRING");

		System.out.println("########################>>>>>" + userid);
		System.out.println("########################>>>>>" + userRoles);
		JsonResponse<List<ManageCenterWebModel>> resp = new JsonResponse<List<ManageCenterWebModel>>();
      try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-manage-center-table?userid=" + userid+"&userrole="+userRoles,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ManageCenterWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ManageCenterWebModel>>() {
				});
	
		  for (ManageCenterWebModel m : quotationNewModel) {
		  
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

		logger.info("Method :viewManageCenter ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/manage-center-delete")
	public @ResponseBody JsonResponse<Object> deleteCenter(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteCenter starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userid = (String) session.getAttribute("USER_ID");
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "deleteCenter?id="+id+"&userid="+userid, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteCenter ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("manage-center-edit-details-save")
	public @ResponseBody JsonResponse<Object> editCenterDetails(Model model, @RequestBody ManageCenterWebModel editCenterWebModel,
			HttpSession session) {
		logger.info("Method : editCenterDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = (String) session.getAttribute("USER_ID");
		System.out.println("########"+editCenterWebModel);
		try {

			resp = restTemplate.postForObject(env.getLabUrl() + "edit-center-details?userId="+userId, editCenterWebModel,
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
		
		System.out.println("@@@@@@@@@@"+resp);
		logger.info("Method : editCenterDetails ends");
		return resp;
	}
	
	
	
	/**************add more center manage *******************/

	@SuppressWarnings("unchecked")
	@GetMapping("manage-center-addmore")
	public @ResponseBody JsonResponse<List<AddCenterAddMoreWebModel>> viewManageAddMore(HttpSession session,Model model, @RequestParam String id) {
		logger.info("Method :viewManageAddMore starts");
		String userid = (String) session.getAttribute("USER_ID");

		System.out.println("########################" + userid);
		JsonResponse<List<AddCenterAddMoreWebModel>> resp = new JsonResponse<List<AddCenterAddMoreWebModel>>();
		
		List<DropDownModel> courseList = new ArrayList<DropDownModel>();	
		List<DropDownModel> getshiftList = new ArrayList<DropDownModel>();
		
		try {
			DropDownModel[] courseType = restTemplate.getForObject(env.getLabUrl() + "getCourseId",
					DropDownModel[].class);

			courseList = Arrays.asList(courseType);
			model.addAttribute("courseList", courseList);
			System.out.println("############"+courseList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] shiftList = restTemplate.getForObject(env.getLabUrl() + "getShiftlist",
					DropDownModel[].class);
			getshiftList = Arrays.asList(shiftList);

			model.addAttribute("getshiftList", getshiftList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
      try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-manage-center-addmore?id=" + id,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<AddCenterAddMoreWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<AddCenterAddMoreWebModel>>() {
				});
		for (AddCenterAddMoreWebModel m : quotationNewModel) {
			m.setCourseList(courseList);
			m.setGetshiftList(getshiftList);
		}
		
	      
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}
		System.out.println("RESPONSE" + resp);
		logger.info("Method :viewManageAddMore ends");
		
		return resp;
	}
	

	@SuppressWarnings("unchecked")
	@GetMapping("manage-center-get-course-dropdown-list")
	public @ResponseBody JsonResponse<List<DropDownModel>> getCourseDropDownList(Model model,
			HttpSession session) {
		logger.info("Method :getDropDownListByAJAX starts");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
        
		try {
			DropDownModel[] courseList = restTemplate.getForObject(env.getLabUrl() + "get-course-manage-list",
					DropDownModel[].class);
			List<DropDownModel> getCourseList = Arrays.asList(courseList);
			resp.setBody(getCourseList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getDropDownListByAJAX ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("manage-center-get-shift-dropdown-list")
	public @ResponseBody JsonResponse<List<DropDownModel>> getShiftDropDownList(Model model,
			HttpSession session) {
		logger.info("Method :getShiftDropDownList starts");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
        
		try {
			DropDownModel[] shiftMngList = restTemplate.getForObject(env.getLabUrl() + "get-shift-manage-list",
					DropDownModel[].class);
			List<DropDownModel> getShiftList = Arrays.asList(shiftMngList);
			resp.setBody(getShiftList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getShiftDropDownList ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "manage-center-course-list" })
	public @ResponseBody JsonResponse<Object> getmanageCenterCourseList(@RequestParam String name) {
		logger.info("Method : getmanageCenterCourseList starts");
         System.out.println("name222222"+name);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getLabUrl() + "get-manage-course-list?id="+name,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		System.out.println("Course list#################"+res);
		logger.info("Method : getmanageCenterCourseList ends");
		return res;

	}
	


}
