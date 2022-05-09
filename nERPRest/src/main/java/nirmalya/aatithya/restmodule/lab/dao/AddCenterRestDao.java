package nirmalya.aatithya.restmodule.lab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.AddCenterAddMoreRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddCenterRestModel;

@Repository

public class AddCenterRestDao {
	Logger logger = LoggerFactory.getLogger(AddCenterRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * add center details(Medication)
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public JsonResponse<Object> addCenterRestDetailsDao(AddCenterRestModel addCenterRestModel,String userId) {
		logger.info("Method : addCenterDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println("addCenterRestModel" + addCenterRestModel);
		try {

			String centerList = "";
			for (AddCenterAddMoreRestModel a : addCenterRestModel.getCenterDetails()) {
				centerList = centerList + "(centerid,\'" + a.getCourseId() + "\',\'" + a.getShiftid() + "\',\'" + a.getDate()
							+ "\',\'" + a.getfDate() + "\',\'" + a.gettDate() + "\',\'" + a.getNoOfSeats()+ "\'),";
				}
			centerList = centerList.substring(0, centerList.length() - 1);

			System.out.println(centerList);
			
			if (addCenterRestModel.getCenterCode() != null) {
				List<Object[]> x = em.createNamedStoredProcedureQuery("add_center_details")
						.setParameter("uid", userId)
						.setParameter("centerCode", addCenterRestModel.getCenterCode())
						.setParameter("centerName", addCenterRestModel.getCenterName())
						.setParameter("locationName", addCenterRestModel.getLocationName())
						.setParameter("examtype", addCenterRestModel.getExamType())
						.setParameter("centerAddress", addCenterRestModel.getCenterAddress())
						.setParameter("status", addCenterRestModel.getStatus())
						.setParameter("remarks", addCenterRestModel.getRemarks())
						.setParameter("inchargeName", addCenterRestModel.getInchargeName())
						.setParameter("mobileNo", addCenterRestModel.getMobileNo())
						.setParameter("emailId", addCenterRestModel.getEmailId())
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

		logger.info("Method : addCenterDetailsDao ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getLocList() {
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
	public List<DropDownModel> getCourseList(String id) {
		logger.info("Method : getCourseList starts");

		List<DropDownModel> getCorList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getCorList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("center_course_dropdown_list").setParameter("examId", id).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getCorList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getCorList);

		logger.info("Method : getCourseList ends");
		return getCorList;
	}
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getShiftList() {
		logger.info("Method : getCourseList starts");

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

		logger.info("Method : getCourseList ends");
		return getShiftList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getExamRestList() {
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
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCourseRestCenterList(String id) {
		logger.info("Method : getCourseRestCenterList starts");
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
		logger.info("Method : getCourseRestCenterList ends");
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCenterCourseajaxRestList(String id) {
		logger.info("Method : getCenterCourseajaxRestList starts");
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
		logger.info("Method : getCenterCourseajaxRestList ends");
		return resp;
	}

	
}
