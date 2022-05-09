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

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.CenterAllocatedRestDao;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocatedRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class CenterAllocatedRestController {
	
	Logger logger = LoggerFactory.getLogger(CenterAllocationRestController.class);

	@Autowired
	CenterAllocatedRestDao centerAllocatedRestDao;
	
	@GetMapping(value = "rest-centerallocationdetails")
	public JsonResponse<List<CenterAllocatedRestModel>> centerRestAllocation(@RequestParam String exam,@RequestParam String roll,@RequestParam String ptype,@RequestParam String stat) {
		logger.info("Method :centerRestAllocation starts");

		logger.info("Method :centerRestAllocation endss");
		return centerAllocatedRestDao.centerAllocatedRestDao(exam,roll,ptype,stat);

	}
	
	@GetMapping(value = "rest-alloCenterViewRest")
	public JsonResponse<List<CenterAllocatedRestModel>> alloCenterViewRest() {
		logger.info("Method :centerRestAllocation starts");

		logger.info("Method :centerRestAllocation endss");
		return centerAllocatedRestDao.fetchDataRestDao();

	}
	@RequestMapping(value = "get-candidatereord", method = { RequestMethod.GET })
	public List<CenterAllocatedRestModel> getCandidateRecord() {
		logger.info("Method : getCandidateRecord starts");
			System.out.println("#####");
		logger.info("Method : getCandidateRecord ends");
		return centerAllocatedRestDao.getCandidateRecordDao();
	}


}
