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
import nirmalya.aathithya.webmodule.lab.model.CenterAllocatedWebModel;
import nirmalya.aathithya.webmodule.lab.model.CenterAllocationWebModel;

@Controller
@RequestMapping(value = { "lab/" })

public class CenterAllocatedWebController {
	Logger logger = LoggerFactory.getLogger(CenterAllocatedWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("center-allocated")
	public String centerAllocated(Model model, HttpSession session) {

		logger.info("Method : centerAllocated starts");
		
		try {
			DropDownModel[] courseList = restTemplate.getForObject(env.getLabUrl() + "get-examination-list",
					DropDownModel[].class);
			List<DropDownModel> getExamList = Arrays.asList(courseList);

			model.addAttribute("examList", getExamList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	
		
		
		try {
			CenterAllocatedWebModel[] candidateList = restTemplate.getForObject(env.getLabUrl() + "get-candidatereord",
					CenterAllocatedWebModel[].class);
			List<CenterAllocatedWebModel> getcandidateList = Arrays.asList(candidateList);

			model.addAttribute("getcandidateList", getcandidateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		logger.info("Method : centerAllocated ends");
		return "lab/center-allocated";
	}
	
	
	
	@SuppressWarnings("unchecked")

	@GetMapping("center-allocated-details")
	public @ResponseBody JsonResponse<List<CenterAllocatedWebModel>> centerAllocationWeb(Model model, HttpSession session,
			@RequestParam String exam, @RequestParam String roll,@RequestParam String ptype,@RequestParam String stat) {

		logger.info("Method : centerAllocationWeb starts");

		JsonResponse<List<CenterAllocatedWebModel>> resp = new JsonResponse<List<CenterAllocatedWebModel>>();
		//List<CenterAllocatedWebModel> returnList = new ArrayList<CenterAllocatedWebModel>();

		System.out.println("=============>>>" + exam);
		System.out.println("=============>>>" + roll);
		System.out.println("=============>>>" + ptype);
		System.out.println("=============>>>" + stat);
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-centerallocationdetails?exam="+exam+"&roll="+roll+"&ptype="+ptype+"&stat="+stat,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Success");
		} else {
			resp.setMessage("unuccess");
		}
		System.out.println("2323413" + resp);
		logger.info("Method : centerAllocationWeb ends");
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")

	@GetMapping("center-allocated-details-view")
	public @ResponseBody JsonResponse<List<CenterAllocatedWebModel>> alloCenterViewWeb(Model model, HttpSession session)
	{

		logger.info("Method : alloCenterViewWeb starts");

		JsonResponse<List<CenterAllocatedWebModel>> resp = new JsonResponse<List<CenterAllocatedWebModel>>();
		//List<CenterAllocatedWebModel> returnList = new ArrayList<CenterAllocatedWebModel>();

		
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-alloCenterViewRest",
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Success");
		} else {
			resp.setMessage("unuccess");
		}
		System.out.println("2323413" + resp);
		logger.info("Method : alloCenterViewWeb ends");
		return resp;
	}
	 
	 

}
