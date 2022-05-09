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
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationReportRestModel;
import nirmalya.aatithya.restmodule.lab.model.EditExaminationRestModel;
import nirmalya.aatithya.restmodule.lab.model.EditInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageInstructionRestModel;

@Repository

public class ManageInstructionRestDao {
	Logger logger = LoggerFactory.getLogger(ManageInstructionRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public JsonResponse<List<ManageInstructionRestModel>> manageInstructionDao(String userId,String userrole) {
		logger.info("Method : manageInstructionDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<ManageInstructionRestModel> list = new ArrayList<ManageInstructionRestModel>();
		JsonResponse<List<ManageInstructionRestModel>> resp = new JsonResponse<List<ManageInstructionRestModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_instruction_view")
					.setParameter("userid", userId)
					.setParameter("userrole", userrole).getResultList();

			for (Object[] m : x) {

				ManageInstructionRestModel viewdemo = new ManageInstructionRestModel(m[0], m[1], m[2], m[3], m[4],m[5]);
				list.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(list);
		System.out.println("@@@@@@"+resp);
		logger.info("Method : manageInstructionDao ends");

		return resp;
	}
	
/***********************************edit ***********************/
	
	/*
	 * edit instruction details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> editInstructionDao(EditInstructionRestModel editInst,String userId) {
		logger.info("Method : editInstructionDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (editInst.getsId() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("edit_instruction_details")
						.setParameter("uid", userId)
						.setParameter("sid", editInst.getsId())
						.setParameter("exam", editInst.getExams())
						.setParameter("inst", editInst.getInstruction())
						.setParameter("pos", editInst.getPosition())
						.setParameter("stat", editInst.getStatus())
						
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

		logger.info("Method : editInstructionDao ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getExamsList() {
		logger.info("Method : getExamList starts");

		List<DropDownModel> getExamsList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getExamsList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("exam_list_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getExamsList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getExamsList);

		logger.info("Method : getExamList ends");
		return getExamsList;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> deleteInstruction(String id,String userId) {

		logger.info("Method : deleteInstruction Dao starts");
		System.out.println("@@@@@@@@@@@@@@@rest"+id);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {

			List<Object[]> x= em.createNamedStoredProcedureQuery("delete_instruction")
					.setParameter("eid", id)
					.setParameter("uid", userId).getResultList();

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

		logger.info("Method : deleteInstruction Dao ends");
		return response;
	}


	



}
