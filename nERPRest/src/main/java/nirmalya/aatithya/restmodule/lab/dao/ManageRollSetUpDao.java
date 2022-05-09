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
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.EditInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageLocationRestModel;
import nirmalya.aatithya.restmodule.lab.model.RollSetUpRestModel;

@Repository
public class ManageRollSetUpDao {
	Logger logger = LoggerFactory.getLogger(ManageRollSetUpDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RollSetUpRestModel>> manageRollSetUpDao(String userid) {
		logger.info("Method : manageRollSetUpDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<RollSetUpRestModel> list = new ArrayList<RollSetUpRestModel>();
		JsonResponse<List<RollSetUpRestModel>> resp = new JsonResponse<List<RollSetUpRestModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_rollsetup_view")
					.setParameter("userId", userid).getResultList();

			for (Object[] m : x) {

				RollSetUpRestModel viewdemo = new RollSetUpRestModel(m[0], m[1], m[2], m[3], m[4], m[5],m[6],m[7]);
				list.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(list);
		System.out.println(resp);
		logger.info("Method : manageRollSetUpDao ends");

		return resp;
	}
	
	/*
	 * edit instruction details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> editRollSetUpRestDao(RollSetUpRestModel editRoll,String userId) {
		logger.info("Method : editInstructionDao starts");
		System.out.println("==>"+editRoll);
		System.out.println("==>"+userId);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (editRoll.getExamType() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("edit_rollsetup_details")
						.setParameter("uid", userId)
						.setParameter("rid", editRoll.getRid())
						.setParameter("exam", editRoll.getExamType())
						.setParameter("loc", editRoll.getLocCode())
						.setParameter("dis", editRoll.getDistCode())
						.setParameter("eco", editRoll.getExamCode())
						.setParameter("ccod", editRoll.getCenterCode())
						.setParameter("sn", editRoll.getSerialNumber())
						
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
		System.out.println("Response==>"+resp);

		logger.info("Method : editInstructionDao ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> deleteRollSetupDao(String id,String userId) {

		logger.info("Method : deleteRollSetupDao Dao starts");
		System.out.println("@@@@@@@@@@@@@@@rest"+id);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {

			List<Object[]> x= em.createNamedStoredProcedureQuery("delete_rollsetup")
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

		logger.info("Method : deleteRollSetupDao Dao ends");
		return response;
	}


	

	

}
