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

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.AddInstructionModel;
import nirmalya.aathithya.webmodule.lab.model.CenterAllocationReportWebModel;
import nirmalya.aathithya.webmodule.lab.model.CenterAllocationWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class CenterAllocationWebController {
	Logger logger = LoggerFactory.getLogger(CenterAllocationWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("center-allocation")
	public String centerAllocation(Model model, HttpSession session) {

		logger.info("Method : centerAllocation starts");
		
		//for exam dropdown 
		try {
			DropDownModel[] courseList = restTemplate.getForObject(env.getLabUrl() + "get-examination-list",
					DropDownModel[].class);
			List<DropDownModel> getExamList = Arrays.asList(courseList);

			model.addAttribute("examList", getExamList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	
		
		  try 
		  { 
			  CenterAllocationWebModel[] studentList = restTemplate.getForObject(env.getLabUrl() + "get-student-list", CenterAllocationWebModel[].class);
			  List<CenterAllocationWebModel> getStudentList = Arrays.asList(studentList);
		  
		     model.addAttribute("getStudentList", getStudentList); 
		  }
		  catch (RestClientException e)
		  {
			  e.printStackTrace(); }
		 
		
		try {
			CenterAllocationWebModel[] candidateList = restTemplate.getForObject(env.getLabUrl() + "get-candidate-list",
					CenterAllocationWebModel[].class);
			List<CenterAllocationWebModel> getcandidateList = Arrays.asList(candidateList);

			model.addAttribute("getcandidateList", getcandidateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		
		logger.info("Method : centerAllocation ends");
		return "lab/center-allocation";
	}
	
	/*
	 * center allocation
	 */
	/*
	  @SuppressWarnings("unchecked")
	  
	  @PostMapping("center-allocation-details")
	  public @ResponseBody JsonResponse<Object> centerAllocationWeb(Model model, @RequestBody CenterAllocationWebModel centerAllocationWebModel, HttpSession session) {
	  logger.info("Method : centerAllocationWeb starts");
	  JsonResponse<Object> resp = new JsonResponse<Object>();
	  
	  System.out.println("@@@@@@@@@@@@@@@@"+centerAllocationWebModel); 
	  try {
	  
	  resp = restTemplate.postForObject(env.getLabUrl() +
	  "rest-center-allocation-details", centerAllocationWebModel,
	  JsonResponse.class); } catch (RestClientException e) { e.printStackTrace(); }
	  
	  if (resp.getMessage() != "" && resp.getMessage() != null) {
	  resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
	  resp.setMessage("Success"); }
	  
	  logger.info("Method : centerAllocationWeb starts");
	  return resp; 
	  }
	*/
	
	
	
	
	/*
	 * dropdown for course through ajax
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "center-allocation-get-course" })
	public @ResponseBody JsonResponse<Object> getCourseList(@RequestParam String name) {
		logger.info("Method : getCourseList starts");
         System.out.println("name222222"+name);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getLabUrl() + "getCourseList?id=" + name,
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

		logger.info("Method : getCourseList ends");
		return res;

	}
	
@SuppressWarnings("unchecked")
	
	@GetMapping(value = { "center-allocation-roll-code" })
	public @ResponseBody JsonResponse<List<CenterAllocationWebModel>> getRollCode(Model model,@RequestParam String name) {
		logger.info("Method :getRollCode starts");
		System.out.println("@#@#@###@@@#"+name);
		JsonResponse<List<CenterAllocationWebModel>> resp = new JsonResponse<List<CenterAllocationWebModel>>();
      try {
			resp = restTemplate.getForObject(env.getLabUrl() + "get-allocated-roll-code?id=" + name,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
      
      if (resp.getMessage() != null) {
    	  resp.setCode(resp.getMessage());
    	  resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		
        logger.info("Method :getRollCode ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	
	

}
