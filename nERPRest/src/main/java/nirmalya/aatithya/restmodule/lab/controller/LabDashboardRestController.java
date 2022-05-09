package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.LabDashboardRestDao;
import nirmalya.aatithya.restmodule.lab.model.LabDashboardRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageCenterRestModel;

@RestController
@RequestMapping(value = { "lab/" })
public class LabDashboardRestController {
	Logger logger = LoggerFactory.getLogger(LabDashboardRestController.class);

	@Autowired
	LabDashboardRestDao labDashboardRestDao;
	
	/*
	 * card 
	 */
	@RequestMapping(value = "labdashboard-card", method = { RequestMethod.GET })
	public List<LabDashboardRestModel> getDashboardCardRest(@RequestParam String userId)
	{
		logger.info("Method :getDashboardCardRest starts");
		
		logger.info("Method :getDashboardCardRest endss");
		return labDashboardRestDao.getCardRestDao(userId);

	}
	
	
	
	
	@RequestMapping(value = "rest-viewPiechartReport", method = { RequestMethod.GET })
	public List<DropDownModel> viewPiechartRestReport() {
		logger.info("Method : viewPiechartRestReport starts");
			System.out.println("#####");
		logger.info("Method : viewPiechartRestReport ends");
		return labDashboardRestDao.ViewPieChartReportDao();
	}
	
	@RequestMapping(value = "rest-viewBarchartReport", method = { RequestMethod.GET })
	public List<DropDownModel> viewBarchartRestReport() {
		logger.info("Method : viewBarchartRestReport starts");
			System.out.println("#####");
		logger.info("Method : viewBarchartRestReport ends");
		return labDashboardRestDao.ViewBarChartReportDao();
	}
	

}
