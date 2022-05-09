package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.AllocatedCenterRestDao;
import nirmalya.aatithya.restmodule.lab.model.AllocatedCenterRestModel;

@RestController
@RequestMapping(value = { "lab/" })

public class AllocatedCenterRestControlller {
	
	Logger logger = LoggerFactory.getLogger(AllocatedCenterRestControlller.class);

	@Autowired
	AllocatedCenterRestDao allocatedCenterRestDao;
	
	
	
	@GetMapping(value = "get-allocated-center")
	public JsonResponse<List<AllocatedCenterRestModel>> getAllocatedCenterRest(@RequestParam String loc,@RequestParam String cent) {
		logger.info("Method :getAllocatedCenterRest starts");
		
		
		logger.info("Method :getAllocatedCenterRest endss");
		return allocatedCenterRestDao.getAllocattedCenterDao(loc,cent);
		
	}
	

}
