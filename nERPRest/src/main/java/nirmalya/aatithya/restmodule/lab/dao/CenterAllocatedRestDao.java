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
import nirmalya.aatithya.restmodule.lab.model.CenterAllocatedRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationRestModel;

@Repository
public class CenterAllocatedRestDao {
	Logger logger = LoggerFactory.getLogger(CenterAllocatedRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<CenterAllocatedRestModel>> centerAllocatedRestDao(String exam, String roll,String ptype,String stat) {
		logger.info("Method : centerAllocatedRestDao Dao starts");

		List<CenterAllocatedRestModel> getAllemployee = new ArrayList<CenterAllocatedRestModel>();
		JsonResponse<List<CenterAllocatedRestModel>> resp = new JsonResponse<List<CenterAllocatedRestModel>>();

		System.out.println("=====>>" + exam);
		System.out.println("=====>>" + roll);
		System.out.println("=====>>" + ptype);
		System.out.println("=====>>" + stat);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("center_allocated_list")
					.setParameter("exam", exam)
					.setParameter("stat", stat)
					.setParameter("roll", roll)
					.setParameter("ptype", ptype)
					.getResultList();

			
			/*
			 * for (Object[] m : x) { CenterAllocatedRestModel viewdemo = new
			 * CenterAllocatedRestModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7]);
			 * getAllemployee.add(viewdemo);
			 * 
			 * }
			 */
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.setBody(getAllemployee);
		if (resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		// System.out.println("====>"+getAllemployee); resp.setBody(getAllemployee);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@" + resp);
		logger.info("Method : centerAllocatedRestDao  Dao ends");

		return resp;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<CenterAllocatedRestModel>> fetchDataRestDao() {
		logger.info("Method : fetchDataRestDao Dao starts");

		List<CenterAllocatedRestModel> getAllemployee = new ArrayList<CenterAllocatedRestModel>();
		JsonResponse<List<CenterAllocatedRestModel>> resp = new JsonResponse<List<CenterAllocatedRestModel>>();

		

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("fetch_center_details").getResultList();

			
			
			  for (Object[] m : x) { CenterAllocatedRestModel viewdemo = new
			  CenterAllocatedRestModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7], m[8]);
			  getAllemployee.add(viewdemo);
			  
			 }
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.setBody(getAllemployee);
		if (resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Success");
		} else {
			resp.setMessage("Unsuccess");
		}
		// System.out.println("====>"+getAllemployee); resp.setBody(getAllemployee);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@" + resp);
		logger.info("Method : fetchDataRestDao  Dao ends");

		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CenterAllocatedRestModel> getCandidateRecordDao() {
		logger.info("Method : getCandidateRecordDao starts");

		List<CenterAllocatedRestModel> getCandidateList = new ArrayList<CenterAllocatedRestModel>();
		System.out.println("@@@@@"+getCandidateList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("allocated_candi_record").getResultList();
			for (Object[] m : x) {
				CenterAllocatedRestModel dropDownModel = new CenterAllocatedRestModel(m[0], m[1], m[2], m[3], m[4]);
				getCandidateList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getCandidateList);

		logger.info("Method : getCandidateRecordDao ends");
		return getCandidateList;
	}
		

}
