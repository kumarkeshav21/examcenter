package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.CandidateListRestDao;
import nirmalya.aatithya.restmodule.lab.model.CandidateListRestModel;

@RestController
@RequestMapping(value = { "lab/" })
public class CandidateListRestController {
	Logger logger = LoggerFactory.getLogger(CandidateListRestController.class);

	@Autowired
	CandidateListRestDao candidateListRestDao;
	
	
	
	@RequestMapping(value = "rest-candidatelist", method = { RequestMethod.GET })
	public JsonResponse<List<CandidateListRestModel>> RestCandidateList(@RequestParam String userid)
	{
		logger.info("Method :RestCandidateList starts");
		
		logger.info("Method :RestCandidateList endss");
		return candidateListRestDao.candidateListDao(userid);

	}
	
	

}
