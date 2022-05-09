package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.AddInstructionRestDao;
import nirmalya.aatithya.restmodule.lab.dao.CenterAllocationRestDao;
import nirmalya.aatithya.restmodule.lab.model.AddInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationReportRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationRestModel;
@RestController
@RequestMapping(value = { "lab/" })

public class CenterAllocationRestController {
	Logger logger = LoggerFactory.getLogger(CenterAllocationRestController.class);

	@Autowired
	CenterAllocationRestDao centerAllocationRestDao;
	
	/*
	 * Add center details
	 */

	
	  @PostMapping(value = "rest-center-allocation-details") public
	  JsonResponse<Object> centerAllocation(Model model,@RequestBody
	  CenterAllocationRestModel centerAllocationRestModel,HttpSession session) {
	  logger.info("Method : centerAllocation starts");
	  System.out.println("################"+centerAllocationRestModel);
	  
	  logger.info("Method : centerAllocation ends"); return
	  centerAllocationRestDao.centerAllocationRestDao(centerAllocationRestModel); }
	 
	
	
	 
	
	@RequestMapping(value = "get-examination-list", method = { RequestMethod.GET })
	public List<DropDownModel> getExaminationRestList() {
		logger.info("Method : getExamRestList starts");
			System.out.println("#####");
		logger.info("Method : getExamRestList ends");
		return centerAllocationRestDao.getExamList();
	}
	
	
	  @RequestMapping(value = "get-student-list", method = { RequestMethod.GET })
	  public List<CenterAllocationRestModel> getStudentList() {
	  logger.info("Method : getStudentList starts"); System.out.println("#####");
	  logger.info("Method : getStudentList ends"); return
	 centerAllocationRestDao.getStudentList(); 
	  }
	 
	
	@RequestMapping(value = "get-candidate-list", method = { RequestMethod.GET })
	public List<CenterAllocationRestModel> getCandidateList() {
		logger.info("Method : getCandidateList starts");
			System.out.println("#####");
		logger.info("Method : getCandidateList ends");
		return centerAllocationRestDao.getCandidateList();
	}

	@GetMapping(value = "getCourseList")
	public JsonResponse<List<DropDownModel>> getCourseList(@RequestParam String id) {
		logger.info("Method : getCourseList starts");

		logger.info("Method : getCourseList ends");
		return centerAllocationRestDao.getCourseList(id);
	}
	@GetMapping(value = "get-allocated-roll-code")
	public JsonResponse<List<CenterAllocationRestModel>> getAppliedStuList(@RequestParam String id) {
		logger.info("Method :getAppliedStuList starts");
		
		
		logger.info("Method :getAppliedStuList endss");
		return centerAllocationRestDao.getRollCodeRestDao(id);
		
	}
}
