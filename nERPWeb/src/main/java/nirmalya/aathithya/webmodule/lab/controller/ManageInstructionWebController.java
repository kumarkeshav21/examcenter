package nirmalya.aathithya.webmodule.lab.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
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
import nirmalya.aathithya.webmodule.lab.model.EditExaminationWebModel;
import nirmalya.aathithya.webmodule.lab.model.EditInstructionWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageCenterWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageExaminationWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageInstructionWebModel;
import nirmalya.aathithya.webmodule.sales.model.QuotationMasterModel;

@Controller
@RequestMapping(value = { "lab/" })
public class ManageInstructionWebController {

	Logger logger = LoggerFactory.getLogger(ManageInstructionWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	@GetMapping("manage-instruction")
	public String manageInstruction(Model model, HttpSession session) {

		logger.info("Method : manageInstruction starts");
		try {
			DropDownModel[] examslist = restTemplate.getForObject(env.getLabUrl() + "get-exams-list",
					DropDownModel[].class);
			List<DropDownModel> getExamList = Arrays.asList(examslist);

			model.addAttribute("examsList", getExamList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : manageInstruction ends");
		return "lab/manage-instruction";
	}

	// for center allocation report

	@SuppressWarnings("unchecked")
	@GetMapping("manage-instruction-table")
	public @ResponseBody JsonResponse<List<ManageInstructionWebModel>> viewManageInstruction(Model model,
			HttpSession session) {
		logger.info("Method :viewManageInstruction starts");
		String userid = (String) session.getAttribute("USER_ID");
		String userRole = (String)session.getAttribute("USER_ROLES_STRING");

		

		System.out.println("########################" + userid);
		System.out.println("########################" + userRole);
		
		JsonResponse<List<ManageInstructionWebModel>> resp = new JsonResponse<List<ManageInstructionWebModel>>();

		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-manage-instruction-table?userid=" + userid+"&userrole="+userRole,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ManageInstructionWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ManageInstructionWebModel>>() {
				});


		 for (ManageInstructionWebModel m : quotationNewModel) {
		 
		 if (m.getStatus().equals("1")) { m.setStatus("Active"); } else {
		  m.setStatus("InActive"); }
		 
		 }
		 
		for (ManageInstructionWebModel m : quotationNewModel) {

			if (m.getExams() == null) {
				m.setExams("Common");
			}
		}

		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewManageInstruction ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "manage-instruction-edit-details-save", method = { RequestMethod.POST })
	public @ResponseBody JsonResponse<Object> editInstructionDetails(
			@RequestBody EditInstructionWebModel editInstructionWebModel, Model model, HttpSession session) {
		logger.info("Method : editInstructionDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			res = restTemplate.postForObject(env.getLabUrl() + "edit-instruction-details?userId=" + userId,
					editInstructionWebModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : editInstructionDetails function Ends");
		return res;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/manage-instruction-delete")
	public @ResponseBody JsonResponse<Object> deleteInstruction(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteInstruction starts");
		System.out.println("##################" + id);

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = (String) session.getAttribute("USER_ID");
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "deleteInstruction?id=" + id+"&userId="+userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteInstruction ends");
		return resp;
	}

}
