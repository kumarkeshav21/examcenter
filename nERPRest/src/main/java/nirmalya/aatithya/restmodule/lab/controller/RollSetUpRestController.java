package nirmalya.aatithya.restmodule.lab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.RollSetUpDao;
import nirmalya.aatithya.restmodule.lab.model.RollSetUpRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class RollSetUpRestController {
	Logger logger = LoggerFactory.getLogger(AddCenterRestController.class);

	@Autowired
	
	RollSetUpDao rollSetUp;
	
	/*
	 * Add roll set up details
	 */

	@PostMapping(value = "rest-rollsetup-details")
	public JsonResponse<Object> addRollSetUp(@RequestBody RollSetUpRestModel rollSetUpRestModel,@RequestParam String userId) {
		logger.info("Method : addRollSetUp starts");
		System.out.println("################"+rollSetUpRestModel);

		logger.info("Method : addRollSetUp ends");
		return rollSetUp.addRollSetUpDao(rollSetUpRestModel,userId);
	}
}
