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
import nirmalya.aatithya.restmodule.lab.model.CandidateListRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageCenterRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageInstructionRestModel;

@Repository
public class ManageCenterRestDao {
	Logger logger = LoggerFactory.getLogger(ManageCenterRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	@SuppressWarnings("unchecked")
	public JsonResponse<List<ManageCenterRestModel>> manageCenterDao(String userid,String userrole) {
		logger.info("Method : manageCenterDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<ManageCenterRestModel> list = new ArrayList<ManageCenterRestModel>();
		JsonResponse<List<ManageCenterRestModel>> resp = new JsonResponse<List<ManageCenterRestModel>>();
		try {

			
			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_center_view")
					.setParameter("userid", userid)
					.setParameter("userrole", userrole)
					.getResultList();

			for (Object[] m : x) {

				ManageCenterRestModel viewdemo = new ManageCenterRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7], m[8], m[9],m[10],m[11]);
				list.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(list);
		logger.info("Method : manageCenterDao ends");

		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> deleteCenter(String id,String userid) {

		logger.info("Method : deleteCenter Dao starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {

			List<Object[]> x= em.createNamedStoredProcedureQuery("delete_center")
					.setParameter("cid", id)
					.setParameter("uid", userid)
					.getResultList();

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

		logger.info("Method : deleteCenter Dao ends");
		return response;
	}
	
	/*
	 * edit Center details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> editCenterDao(ManageCenterRestModel editCenter,String userId){
		logger.info("Method : editCenterDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			
			String centerList = "";
			for (AddCenterAddMoreRestModel a : editCenter.getCenterDetails()) {
				centerList = centerList + "(centerid,\'" + a.getCourseId() + "\',\'" + a.getShiftid() + "\',\'" + a.getDate()
							+ "\',\'" + a.getfDate() + "\',\'" + a.gettDate() + "\',\'" + a.getNoOfSeats()+ "\'),";
				}
			centerList = centerList.substring(0, centerList.length() - 1);

			if (editCenter.getCenterCode() != null) {
				
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$"+editCenter);
				List<Object[]> x = em.createNamedStoredProcedureQuery("edit_center_details")
						.setParameter("uid", userId)
						.setParameter("sid", editCenter.getsId())
						.setParameter("centcode", editCenter.getCenterCode())
						.setParameter("centname", editCenter.getCenterName())
						.setParameter("locname", editCenter.getLocationName())
						.setParameter("examnm", editCenter.getExamType())
						.setParameter("addr", editCenter.getAddress())
						.setParameter("incharge", editCenter.getInChargeName())
						.setParameter("mob", editCenter.getMobile())
						.setParameter("email", editCenter.getEmail())
						.setParameter("stat", editCenter.getStatus())
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

		logger.info("Method : editCenterDao ends");
		return resp;
	}


	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getLocCenterRestList() {
		logger.info("Method : getLocList starts");

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

		logger.info("Method : getLocList ends");
		return getLocList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getExamCenterRestList() {
		logger.info("Method : getExamRestList starts");

		List<DropDownModel> getExamList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getExamList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("exam_list_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getExamList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getExamList);

		logger.info("Method : getExamRestList ends");
		return getExamList;
	}
	
	//manage data add more section
	@SuppressWarnings("unchecked")
	public JsonResponse<List<AddCenterAddMoreRestModel>> manageAddMoreDao(String id) {
		logger.info("Method : manageAddMoreDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<AddCenterAddMoreRestModel> list = new ArrayList<AddCenterAddMoreRestModel>();
		JsonResponse<List<AddCenterAddMoreRestModel>> resp = new JsonResponse<List<AddCenterAddMoreRestModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_center_addmore").setParameter("cid", id)
					.getResultList();

			for (Object[] m : x) {

				AddCenterAddMoreRestModel viewdemo = new AddCenterAddMoreRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6]);
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
	public List<DropDownModel> getManageCourseList() {
		logger.info("Method : getManageCourseList starts");

		List<DropDownModel> getCorList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getCorList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("center_course_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getCorList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getCorList);

		logger.info("Method : getManageCourseList ends");
		return getCorList;
	}
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getManageShiftList() {
		logger.info("Method : getManageShiftList starts");

		List<DropDownModel> getShiftList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getShiftList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("exam_shift_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getShiftList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getShiftList);

		logger.info("Method : getManageShiftList ends");
		return getShiftList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCourseList() {
		logger.info("Method : getCourseList starts");

		List<DropDownModel> getCorList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getCorList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_course_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getCorList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	 

		logger.info("Method : getCourseList ends");
		return getCorList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getShiftList() {
		logger.info("Method : getManageShiftList starts");

		List<DropDownModel> getShiftList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getShiftList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("exam_shift_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getShiftList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	    System.out.println("################"+getShiftList);

		logger.info("Method : getManageShiftList ends");
		return getShiftList;
	}
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCourseRestCenterManageList(String id) {
		logger.info("Method : getCourseRestCenterManageList starts");
		List<DropDownModel> jobList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			System.out.println(id+"@@@@@@@@@");
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("center_course_dropdown_list").setParameter("examId", id)
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
		logger.info("Method : getCourseRestCenterManageList ends");
		return resp;
	}
	

}
