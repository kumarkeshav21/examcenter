package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.CandidateListRestDao;
import nirmalya.aatithya.restmodule.lab.dao.CenterAllocationReportRestDao;
import nirmalya.aatithya.restmodule.lab.model.AdmitCardRestModel;
import nirmalya.aatithya.restmodule.lab.model.CandidateListRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationReportRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationRestModel;

@RestController
@RequestMapping(value = { "lab/" })
public class CenterAllocationReportRestController {
	Logger logger = LoggerFactory.getLogger(CenterAllocationReportRestController.class);

	@Autowired
	CenterAllocationReportRestDao centerAllocationReportRestDao;
	
	
	
	@GetMapping(value = "getStudentReport")
	public JsonResponse<List<CenterAllocationRestModel>> getStudentReport(@RequestParam String loc,@RequestParam String cent,@RequestParam String cour,@RequestParam Integer pageno) {
		logger.info("Method :getPatientHistoryMedicalCondition starts");
		
		
		logger.info("Method :getPatientHistoryMedicalCondition endss");
		return centerAllocationReportRestDao.getStudentReport(loc,cent,cour,pageno);
		
	}
	
	
	@RequestMapping(value = "get-all-loc-list", method = { RequestMethod.GET })
	public List<DropDownModel> getLocAlloRestList() {
		logger.info("Method : getLocAlloRestList starts");
			System.out.println("#####");
		logger.info("Method : getLocAlloRestList ends");
		return centerAllocationReportRestDao.getLocAlloList();
	}

	@GetMapping(value = "get-Center-all-List")
	public JsonResponse<List<DropDownModel>> getCenterReportList(@RequestParam String id) {
		logger.info("Method : getCenterReportList starts");

		logger.info("Method : getCenterReportList ends");
		return centerAllocationReportRestDao.getCenterReportList(id);
	}
	
	@GetMapping(value = "get-Course-all-List")
	public JsonResponse<List<DropDownModel>> getCourseReportedList(@RequestParam String id) {
		logger.info("Method : getCourseReportList starts");

		logger.info("Method : getCourseReportList ends");
		return centerAllocationReportRestDao.getCourseRestReportList(id);
	}
	@GetMapping(value = "get-applied-all-candi-List")
	public JsonResponse<List<CenterAllocationReportRestModel>> getAppliedStuList(@RequestParam String id) {
		logger.info("Method :getAppliedStuList starts");
		
		
		logger.info("Method :getAppliedStuList endss");
		return centerAllocationReportRestDao.getAppliedStudentList(id);
		
	}
	@GetMapping(value = "get-admit-card-rest")
	public JsonResponse<List<AdmitCardRestModel>> getRestAdmitCard(@RequestParam String roll) {
		logger.info("Method :getRestAdmitCard starts");
		
		
		logger.info("Method :getRestAdmitCard endss");
		return centerAllocationReportRestDao.getAdmitCardDao(roll);
		
	}
}
