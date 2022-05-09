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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.AddInstructionModel;
import nirmalya.aathithya.webmodule.lab.model.RollSetUpWebModel;

@Controller
@RequestMapping(value = { "lab/" })

public class RollSetUpWebController {
	Logger logger = LoggerFactory.getLogger(RollSetUpWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("roll-setup")
	public String rollSetUps(Model model, HttpSession session) {

		logger.info("Method : rollSetUps starts");
		
		try {
			DropDownModel[] examlist = restTemplate.getForObject(env.getLabUrl() + "get-center-exam-list",
					DropDownModel[].class);
			List<DropDownModel> getExamList = Arrays.asList(examlist);

			model.addAttribute("examList", getExamList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		
	
		logger.info("Method : rollSetUps ends");
		return "lab/roll-setup";
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("roll-setup-details-save")
	public @ResponseBody JsonResponse<Object> addRollSetUpDetails(Model model, @RequestBody RollSetUpWebModel rollSetUpWebModel,
			HttpSession session) {
		logger.info("Method : addRollSetUpDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		@SuppressWarnings("unused")
		String userId = (String) session.getAttribute("USER_ID");

		System.out.println("########"+userId);
		try {

			resp = restTemplate.postForObject(env.getLabUrl() + "rest-rollsetup-details?userId="+userId, rollSetUpWebModel,
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
		
		logger.info("Method : addRollSetUpDetails starts");
		return resp;
	}


	

}
