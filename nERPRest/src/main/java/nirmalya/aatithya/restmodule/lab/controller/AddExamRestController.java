package nirmalya.aatithya.restmodule.lab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Arrays;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.AddCenterRestDao;
import nirmalya.aatithya.restmodule.lab.dao.AddExamRestDao;
import nirmalya.aatithya.restmodule.lab.model.AddCenterRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddExamRestModel;

@RestController
@RequestMapping(value = { "lab/" })
public class AddExamRestController {
	
	Logger logger = LoggerFactory.getLogger(AddExamRestController.class);

	@Autowired
	AddExamRestDao addExamRestDao;
	
	/*
	 * Add center details
	 */

	@PostMapping(value = "rest-save-exam-details")
	public JsonResponse<Object> addExamDetails(
			@RequestBody AddExamRestModel addExamRestModel,@RequestParam String userId) {
		logger.info("Method : addExamDetails starts");
		System.out.println("################"+addExamRestModel);

		logger.info("Method : addExamDetails ends");
		return addExamRestDao.addExamRestDetailsDao(addExamRestModel,userId);
	}
	

	@RequestMapping(value = "get-allerName-Dashboard-list", method = { RequestMethod.GET })
	public List<DropDownModel> getallerNamelist() {
		logger.info("Method : getallerExamlist starts");

		logger.info("Method : getallerExamlist ends");
		return addExamRestDao.getallExamlist();

	}


}
