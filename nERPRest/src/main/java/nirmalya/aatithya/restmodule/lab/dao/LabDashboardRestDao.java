package nirmalya.aatithya.restmodule.lab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.LabDashboardRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageCenterRestModel;

@Repository
public class LabDashboardRestDao {

	Logger logger = LoggerFactory.getLogger(LabDashboardRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	public List<LabDashboardRestModel> getCardRestDao(String userId) {
		logger.info("Method : getCardRestDao Dao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<LabDashboardRestModel> resp = new ArrayList<LabDashboardRestModel>();
		System.out.println("@@@@@@@@@kkkkkkkkkk"+ userId.getClass().getName());
		
		try {

		         @SuppressWarnings("unchecked")
				List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_card").
				 setParameter("userid", userId)
				.getResultList();
			
                for (Object[] m : x) {		
				
                	LabDashboardRestModel viewdemo = new LabDashboardRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10],
                			m[11], m[12], m[13], m[14], m[15], m[16], m[17], m[18], m[19], m[20], m[21], m[22],m[23]);
				resp.add(viewdemo);
			}
                
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("==>"+resp);
		logger.info("Method : getCardRestDao Dao ends");

		return resp;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> ViewPieChartReportDao() {
		logger.info("Method : ViewPieChartReportDao starts");

		List<DropDownModel> getCorList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getCorList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("pie_chart").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getCorList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getCorList);

		logger.info("Method : ViewPieChartReportDao ends");
		return getCorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> ViewBarChartReportDao() {
		logger.info("Method : ViewBarChartReportDao starts");

		List<DropDownModel> getCorList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getCorList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("bar_chart").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getCorList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getCorList);

		logger.info("Method : ViewBarChartReportDao ends");
		return getCorList;
	}
	
}
