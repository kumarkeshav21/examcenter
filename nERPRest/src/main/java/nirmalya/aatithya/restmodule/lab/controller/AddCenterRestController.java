package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.AddCenterRestDao;
import nirmalya.aatithya.restmodule.lab.model.AddCenterRestModel;

@RestController
@RequestMapping(value = { "lab/" })
public class AddCenterRestController {
	Logger logger = LoggerFactory.getLogger(AddCenterRestController.class);

	@Autowired
	AddCenterRestDao addCenterRestDao;
	
	/*
	 * Add center details
	 */

	@PostMapping(value = "rest-save-center-details")
	public JsonResponse<Object> addCenterDetails(@RequestBody AddCenterRestModel addCenterRestModel,@RequestParam String userId) {
		logger.info("Method : addCenterDetails starts");
		System.out.println("################"+addCenterRestModel);

		logger.info("Method : addCenterDetails ends");
		return addCenterRestDao.addCenterRestDetailsDao(addCenterRestModel,userId);
	}
	
	@RequestMapping(value = "get-loc-list", method = { RequestMethod.GET })
	public List<DropDownModel> getLocRestList() {
		logger.info("Method : getLocRestList starts");
			System.out.println("#####");
		logger.info("Method : getLocRestList ends");
		return addCenterRestDao.getLocList();
	}
	
	@RequestMapping(value = "get-course-list", method = { RequestMethod.GET })
	public List<DropDownModel> getCourseRestList(@RequestParam String id) {
		logger.info("Method : getCourseRestList starts");
			System.out.println("#####");
		logger.info("Method : getCourseRestList ends");
		return addCenterRestDao.getCourseList(id);
	}
	@RequestMapping(value = "get-shift-list", method = { RequestMethod.GET })
	public List<DropDownModel> getShiftRestList() {
		logger.info("Method : getShiftRestList starts");
			System.out.println("#####");
		logger.info("Method : getShiftRestList ends");
		return addCenterRestDao.getShiftList();
	}
	
	@RequestMapping(value = "get-center-exam-list", method = { RequestMethod.GET })
	public List<DropDownModel> getExamCenterRestList() {
		logger.info("Method : getExamCenterRestList starts");
			System.out.println("#####");
		logger.info("Method : getExamCenterRestList ends");
		return addCenterRestDao.getExamRestList();
	}
	
	/**********center course dropdown according to exxam********************/
	@GetMapping(value = "get-center-course-rest-list")
	public JsonResponse<List<DropDownModel>> getCenterCourseajaxRestList(@RequestParam String id) {
		logger.info("Method : getCenterCourseajaxRestList starts");

		logger.info("Method : getCenterCourseajaxRestList ends");
		return addCenterRestDao.getCenterCourseajaxRestList(id);
	}
	
	@GetMapping(value = "get-center-course-list")
	public JsonResponse<List<DropDownModel>> getCenterCourseRestList(@RequestParam String id) {
		logger.info("Method : getCenterCourseRestList starts");

		logger.info("Method : getCenterCourseRestList ends");
		return addCenterRestDao.getCourseRestCenterList(id);
	}


}
