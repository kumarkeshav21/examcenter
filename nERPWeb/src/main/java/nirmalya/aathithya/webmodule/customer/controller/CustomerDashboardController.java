package nirmalya.aathithya.webmodule.customer.controller;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.customer.model.CustomerDashboardModel;

@Controller
@RequestMapping(value = "customer/")
public class CustomerDashboardController {
	Logger logger = LoggerFactory.getLogger(CustomerDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("dashboard-customer")
	public String customerDashboard(Model model, HttpSession session) {
		logger.info("Method : customerDashboard starts");
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		
		model.addAttribute("userId", userId);
		
		logger.info("Method : customerDashboard ends");
		return "customer/customer-dashboard";
	}

	// get customer details

	@SuppressWarnings("unchecked")
	@GetMapping("dashboard-customer-get-details")
	public @ResponseBody JsonResponse<CustomerDashboardModel> getCustomerDetails(HttpSession session) {

		logger.info("Method : getCustomerDetails starts");
		
		JsonResponse<CustomerDashboardModel> response = new JsonResponse<CustomerDashboardModel>();
		String userId = "";
		try {
			userId = "CUS0023";
			//userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getCustomerUrl() + "getcustomerdetails?id=" + userId,
					JsonResponse.class);

		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();

		CustomerDashboardModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<CustomerDashboardModel>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		if (customer.getFromDate() != null && customer.getFromDate() != "") {
			customer.setFromDate(DateFormatter.dateFormat(customer.getFromDate(), dateFormat));
		}
		if (customer.getToDate() != null && customer.getToDate() != "") {
			customer.setToDate(DateFormatter.dateFormat(customer.getToDate(), dateFormat));
		}
		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		System.out.println("webbbb====" + response);
		logger.info("Method : getCustomerDetails ends");
		return response;
	}

}
