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
import nirmalya.aatithya.restmodule.lab.model.AddCenterAddMoreRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddExamRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddMoreExamCourseRestModel;

@Repository

public class AddExamRestDao {
	Logger logger = LoggerFactory.getLogger(AddExamRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * add center details(Medication)
	 */	
	public JsonResponse<Object> addExamRestDetailsDao(AddExamRestModel addExam,String userId) {
		logger.info("Method : addExamRestDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			String courseList = "";
			for (AddMoreExamCourseRestModel a : addExam.getCourseNameList()) {
				courseList = courseList + "(eid,\'" + a.getCourseName() + "\'),";
				}
			courseList = courseList.substring(0, courseList.length() - 1);

			System.out.println(courseList);
			if (addExam.getExamName() != null) {

				em.createNamedStoredProcedureQuery("add_exam_details")
						.setParameter("uid", userId)
						.setParameter("examName", addExam.getExamName())
						.setParameter("examstatus", addExam.getStatus())
						.setParameter("authoName", addExam.getAuthoName())
						.setParameter("mobilenumber", addExam.getMobileNo())
						.setParameter("email", addExam.getEmailId())
						.setParameter("logo", addExam.getUploadLogo())
						.setParameter("courseList",courseList)
						
						
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

		logger.info("Method : addExamRestDetailsDao ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getallExamlist() {
		logger.info("Method : getallExamlist starts");

		List<DropDownModel> alernameList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("exam_list_dropdown_list")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				alernameList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getallExamlist ends");
		return alernameList;
	}

	

}
