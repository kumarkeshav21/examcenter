package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.ManageInstructionRestDao;
import nirmalya.aatithya.restmodule.lab.dao.ManageLocationRestDao;
import nirmalya.aatithya.restmodule.lab.model.ManageInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageLocationRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class ManageLocationRestController {
	Logger logger = LoggerFactory.getLogger(ManageLocationRestController.class);

	@Autowired
	ManageLocationRestDao manageLocationRestDao;
	
	@RequestMapping(value = "rest-manage-location-table", method = { RequestMethod.GET })
	public JsonResponse<List<ManageLocationRestModel>> RestManageLocationController(@RequestParam String userid)
	{
		logger.info("Method :RestManageLocationController starts");
		
		logger.info("Method :RestManageLocationController endss");
		return manageLocationRestDao.manageLocationtionDao(userid);

	}
	

}
