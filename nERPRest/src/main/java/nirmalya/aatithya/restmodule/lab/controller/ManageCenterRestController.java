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
import nirmalya.aatithya.restmodule.lab.model.AddCenterAddMoreRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageCenterRestModel;

@RestController
@RequestMapping(value = { "lab/" })
public class ManageCenterRestController {
	Logger logger = LoggerFactory.getLogger(ManageCenterRestController.class);

	@Autowired
	ManageCenterRestDao manageCenterRestDao;
	
	@RequestMapping(value = "rest-manage-center-table", method = { RequestMethod.GET })
	public JsonResponse<List<ManageCenterRestModel>> RestCandidateList(@RequestParam String userid,@RequestParam String userrole)
	{
		logger.info("Method :RestCandidateList starts");
		
		logger.info("Method :RestCandidateList endss");
		return manageCenterRestDao.manageCenterDao(userid,userrole);

	}
	
	
	
	@RequestMapping(value = "deleteCenter", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteCenter(@RequestParam String id,@RequestParam String userid) {
		logger.info("Method : rest deleteCenter starts");

		logger.info("Method : rest deleteCenter ends");
		return manageCenterRestDao.deleteCenter(id,userid);
	}
	
	/*
	 * edit center details
	 */

	@PostMapping(value = "edit-center-details")
	public JsonResponse<Object> editCenterDetails(@RequestBody ManageCenterRestModel editCenterRestModel,@RequestParam String userId) {
		logger.info("Method : editCenterDetails starts");
		System.out.println("@@@@@@@@@@@@@@@@@@@"+editCenterRestModel);

		logger.info("Method : editCenterDetails ends");
		return manageCenterRestDao.editCenterDao(editCenterRestModel,userId);
	}
	
	@RequestMapping(value = "get-loc-center-list", method = { RequestMethod.GET })
	public List<DropDownModel> getLocCenterRestList() {
		logger.info("Method : getLocCenterRestList starts");
			System.out.println("#####");
		logger.info("Method : getLocCenterRestList ends");
		return manageCenterRestDao.getLocCenterRestList();
	}
	
	@RequestMapping(value = "get-exam-center-list", method = { RequestMethod.GET })
	public List<DropDownModel> getCenterRestList() {
		logger.info("Method : getCenterRestList starts");
			System.out.println("#####");
		logger.info("Method : getCenterRestList ends");
		return manageCenterRestDao.getExamCenterRestList();
	}
	
	//manage center add more part
	
	@RequestMapping(value = "rest-manage-center-addmore", method = { RequestMethod.GET })
	public JsonResponse<List<AddCenterAddMoreRestModel>> RestAddMoreList(@RequestParam String id)
	{
		logger.info("Method :RestAddMoreList starts");
		System.out.println("#####"+id);
		logger.info("Method :RestAddMoreList endss");
		return manageCenterRestDao.manageAddMoreDao(id);

	}
	
	@RequestMapping(value = "get-course-manage-list", method = { RequestMethod.GET })
	public List<DropDownModel> getManageCourseRestList() {
		logger.info("Method : getManageCourseRestList starts");
			System.out.println("#####");
		logger.info("Method : getCourgetManageCourseRestListseRestList ends");
		return manageCenterRestDao.getManageCourseList();
	}
	@RequestMapping(value = "get-shift-manage-list", method = { RequestMethod.GET })
	public List<DropDownModel> getManageShiftRestList() {
		logger.info("Method : getManageShiftRestList starts");
			System.out.println("#####");
		logger.info("Method : getManageShiftRestList ends");
		return manageCenterRestDao.getManageShiftList();
	}
	/*
	 * for Course list
	 */
	@RequestMapping(value = "getCourseId", method = { RequestMethod.GET })
	public List<DropDownModel> getCourseList() {
		logger.info("Method : getCourseList starts");
		
		logger.info("Method : getCourseList ends");

		return manageCenterRestDao.getCourseList();
	}
	@RequestMapping(value = "getShiftlist", method = { RequestMethod.GET })
	public List<DropDownModel> getShiftList() {
		logger.info("Method : getShiftList starts");
		
		logger.info("Method : getShiftList ends");

		return manageCenterRestDao.getShiftList();
	}
	@GetMapping(value = "get-manage-course-list")
	public JsonResponse<List<DropDownModel>> getManageCourseRestList(@RequestParam String id) {
		logger.info("Method : getCenterCourseRestList starts");

		logger.info("Method : getCenterCourseRestList ends");
		return manageCenterRestDao.getCourseRestCenterManageList(id);
	}
}
