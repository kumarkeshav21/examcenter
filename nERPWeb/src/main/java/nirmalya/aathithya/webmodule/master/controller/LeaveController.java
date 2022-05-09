package nirmalya.aathithya.webmodule.master.controller;

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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.LeaveModel;





@Controller
@RequestMapping(value = { "master/" })
public class LeaveController {
	Logger logger = LoggerFactory.getLogger(LeaveController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	
	

	@GetMapping(value = { "leave-type" })
	
	public String LeaveType(Model model, HttpSession session) {
		logger.info("Method : LeaveType starts");
		//Leave Type List
		try {
			DropDownModel[] leave = restTemplate.getForObject(env.getMasterUrl() + "getLeavetypeLists",
					DropDownModel[].class);
			List<DropDownModel> getLeavetypeList = Arrays.asList(leave);
			model.addAttribute("getLeavetypeList", getLeavetypeList);

		} catch (RestClientException e) {
			e.printStackTrace();

		}
		
		//Job Title List

		try {
			DropDownModel[] job = restTemplate.getForObject(env.getMasterUrl() + "getJobTitlLists",
					DropDownModel[].class);
			List<DropDownModel> getJobTitleList = Arrays.asList(job);
			model.addAttribute("getJobTitleList", getJobTitleList);

		} catch (RestClientException e) {
			e.printStackTrace();

		}
		// Employee Status Dropdown
		try {
			DropDownModel[] employee = restTemplate.getForObject(env.getMasterUrl() + "getEmpstatus",
					DropDownModel[].class);
			List<DropDownModel> getEmployeeStatus = Arrays.asList(employee);
			model.addAttribute("getEmployeeStatus", getEmployeeStatus);

		} catch (RestClientException e) {
			e.printStackTrace();

		}
		// Employee Name
		try {
			DropDownModel[] empName = restTemplate.getForObject(env.getMasterUrl() + "getEmployeename",
					DropDownModel[].class);
			List<DropDownModel> getEmployeeName = Arrays.asList(empName);
			model.addAttribute("getEmployeeName", getEmployeeName);

		} catch (RestClientException e) {
			e.printStackTrace();

		}
		logger.info("Method : LeaveType ends");
		return "master/leaveType";
	}
	//Leave Type Add
		@SuppressWarnings("unchecked")
		@PostMapping("/leave-type-add")
		public @ResponseBody JsonResponse<Object> addLeave(HttpSession session,
				@RequestBody LeaveModel leaveModel) {

			logger.info("Method : addLeave");
			String userId = "";
			String dateFormat = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormat = (String) session.getAttribute("DATEFORMAT");
				System.out.println(dateFormat);
				
			} catch (Exception e) {

			}
			leaveModel.setCreatedBy(userId);
			if (leaveModel.getLeaveProperJdate() != null && leaveModel.getLeaveProperJdate() != "") {
				leaveModel.setLeaveProperJdate(DateFormatter.inputDateFormat(leaveModel.getLeaveProperJdate(), dateFormat));
			}
			
			System.out.println(leaveModel);

			JsonResponse<Object> resp = new JsonResponse<Object>();
			System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrsop" + resp);
			try {
				resp = restTemplate.postForObject(env.getMasterUrl() + "addLeave", leaveModel,
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

			logger.info("Method : addLeave ends");
			System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrreturn" + resp);
			return resp;
		}
		
		//View page for  Leave types

		@SuppressWarnings("unchecked")
		@GetMapping("/leave-type-vieww")
		public @ResponseBody List<LeaveModel> viewLeaveType(HttpSession session) {
			logger.info("Method : viewLeaveType starts");

			JsonResponse<List<LeaveModel>> resp = new JsonResponse<List<LeaveModel>>();
			List<LeaveModel> returnList = new ArrayList<LeaveModel>();

			try {
				resp = restTemplate.getForObject(env.getMasterUrl() + "viewLeave", JsonResponse.class);
				returnList = resp.getBody();
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<LeaveModel> leaveModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<LeaveModel>>() {
					});
			String dateFormat = "";
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {

			}
			for (LeaveModel a : leaveModel) {
				if (a.getLeaveProperJdate() != null && a.getLeaveProperJdate() != "") {
					a.setLeaveProperJdate(DateFormatter.dateFormat(a.getLeaveProperJdate(), dateFormat));
				}
				
				
			}
			//System.out.println("returnListtttttttttttttttttttweb"+returnList);
			logger.info("Method : viewLeave ends");
			return returnList;
		}
		
		//Leave Type Delete	 
		@SuppressWarnings("unchecked")
		@GetMapping("/leave-type-delete")
		public @ResponseBody JsonResponse<LeaveModel> deleteLeave(@RequestParam String deleteId) {
			logger.info("Method : deleteEmployeeDetails starts");

			JsonResponse<LeaveModel> resp = new JsonResponse<LeaveModel>();

			try {
				resp = restTemplate.getForObject(env.getMasterUrl() + "deleteLeave?deleteId=" + deleteId,
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
	//System.out.println("deletewwwwwwwwwwwwweeeeeeeeeeeebbbbbbbbbbbbbb"+resp);
			logger.info("Method :  deleteLeave ends");
			return resp;
		}
		
		//For Leave Type Edit

		@SuppressWarnings("unchecked")
		@PostMapping("/leave-type-edit")
		public @ResponseBody JsonResponse<LeaveModel> editLeaves(@RequestParam String id, HttpSession session) {

			logger.info("Method : editEmployeeDetails starts");

			JsonResponse<LeaveModel> jsonResponse = new JsonResponse<LeaveModel>();
			//System.out.println(id);
			try {
				jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "editLeaves?id=" + id,
						JsonResponse.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}		
			ObjectMapper mapper = new ObjectMapper();

			LeaveModel leaveModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<LeaveModel>() {
			});
			String dateFormat = "";
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {

			}
			if (leaveModel.getLeaveProperJdate() != null && leaveModel.getLeaveProperJdate() != "") {
				leaveModel.setLeaveProperJdate(DateFormatter.dateFormat(leaveModel.getLeaveProperJdate(), dateFormat));
			}
			jsonResponse.setBody(leaveModel);
			if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
				jsonResponse.setCode(jsonResponse.getMessage());
				jsonResponse.setMessage("Unsuccess");

			} else {
				jsonResponse.setMessage("Success");
			}

			//System.out.println("##@@@@" + jsonResponse);
			logger.info("Method : editEmployeeDetails ends");
			return jsonResponse;
		}
		
		//Leave Rule Type Add
		@SuppressWarnings("unchecked")
		@PostMapping("/leave-type-rule-add")
		public @ResponseBody JsonResponse<Object> addLeaveRule(HttpSession session,
				@RequestBody LeaveModel leaveModel) {

			logger.info("Method : addLeaveRule");

			String dateFormat = "";
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
				System.out.println(dateFormat);

			} catch (Exception e) {

			}
			if (leaveModel.getProperJoinDate() != null && leaveModel.getProperJoinDate() != "") {
				leaveModel.setProperJoinDate(DateFormatter.inputDateFormat(leaveModel.getProperJoinDate(), dateFormat));
			}
			
			//System.out.println(studentModel);

			JsonResponse<Object> resp = new JsonResponse<Object>();
			//System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrsop" + resp);
			try {
				resp = restTemplate.postForObject(env.getMasterUrl() + "addLeaveRule", leaveModel,
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

			logger.info("Method : addLeaveRule ends");
			//System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrreturn" + resp);
			return resp;
		}
		//View page for Leave Rule Type

		@SuppressWarnings("unchecked")
		@GetMapping("/leave-type-rule-view")
		public @ResponseBody List<LeaveModel> viewLeaveRule(HttpSession session) {
			logger.info("Method : viewLeaveRule starts");

			JsonResponse<List<LeaveModel>> resp = new JsonResponse<List<LeaveModel>>();
			List<LeaveModel> returnList = new ArrayList<LeaveModel>();

			try {
				resp = restTemplate.getForObject(env.getMasterUrl() + "viewLeaveRules", JsonResponse.class);
				returnList = resp.getBody();
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<LeaveModel> leaveModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<LeaveModel>>() {
					});
			String dateFormat = "";
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {

			}
			for (LeaveModel a : leaveModel) {
				if (a.getLeaveProperJdate() != null && a.getLeaveProperJdate() != "") {
					a.setLeaveProperJdate(DateFormatter.dateFormat(a.getLeaveProperJdate(), dateFormat));
				}						
			}
			//System.out.println("returnListtttttttttttttttttttwebRule"+returnList);
			logger.info("Method : viewLeaveRule ends");
			return returnList;
		} 
		
		//Leave Type Rule Delete	 
				@SuppressWarnings("unchecked")
				@GetMapping("/leave-type-rule-delete")
				public @ResponseBody JsonResponse<LeaveModel> deleteLeaveRule(@RequestParam String deleteId) {
					logger.info("Method : deleteLeaveRule starts");
					JsonResponse<LeaveModel> resp = new JsonResponse<LeaveModel>();

					try {
						resp = restTemplate.getForObject(env.getMasterUrl() + "deleteLeaveRule?deleteId=" + deleteId,
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
			//System.out.println("deletewwwwwwwwwwwwweeeeeeeeeeeebbbbbbbbbbbbbb"+resp);
					logger.info("Method :  deleteLeaveRule ends");
					return resp;
				}
				//For Leave Rule Type Edit

				@SuppressWarnings("unchecked")
				@PostMapping("/leave-type-rule-edit")
				public @ResponseBody JsonResponse<LeaveModel> editLeavesRule(@RequestParam String id, HttpSession session) {

					logger.info("Method : editLeavesRule starts");

					JsonResponse<LeaveModel> jsonResponse = new JsonResponse<LeaveModel>();
					System.out.println(id);
					try {
						jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "editLeavesRule?id=" + id,
								JsonResponse.class);

					} catch (RestClientException e) {
						e.printStackTrace();
					}
					ObjectMapper mapper = new ObjectMapper();

					LeaveModel leaveModel = mapper.convertValue(jsonResponse.getBody(), new TypeReference<LeaveModel>() {
					});
					String dateFormat = "";
					try {
						dateFormat = (String) session.getAttribute("DATEFORMAT");
					} catch (Exception e) {

					}
					if (leaveModel.getProperJoinDate() != null && leaveModel.getProperJoinDate() != "") {
						leaveModel.setProperJoinDate(DateFormatter.dateFormat(leaveModel.getProperJoinDate(), dateFormat));
					}
					
					jsonResponse.setBody(leaveModel);

					if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
						jsonResponse.setCode(jsonResponse.getMessage());
						jsonResponse.setMessage("Unsuccess");

					} else {
						jsonResponse.setMessage("Success");
					}

					//System.out.println("##@@@@wwwwwwwebedit" + jsonResponse);
					logger.info("Method : editLeavesRule ends");
					return jsonResponse;
				}

}
