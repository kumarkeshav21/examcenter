package nirmalya.aathithya.webmodule.master.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.HrMasterModel;
import nirmalya.aathithya.webmodule.master.model.ProcurementMasterModel;

@Controller
@RequestMapping(value = "master")
public class ProcurementMasterController {

	Logger logger = LoggerFactory.getLogger(ProcurementMasterController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("/view-procurement")
	public String displayPage(Model model, HttpSession session) {
		logger.info("Method : procurement starts");

		logger.info("Method : procurement ends");
		return "master/view-procurement";
	}

	/*
	 * ///////////// Brand Type//////////////
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping("/view-procurement-add-brand-type") public @ResponseBody
	 * JsonResponse<Object> addBrandType(@RequestBody ProcurementMasterModel brand,
	 * HttpSession session) { logger.info("Method : addBrandType starts");
	 * 
	 * JsonResponse<Object> resp = new JsonResponse<Object>();
	 * 
	 * String userId = "";
	 * 
	 * try { userId = (String) session.getAttribute("USER_ID"); } catch (Exception
	 * e) { e.printStackTrace(); }
	 * 
	 * brand.setBrandCreatedBy(userId);
	 * 
	 * try { resp = restTemplate.postForObject(env.getMasterUrl() + "addBrandType",
	 * brand, JsonResponse.class); } catch (RestClientException e) {
	 * e.printStackTrace(); }
	 * 
	 * String message = resp.getMessage();
	 * 
	 * if (message != null && message != "") {
	 * 
	 * } else {
	 * 
	 * resp.setMessage("Success"); }
	 * 
	 * logger.info("Method : addBrandType starts"); System.out.println(resp);
	 * 
	 * return resp; }
	 * 
	 * // View page for Hr job types
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("/view-procurement-view-brand-type") public @ResponseBody
	 * List<ProcurementMasterModel> viewBrandType(HttpSession session) {
	 * logger.info("Method : viewBrandType starts");
	 * 
	 * JsonResponse<List<ProcurementMasterModel>> resp = new
	 * JsonResponse<List<ProcurementMasterModel>>(); List<ProcurementMasterModel>
	 * brandList = new ArrayList<ProcurementMasterModel>();
	 * 
	 * try { resp = restTemplate.getForObject(env.getMasterUrl() + "viewBrandType",
	 * JsonResponse.class); brandList = resp.getBody(); } catch (RestClientException
	 * e) { e.printStackTrace(); } System.out.println(brandList);
	 * logger.info("Method : viewBrandType ends"); return brandList; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("/view-procurement-brand-type-delete") public @ResponseBody
	 * JsonResponse<Object> deletebrandType(HttpSession session, @RequestParam
	 * String id) { logger.info("Method : deletebrandType starts");
	 * 
	 * JsonResponse<Object> resp = new JsonResponse<Object>();
	 * 
	 * try { resp = restTemplate.getForObject(env.getMasterUrl() +
	 * "deletebrandType?id=" + id, JsonResponse.class); } catch (RestClientException
	 * e) { e.printStackTrace(); }
	 * 
	 * String message = resp.getMessage();
	 * 
	 * if (message != null && message != "") {
	 * 
	 * } else {
	 * 
	 * resp.setMessage("Success"); }
	 * 
	 * logger.info("Method : deletebrandType ends"); return resp; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("view-procurement-Brand-report-excel") public ModelAndView
	 * downloadBrandExcelReport(HttpServletResponse servResponse) {
	 * logger.info("Method : downloadBrandExcelReport start");
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); JsonResponse<Object>
	 * jsonResponse = new JsonResponse<Object>();
	 * 
	 * try { jsonResponse = restTemplate.getForObject(env.getMasterUrl() +
	 * "getBrandExcelData", JsonResponse.class); ObjectMapper mapper = new
	 * ObjectMapper(); List<ProcurementMasterModel> location =
	 * mapper.convertValue(jsonResponse.getBody(), new
	 * TypeReference<List<ProcurementMasterModel>>() { });
	 * 
	 * System.out.println(location); map.put("BrandExcelReport", location);
	 * servResponse.setContentType("application/ms-excel");
	 * servResponse.setHeader("Content-disposition",
	 * "attachment; filename=BrandExcelReport.xls");
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * logger.error("HrMasterController -> downloadExcelReport GET", e); }
	 * logger.info("Method : downloadBrandExcelReport ends"); return new
	 * ModelAndView(new ExcelBrandReport(), map); }
	 */
	//////// Measurement///////////////////////

	@SuppressWarnings("unchecked")
	@PostMapping("/view-procurement-add-measurement-type")
	public @ResponseBody JsonResponse<Object> addMeasureType(@RequestBody ProcurementMasterModel brand,
			HttpSession session) {
		logger.info("Method : addMeasureType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		brand.setMeasurementCreatedBy(userId);

		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addMeasureType", brand, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : addMeasureType starts");
		System.out.println(resp);

		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-measurement-type")
	public @ResponseBody List<ProcurementMasterModel> viewMeasureType(HttpSession session) {
		logger.info("Method : viewMeasureType starts");

		JsonResponse<List<ProcurementMasterModel>> resp = new JsonResponse<List<ProcurementMasterModel>>();
		List<ProcurementMasterModel> MeasureList = new ArrayList<ProcurementMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewMeasureType", JsonResponse.class);
			MeasureList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(MeasureList);
		logger.info("Method : viewMeasureType ends");
		return MeasureList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-measurement-type-delete")
	public @ResponseBody JsonResponse<Object> deleteMeasureType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteMeasureType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteMeasureType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteMeasureType ends");
		return resp;
	}

////////ReqisitionType///////////////////////

	@SuppressWarnings("unchecked")
	@PostMapping("/view-procurement-add-reqi-type")
	public @ResponseBody JsonResponse<Object> addReqiType(@RequestBody ProcurementMasterModel requisition,
			HttpSession session) {
		logger.info("Method : addReqiType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		requisition.setRequisitionCreatedBy(userId);

		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addReqiType", requisition, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : addReqiType starts");
		System.out.println(resp);

		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-reqi-type")
	public @ResponseBody List<ProcurementMasterModel> viewRequisitionType(HttpSession session) {
		logger.info("Method : viewRequisitionType starts");

		JsonResponse<List<ProcurementMasterModel>> resp = new JsonResponse<List<ProcurementMasterModel>>();
		List<ProcurementMasterModel> RequisitonList = new ArrayList<ProcurementMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewRequisitionType", JsonResponse.class);
			RequisitonList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(RequisitonList);
		logger.info("Method : viewRequisitionType ends");
		return RequisitonList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-reqi-type-delete")
	public @ResponseBody JsonResponse<Object> deleteRequiType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteRequiType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteRequiType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteRequiType ends");
		return resp;
	}

////////ReqisitionPriorityType///////////////////////

	@SuppressWarnings("unchecked")
	@PostMapping("/view-procurement-add-reqi-priority-type")
	public @ResponseBody JsonResponse<Object> addReqiPriorityType(@RequestBody ProcurementMasterModel reqiprio,
			HttpSession session) {
		logger.info("Method : addReqiPriorityType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		reqiprio.setRequiPriorityCreatedBy(userId);

		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addReqiPriorityType", reqiprio, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : addReqiPriorityType starts");
		System.out.println(resp);

		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-reqi-priority-type")
	public @ResponseBody List<ProcurementMasterModel> viewReqiPriorityType(HttpSession session) {
		logger.info("Method : viewReqiPriorityType starts");

		JsonResponse<List<ProcurementMasterModel>> resp = new JsonResponse<List<ProcurementMasterModel>>();
		List<ProcurementMasterModel> RequisitonPriorityList = new ArrayList<ProcurementMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewReqiPriorityType", JsonResponse.class);
			RequisitonPriorityList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(RequisitonPriorityList);
		logger.info("Method : viewReqiPriorityType ends");
		return RequisitonPriorityList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-reqi-priority-type-delete")
	public @ResponseBody JsonResponse<Object> deleteRequiPriorityType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteRequiPriorityType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteRequiPriorityType?id=" + id,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteRequiPriorityType ends");
		return resp;
	}

////////Payment Term Type///////////////////////

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/view-procurement-add-payment-term-type")
	public @ResponseBody JsonResponse<Object> addPaymentTerm(@RequestBody ProcurementMasterModel procure, Model model,
			HttpSession session) {
		logger.info("Method : add PayTerm starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		procure.setPaymentTermCreatedBy(userId);

		try {
			procure.setPaymentTermUpdatedBy(userId);

			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addPayTerm", procure, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : add PayTerm ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-payment-term-type")
	public @ResponseBody List<ProcurementMasterModel> viewPaymentTerm(HttpSession session) {
		logger.info("Method : viewPaymentTerm starts");

		JsonResponse<List<ProcurementMasterModel>> resp = new JsonResponse<List<ProcurementMasterModel>>();
		List<ProcurementMasterModel> PaymentTermList = new ArrayList<ProcurementMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewPaymentTerm", JsonResponse.class);
			PaymentTermList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(PaymentTermList);
		logger.info("Method : viewPaymentTerm ends");
		return PaymentTermList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-payment-term-type-delete")
	public @ResponseBody JsonResponse<Object> deletePaymentTerm(HttpSession session, @RequestParam String id) {
		logger.info("Method : deletePaymentTerm starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deletePaymentTerm?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deletePaymentTerm ends");
		return resp;
	}

////////Legal Term Type///////////////////////

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/view-procurement-add-legal-term-type")
	public @ResponseBody JsonResponse<Object> addLegalTerm(@RequestBody ProcurementMasterModel procure, Model model,
			HttpSession session) {
		logger.info("Method : add LegalTerm starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		procure.setLegalTermCreatedBy(userId);

		try {
			procure.setLegalTermUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addLegalTerm", procure, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : add LegalTerm ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-legal-term-type")
	public @ResponseBody List<ProcurementMasterModel> viewLegalTerm(HttpSession session) {
		logger.info("Method : viewLegalTerm starts");

		JsonResponse<List<ProcurementMasterModel>> resp = new JsonResponse<List<ProcurementMasterModel>>();
		List<ProcurementMasterModel> LegalTermList = new ArrayList<ProcurementMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewLegalTerm", JsonResponse.class);
			LegalTermList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(LegalTermList);
		logger.info("Method : viewLegalTerm ends");
		return LegalTermList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-legal-term-type-delete")
	public @ResponseBody JsonResponse<Object> deleteLegalTerm(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteLegalTerm starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteLegalTerm?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteLegalTerm ends");
		return resp;
	}

////////Payment Status Type///////////////////////

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/view-procurement-add-payment-status-type")
	public @ResponseBody JsonResponse<Object> addPaymentStatus(@RequestBody ProcurementMasterModel procure, Model model,
			HttpSession session) {
		logger.info("Method : addPaymentStatus starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		procure.setPaymentStatusCreatedBy(userId);

		try {
			procure.setPaymentStatusUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addPaymentStatus", procure,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addPaymentStatus ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-payment-status-type")
	public @ResponseBody List<ProcurementMasterModel> viewPaymentStatus(HttpSession session) {
		logger.info("Method : viewPaymentStatus starts");

		JsonResponse<List<ProcurementMasterModel>> resp = new JsonResponse<List<ProcurementMasterModel>>();
		List<ProcurementMasterModel> paymentStatusList = new ArrayList<ProcurementMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewPaymentStatus", JsonResponse.class);
			paymentStatusList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : viewPaymentStatus ends");
		return paymentStatusList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-payment-status-type-delete")
	public @ResponseBody JsonResponse<Object> deletePaymentStatus(HttpSession session, @RequestParam String id) {
		logger.info("Method : deletePaymentStatus starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deletePaymentStatus?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deletePaymentStatus ends");
		return resp;
	}

////////Delivery Method Type///////////////////////

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/view-procurement-add-delivery-method-type")
	public @ResponseBody JsonResponse<Object> addDeliveryMethod(@RequestBody ProcurementMasterModel procure, Model model,
			HttpSession session) {
		logger.info("Method : addDeliveryMethod starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		procure.setDeliveryMethodCreatedBy(userId);

		try {
			procure.setDeliveryMethodUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addDeliveryMethod", procure,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addDeliveryMethod ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-delivery-method-type")
	public @ResponseBody List<ProcurementMasterModel> viewDeliveryMethod(HttpSession session) {
		logger.info("Method : viewPaymentStatus starts");

		JsonResponse<List<ProcurementMasterModel>> resp = new JsonResponse<List<ProcurementMasterModel>>();
		List<ProcurementMasterModel> DeliveryMethodList = new ArrayList<ProcurementMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewDeliveryMethod", JsonResponse.class);
			DeliveryMethodList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : viewDeliveryMethod ends");
		return DeliveryMethodList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-delivery-method-delete")
	public @ResponseBody JsonResponse<Object> deleteDeliveryMethod(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteDeliveryMethod starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteDeliveryMethod?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteDeliveryMethod ends");
		return resp;
	}

}
