package nirmalya.aatithya.restmodule.user.dao;

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
import nirmalya.aatithya.restmodule.common.utils.GeneratePackageDetailsParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.user.model.RestPackageSubscriptionModel;

@Repository
public class PackageSubscriptionDao {
Logger logger = LoggerFactory.getLogger(PackageSubscriptionDao.class);
	
	@Autowired
	ServerDao serverDao;
	
	@Autowired
	EntityManager em;
	
	// package list
	
		@SuppressWarnings("unchecked")
		public List<DropDownModel> packageList() {
			logger.info("Method : packageList Dao starts");

					List<DropDownModel> packageList = new ArrayList<DropDownModel>();

					try {
 
						List<Object[]> x = em.createNamedStoredProcedureQuery("get_package_list")
								.getResultList();
						for (Object[] m : x) {
							DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
							packageList.add(dropDownModel);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(packageList);
					logger.info("Method : packageList Dao ends");

					return packageList;
			}
		
	//get amount
		
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestPackageSubscriptionModel>> getPackageAmount(String packageName) {

			logger.info("Method : getPackageAmount Dao starts");
			//System.out.println(packageName);
			List<RestPackageSubscriptionModel> viewamount = new ArrayList<RestPackageSubscriptionModel>();
			JsonResponse<List<RestPackageSubscriptionModel>> resp = new JsonResponse<List<RestPackageSubscriptionModel>>();

			try { 
				 
				List<Object[]> x = em.createNamedStoredProcedureQuery("get_package_amount").setParameter("package", packageName)
						.getResultList();

				for (Object[] m : x) {
					RestPackageSubscriptionModel amount = new RestPackageSubscriptionModel( null,null,m[0],m[1],null,null,null,null,null);
						
					viewamount.add(amount);
				}
				System.out.println(viewamount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(viewamount);
			logger.info("Method : getPackageAmount Dao ends");
			return resp;

		}
		
	//Save package details
		
		public JsonResponse<Object> savePackageDetails(RestPackageSubscriptionModel packagedetails) {
			logger.info("Method : savePackageDetails dao starts");
		
			JsonResponse<Object> resp = new JsonResponse<Object>();
						
			try {
				String value = GeneratePackageDetailsParameter.addPackageDetails(packagedetails);
				System.out.println("value==="+value);
				if (packagedetails.getRechargeId() != null && packagedetails.getRechargeId() != "") {
					System.out.println("if===="+packagedetails.getRechargeId());
					em.createNamedStoredProcedureQuery("PackageSubscription").setParameter("actionType", "modifyPackageDetails")
							.setParameter("actionValue", value).execute();

				} else {
					System.out.println("else===="+packagedetails.getRechargeId());
					em.createNamedStoredProcedureQuery("save_package_details")
					.setParameter("p_custId", packagedetails.getCustId() )
					.setParameter("p_packageId", packagedetails.getPackageId())
					.setParameter("p_amount", packagedetails.getAmount())
					.setParameter("p_fromDate", packagedetails.getFromDate())
					.setParameter("p_toDate", packagedetails.getToDate())
					.setParameter("p_createdBy", packagedetails.getCreatedBy())
					.execute();

				}
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					System.out.println(err);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}

			
			System.out.println(resp);
			logger.info("Method : savePackageDetails dao ends");
			return resp;
		}
			
}
