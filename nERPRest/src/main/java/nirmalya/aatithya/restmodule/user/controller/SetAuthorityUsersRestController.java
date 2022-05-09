package nirmalya.aatithya.restmodule.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.SetAuthorityDao;
import nirmalya.aatithya.restmodule.user.model.RestProcessMasterModel;

@RestController
@RequestMapping(value = "user/")
public class SetAuthorityUsersRestController {

	@Autowired
	SetAuthorityDao setAuthorityDao;

	Logger logger = LoggerFactory.getLogger(SetAuthorityUsersRestController.class);
	
	@GetMapping(value = "getProcessMasterList")
	public ResponseEntity<JsonResponse<List<RestProcessMasterModel>>> getProcessMasterList() {
		logger.info("Method : getProcessMasterList starts");
		
		logger.info("Method : getProcessMasterList ends");
		return setAuthorityDao.getProcessMasterList();
	}
	
	@PostMapping(value = "saveProcessMaster")
	public ResponseEntity<JsonResponse<Object>> saveProcessMaster(@RequestBody RestProcessMasterModel id) {
		logger.info("Method : saveProcessMaster starts");
		
		logger.info("Method : saveProcessMaster ends");
		return setAuthorityDao.saveProcessMaster(id);
	}
	
}
