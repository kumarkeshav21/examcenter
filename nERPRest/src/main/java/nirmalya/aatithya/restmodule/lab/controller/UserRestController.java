package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.UserRestDao;
import nirmalya.aatithya.restmodule.lab.model.AddUserRestModel;

@RestController
@RequestMapping(value = { "lab/" })
public class UserRestController {
	
	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	UserRestDao addUserRestDao;
	
	/*
	 * Add new user details
	 */

	@PostMapping(value = "rest-save-new-user-details")
	public JsonResponse<Object> addNewUserRestDetails(
			@RequestBody AddUserRestModel addUserRestModel,@RequestParam String userId) {
		logger.info("Method : addNewUserRestDetails starts");
		System.out.println("################"+addUserRestModel);

		logger.info("Method : addNewUserRestDetails ends");
		return addUserRestDao.addNewUserRestDetailsDao(addUserRestModel);
	}
	
	@RequestMapping(value = "get-user-list", method = { RequestMethod.GET })
	public List<DropDownModel> getUserRestList() {
		logger.info("Method : getLocRestList starts");
			System.out.println("#####");
		logger.info("Method : getLocRestList ends");
		return addUserRestDao.getUserList();
	}
	

}
