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
import nirmalya.aatithya.restmodule.lab.model.AddExamRestModel;
import nirmalya.aatithya.restmodule.lab.model.AddInstructionRestModel;

@Repository
public class AddInstructionRestDao {
	Logger logger = LoggerFactory.getLogger(AddInstructionRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * add center details(Medication)
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> addInstructionRestDetailsDao(AddInstructionRestModel addInst,String userId) {
		logger.info("Method : addInstructionRestDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (addInst.getInstruction() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("add_instruction_details")
						.setParameter("uid", userId)
						.setParameter("i_exams", addInst.getExamType())
						.setParameter("i_instruction", addInst.getInstruction())
						.setParameter("i_position", addInst.getPosition())
						.setParameter("i_status", addInst.getStatus()).getResultList();

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

		logger.info("Method : addInstructionRestDetailsDao ends");
		return resp;
	}
	
	/*
	 * @SuppressWarnings("unchecked") public List<DropDownModel> getallExamlist() {
	 * logger.info("Method : getallExamlist starts");
	 * 
	 * List<DropDownModel> alernameList = new ArrayList<DropDownModel>();
	 * 
	 * try { List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("exam_list_dropdown_list")
	 * .getResultList();
	 * 
	 * for (Object[] m : x) { DropDownModel dropDownModel = new
	 * DropDownModel(m[0].toString(), m[1]); alernameList.add(dropDownModel); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * System.out.println("@@@@@@@@@@"+alernameList) ;
	 * logger.info("Method : getallExamlist ends"); return alernameList; }
	 */
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getExamList() {
		logger.info("Method : getExamList starts");

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

		logger.info("Method : getExamList ends");
		return getExamList;
	}
	
	
	
	

	

}
