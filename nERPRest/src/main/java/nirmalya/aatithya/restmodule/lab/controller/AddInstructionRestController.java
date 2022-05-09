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
import nirmalya.aatithya.restmodule.lab.dao.AddInstructionRestDao;
import nirmalya.aatithya.restmodule.lab.model.AddInstructionRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class AddInstructionRestController {
	Logger logger = LoggerFactory.getLogger(AddInstructionRestController.class);

	@Autowired
	AddInstructionRestDao addInstructionRestDao;
	
	/*
	 * Add center details
	 */

	@PostMapping(value = "rest-save-instruction-details")
	public JsonResponse<Object> addInstructionnDetails(
			@RequestBody AddInstructionRestModel addInstructionRestModel,@RequestParam String userId) {
		logger.info("Method : addInstructionnDetails starts");
		System.out.println("################"+addInstructionRestModel);

		logger.info("Method : addInstructionnDetails ends");
		return addInstructionRestDao.addInstructionRestDetailsDao(addInstructionRestModel,userId);
	}
	
	/*
	 * @RequestMapping(value = "get-alletype-Exam-list", method = {
	 * RequestMethod.GET }) public List<DropDownModel> getallerNamelist() {
	 * logger.info("Method : getallerExamlist starts");
	 * 
	 * logger.info("Method : getallerExamlist ends"); return
	 * addInstructionRestDao.getallExamlist();
	 * 
	 * }
	 */
	@RequestMapping(value = "get-exam-list", method = { RequestMethod.GET })
	public List<DropDownModel> getExamRestList() {
		logger.info("Method : getExamRestList starts");
			System.out.println("#####");
		logger.info("Method : getExamRestList ends");
		return addInstructionRestDao.getExamList();
	}


}
