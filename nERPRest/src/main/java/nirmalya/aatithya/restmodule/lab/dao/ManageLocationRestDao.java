package nirmalya.aatithya.restmodule.lab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.ManageInstructionRestModel;
import nirmalya.aatithya.restmodule.lab.model.ManageLocationRestModel;

@Repository

public class ManageLocationRestDao {
	Logger logger = LoggerFactory.getLogger(ManageLocationRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public JsonResponse<List<ManageLocationRestModel>> manageLocationtionDao(String userid) {
		logger.info("Method : manageInstructionDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<ManageLocationRestModel> list = new ArrayList<ManageLocationRestModel>();
		JsonResponse<List<ManageLocationRestModel>> resp = new JsonResponse<List<ManageLocationRestModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("manage_location_view")
					.setParameter("userId", userid).getResultList();

			for (Object[] m : x) {

				ManageLocationRestModel viewdemo = new ManageLocationRestModel(m[0], m[1], m[2], "N/A");
				list.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(list);
		logger.info("Method : manageInstructionDao ends");

		return resp;
	}


}
