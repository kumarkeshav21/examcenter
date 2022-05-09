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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.LeavePolicyModel;

@Controller
@RequestMapping(value = "master/")
public class LeavePolicyController {

	Logger logger = LoggerFactory.getLogger(LeavePolicyController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("leave-policy")
	public String leavePolicy(Model model, HttpSession session) {
		logger.info("Method : leavePolicy starts");

		//leave type list
				try {
					DropDownModel[] leaveType = restClient.getForObject(env.getMasterUrl() + "getleavelist",
							DropDownModel[].class);
					List<DropDownModel> leavelist = Arrays.asList(leaveType);
					model.addAttribute("leavelist", leavelist);
				} catch (RestClientException e) {
					e.printStackTrace();
				}
		
		logger.info("Method : leavePolicy ends");
		return "master/leave-policy";
	}
	
	// view

		@SuppressWarnings("unchecked")
		@GetMapping("leave-policy-view")
		public @ResponseBody List<LeavePolicyModel> viewLeavePolicy(HttpSession session) {

			logger.info("Method : viewLeavePolicy starts");

			JsonResponse<List<LeavePolicyModel>> resp = new JsonResponse<List<LeavePolicyModel>>();

			try {
				resp = restClient.getForObject(env.getMasterUrl() + "viewleavepolicy", JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<LeavePolicyModel> policy = mapper.convertValue(resp.getBody(), new TypeReference<List<LeavePolicyModel>>() {
			});
			String dateFormat = "";
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {

			}
			for (LeavePolicyModel a : policy) {
				if (a.getLeaveApplyDate() != null && a.getLeaveApplyDate() != "") {
					a.setLeaveApplyDate(DateFormatter.dateFormat(a.getLeaveApplyDate(), dateFormat));
				}
			}

			//System.out.println(policy);
			logger.info("Method : viewLeavePolicy ends");

			return policy;
		}
		
		// view

		@SuppressWarnings("unchecked")
		@GetMapping("leave-policy-apply-view")
		public @ResponseBody List<LeavePolicyModel> viewLeaveApply(HttpSession session) {

			logger.info("Method : viewLeaveApply starts");

			JsonResponse<List<LeavePolicyModel>> resp = new JsonResponse<List<LeavePolicyModel>>();
			
			String userId="";
			
			try {
				userId = (String) session.getAttribute("USER_ID");
				resp = restClient.getForObject(env.getMasterUrl() + "viewleaveApply?userId="+userId, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<LeavePolicyModel> leaveapply = mapper.convertValue(resp.getBody(),
					new TypeReference<List<LeavePolicyModel>>() {
					});
			
			String dateFormat = "";
			try {
				
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {

			}
			//leaveapply.setEmpID(userId);
			for (LeavePolicyModel a : leaveapply) {
				if (a.getToDate() != null && a.getToDate() != "") {
					a.setToDate(DateFormatter.dateFormat(a.getToDate(), dateFormat));
				}
				if (a.getFromDate() != null && a.getFromDate() != "") {
					a.setFromDate(DateFormatter.dateFormat(a.getFromDate(), dateFormat));
				}
			}

			System.out.println(leaveapply);
			logger.info("Method : viewLeaveApply ends");

			return leaveapply;
		}
		
		//add
		@SuppressWarnings({ "unchecked" })
		@PostMapping(value = "leave-policy-save")
		public @ResponseBody JsonResponse<Object> saveLeaveApply(@RequestBody List<LeavePolicyModel> leaveModel,
				HttpSession session) {
			logger.info("Method : saveLeaveApply function starts");
			System.out.println(leaveModel);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			String userId = "";
			String dateFormat = "";
			
			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {

			}
			for (LeavePolicyModel m : leaveModel) {
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

				resp = restClient.postForObject(env.getMasterUrl() + "addleaveapply", leaveModel,
						JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}

			String message = resp.getMessage();

			if (message != null && message != "") {

			} else {
				resp.setMessage("Success");
			}
			System.out.println("WEBBB"+resp);
			logger.info("Method : saveLeaveApply function Ends");
			
			return resp;
		}


}
