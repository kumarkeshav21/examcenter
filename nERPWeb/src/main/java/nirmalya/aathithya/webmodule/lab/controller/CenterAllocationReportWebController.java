package nirmalya.aathithya.webmodule.lab.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;
import nirmalya.aathithya.webmodule.lab.model.AdmitCardWebModel;
import nirmalya.aathithya.webmodule.lab.model.CandidateListWebModel;
import nirmalya.aathithya.webmodule.lab.model.CenterAllocatedWebModel;
import nirmalya.aathithya.webmodule.lab.model.CenterAllocationReportWebModel;
import nirmalya.aathithya.webmodule.lab.model.CenterAllocationWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageCenterWebModel;
import nirmalya.aathithya.webmodule.sales.model.SalesCustomerModel;

@Controller
@RequestMapping(value = { "lab/" })
public class CenterAllocationReportWebController {
	Logger logger = LoggerFactory.getLogger(CenterAllocationReportWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	@GetMapping("center-allocation-report")
	public String centerAllocationReort(Model model, HttpSession session) {

		logger.info("Method : centerAllocationReort starts");

		try {
			DropDownModel[] locList = restTemplate.getForObject(env.getLabUrl() + "get-all-loc-list",
					DropDownModel[].class);
			List<DropDownModel> getLocList = Arrays.asList(locList);

			model.addAttribute("locList", getLocList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : centerAllocationReort ends");
		return "lab/center-allocation-report";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("center-allocation-report-view")
	public @ResponseBody List<CenterAllocationWebModel> getPatientHistoryMedicalCondition(HttpSession session,
			@RequestParam String loc, @RequestParam String cent, @RequestParam String cour,
			@RequestParam Integer pageno) {

		logger.info("Method : viewAssignedData starts");

		JsonResponse<List<CenterAllocationWebModel>> resp = new JsonResponse<List<CenterAllocationWebModel>>();
		List<CenterAllocationWebModel> returnList = new ArrayList<CenterAllocationWebModel>();

		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "getStudentReport?loc=" + loc + "&cent=" + cent
					+ "&cour=" + cour + "&pageno=" + pageno, JsonResponse.class);
			returnList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(returnList);
		logger.info("Method : viewAssignedData ends");
		return returnList;
	}

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "center-allocation-report-get-center" })
	public @ResponseBody JsonResponse<Object> getCenterList(@RequestParam String name) {
		logger.info("Method : getCenterList starts");
		System.out.println("name222222" + name);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getLabUrl() + "get-Center-all-List?id=" + name, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : getCenterList ends");
		return res;

	}

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "center-allocation-report-get-course" })
	public @ResponseBody JsonResponse<Object> getCourseRepoList(@RequestParam String name) {
		logger.info("Method : getCourseRepoList starts");
		System.out.println("name222222" + name);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getLabUrl() + "get-Course-all-List?id=" + name, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		System.out.println("Course list#################" + res);
		logger.info("Method : getCourseRepoList ends");
		return res;

	}

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "center-allocation-report-applied-student" })
	public @ResponseBody JsonResponse<List<CenterAllocationReportWebModel>> getAppliedCandiList(Model model,
			@RequestParam String name) {
		logger.info("Method :getAppliedCandiList starts");
		System.out.println("@#@#@###@@@#" + name);
		JsonResponse<List<CenterAllocationReportWebModel>> resp = new JsonResponse<List<CenterAllocationReportWebModel>>();
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "get-applied-all-candi-List?id=" + name,
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

		logger.info("Method :getAppliedCandiList ends");
		System.out.println("RESPONSE" + resp);
		return resp;
	}

	/*
	 * @GetMapping("center-allocation-report-admitCard") public String
	 * admitCard(Model model, HttpSession session) {
	 * 
	 * logger.info("Method : admitCard starts");
	 * 
	 * 
	 * 
	 * logger.info("Method : admitCard ends"); return "lab/admit-card"; }
	 */

	/***************************************************/

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping(value = { "center-allocation-report-admitCard" })
	 * public @ResponseBody JsonResponse<List<AdmitCardWebModel>> getAdmitCard(Model
	 * model,@RequestParam String roll,HttpSession session) {
	 * logger.info("Method :getAdmitCard starts");
	 * System.out.println("@#@#@###@@@#"+roll);
	 * JsonResponse<List<AdmitCardWebModel>> resp = new
	 * JsonResponse<List<AdmitCardWebModel>>(); try { resp =
	 * restTemplate.getForObject(env.getLabUrl() + "get-admit-card-rest?roll=" +
	 * roll, JsonResponse.class); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * if (resp.getMessage() != null) { resp.setCode(resp.getMessage());
	 * resp.setMessage("Unsuccess"); } else { resp.setMessage("Success"); }
	 * 
	 * logger.info("Method :getAdmitCard ends"); System.out.println("RESPONSE" +
	 * resp); return resp; }
	 */
	/*
	 * @Autowired PdfGeneratatorUtil pdfGeneratorUtil;
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping(value = { "/pdf" }) public void
	 * generateOfferletter(HttpServletResponse response, Model model, HttpSession
	 * session,
	 * 
	 * @RequestParam("roll") String roll) {
	 * logger.info("Method : generateOfferletter starts");
	 * System.out.println("roll==>"+roll);
	 * 
	 * JsonResponse<List<AdmitCardWebModel>> jsonResponse = new
	 * JsonResponse<List<AdmitCardWebModel>>(); try { jsonResponse =
	 * restTemplate.getForObject(env.getLabUrl() + "get-admit-card-rest?roll=" +
	 * roll, JsonResponse.class);
	 * 
	 * } catch (
	 * 
	 * RestClientException e) { e.printStackTrace(); } ObjectMapper mapper = new
	 * ObjectMapper(); System.out.println(jsonResponse); List<AdmitCardWebModel>
	 * offerLetter = mapper.convertValue(jsonResponse.getBody(), new
	 * TypeReference<List<AdmitCardWebModel>>() { });
	 * System.out.println("offerLetter==>>"+offerLetter); Map<String, Object> data =
	 * new HashMap<String, Object>();
	 * 
	 * data.put("offerLetter", offerLetter); System.out.println("data==>>"+data);
	 * 
	 * response.setContentType("application/pdf");
	 * response.setHeader("Content-disposition", "inline; filename=AdmitCard.pdf");
	 * File file; byte[] fileData = null; try { file =
	 * pdfGeneratorUtil.createPdf("lab/admit-card", data); InputStream in = new
	 * FileInputStream(file); fileData = IOUtils.toByteArray(in);
	 * response.setContentLength(fileData.length);
	 * response.getOutputStream().write(fileData);
	 * response.getOutputStream().flush();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } catch (Exception e) {
	 * e.printStackTrace(); } logger.info("Method : generateOfferletter ends");
	 * 
	 * }
	 */

	
	@SuppressWarnings("unchecked")
	//@GetMapping(value = { "/pdf" })
	@GetMapping("/pdf")
	public String centerAllocationReortPdf(Model model, HttpSession session,@RequestParam("roll") String roll) {

		logger.info("Method : centerAllocationReortPdf starts");
		System.out.println("===>"+roll);
		
		@SuppressWarnings("unused")
		JsonResponse<List<AdmitCardWebModel>> resp = new JsonResponse<List<AdmitCardWebModel>>();
		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "get-admit-card-rest?roll=" + roll,
					JsonResponse.class);
			
			
			List<JsonResponse<List<AdmitCardWebModel>>> getFullDetails = Arrays.asList(resp);

			model.addAttribute("getFullDetails", getFullDetails.get(0).getBody());
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
			System.out.println("#####"+resp);
		logger.info("Method : centerAllocationReortPdf ends");
		return "lab/admit-card";
	}
	 
}
