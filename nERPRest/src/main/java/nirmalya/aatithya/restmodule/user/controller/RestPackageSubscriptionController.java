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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.dao.PackageSubscriptionDao;
import nirmalya.aatithya.restmodule.user.model.RestPackageSubscriptionModel;


@RestController
@RequestMapping(value = "user/")
public class RestPackageSubscriptionController {
	
Logger logger = LoggerFactory.getLogger(RestPackageSubscriptionController.class);
	
	@Autowired
	PackageSubscriptionDao packageSubscriptionDao;
	
	//country list
	
		@GetMapping(value = "getpackagelist")
		public List<DropDownModel> packageList() {
			logger.info("Method : packageList starts");

			logger.info("Method : packageList ends");
			return packageSubscriptionDao.packageList();
		}
		
	//	get package amount	
		
		@GetMapping(value="getpackageamount")
		public JsonResponse<List<RestPackageSubscriptionModel>> getPackageAmount(@RequestParam String packageName){
			logger.info("Method : getPackageAmount starts");
			
			logger.info("Method : getPackageAmount ends");
			return packageSubscriptionDao.getPackageAmount(packageName);
		}
	
	//save package details
		
	/*
	 * @PostMapping(value = "savepackagedetails") public
	 * ResponseEntity<JsonResponse<RestPackageSubscriptionModel>>
	 * savePackageDetails(
	 * 
	 * @RequestBody List<RestPackageSubscriptionModel> deatails) {
	 * logger.info("Method : savePackageDetails starts");
	 * 
	 * logger.info("Method : savePackageDetails ends"); return
	 * packageSubscriptionDao.savePackageDetails(leave); }
	 */
		
		
		@PostMapping(value="savepackagedetails")
		public JsonResponse<Object> savePackageDetails(@RequestBody RestPackageSubscriptionModel details){
			logger.info("Method : savePackageDetails starts");
			
			logger.info("Method : savePackageDetails ends");
			return packageSubscriptionDao.savePackageDetails(details);
		}
	
}

