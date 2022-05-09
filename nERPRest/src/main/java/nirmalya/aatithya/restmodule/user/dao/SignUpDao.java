package nirmalya.aatithya.restmodule.user.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateSignUpParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.RestSignUpModel;
import nirmalya.aatithya.restmodule.user.model.VerificationRestModel;

@Repository
public class SignUpDao {
	Logger logger = LoggerFactory.getLogger(SignUpDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;

	// country list

	@SuppressWarnings("unchecked")
	public List<DropDownModel> countryList() {
		logger.info("Method : countryList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_country_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				countryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : countryList Dao ends");

		return countryList;
	}

	// state list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getStateName(String id) {

		logger.info("Method : getStateName starts");
		System.out.println("======"+id);
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_state_list").setParameter("country", id)
					
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getStateName ends");
		return resp;
	}

	// add sign up details

	@SuppressWarnings("unchecked")
	public JsonResponse<Object> addSignUp(RestSignUpModel details) {

		 System.out.println("######" + details);
		logger.info("Method : addSignUp starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			//String values = GenerateSignUpParameter.getAddSignUpDetailsS(details);
			// System.out.println(values);

			if (details.getUserId() == null || details.getUserId() == "") {

				List<Object[]> x = em.createNamedStoredProcedureQuery("save_costumer_details") 
						.setParameter("p_fName", details.getfName())
						.setParameter("p_cName", details.getCompanyName())
						.setParameter("p_password", details.getPassward())
						.setParameter("p_contperson", details.getContactPerson())
						.setParameter("p_email", details.getEmail())
						.setParameter("p_mobile", details.getMobileNo())
						.setParameter("p_gstno", details.getGstNo())
						.setParameter("p_address", details.getAddress())
						.setParameter("p_country", details.getCountry())
						.setParameter("p_state", details.getState())
						.setParameter("p_pincode", details.getPincode())
						.setParameter("p_status", details.getStatus())
						.getResultList();

				resp.setBody(x.get(0));

			}  
		} catch

		(Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		logger.info("Method : addSignUp ends");
		return resp;
	}
	
	// verification  details

		@SuppressWarnings("unchecked")
		public JsonResponse<Object> verificationRestDa(String mobile,String email) {

			 System.out.println("######" + mobile);
			 System.out.println("@@@@@@"+email);
			logger.info("Method : verificationRestDao starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();

			try {

				
			} catch

			(Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			System.out.println("*******"+resp);

			logger.info("Method : verificationRestDao ends");
			return resp;
		}
		
		
		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> verificationRestDao(String mobile,String email) {
			logger.info("Method : getCenterReportList starts");
			List<DropDownModel> jobList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
				System.out.println(mobile+"@@@@@@@@@");
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("user_verification")
						.setParameter("mobile", mobile)
						.setParameter("email", email)
						.getResultList();
				if(x.size() > 0) {
				for (Object[] m : x) {

					DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					jobList.add(dropDownModel);
				}
				resp.setMessage("Success");
				}else {
					resp.setMessage("Unsuccess");
				}

				resp.setBody(jobList);

			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : getCenterReportList ends");
			return resp;
		}
		
		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> updatePassRestDao(String pass,String uid) {
			logger.info("Method : updatePassRestDao starts");
			List<DropDownModel> jobList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("update_password")
						.setParameter("pass", pass)
						.setParameter("uid", uid)
						.getResultList();
				

				resp.setBody(jobList);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("pppppppppppppppppp"+resp);
			logger.info("Method : updatePassRestDao ends");
			return resp;
		}

}
