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
import nirmalya.aatithya.restmodule.lab.model.AddInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationReportRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationRestModel;

@Repository
public class CenterAllocationRestDao {
	Logger logger = LoggerFactory.getLogger(CenterAllocationRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * center allocation
	 */
	 @SuppressWarnings({ "unchecked", "unused" }) public JsonResponse<Object>
	  centerAllocationRestDao(CenterAllocationRestModel addCenter) {
	 logger.info("Method : centerAllocationRestDao starts"); JsonResponse<Object>
	 resp = new JsonResponse<Object>(); System.out.println(addCenter); try {
	  System.out.println("exam"+addCenter.getExamName());
	  System.out.println("status"+addCenter.getStatus());
	  System.out.println("status"+addCenter.getRollCode());
	  
	  if (addCenter.getExamName() != null) {
	  
	  List<Object[]> x =
	  em.createNamedStoredProcedureQuery("center_allocation_list")
	 .setParameter("examname",addCenter.getExamName())
	  //.setParameter("coursename",addCenter.getCourseName())
	  .setParameter("stat",addCenter.getStatus()).getResultList();
	  
	  }
	  
	  } catch (Exception e) { try { String[] err = serverDao.errorProcedureCall(e);
	  System.out.println("err" + err); resp.setCode(err[0]);
	 resp.setMessage(err[1]);
	  
	  } catch (Exception e1) { e1.printStackTrace(); } }
	 
	  logger.info("Method : centerAllocationRestDao ends"); return resp; }
	 
	
	
	
	/*****course drop down******/
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getExamList() {
		logger.info("Method : getCourseList starts");

		List<DropDownModel> getExaList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getExaList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("allocation_exam_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getExaList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getExaList);

		logger.info("Method : getCourseList ends");
		return getExaList;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCourseList(String id) {
		logger.info("Method : getCourseList starts");
		List<DropDownModel> jobList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			System.out.println(id+"@@@@@@@@@");
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("centerallocation_course_list").setParameter("eid", id)
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
		logger.info("Method : getCourseList ends");
		return resp;
	}

	
	 @SuppressWarnings("unchecked") public List<CenterAllocationRestModel>
	 getStudentList() { logger.info("Method : getStudentList starts");
	 
	  List<CenterAllocationRestModel> getStudentList = new
	  ArrayList<CenterAllocationRestModel>();
	  System.out.println("@@@@@"+getStudentList);
	  
	  try { List<Object[]> x =
	  em.createNamedStoredProcedureQuery("candidate_allocation_view").getResultList
	  (); for (Object[] m : x) { CenterAllocationRestModel dropDownModel = new
	  CenterAllocationRestModel(m[0], m[1], m[2], m[3], m[4],m[5]);
	  getStudentList.add(dropDownModel); }
	  
	  } catch (Exception e) { e.printStackTrace(); }
	 System.out.println("@@@@@"+getStudentList);
	  
	 logger.info("Method : getCourseList ends");
	 return getStudentList; }
	 
	@SuppressWarnings("unchecked")
	public List<CenterAllocationRestModel> getCandidateList() {
		logger.info("Method : getCandidateList starts");

		List<CenterAllocationRestModel> getCandidateList = new ArrayList<CenterAllocationRestModel>();
		System.out.println("@@@@@"+getCandidateList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("candidate_allocation_count").getResultList();
			for (Object[] m : x) {
				CenterAllocationRestModel dropDownModel = new CenterAllocationRestModel(m[0], m[1], m[2], m[3], m[4]);
				getCandidateList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getCandidateList);

		logger.info("Method : getCourseList ends");
		return getCandidateList;
	}
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<CenterAllocationRestModel>> getRollCodeRestDao(String id) {
		logger.info("Method : getRollCodeRestDao Dao starts");

		List<CenterAllocationRestModel> getAllemployee = new ArrayList<CenterAllocationRestModel>();
		JsonResponse<List<CenterAllocationRestModel>> resp = new JsonResponse<List<CenterAllocationRestModel>>();
		System.out.println("@@@@@@"+id);
		System.out.println("@@@@@@"+resp);
		

		try {			
			List<Object[]> x = em.createNamedStoredProcedureQuery("roll_code_view")
					.setParameter("ex", id)
					.getResultList();

			for (Object[] m : x) {		
			//	System.out.println("getAllemployee" + getAllemployee);
				CenterAllocationRestModel viewdemo = new CenterAllocationRestModel(m[0],m[1]);		
				System.out.println("@@@@@@@@@@@@@"+viewdemo);
				getAllemployee.add(viewdemo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.setBody(getAllemployee);
		System.out.println("=======>>>"+resp);
		logger.info("Method : getRollCodeRestDao  Dao ends");

		return resp;
	}
	


}
