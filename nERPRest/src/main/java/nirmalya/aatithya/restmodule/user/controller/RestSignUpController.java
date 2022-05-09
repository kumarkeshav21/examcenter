package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.SignUpDao;
import nirmalya.aatithya.restmodule.user.model.RestSignUpModel;
import nirmalya.aatithya.restmodule.user.model.VerificationRestModel;

@RestController
@RequestMapping(value = "user/")
public class RestSignUpController {
Logger logger = LoggerFactory.getLogger(RestSignUpController.class);
	
	@Autowired
	SignUpDao signUpDao;
	
	//country list
	
	@GetMapping(value = "getcountrylist")
	public List<DropDownModel> countryList() {
		logger.info("Method : countryList starts");

		logger.info("Method : countryList ends");
		return signUpDao.countryList();
	}
	
	//state list
	
	@GetMapping(value = "getstatelist")
	public JsonResponse<List<DropDownModel>> getStateName(@RequestParam String id) {
		logger.info("Method : getStateName starts");
		
		logger.info("Method : getStateName ends");
		return signUpDao.getStateName(id);
	}
	
	//save signup details
	
	@PostMapping(value="addsignupdetails")
	public JsonResponse<Object> addSignUp(@RequestBody RestSignUpModel details){
		logger.info("Method : addSignUp starts");
		
		logger.info("Method : addSignUp ends");
		return signUpDao.addSignUp(details);
	}
	
	@GetMapping(value="verification-emailmobile-rest")
	public JsonResponse<List<DropDownModel>> verificationRest(@RequestParam String mobile,@RequestParam String email){
		logger.info("Method : verificationRest starts");
		 System.out.println("@@@@@@"+mobile);
		logger.info("Method : verificationRest ends");
		return signUpDao.verificationRestDao(mobile,email);
	}
	
	@GetMapping(value="update-user-passwordrest")
	public JsonResponse<List<DropDownModel>> updatePassRest(@RequestParam String pass,@RequestParam String uid){
		logger.info("Method : updatePassRest starts");
		 System.out.println("@@@@@@"+pass);
		logger.info("Method : updatePassRest ends");
		return signUpDao.updatePassRestDao(pass,uid);
	}
	
	

}
