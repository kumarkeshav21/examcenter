package nirmalya.aatithya.restmodule.lab.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.AddInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.RollSetUpRestModel;

@Repository
public class RollSetUpDao {
	Logger logger = LoggerFactory.getLogger(RollSetUpDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * add center details(Medication)
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> addRollSetUpDao(RollSetUpRestModel addSetup,String userId) {
		logger.info("Method : addRollSetUpDao starts");
		System.out.println("################"+addSetup);

		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			
			  if (addSetup.getExamType() != null) {
			  
			  List<Object[]> x =
			  em.createNamedStoredProcedureQuery("roll_setup_details")
			  .setParameter("uid", userId) 
			  .setParameter("exam", addSetup.getExamType())
			  .setParameter("loccode", addSetup.getLocCode())
			  .setParameter("distCode", addSetup.getDistCode())
			  .setParameter("examCode",addSetup.getExamCode())
			  .setParameter("centerCode",addSetup.getCenterCode())
			  .setParameter("serialNumber",addSetup.getSerialNumber()).getResultList();
			  
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

		logger.info("Method : addRollSetUpDao ends");
		return resp;
	}

}
