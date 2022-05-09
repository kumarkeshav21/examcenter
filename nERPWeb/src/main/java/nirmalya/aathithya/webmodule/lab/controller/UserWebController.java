package nirmalya.aathithya.webmodule.lab.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.AddInstructionModel;
import nirmalya.aathithya.webmodule.lab.model.AddUserWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class UserWebController {
	Logger logger = LoggerFactory.getLogger(UserWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("lab-user")
	public String addcenter(Model model, HttpSession session) {

		logger.info("Method : addcenter starts");

		try {
			DropDownModel[] userList = restTemplate.getForObject(env.getLabUrl() + "get-user-list",
					DropDownModel[].class);
			List<DropDownModel> getUserList = Arrays.asList(userList);

			model.addAttribute("userList", getUserList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : addcenter ends");
		return "lab/user";
	}

	/*
	 * Add User details
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("add-newUser-details-save")
	public @ResponseBody JsonResponse<Object> addUserDetails(Model model, @RequestBody AddUserWebModel addUser,
			HttpSession session) {
		logger.info("Method : addUserDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		
		String enc = addUser.getPassword();
		if (enc != null && enc != "") {
			enc = passwordEncoder.encode(enc);
			addUser.setPassword(enc);
		}

		@SuppressWarnings("unused")
		String userId = (String) session.getAttribute("USER_ID");

		System.out.println("########" + resp);
		
		
		/*
		 * byte[] encodeByte = Base64.getDecoder().decode(id.getBytes()); String id1 =
		 * (new String(encodeByte)); System.out.println("$$$$$$$$$$$"+id1);
		 */
		
		
		try {

			resp = restTemplate.postForObject(env.getLabUrl() + "rest-save-new-user-details?userId=" + userId, addUser,
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

		

		logger.info("Method : addUserDetails ends");
		return resp;
	}

}
