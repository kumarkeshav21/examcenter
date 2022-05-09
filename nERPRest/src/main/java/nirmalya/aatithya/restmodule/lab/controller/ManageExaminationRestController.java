package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.ManageCenterRestDao;
import nirmalya.aatithya.restmodule.lab.dao.ManageExaminationRestDao;
import nirmalya.aatithya.restmodule.lab.model.AddCenterAddMoreRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddMoreExamCourseRestModel;
import nirmalya.aatithya.restmodule.lab.model.EditExaminationRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageCenterRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageExaminationRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageInstructionRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class ManageExaminationRestController {
	Logger logger = LoggerFactory.getLogger(ManageExaminationRestController.class);

	@Autowired
	ManageExaminationRestDao manageExaminationRestDao;
	
	@RequestMapping(value = "rest-manage-examination-table", method = { RequestMethod.GET })
	public JsonResponse<List<ManageExaminationRestModel>> RestManageExam(@RequestParam String userid,@RequestParam String userrole)
	{
		logger.info("Method :RestManageExam starts");
		
		logger.info("Method :RestManageExam endss");
		return manageExaminationRestDao.manageExaminationnDao(userid,userrole);

	}
	
	/*
	 * edit exam details
	 */

	@PostMapping(value = "edit-examination-details")
	public JsonResponse<Object> editExaminationDetails(
			@RequestBody EditExaminationRestModel editExaminationRestModel,@RequestParam String userId) {
		logger.info("Method : editExaminationDetails starts");
		System.out.println("################"+editExaminationRestModel);

		logger.info("Method : editExaminationDetails ends");
		return manageExaminationRestDao.editExaminationDao(editExaminationRestModel,userId);
	}
	
	@RequestMapping(value = "deleteExamination", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteExamination(@RequestParam String id,@RequestParam String userId) {
		logger.info("Method : rest deleteExamination starts");
			System.out.println("=====>>>>"+id);
		logger.info("Method : rest deleteExamination ends");
		return manageExaminationRestDao.deleteExamination(id,userId);
	}
	
	//manage center add more part
	
	
	
	@RequestMapping(value = "get-exam-Course-id", method = { RequestMethod.GET })
	public List<DropDownModel> getExamManageCourseList(@RequestParam String id) {
		logger.info("Method : getExamManageCourseList starts");
		
		logger.info("Method : getExamManageCourseList ends");

		return manageExaminationRestDao.getManageCourseList(id);
	}
	
		@RequestMapping(value = "rest-manage-exam-addmore", method = { RequestMethod.GET })
		public JsonResponse<List<AddMoreExamCourseRestModel>> manageExamCourseRest(@RequestParam String id)
		{
			logger.info("Method :manageExamCourseRest starts");
			System.out.println("#####"+id);
			logger.info("Method :manageExamCourseRest endss");
			return manageExaminationRestDao.manageExamCourseDao(id);

		}

}
