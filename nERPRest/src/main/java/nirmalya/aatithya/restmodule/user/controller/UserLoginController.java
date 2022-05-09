package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.UserLoginDao;
import nirmalya.aatithya.restmodule.user.model.User;

/**
 * @author Nirmalya Labs
 *
 */
@RestController
@RequestMapping(value = "user/")
public class UserLoginController {

	Logger logger = LoggerFactory.getLogger(UserLoginController.class);

	@Autowired
	UserLoginDao userLoginDao;

	/**
	 * Function to check connection
	 *
	 */
	@RequestMapping(value = "welcome", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<String>> welcome() {
		logger.info("Method : welcome starts");

		JsonResponse<String> jsonResponse = new JsonResponse<String>();
		jsonResponse.setBody("Available");

		ResponseEntity<JsonResponse<String>> response = new ResponseEntity<JsonResponse<String>>(jsonResponse,
				HttpStatus.ACCEPTED);
		logger.info("Method : welcome ends");
		return response;
	}

	/**
	 * Function to get user by name
	 *
	 */
	@RequestMapping(value = "getUserByUsername", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<User>> getUserByUsername(@RequestParam String username,
			HttpServletRequest request) {
		logger.info("Method : getUserByUsername starts");
		//String ip = request.getRemoteAddr();

		//System.out.println("@@@@@@@ip" + ip);
		logger.info("Method : getUserByUsername ends");
		return userLoginDao.getUserByUsername(username);
	}

	@RequestMapping(value = "browserdDetails", method = { RequestMethod.GET })
	public JsonResponse<Object> savBrowserDetails(@RequestParam String userId,@RequestParam String ip, @RequestParam String browserName,
			@RequestParam String dashboard) {
		logger.info("Method : savBrowserDetails starts");
		System.out.println("#############" + browserName);
		logger.info("Method : savBrowserDetails ends");
		return userLoginDao.savBrowserDetails(userId,ip,browserName,dashboard);
	}

	/**
	 * Function to register user
	 *
	 */
	@RequestMapping(value = "registerUser", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<String>> registerUser(@RequestBody User user) {
		logger.info("Method : registerUser starts");

		logger.info("Method : registerUser ends");
		return userLoginDao.registerUser(user);
	}

}
