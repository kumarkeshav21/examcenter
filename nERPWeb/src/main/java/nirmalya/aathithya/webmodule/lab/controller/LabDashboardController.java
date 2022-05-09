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
import nirmalya.aathithya.webmodule.lab.model.LabDashboardModel;
import nirmalya.aathithya.webmodule.lab.model.LabDashboardPatientModel;
import nirmalya.aathithya.webmodule.lab.model.LabDashboardWebModel;
import nirmalya.aathithya.webmodule.lab.model.LabPatientPrescriptionModel;
import nirmalya.aathithya.webmodule.lab.model.ManageCenterWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class LabDashboardController {
	Logger logger = LoggerFactory.getLogger(LabDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	/*
	 * Get Mapping for view-doctor-insurance
	 */

	@GetMapping("lab-dashboard")
	public String labDashboard(Model model, HttpSession session) {

		logger.info("Method : labDashboard starts");

	
		
		
		String userId = (String) session.getAttribute("USER_ID");
		
		try {
			//System.out.println(env.getChemistUrl());
			LabDashboardWebModel[] data = restTemplate.getForObject(
					env.getLabUrl() + "labdashboard-card?userId=" + userId, LabDashboardWebModel[].class);
			System.out.println("@@@@@@@@@@@@@@@pppppppppp"+data);
			//System.out.println("@@@@@@@@@@@@@@@"+env.getChemistUrl());
			List<LabDashboardWebModel> dataList = Arrays.asList(data);
			System.out.println("@@@@@@@@@@@@@@@pppppppllll"+dataList);
			if(dataList.size()> 0)
			{
				model.addAttribute("angul",dataList.get(0).getAngul());
				model.addAttribute("balangir",dataList.get(0).getBalangir());
				model.addAttribute("balasore",dataList.get(0).getBalasor());
				model.addAttribute("bargarh",dataList.get(0).getBargarh());
				model.addAttribute("baripada",dataList.get(0).getBaripada());
				model.addAttribute("berhampur",dataList.get(0).getBerhampur());
				model.addAttribute("bhadrak",dataList.get(0).getBhadrak());
				model.addAttribute("bhubaneshwar",dataList.get(0).getBhubaneswar());
				model.addAttribute("cuttack",dataList.get(0).getCuttack());
				model.addAttribute("dhenkanal",dataList.get(0).getDhenkanal());
				model.addAttribute("jagatsinghpur",dataList.get(0).getJagatsinghpur());
				model.addAttribute("jajpur",dataList.get(0).getJajpur());
				model.addAttribute("jeypore",dataList.get(0).getJeypore());
				model.addAttribute("jharsuguda",dataList.get(0).getJharsuguda());
				model.addAttribute("keonjhar",dataList.get(0).getKeonjhar());
				model.addAttribute("kolkata",dataList.get(0).getKolkata());
				model.addAttribute("nayagarh",dataList.get(0).getNayagarh());
				model.addAttribute("paralakhemundi",dataList.get(0).getParalakhemundi());
				model.addAttribute("patna",dataList.get(0).getPatna());
				model.addAttribute("phulbani",dataList.get(0).getPhulbani());
				model.addAttribute("ranchi",dataList.get(0).getRanchi());
				model.addAttribute("rayagarh",dataList.get(0).getRayagada());
				model.addAttribute("rourkela",dataList.get(0).getRourkela());
				model.addAttribute("sambalpur",dataList.get(0).getSambalpur());
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : labDashboard ends");
		return "lab/lab-dashboard";
	}
	
	
	
	

/*	@SuppressWarnings("unchecked")
	@GetMapping("dashboard-center-location")
	public @ResponseBody JsonResponse<List<LabDashboardWebModel>> viewPiechartReport(Model model,HttpSession session) {
		logger.info("Method :viewPiechartReport starts");
		String userid = (String) session.getAttribute("USER_ID");
		//String userRoles = (String)session.getAttribute("USER_ROLES_STRING");

		System.out.println("########################>>>>>" + userid);
		//System.out.println("########################>>>>>" + userRoles);
		JsonResponse<List<LabDashboardWebModel>> resp = new JsonResponse<List<LabDashboardWebModel>>();
      try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-viewPiechartReport?userid=" + userid,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabDashboardWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LabDashboardWebModel>>() {
				});
	
		 
		 
		
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewPiechartReport ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}*/
	
	/***************************************/
	
	@SuppressWarnings("unchecked")
	@GetMapping("lab-dashboard-center-location")
	public @ResponseBody JsonResponse<List<DropDownModel>> viewPiechartReport(Model model,HttpSession session) {
		logger.info("Method :viewPiechartReport starts");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
        
		try {
			DropDownModel[] courseList = restTemplate.getForObject(env.getLabUrl() + "rest-viewPiechartReport",
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

		logger.info("Method :viewPiechartReport ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}
    
/***************************************/
	
	@SuppressWarnings("unchecked")
	@GetMapping("lab-dashboard-center-barchart")
	public @ResponseBody JsonResponse<List<DropDownModel>> viewBarchartReport(Model model,HttpSession session) {
		logger.info("Method :viewBarchartReport starts");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
        
		try {
			DropDownModel[] courseList = restTemplate.getForObject(env.getLabUrl() + "rest-viewBarchartReport",
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

		logger.info("Method :viewBarchartReport ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}

	/*
	 * View Test Report Done
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "lab-dashboard-test-report" })
	public @ResponseBody JsonResponse<Object> viewTestReport(Model model, HttpSession session) {
		logger.info("Method : viewTestReport starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId2 = (String) session.getAttribute("USER_ID");

		try {
			res = restTemplate.getForObject(env.getLabUrl() + "rest-viewTestReport?id=" + userId2, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : viewTestReport ends");
		return res;
	}

	/*
	 * View Health Check Up
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "lab-dashboard-health-checkup" })
	public @ResponseBody JsonResponse<Object> viewTHealthCheckup(Model model, HttpSession session) {
		logger.info("Method : viewTHealthCheckup starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId2 = (String) session.getAttribute("USER_ID");

		try {
			res = restTemplate.getForObject(env.getLabUrl() + "rest-viewTHealthCheckup?id=" + userId2,
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

		logger.info("Method : viewTHealthCheckup ends");
		return res;
	}



	@SuppressWarnings("unchecked")

	@GetMapping("lab-dashboard-ag-grid")
	public @ResponseBody JsonResponse<List<LabDashboardPatientModel>> getLabViewDashboard(@RequestParam String date,
			HttpSession session) {

		logger.info("Method :getLabViewDashboard starts");

		String userid = (String) session.getAttribute("USER_ID");

		JsonResponse<List<LabDashboardPatientModel>> resp = new JsonResponse<List<LabDashboardPatientModel>>();

		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-getLabView?&userid=" + userid + "&date=" + date,
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getLabViewDashboard ends");

		return resp;
	}

	/****************************************
	 * for ag grid table
	 *****************************/
	@SuppressWarnings("unchecked")
	@GetMapping("lab-dashboard-modal")
	public @ResponseBody List<LabPatientPrescriptionModel> viewlabPatientDesc(@RequestParam String patientid,
			@RequestParam String orderid, HttpSession session) {
		logger.info("Method :viewlabPatientDesc starts");
		String userid = (String) session.getAttribute("USER_ID");

		JsonResponse<List<LabPatientPrescriptionModel>> resp = new JsonResponse<List<LabPatientPrescriptionModel>>();

		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "getLabPatientprecription?userid=" + userid
					+ "&patientid=" + patientid + "&orderid=" + orderid, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabPatientPrescriptionModel> quotationModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LabPatientPrescriptionModel>>() {
				});

		for (LabPatientPrescriptionModel m : quotationModel) {
		}
		resp.setBody(quotationModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewlabPatientDesc ends");
		// System.out.println("RESPONSE" + resp);
		return resp.getBody();
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@GetMapping(value = "/lab-dashboard-save-lab-status")
	public @ResponseBody JsonResponse<Object> saveLabStatus(@RequestParam String orderid, Model model,
			HttpSession session, @RequestParam String status) {
		logger.info("Method : saveLabStatus  methods Starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println(orderid);

		try {
			resp = restTemplate.getForObject(
					env.getLabUrl() + "rest-saveLabStatus?orderid=" + orderid + "&status=" + status,
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

		logger.info("Method : saveLabStatus methods ends");
		return resp;
	}

}
