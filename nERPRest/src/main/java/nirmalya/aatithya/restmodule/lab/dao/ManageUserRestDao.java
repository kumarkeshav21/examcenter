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
import nirmalya.aatithya.restmodule.lab.model.EditUserDetailRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageUserRestModel;

@Repository


public class ManageUserRestDao {
	
	Logger logger = LoggerFactory.getLogger(ManageUserRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<ManageUserRestModel>> manageUserRestDao(String userid) {
		logger.info("Method : manageInstructionDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<ManageUserRestModel> list = new ArrayList<ManageUserRestModel>();
		JsonResponse<List<ManageUserRestModel>> resp = new JsonResponse<List<ManageUserRestModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_user_view")
					.setParameter("userId", userid).getResultList();

			for (Object[] m : x) {

				ManageUserRestModel viewdemo = new ManageUserRestModel(m[0], m[1], m[2],m[3],m[4],m[5]);
				list.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(list);
		System.out.println("=======>>>>"+resp);
		logger.info("Method : manageInstructionDao ends");

		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> editUserDetailsDao(EditUserDetailRestModel editInst,String userId) {
		logger.info("Method : editInstructionDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (editInst.getsId() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("edit_user_details")
						.setParameter("uid", userId)
						.setParameter("sid", editInst.getsId())
						.setParameter("utype", editInst.getUserType())
						.setParameter("uname", editInst.getUserName())
						.setParameter("email", editInst.getEmail())
						.setParameter("phone", editInst.getPhone())
						.setParameter("address", editInst.getAddress())
						
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
	public ResponseEntity<JsonResponse<Object>> deleteUserDao(String id,String userId) {

		logger.info("Method : deleteUserDao Dao starts");
		System.out.println("@@@@@@@@@@@@@@@rest"+id);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {

			List<Object[]> x= em.createNamedStoredProcedureQuery("delete_users_details")
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

		logger.info("Method : deleteUserDao Dao ends");
		return response;
	}



}
