package nirmalya.aatithya.restmodule.lab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.AllocatedCenterRestModel;

@Repository
public class AllocatedCenterRestDao {
	Logger logger = LoggerFactory.getLogger(CenterAllocationReportRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<AllocatedCenterRestModel>> getAllocattedCenterDao(String loc,String cent) {
		logger.info("Method : getStudentReport Dao starts");

		List<AllocatedCenterRestModel> getAllemployee = new ArrayList<AllocatedCenterRestModel>();
		JsonResponse<List<AllocatedCenterRestModel>> resp = new JsonResponse<List<AllocatedCenterRestModel>>();
		System.out.println("location id#######"+loc);
		System.out.println("center id##"+cent);
		
		try {			
			List<Object[]> x = em.createNamedStoredProcedureQuery("search_allocated_center")
					.setParameter("loc", loc)
					.setParameter("cent", cent)
					.getResultList();

			for (Object[] m : x) {		
			//	System.out.println("getAllemployee" + getAllemployee);
				AllocatedCenterRestModel viewdemo = new AllocatedCenterRestModel(m[0], m[1], m[2], m[3], m[4],m[5]);		
				System.out.println("@@@@@@@@@@@@@"+viewdemo);
				getAllemployee.add(viewdemo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+resp);
		logger.info("Method : getStudentReport  Dao ends");

		return resp;
	}
	

}
