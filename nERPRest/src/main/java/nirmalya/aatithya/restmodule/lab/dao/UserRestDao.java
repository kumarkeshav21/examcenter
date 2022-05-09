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
import nirmalya.aatithya.restmodule.lab.model.AddUserRestModel;

@Repository
public class UserRestDao {
	Logger logger = LoggerFactory.getLogger(UserRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * add center details(Medication)
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> addNewUserRestDetailsDao(AddUserRestModel addUser) {
		logger.info("Method : addNewUserRestDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (addUser.getUserName() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("add_user_details")
						.setParameter("usertype", addUser.getUserType())
						.setParameter("username", addUser.getUserName())
						.setParameter("useremail", addUser.getEmail())
						.setParameter("userphone", addUser.getPhone())
						.setParameter("useraddress", addUser.getAddress())
						.setParameter("userpassword", addUser.getPassword())
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

		logger.info("Method : addNewUserRestDetailsDao ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getUserList() {
		logger.info("Method : getUserList starts");

		List<DropDownModel> getUserList = new ArrayList<DropDownModel>();
		System.out.println("@@@@@"+getUserList);

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_dropdown_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getUserList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	     System.out.println("@@@@@"+getUserList);

		logger.info("Method : getUserList ends");
		return getUserList;
	}
	

}
