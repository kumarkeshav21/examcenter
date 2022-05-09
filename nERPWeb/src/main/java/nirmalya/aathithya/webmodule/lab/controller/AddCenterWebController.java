package nirmalya.aathithya.webmodule.lab.controller;

import java.math.BigInteger;
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
import nirmalya.aathithya.webmodule.lab.model.AddCenterWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageCenterWebModel;

@Controller
@RequestMapping(value = { "lab/" })

public class AddCenterWebController {
	Logger logger = LoggerFactory.getLogger(AddCenterWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("add-center")
	public String addcenter(Model model, HttpSession session) {

		logger.info("Method : addcenter starts");
		
		try {
			DropDownModel[] locList = restTemplate.getForObject(env.getLabUrl() + "get-loc-list",
					DropDownModel[].class);
			List<DropDownModel> getLocList = Arrays.asList(locList);

			model.addAttribute("locList", getLocList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		//for course in add more section
	
		  try { 
			  DropDownModel[] courseList = restTemplate.getForObject(env.getLabUrl() + "get-course-list",DropDownModel[].class);
			 
			//  DropDownModel[] courseList = restTemplate.getForObject(env.getLabUrl() + "get-examination-list",DropDownModel[].class);
		List<DropDownModel> getCourseList = Arrays.asList(courseList);
		  
		  model.addAttribute("courseList", getCourseList);
		  } catch (RestClientException e)
		  {
			  e.printStackTrace();
		}
		 
		try {
			DropDownModel[] shiftList = restTemplate.getForObject(env.getLabUrl() + "get-shift-list",
					DropDownModel[].class);
			List<DropDownModel> getShiftList = Arrays.asList(shiftList);

			model.addAttribute("shiftList", getShiftList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		//examlist drop down
		
		try {
			DropDownModel[] examlist = restTemplate.getForObject(env.getLabUrl() + "get-center-exam-list",
					DropDownModel[].class);
			List<DropDownModel> getExamList = Arrays.asList(examlist);

			model.addAttribute("examList", getExamList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	
		logger.info("Method : addcenter ends");
		return "lab/add-center";
	}
	
	/*
	 * Add Center details
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("add-center-details-save")
	public @ResponseBody JsonResponse<Object> addCenterDetails(Model model, @RequestBody AddCenterWebModel addCenterWebModel,
			HttpSession session) {
		logger.info("Method : addCenterDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		@SuppressWarnings("unused")
		String userId = (String) session.getAttribute("USER_ID");

		System.out.println("########"+userId);
		System.out.println("@@@@@@@@@@@@@@@"+addCenterWebModel);
		try {

			resp = restTemplate.postForObject(env.getLabUrl() + "rest-save-center-details?userId="+userId, addCenterWebModel,
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

		logger.info("Method : addCenterDetails starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("add-center-get-course-dropdown-list")
	public @ResponseBody JsonResponse<List<DropDownModel>> getDropDownListByAJAX(Model model,
			HttpSession session,@RequestParam String name) {
		logger.info("Method :getDropDownListByAJAX starts");
		System.out.println("@@@@@@@@"+name);
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
        
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "get-center-course-rest-list?id=" + name,
					JsonResponse.class);
			
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
	@GetMapping("add-center-get-shift-dropdown-list")
	public @ResponseBody JsonResponse<List<DropDownModel>> getShiftDropDownListByAJAX(Model model,
			HttpSession session) {
		logger.info("Method :getShiftDropDownListByAJAX starts");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
        
		try {
			DropDownModel[] shiftList = restTemplate.getForObject(env.getLabUrl() + "get-shift-list",
					DropDownModel[].class);
			List<DropDownModel> getShiftList = Arrays.asList(shiftList);
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

		logger.info("Method :getShiftDropDownListByAJAX ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "add-center-course-list" })
	public @ResponseBody JsonResponse<Object> getCenterCourseList(@RequestParam String name) {
		logger.info("Method : getCenterCourseList starts");
         System.out.println("name222222"+name);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getLabUrl() + "get-center-course-list?id=" + name,
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
		logger.info("Method : getCenterCourseList ends");
		return res;

	}
	

}
