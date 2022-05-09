package nirmalya.aathithya.webmodule.master.controller;

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
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.LeaveApplyWebModel;

@Controller
@RequestMapping(value = "master/")
public class LeaveApplyWebController {
	Logger logger = LoggerFactory.getLogger(LeaveApplyWebController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("leave-apply")
	public String leaveApply(Model model, HttpSession session) {
		logger.info("Method : leaveApply starts");

		// leave type list
		try {
			DropDownModel[] leaveType = restClient.getForObject(env.getMasterUrl() + "getleavelists",
					DropDownModel[].class);
			List<DropDownModel> leavelist = Arrays.asList(leaveType);
			model.addAttribute("leavelist", leavelist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String userId = "";
		String userName = "";
		String userRole = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			userRole = (String) session.getAttribute("USER_ROLES_STRING");

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		String[] splitData = userRole.split(",");
		//System.out.println(Arrays.asList("splitData " + splitData));
		for (String a : splitData) {
			if (a.contentEquals("rol002")) {
				//System.out.println("iin side if");
				model.addAttribute("hrRole", a);
			} 
				
		}
		model.addAttribute("userId", userId);
		model.addAttribute("userName", userName);
		model.addAttribute("userRole", userRole);

		logger.info("Method : leaveApply ends");
		return "master/leave-apply";
	}

	// view leave apply

	@SuppressWarnings("unchecked")
	@GetMapping("leave-apply-view")
	public @ResponseBody List<LeaveApplyWebModel> viewLeaveApply(HttpSession session) {

		logger.info("Method : viewLeaveApply starts");

		JsonResponse<List<LeaveApplyWebModel>> resp = new JsonResponse<List<LeaveApplyWebModel>>();

		String userId = "";
        String userRole = "";
		String isHr = "";
		try { 
			userRole = (String) session.getAttribute("USER_ROLES_STRING");
		
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		String [] splitData = userRole.split(",");
		 for(String a : splitData) {
			 if(a.contentEquals("rol002"))
				 isHr = isHr + "HR";
		 } 
		 
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restClient.getForObject(env.getMasterUrl() + "viewleaveapply?userId=" + userId +"&isHr="+ isHr, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LeaveApplyWebModel> leaveapply = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LeaveApplyWebModel>>() {
				});

		String dateFormat = "";
		try {

			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}

		if(leaveapply!= null)
		// leaveapply.setEmpID(userId);
		for (LeaveApplyWebModel a : leaveapply) {
			if (a.getLeaveApplyDate() != null && a.getLeaveApplyDate() != "") {
				a.setLeaveApplyDate(DateFormatter.dateFormat(a.getLeaveApplyDate(), dateFormat));
			}
			if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
				a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
			}
		}

		//System.out.println(leaveapply);
		logger.info("Method : viewLeaveApply ends");

		return leaveapply;
	}

	/*
	 * //Main save for leave apply
	 * 
	 */

	@SuppressWarnings({ "unchecked" })
	@PostMapping(value = "leave-apply-save")
	public @ResponseBody JsonResponse<Object> saveLeaveApply(@RequestBody List<LeaveApplyWebModel> leaveModel,
			HttpSession session) {
		logger.info("Method : saveLeaveApply function starts");

		//System.out.println(leaveModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (LeaveApplyWebModel m : leaveModel) {
			m.setCreatedBy(userId);
			if (m.getLeaveApplyDate() != null && m.getLeaveApplyDate() != "") {
				m.setLeaveApplyDate(DateFormatter.dateFormat(m.getLeaveApplyDate(), dateFormat));
			}
			if (m.getFromDate() != null && m.getFromDate() != "") {
				m.setFromDate(DateFormatter.dateFormat(m.getFromDate(), dateFormat));
			}
			if (m.getToDate() != null && m.getToDate() != "") {
				m.setToDate(DateFormatter.dateFormat(m.getToDate(), dateFormat));
			}

		}
		try {

			resp = restClient.postForObject(env.getMasterUrl() + "saveleaveapply", leaveModel, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}
		//System.out.println("WEBBB" + resp);
		logger.info("Method : saveLeaveApply function Ends");

		return resp;
	}

	//   edit leave apply

	@SuppressWarnings("unchecked")
	@GetMapping("leave-apply-edit")
	public @ResponseBody List<LeaveApplyWebModel> editLeaveApply(@RequestParam String id, HttpSession session) {

		logger.info("Method : editLeaveApply starts");

		JsonResponse<List<LeaveApplyWebModel>> resp = new JsonResponse<List<LeaveApplyWebModel>>();
		try {

			resp = restClient.getForObject(env.getMasterUrl() + "editleaveapply?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LeaveApplyWebModel> leaveapply = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LeaveApplyWebModel>>() {
				});

		String dateFormat = "";
		try {

			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		int i = 0;
		for (LeaveApplyWebModel a : leaveapply) {
			i = i + 1;
			a.setRowId(i);
			if (a.getToDate() != null && a.getToDate() != "") {
				a.setToDate(DateFormatter.dateFormat(a.getToDate(), dateFormat));
			}
			if (a.getFromDate() != null && a.getFromDate() != "") {
				a.setFromDate(DateFormatter.dateFormat(a.getFromDate(), dateFormat));
			}
			if (a.getLeaveApplyDate() != null && a.getLeaveApplyDate() != "") {
				a.setLeaveApplyDate(DateFormatter.dateFormat(a.getLeaveApplyDate(), dateFormat));
			}
			if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
				a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
			}
			if (a.getApprovedDate()!= null && a.getApprovedDate() != "") {
				a.setApprovedDate(DateFormatter.dateFormat(a.getApprovedDate(), dateFormat));
			}
			if (a.getRejectDate()!= null && a.getRejectDate() != "") {
				a.setRejectDate(DateFormatter.dateFormat(a.getRejectDate(), dateFormat));
			}

		}
		//System.out.println("Response" + resp);
		resp.setBody(leaveapply);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			// System.out.println(resp.getMessage());
			resp.setMessage("Success");
		}

		// System.out.println(resp);
		logger.info("Method : editLeaveApply ends");

		return leaveapply;
	}

	// delete leave details table

	@SuppressWarnings("unchecked")
	@GetMapping("leave-apply-delete")
	public @ResponseBody JsonResponse<LeaveApplyWebModel> deleteLeaveApply(@RequestParam String deleteId) {

		logger.info("Method : deleteLeaveApply starts");
		//System.out.println(deleteId);
		JsonResponse<LeaveApplyWebModel> response = new JsonResponse<LeaveApplyWebModel>();

		try {
			response = restClient.getForObject(env.getMasterUrl() + "deleteleaveapply?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}

		logger.info("Method : deleteLeaveApply ends");
		return response;
	}

	// approve leave

	@SuppressWarnings("unchecked")
	@GetMapping("leave-apply-approve")
	public @ResponseBody JsonResponse<LeaveApplyWebModel> approveLeaveApply(@RequestParam String approveId,String name) {

		logger.info("Method : approveLeaveApply starts");
		JsonResponse<LeaveApplyWebModel> response = new JsonResponse<LeaveApplyWebModel>();

		try {
			response = restClient.getForObject(env.getMasterUrl() + "approveleaveapply?id=" + approveId + "&name=" + name,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : approveLeaveApply ends");
		return response;
	}
	
	//reject leave
	
	@SuppressWarnings("unchecked")
	@GetMapping("leave-apply-reject")
	public @ResponseBody JsonResponse<LeaveApplyWebModel> rejectLeaveApply(@RequestParam String rejectId,String name) {

		logger.info("Method : rejectLeaveApply starts");
		JsonResponse<LeaveApplyWebModel> response = new JsonResponse<LeaveApplyWebModel>();

		try {
			response = restClient.getForObject(env.getMasterUrl() + "rejectleaveapply?id=" + rejectId + "&name=" + name,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : rejectLeaveApply ends");
		return response;
	}
}
