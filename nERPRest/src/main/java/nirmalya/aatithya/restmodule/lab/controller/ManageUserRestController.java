package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.ManageUserRestDao;
import nirmalya.aatithya.restmodule.lab.model.EditInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.EditUserDetailRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageUserRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class ManageUserRestController {
	Logger logger = LoggerFactory.getLogger(ManageUserRestController.class);
	
	@Autowired
	ManageUserRestDao manageUserRestDao;
	
	@RequestMapping(value = "manage-user-details", method = { RequestMethod.GET })
	public JsonResponse<List<ManageUserRestModel>> RestManageUser(@RequestParam String userid)
	{
		logger.info("Method :RestManageUser starts");
		
		logger.info("Method :RestManageUser endss");
		return manageUserRestDao.manageUserRestDao(userid);

	}
	
	@PostMapping(value = "edit-user-details")
	public JsonResponse<Object> editUsersRestDetails(
			@RequestBody EditUserDetailRestModel manageUserRestModel,@RequestParam String userId) {
		logger.info("Method : editInstructionDetails starts");
		System.out.println("################"+manageUserRestModel);

		logger.info("Method : editInstructionDetails ends");
		return manageUserRestDao.editUserDetailsDao(manageUserRestModel,userId);
	}
	@RequestMapping(value = "delete-user", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteUserRest(@RequestParam String id,@RequestParam String userId) {
		logger.info("Method : rest deleteUserRest starts");

		logger.info("Method : rest deleteUserRest ends");
		return manageUserRestDao.deleteUserDao(id,userId);
	}

}
