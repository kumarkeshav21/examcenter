package nirmalya.aathithya.webmodule.lab.controller;

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

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.EditInstructionWebModel;
import nirmalya.aathithya.webmodule.lab.model.EditUserDetailWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageUserWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class ManageUserWebController {
	Logger logger = LoggerFactory.getLogger(ManageUserWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("manage-user")
	public String addcenter(Model model, HttpSession session) {

		logger.info("Method : labDashboard starts");
	
		logger.info("Method : labDashboard ends");
		return "lab/manage-user";
	}
	
	//for manage location 
	@SuppressWarnings("unchecked")
	
	@GetMapping("manage-user-table")
	public @ResponseBody JsonResponse<List<ManageUserWebModel>> viewManageUser(Model model,HttpSession session){
		logger.info("Method :viewManageUser starts");
		String userid = (String)session.getAttribute("USER_ID");
		
		System.out.println("########################"+userid);
		JsonResponse<List<ManageUserWebModel>> resp = new JsonResponse<List<ManageUserWebModel>>();

		try {
			resp = 
			restTemplate.getForObject(env.getLabUrl() + "manage-user-details?userid="+userid, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ManageUserWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ManageUserWebModel>>() {
				});
		
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}
		
		

		logger.info("Method :viewManageUser ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "manage-user-edit-details-save", method = { RequestMethod.POST })
	public @ResponseBody JsonResponse<Object> editUsersDetails(
			@RequestBody EditUserDetailWebModel editUserDetailWebModel, Model model, HttpSession session) {
		logger.info("Method : editUsersDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			res = restTemplate.postForObject(env.getLabUrl() + "edit-user-details?userId=" + userId,
					editUserDetailWebModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : editUsersDetails function Ends");
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/manage-user-delete")
	public @ResponseBody JsonResponse<Object> deleteUser(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteUser starts");
		System.out.println("##################" + id);

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = (String) session.getAttribute("USER_ID");
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "delete-user?id=" + id+"&userId="+userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteUser ends");
		return resp;
	}


}
