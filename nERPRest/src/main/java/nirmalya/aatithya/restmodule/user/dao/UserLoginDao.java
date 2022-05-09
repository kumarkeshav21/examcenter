/**
 * Repository for user handling related call
**/
package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateUserParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.User;

/**
 * @author Nirmalya Labs
 *
 */
@Repository
public class UserLoginDao {

	Logger logger = LoggerFactory.getLogger(UserLoginDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	
	/*
	 * public class IPaddress {
	 * 
	 * }
	 */
	
	/**
	 * function to return user by name
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<User>> getUserByUsername(String username) {
		logger.info("Method : getUserByUsername starts");
		System.out.print("username"+username);
		//System.out.print("ip"+request);
		//System.out.print("browser"+browserName);
		
		JsonResponse<User> jsonResponse = new JsonResponse<User>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<User> userArray = new ArrayList<User>();
		List<String> userRole = new ArrayList<String>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("add_login")//add_login
					.setParameter("p_username",username).getResultList();
					//.setParameter("p_ip", request)

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		try {

			String value = "SET @p_userName='" + username + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_login_details")
					.setParameter("p_userName", username).getResultList();

			for (Object[] m : x) {
				String role = (String) m[6];

				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
					System.out.println("****userRole***");
				}
			//boolean b1=Boolean.getBoolean((String) m[11]);
				User user = new User(m[0], m[1], m[2], m[3], m[4], null, null, null, null, null, null,m[5], null, null,
						userRole, m[7], m[8], m[9], m[10],m[11], m[12]);
				userArray.add(user);
				System.out.println("@@@@"+userArray);
			}

			if (userArray.size() > 0) {
				jsonResponse.setBody(userArray.get(0));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		ResponseEntity<JsonResponse<User>> response = new ResponseEntity<JsonResponse<User>>(jsonResponse,
				HttpStatus.OK);
		System.out.print(response);
		logger.info("Method : getUserByUsername ends");

		return response;

	}
	
	/*
	 * Save Family Details
	 */
	@SuppressWarnings("unused")
	public JsonResponse<Object> savBrowserDetails(String userId,String ip,String browserName,String dashboard) {
		logger.info("Method : savBrowserDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			
				em.createNamedStoredProcedureQuery("add_browsers_details")
				.setParameter("userid", userId)
				.setParameter("ipaddress", ip)
				.setParameter("browser", browserName)
				.setParameter("dashboard", dashboard)
				.execute();
			

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		logger.info("Method : savBrowserDetails ends");
		return resp;
	}

	/**
	 * function to register user
	 *
	 */
	public ResponseEntity<JsonResponse<String>> registerUser(User user) {
		logger.info("Method : registerUser starts");

		JsonResponse<String> jsonResponse = new JsonResponse<String>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		try {
			String value = GenerateUserParameter.getUserParam(user);

			em.createNamedStoredProcedureQuery("userRoutines").setParameter("actionType", "getByName")
					.setParameter("actionValue", value).execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				String[] err = serverDao.errorProcedureCall(e);

				jsonResponse.setCode(err[0]);
				jsonResponse.setMessage(err[1]);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		ResponseEntity<JsonResponse<String>> response = new ResponseEntity<JsonResponse<String>>(jsonResponse,
				HttpStatus.OK);
		logger.info("Method : registerUser ends");

		return response;

	}

}
