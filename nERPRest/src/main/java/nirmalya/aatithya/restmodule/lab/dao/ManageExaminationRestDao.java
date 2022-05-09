package nirmalya.aatithya.restmodule.lab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.AddCenterAddMoreRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddMoreExamCourseRestModel;
import nirmalya.aatithya.restmodule.lab.model.EditExaminationRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageCenterRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageExaminationRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageInstructionRestModel;

@Repository
public class ManageExaminationRestDao {
	Logger logger = LoggerFactory.getLogger(ManageExaminationRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	@SuppressWarnings("unchecked")
	public JsonResponse<List<ManageExaminationRestModel>> manageExaminationnDao(String userid,String userrole) {
		logger.info("Method : manageExaminationnDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<ManageExaminationRestModel> list = new ArrayList<ManageExaminationRestModel>();
		JsonResponse<List<ManageExaminationRestModel>> resp = new JsonResponse<List<ManageExaminationRestModel>>();
		try {

			
			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_examination_view").setParameter("userid", userid)
					.setParameter("userrole",userrole)
					.getResultList();

			for (Object[] m : x) {

				ManageExaminationRestModel viewdemo = new ManageExaminationRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6]);
				list.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(list);
		logger.info("Method : manageExaminationnDao ends");

		return resp;
	}
	
/***********************************edit ***********************/
	
	/*
	 * edit exam details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> editExaminationDao(EditExaminationRestModel editExam,String userId) {
		logger.info("Method : editExaminationDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			
			String centerList = "";
			for (AddMoreExamCourseRestModel a : editExam.getCenterDetails()) {
				centerList = centerList + "(centerid,\'" + a.getCourseName() + "\'),";
				}
			centerList = centerList.substring(0, centerList.length() - 1);

			
			
			
			
			if (editExam.getsId() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("edit_examination_details")
						.setParameter("uid", userId)
						.setParameter("sid", editExam.getsId())
						.setParameter("ename", editExam.getExamName())
						.setParameter("stat", editExam.getStatus())
						.setParameter("autho", editExam.getAuthority())
						.setParameter("mobile", editExam.getMobile())
						.setParameter("email", editExam.getEmail())
						.setParameter("image", editExam.getUploadLogo())
						.setParameter("centerList", centerList)
						.getResultList();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println("err" + err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		logger.info("Method : editExaminationDao ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> deleteExamination(String id,String userId) {

		logger.info("Method : deleteExamination Dao starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println("=====>>>"+id);

		try {

			List<Object[]> x= em.createNamedStoredProcedureQuery("delete_examination")
					.setParameter("uid", userId)
					.setParameter("eid", id).getResultList();

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : deleteExamination Dao ends");
		return response;
	}
	
	
	
	

	/*
	 * @SuppressWarnings("unchecked") public JsonResponse<List<DropDownModel>>
	 * getManageExamDao(String id) {
	 * logger.info("Method : getManageExamDao starts"); List<DropDownModel> jobList
	 * = new ArrayList<DropDownModel>(); JsonResponse<List<DropDownModel>> resp =
	 * new JsonResponse<List<DropDownModel>>(); System.out.println(id+"@@@@@@@@@");
	 * try { List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("manage_course_drop_down").setParameter(
	 * "eid", id) .getResultList(); for (Object[] m : x) {
	 * 
	 * DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
	 * jobList.add(dropDownModel); }
	 * 
	 * resp.setBody(jobList);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * System.out.println("pppppppppppppppppp"+resp);
	 * logger.info("Method : getManageExamDao ends"); return resp; }
	 */
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getManageExamDao(String id) {
		logger.info("Method : getManageExamDao starts");

		List<DropDownModel> getCorList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getCorList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_course_drop_down").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getCorList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	 

		logger.info("Method : getManageExamDao ends");
		return getCorList;
	}
	
	//manage data add more section
		@SuppressWarnings("unchecked")
		public JsonResponse<List<AddMoreExamCourseRestModel>> manageExamCourseDao(String id) {
			logger.info("Method : manageAddMoreDao starts");
//			List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
			List<AddMoreExamCourseRestModel> list = new ArrayList<AddMoreExamCourseRestModel>();
			JsonResponse<List<AddMoreExamCourseRestModel>> resp = new JsonResponse<List<AddMoreExamCourseRestModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("manage_course_addmore").setParameter("eid", id)
						.getResultList();

				for (Object[] m : x) {

					AddMoreExamCourseRestModel viewdemo = new AddMoreExamCourseRestModel(m[0],m[1]);
					list.add(viewdemo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(list);
			System.out.println("@@@@@"+resp);
			logger.info("Method : manageAddMoreDao ends");
			

			return resp;
		}
		
		@SuppressWarnings("unchecked")
		public List<DropDownModel> getManageCourseList(String id) {
			logger.info("Method : getManageCourseList starts");

			List<DropDownModel> getCorList = new ArrayList<DropDownModel>();
			System.out.println("@@@@@"+getCorList);

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("manage_exam_course_drop_down")
						.setParameter("eid", id).getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
					getCorList.add(dropDownModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		 System.out.println("##=====>>>>>>"+getCorList);

			logger.info("Method : getManageCourseList ends");
			return getCorList;
		}
	
	


}
