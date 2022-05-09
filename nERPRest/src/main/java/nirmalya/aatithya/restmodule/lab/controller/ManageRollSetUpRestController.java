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
import nirmalya.aatithya.restmodule.lab.dao.ManageLocationRestDao;
import nirmalya.aatithya.restmodule.lab.dao.ManageRollSetUpDao;
import nirmalya.aatithya.restmodule.lab.model.EditInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageLocationRestModel;
import nirmalya.aatithya.restmodule.lab.model.RollSetUpRestModel;

@RestController
@RequestMapping(value = { "lab/" })


public class ManageRollSetUpRestController {
	Logger logger = LoggerFactory.getLogger(ManageRollSetUpRestController.class);

	@Autowired
	ManageRollSetUpDao manageRollSetUpDao;
	
	@RequestMapping(value = "rest-manage-roll-setup", method = { RequestMethod.GET })
	public JsonResponse<List<RollSetUpRestModel>> RestManageLocationController(@RequestParam String userid)
	{
		logger.info("Method :RestManageLocationController starts");
		
		logger.info("Method :RestManageLocationController endss");
		return manageRollSetUpDao.manageRollSetUpDao(userid);

	}
	
	@PostMapping(value = "edit-rollsetup-rest")
	public JsonResponse<Object> editRollSetUpRest(@RequestBody RollSetUpRestModel rollSetUpRestModel,@RequestParam String userId) {
		logger.info("Method : editRollSetUpRest starts");
		System.out.println("################"+rollSetUpRestModel);

		logger.info("Method : editRollSetUpRest ends");
		return manageRollSetUpDao.editRollSetUpRestDao(rollSetUpRestModel,userId);
	}
	@RequestMapping(value = "deleteRollsetup", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteRollsetupRest(@RequestParam String id,@RequestParam String userId) {
		logger.info("Method : rest deleteRollsetupRest starts");

		logger.info("Method : rest deleteRollsetupRest ends");
		return manageRollSetUpDao.deleteRollSetupDao(id,userId);
	}


}
