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

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.ManageInstructionRestDao;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationReportRestModel;
import nirmalya.aatithya.restmodule.lab.model.EditExaminationRestModel;
import nirmalya.aatithya.restmodule.lab.model.EditInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageInstructionRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class ManageInstructionRestController {
	Logger logger = LoggerFactory.getLogger(ManageInstructionRestController.class);

	@Autowired
	ManageInstructionRestDao manageInstructionRestDao;
	

	@RequestMapping(value = "rest-manage-instruction-table", method = { RequestMethod.GET })
	public JsonResponse<List<ManageInstructionRestModel>> RestInstructionDao(@RequestParam String userid,@RequestParam String userrole)
	{
		logger.info("Method :RestInstructionDao starts");
		System.out.println("@@@@@222222@@@@@"+userid);
		logger.info("Method :RestInstructionDao endss");
		
		return manageInstructionRestDao.manageInstructionDao(userid,userrole);

	}
	
	/*
	 * edit instruction details
	 */

	@PostMapping(value = "edit-instruction-details")
	public JsonResponse<Object> editInstructionDetails(
			@RequestBody EditInstructionRestModel editInstructionRestModel,@RequestParam String userId) {
		logger.info("Method : editInstructionDetails starts");
		System.out.println("################"+editInstructionRestModel);

		logger.info("Method : editInstructionDetails ends");
		return manageInstructionRestDao.editInstructionDao(editInstructionRestModel,userId);
	}
	
	@RequestMapping(value = "get-exams-list", method = { RequestMethod.GET })
	public List<DropDownModel> getExamsRestList() {
		logger.info("Method : getExamRestList starts");
			System.out.println("#####");
		logger.info("Method : getExamRestList ends");
		return manageInstructionRestDao.getExamsList();
	}
	
	@RequestMapping(value = "deleteInstruction", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteInstruction(@RequestParam String id,@RequestParam String userId) {
		logger.info("Method : rest deleteInstruction starts");

		logger.info("Method : rest deleteInstruction ends");
		return manageInstructionRestDao.deleteInstruction(id,userId);
	}

	

}
