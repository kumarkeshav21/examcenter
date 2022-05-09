package nirmalya.aathithya.webmodule.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.user.model.Activity;
import nirmalya.aathithya.webmodule.user.model.ActivityAvlFunctionModel;
import nirmalya.aathithya.webmodule.user.model.Function;
import nirmalya.aathithya.webmodule.user.model.Module;
import nirmalya.aathithya.webmodule.user.model.PackageSubscriptionModel;
import nirmalya.aathithya.webmodule.user.model.SignUpModel;
import nirmalya.aathithya.webmodule.user.model.User;
import nirmalya.aathithya.webmodule.user.model.UserRolesAndModuleIdModel;
import nirmalya.aathithya.webmodule.user.model.VerificationWebModel;

/**
 * @author Nirmalya Labs
 *
 */
@Controller
public class AccessController {

	Logger logger = LoggerFactory.getLogger(AccessController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRolesAndModuleIdModel userModel;

	

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("welcome")
	public String welcome(Model model, HttpSession session) {
		logger.info("Method : welcome starts");

		logger.info("Method : welcome ends");
		return "welcome";
	}

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("hrms-dashboard")
	public String hrmsDashboard(Model model, HttpSession session) {
		logger.info("Method : hrmsDashboard starts");

		logger.info("Method : hrmsDashboard ends");
		return "employee/hrms-dashboard";
	}

	@GetMapping("/hrms-index")
	public String hrmsIndex(Model model, HttpSession session) {
		logger.info("Method : hrmsIndex starts");

		logger.info("Method : hrmsIndex ends");
		return "hrms-index";
	}

	/**
	 * Function for home page
	 *
	 */
	@GetMapping("/")
	public String home(Model model) {
		logger.info("Method : / starts");

		try {
			DropDownModel[] country = restTemplate.getForObject(env.getUserUrl() + "getcountrylist",
					DropDownModel[].class);
			List<DropDownModel> countrylist = Arrays.asList(country);
			model.addAttribute("countrylist", countrylist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] packageName = restTemplate.getForObject(env.getUserUrl() + "getpackagelist",
					DropDownModel[].class);
			List<DropDownModel> packagelist = Arrays.asList(packageName);
			model.addAttribute("packagelist", packagelist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : / ends");
		// return "app_index";
		// return "nerp-login";
		return "index2";

	}

	// dropdown for state through ajax

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/get-state-list" })
	public @ResponseBody JsonResponse<Object> getStateName(@RequestParam String country) {
		logger.info("Method : getStateName starts");

		// System.out.println(country);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "getstatelist?id=" + country, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		// System.out.println(res);
		logger.info("Method : getStateNameAJAX ends");
		return res;

	}

	// get amount

	@SuppressWarnings("unchecked")
	@GetMapping("/get-package-amount")
	public @ResponseBody JsonResponse<List<PackageSubscriptionModel>> getPackageAmount(@RequestParam String packageName,
			HttpSession session) {

		logger.info("Method : getPackageAmount starts");
		// System.out.println(packageName);
		JsonResponse<List<PackageSubscriptionModel>> resp = new JsonResponse<List<PackageSubscriptionModel>>();
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getpackageamount?packageName=" + packageName,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<PackageSubscriptionModel> amount = mapper.convertValue(resp.getBody(),
				new TypeReference<List<PackageSubscriptionModel>>() {
				});

		resp.setBody(amount);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		// System.out.println(resp);
		logger.info("Method : getPackageAmount ends");
		return resp;
	}

	// add signup

	@SuppressWarnings("unchecked")
	@PostMapping("/save-signup-details")
	public @ResponseBody JsonResponse<Object> addSignUp(@RequestBody SignUpModel details, HttpSession session) {

		logger.info("Method : addSignUp starts");

		// System.out.println(details);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String enc = details.getPassward();
			if (enc != null && enc != "") {
				enc = passwordEncoder.encode(enc);
				details.setPassward(enc);
			}

			resp = restTemplate.postForObject(env.getUserUrl() + "addsignupdetails", details, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		// System.out.println(resp);
		logger.info("Method : addSignUp ends");

		return resp;
	}
	
	@SuppressWarnings("unchecked")

	@GetMapping("/verification-mobile-email")
	public @ResponseBody JsonResponse<Object> forgetPassWeb(@RequestParam String mobile,@RequestParam String email,HttpSession session) {
		logger.info("Method : forgetPassWeb starts");
         System.out.println("mobile#####"+mobile);
         System.out.println("email#####"+email);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "verification-emailmobile-rest?mobile=" +mobile+"&email="+email,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(res);
		logger.info("Method : forgetPassWeb ends");
		return res;

	}
	
	
	@SuppressWarnings("unchecked")

	@GetMapping("/update-user-password")
	public @ResponseBody JsonResponse<Object> uppdatePassWeb(@RequestParam String pass,@RequestParam String uid,HttpSession session) {
		logger.info("Method : uppdatePassWeb starts");
         System.out.println("mobile#####"+uid);
         System.out.println("email#####"+pass);
         
        
 		if (pass != null && pass != "") {
 			pass = passwordEncoder.encode(pass);
 			
 		}

 		 System.out.println("email#####"+pass);
         
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "update-user-passwordrest?pass=" +pass+"&uid="+uid,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() == null) {
			res.setCode(res.getMessage());
			res.setMessage("Success");
		} else {
			res.setMessage("Unsuccess");
		}
		
		System.out.println(res);
		logger.info("Method : uppdatePassWeb ends");
		return res;

	}


	// add package details

	@SuppressWarnings("unchecked")
	@PostMapping("/save-packagess-details")
	public @ResponseBody JsonResponse<Object> savePackageDetails(@RequestBody PackageSubscriptionModel details,
			HttpSession session) {
		logger.info("Method : savePackageDetails function starts");
		// System.out.println(details);
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";
		String dateFormat = "";

		try {

			userId = (String) session.getAttribute("USER_ID");
			userId = "EMPL0002";
			// dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("dateFormat " + dateFormat);

		details.setCreatedBy(userId);

		/*
		 * if (details.getFromDate() != null && details.getFromDate() != "") {
		 * details.setFromDate(DateFormatter.inputDateFormat(details.getFromDate(),
		 * dateFormat)); } if (details.getToDate() != null && details.getToDate() != "")
		 * { details.setToDate(DateFormatter.inputDateFormat(details.getToDate(),
		 * dateFormat)); }
		 */
		// System.out.println("After formatting " + details);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "savepackagedetails", details, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String message = resp.getMessage();
		if (message != null && message != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		// System.out.println("WEBBB" + resp);
		logger.info("Method : savePackageDetails function Ends");
		return resp;
	}

	/**
	 * Function to show register user form
	 *
	 */
	@GetMapping("register")
	public String addUser(Model model, HttpSession session) {
		logger.info("Method : register starts");

		User user = new User();

		User form = (User) session.getAttribute("suser");

		String message = (String) session.getAttribute("message");
		if (message != null && message != "") {
			model.addAttribute("message", message);
		}

		session.setAttribute("message", "");

		if (form != null) {
			form.setUserPassword(null);
			model.addAttribute("user", form);
			session.setAttribute("suser", null);
		} else {
			model.addAttribute("user", user);
		}

		logger.info("Method : register ends");
		return "register";
	}

	/**
	 * Function show login form
	 *
	 */
	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		logger.info("Method : login starts");

		try {
			DropDownModel[] country = restTemplate.getForObject(env.getUserUrl() + "getcountrylist",
					DropDownModel[].class);
			List<DropDownModel> countrylist = Arrays.asList(country);
			model.addAttribute("countrylist", countrylist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] packageName = restTemplate.getForObject(env.getUserUrl() + "getpackagelist",
					DropDownModel[].class);
			List<DropDownModel> packagelist = Arrays.asList(packageName);
			model.addAttribute("packagelist", packagelist);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = (String) session.getAttribute("loginMessage");

		if (message != null && message != "") {
			model.addAttribute("loginMessage", message);
			session.setAttribute("loginMessage", null);
		}

		logger.info("Method : login starts");
		return "index2";
	}

	/**
	 * Function show index page after login
	 *
	 */

	@GetMapping("access-denied")
	public String accessDenied(Model model, HttpSession session) {
		logger.info("Method : access-denied starts");

		logger.info("Method : access-denied ends");
		return "accessDenied";
	}

	/**
	 * Function to logout user
	 *
	 */
	@GetMapping("logout")
	public String logout(Model model, HttpSession session) {
		logger.info("Method : access-denied Starts");

		session.invalidate();

		logger.info("Method : access-denied ends");
		return "redirect:";
	}

	/**
	 * Function to post register user form
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("addUser")
	public String addUserForm(@ModelAttribute User user, Model model, HttpSession session) {
		logger.info("Method POST : addUser starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		try {
			String enc = user.getUserPassword();
			if (enc != null && enc != "") {
				enc = passwordEncoder.encode(enc);
				user.setUserPassword(enc);
			}

			jsonResponse = restTemplate.postForObject(env.getUserUrl() + "registerUser", user, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != "") {
			session.setAttribute("message", jsonResponse.getMessage());
			session.setAttribute("suser", user);
			return "redirect:register";
		}

		logger.info("Method POST : addUser ends");
		return "redirect:login";
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index")
	public String index(Model model, HttpSession session,HttpServletRequest request,String username) {
		logger.info("Method : index starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
			String userId = null;
		    String ip = request.getRemoteAddr();
		  try {
			  userId = (String) session.getAttribute("USER_ID");
		      System.out.println("#####" + userId); }
		  catch (Exception e) {
		     e.printStackTrace();
		     }
		  String userAgent = request.getHeader("user-agent");
		  String browserName = "";
		  String browserVer = "";
		  if(userAgent.contains("Chrome")) 
		  { // checking if Chrome 
			  String substring =
		  userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0];
		  browserName =substring.split("/")[0];
		  browserVer = substring.split("/")[1];
		  System.out.println("browser" + browserName + userAgent); }
		  
		  if (userAgent.contains("Firefox")) 
		  { // Checking if Firefox
			  String substring = userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0];
		  browserName = substring.split("/")[0];
		  browserVer = substring.split("/")[1];
		  System.out.println("browser" + browserName + browserVer); }
		  // @SuppressWarnings("unused")
		  System.out.println(userId+" *** "+ip+"  **  "+browserName);
		  
		 // session.invalidate();
		  
		  
		 
		 String dashboard = (String) session.getAttribute("DASHBOARD");
		 System.out.println("@#@#@#@#@#@#@"+dashboard);
		  try {
	  
			  resp = restTemplate.getForObject( env.getUserUrl() +"browserdDetails?userId=" + userId +
					 "&ip=" + ip+"&browserName=" + browserName+"&dashboard="+dashboard,
	      JsonResponse.class);
		  }
		  catch (RestClientException e)
		  {
			  e.printStackTrace(); 
		  }
		  
		  
		 

		return "extend-index";
		//return null;
	}





	/**
	 * Web Controller - Get details For Modal
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/restaurant/kitchen-staff-order-details-modal" })
	public @ResponseBody JsonResponse<Object> modalQuotation(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : summaryModal starts");
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getKitchenUrl() + "getOrderSummary?id=" + index, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : summaryModal ends");
		return res;
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index-get-function-list/{id}")
	public String getMenuDetails(Model model, HttpSession session, @PathVariable String id) {
		logger.info("Method : index starts");

		List<Module> module = new ArrayList<Module>();
		List<Function> funDetails = new ArrayList<Function>();
		String activityUrl = "";
		try {

			module = (List<Module>) session.getAttribute("MENU");
			if (module != null && module.size() > 0) {
				List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(id))
						.collect(Collectors.toList());
				if (!fList.isEmpty()) {
					funDetails = fList.get(0).getModule();
					for (Function a : funDetails) {
						if (a.getFunction() != null) {
							a.setDefaultUrl(a.getFunction().get(0).getActivity());
						}

					}
				}

				session.setAttribute("funList", funDetails);
				session.setAttribute("moduleId", id);
				if (!funDetails.isEmpty()) {
					activityUrl = funDetails.get(0).getFunction().get(0).getActivity();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : index  extend-index ends");

		// return "extend-index2";
		System.out.println("######" + activityUrl);
		return "redirect:" + activityUrl;

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/index-get-activity-list" })
	public @ResponseBody JsonResponse<List<Activity>> getActivityList(@RequestParam String funId,
			@RequestParam String moduleId, HttpSession session) {
		logger.info("Method : getActivityList starts");

		JsonResponse<List<Activity>> res = new JsonResponse<List<Activity>>();
		List<Activity> activityList = new ArrayList<Activity>();

		try {
			List<Module> module = new ArrayList<Module>();
			try {

				module = (List<Module>) session.getAttribute("MENU");
				if (module != null && module.size() > 0) {
					List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(moduleId))
							.collect(Collectors.toList());
					if (fList != null) {
						List<Function> funDetails = (List<Function>) fList.get(0).getModule().stream()
								.filter(a -> a.getFunctionId().equals(funId)).collect(Collectors.toList());
						activityList = funDetails.get(0).getFunction();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			res.setBody(activityList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		System.out.println("Activity" + res);
		logger.info("Method : getActivityList ends");
		
		return res;
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index-get-function-list-resp")
	public @ResponseBody JsonResponse<List<Function>> getFunctionDetails(Model model, HttpSession session,
			@RequestParam String moduleId) {
		logger.info("Method : index starts");

		JsonResponse<List<Function>> res = new JsonResponse<List<Function>>();
		List<Activity> activityList = new ArrayList<Activity>();

		List<Module> module = new ArrayList<Module>();
		List<Function> funDetails = new ArrayList<Function>();
		String activityUrl = "";
		try {

			module = (List<Module>) session.getAttribute("MENU");
			if (module != null && module.size() > 0) {
				List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(moduleId))
						.collect(Collectors.toList());
				if (!fList.isEmpty()) {
					funDetails = fList.get(0).getModule();
					for (Function a : funDetails) {
						if (a.getFunction() != null) {
							a.setDefaultUrl(a.getFunction().get(0).getActivity());
							a.setDefaultUrlId(a.getFunction().get(0).getActivityId());
						}

					}
				}

				session.setAttribute("funList", funDetails);
				session.setAttribute("moduleId", moduleId);
				if (!funDetails.isEmpty()) {
					activityUrl = funDetails.get(0).getFunction().get(0).getActivity();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.setBody(funDetails);
		logger.info("Method : index  extend-index ends");

		// return "extend-index2";
		return res;

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/index-get-module-list" })
	public @ResponseBody JsonResponse<List<Module>> getModuleDetails(Model model, HttpSession session,
			@RequestParam String moduleId) {
		logger.info("Method : getModuleDetails starts");

		JsonResponse<List<Module>> res = new JsonResponse<List<Module>>();

		List<Module> module = (List<Module>) session.getAttribute("MENU");

		res.setBody(module);
		logger.info("Method : getModuleDetails ends");
		return res;

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/index-get-avl-function-list")
	public @ResponseBody JsonResponse<ActivityAvlFunctionModel> getAvlFunctionByActivityRole(
			@RequestBody List<DropDownModel> data, HttpSession session) {
		logger.info("Method : updateModule starts");

		JsonResponse<ActivityAvlFunctionModel> resp = new JsonResponse<ActivityAvlFunctionModel>();

		try {
			// resp = restTemplate.postForObject(env.getUserUrl() +
			// "getAvlFunctionByActivityRole" , data , JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : getAvlFunctionByActivityRole starts");
		return resp;
	}

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("error")
	public String error(Model model, HttpSession session) {
		logger.info("Method : error starts");

		logger.info("Method : error ends");
		return "error";
	}
}
