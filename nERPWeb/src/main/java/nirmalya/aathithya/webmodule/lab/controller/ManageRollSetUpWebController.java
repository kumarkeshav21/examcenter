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
import nirmalya.aathithya.webmodule.lab.model.EditInstructionWebModel;
import nirmalya.aathithya.webmodule.lab.model.RollSetUpWebModel;

@Controller
@RequestMapping(value = { "lab/" })

public class ManageRollSetUpWebController {
	Logger logger = LoggerFactory.getLogger(ManageRollSetUpWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("manage-roll-setup")
	public String manageRollSetup(Model model, HttpSession session) {

		logger.info("Method : manageRollSetup starts");
		try {
			DropDownModel[] examslist = restTemplate.getForObject(env.getLabUrl() + "get-exams-list",
					DropDownModel[].class);
			List<DropDownModel> getExamList = Arrays.asList(examslist);

			model.addAttribute("examsList", getExamList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : manageRollSetup ends");
		return "lab/manage-roll-setup";
	}
	
	
	//for manage location 
	@SuppressWarnings("unchecked")
	
	@GetMapping("manage-roll-setup-table")
	public @ResponseBody JsonResponse<List<RollSetUpWebModel>> viewRollSetManage(Model model,HttpSession session){
		logger.info("Method :viewRollSetManage starts");
		String userid = (String)session.getAttribute("USER_ID");
		
		System.out.println("########################"+userid);
		JsonResponse<List<RollSetUpWebModel>> resp = new JsonResponse<List<RollSetUpWebModel>>();

		try {
			resp = 
			restTemplate.getForObject(env.getLabUrl() + "rest-manage-roll-setup?userid="+userid, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<RollSetUpWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<RollSetUpWebModel>>() {
				});
		for(RollSetUpWebModel m:quotationNewModel) {
			

		}
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}
		
		

		logger.info("Method :viewRollSetManage ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "manage-roll-setup-edit-details-save", method = { RequestMethod.POST })
	public @ResponseBody JsonResponse<Object> editRollsetUpSave(
			@RequestBody RollSetUpWebModel rollSetUpWebModel, Model model, HttpSession session) {
		logger.info("Method : editRollsetUpSave function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		System.out.println("=====>>>"+rollSetUpWebModel);
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			res = restTemplate.postForObject(env.getLabUrl() + "edit-rollsetup-rest?userId=" + userId,
					rollSetUpWebModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : editRollsetUpSave function Ends");
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/manage-roll-setup-delete")
	public @ResponseBody JsonResponse<Object> deleteRollSetup(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteRollSetup starts");
		System.out.println("##################" + id);

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = (String) session.getAttribute("USER_ID");
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "deleteRollsetup?id=" + id+"&userId="+userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteRollSetup ends");
		return resp;
	}


	


}
