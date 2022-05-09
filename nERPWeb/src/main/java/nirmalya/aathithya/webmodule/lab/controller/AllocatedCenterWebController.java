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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.AllocatedCenterWebModel;

@Controller
@RequestMapping(value = { "lab/" })

public class AllocatedCenterWebController {
	Logger logger = LoggerFactory.getLogger(AllocatedCenterWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("allocated-center")
	public String centerAllocated(Model model, HttpSession session) {

		logger.info("Method : centerAllocated starts");
		try {
			DropDownModel[] locList = restTemplate.getForObject(env.getLabUrl() + "get-all-loc-list",
					DropDownModel[].class);
			List<DropDownModel> getLocList = Arrays.asList(locList);

			model.addAttribute("locList", getLocList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		
		
	
		logger.info("Method : centerAllocated ends");
		return "lab/allocated-center";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("allocated-center-view")
	public @ResponseBody List<AllocatedCenterWebModel> getAllocatedCenter(HttpSession session,@RequestParam String loc,@RequestParam String cent) {

		logger.info("Method : getAllocatedCenter starts");
		System.out.println("loc=====>>>>>>>"+loc);
		System.out.println("center=====>>>>>>>"+loc);
		
		JsonResponse<List<AllocatedCenterWebModel>> resp = new JsonResponse<List<AllocatedCenterWebModel>>();
		List<AllocatedCenterWebModel> returnList = new ArrayList<AllocatedCenterWebModel>();
	
		
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "get-allocated-center?loc="+loc+"&cent=" + cent, JsonResponse.class);
			returnList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(returnList);
		logger.info("Method : getAllocatedCenter ends");
		return returnList;
	}

}
