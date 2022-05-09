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
import nirmalya.aatithya.restmodule.lab.model.CandidateListRestModel;
import nirmalya.aatithya.restmodule.lab.model.CenterAllocationReportRestModel;

@Repository
public class CandidateListRestDao {
	Logger logger = LoggerFactory.getLogger(CandidateListRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	@SuppressWarnings("unchecked")
	public JsonResponse<List<CandidateListRestModel>> candidateListDao(String userId) {
		logger.info("Method : candidateListDao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<CandidateListRestModel> list = new ArrayList<CandidateListRestModel>();
		JsonResponse<List<CandidateListRestModel>> resp = new JsonResponse<List<CandidateListRestModel>>();

		try {

			
			List<Object[]> x = em.createNamedStoredProcedureQuery("candidate_list_details").setParameter("userId", userId)
					.getResultList();

			for (Object[] m : x) {

				CandidateListRestModel viewdemo = new CandidateListRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						"N/A");
				list.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(list);
		logger.info("Method : candidateListDao ends");

		return resp;
	}
}
