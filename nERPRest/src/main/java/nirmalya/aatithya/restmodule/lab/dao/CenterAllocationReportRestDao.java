
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
import nirmalya.aatithya.restmodule.lab.model.AdmitCardRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationReportRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationRestModel;

@Repository
public class CenterAllocationReportRestDao {
	Logger logger = LoggerFactory.getLogger(CenterAllocationReportRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<CenterAllocationRestModel>> getStudentReport(String loc,String cent,String cour,Integer pageno) {
		logger.info("Method : getStudentReport Dao starts");

		List<CenterAllocationRestModel> getAllemployee = new ArrayList<CenterAllocationRestModel>();
		JsonResponse<List<CenterAllocationRestModel>> resp = new JsonResponse<List<CenterAllocationRestModel>>();
		System.out.println("location id#######"+loc);
		System.out.println("center id##"+cent);
		System.out.println("course id####"+cour);
		System.out.println("page number##"+pageno);

		try {			
			List<Object[]> x = em.createNamedStoredProcedureQuery("search_center_allocation")
					.setParameter("loc", loc)
					.setParameter("cent", cent)
					.setParameter("cours", cour)
					.setParameter("pageno", pageno).getResultList();

			for (Object[] m : x) {		
			//	System.out.println("getAllemployee" + getAllemployee);
				CenterAllocationRestModel viewdemo = new CenterAllocationRestModel(m[0], m[1], m[2], m[3], m[4],m[5], m[6]);		
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
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getLocAlloList() {
		logger.info("Method : getLocAlloList starts");

		List<DropDownModel> getLocList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getLocList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("exam_loc_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getLocList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getLocList);

		logger.info("Method : getLocAlloList ends");
		return getLocList;
	}
	
/*****center drop down*/
	

	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCenterReportList(String id) {
		logger.info("Method : getCenterReportList starts");
		List<DropDownModel> jobList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			System.out.println(id+"@@@@@@@@@");
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("center_list_drop_down").setParameter("eid", id)
					.getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jobList.add(dropDownModel);
			}

			resp.setBody(jobList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("pppppppppppppppppp"+resp);
		logger.info("Method : getCenterReportList ends");
		return resp;
	}

/*****course drop down*/
	

	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCourseRestReportList(String id) {
		logger.info("Method : getCourseRestReportList starts");
		List<DropDownModel> jobList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			System.out.println(id+"@@@@@@@@@");
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("course_list_drop_down").setParameter("eid", id)
					.getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jobList.add(dropDownModel);
			}

			resp.setBody(jobList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("pppppppppppppppppp"+resp);
		logger.info("Method : getCourseRestReportList ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CenterAllocationReportRestModel>> getAppliedStudentList(String id) {
		logger.info("Method : getAppliedStudentList Dao starts");

		List<CenterAllocationReportRestModel> getAllemployee = new ArrayList<CenterAllocationReportRestModel>();
		JsonResponse<List<CenterAllocationReportRestModel>> resp = new JsonResponse<List<CenterAllocationReportRestModel>>();
		
		

		try {			
			List<Object[]> x = em.createNamedStoredProcedureQuery("candidate_applied_number")
					.setParameter("loc", id)
					.getResultList();

			for (Object[] m : x) {		
			//	System.out.println("getAllemployee" + getAllemployee);
				CenterAllocationReportRestModel viewdemo = new CenterAllocationReportRestModel(m[0], m[1]);		
				System.out.println("@@@@@@@@@@@@@"+viewdemo);
				getAllemployee.add(viewdemo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : getAppliedStudentList  Dao ends");

		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<AdmitCardRestModel>> getAdmitCardDao(String roll) {
		logger.info("Method : getAdmitCardDao Dao starts");

		List<AdmitCardRestModel> getAllemployee = new ArrayList<AdmitCardRestModel>();
		JsonResponse<List<AdmitCardRestModel>> resp = new JsonResponse<List<AdmitCardRestModel>>();
		System.out.println("####===========>>>>>>"+roll);
		
		

		try {			
			List<Object[]> x = em.createNamedStoredProcedureQuery("candidate_admit_card")
					.setParameter("roll", roll)
					.getResultList();

			for (Object[] m : x) {		
			//	System.out.println("getAllemployee" + getAllemployee);
				AdmitCardRestModel viewdemo = new AdmitCardRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16]);		
				System.out.println("@@@@@@@@@@@@@"+viewdemo);
				getAllemployee.add(viewdemo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : getAdmitCardDao  Dao ends");

		return resp;
	}


}
