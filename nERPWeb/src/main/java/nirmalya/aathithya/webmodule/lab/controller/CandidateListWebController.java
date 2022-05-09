package nirmalya.aathithya.webmodule.lab.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.CandidateListWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class CandidateListWebController {
	Logger logger = LoggerFactory.getLogger(CandidateListWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("candidate-list")
	public String candidateList(Model model, HttpSession session) {

		logger.info("Method : candidateList starts");
	
		logger.info("Method : candidateList ends");
		return "lab/candidate-list";
	}

	//for getting candidate list 
		@SuppressWarnings("unchecked")
		
		@GetMapping("candidate-list-view-table")
		public @ResponseBody JsonResponse<List<CandidateListWebModel>> viewCandidateList(Model model,HttpSession session){
			logger.info("Method :viewCandidateList starts");
			String userid = (String)session.getAttribute("USER_ID");
			
			System.out.println("########################"+userid);
			JsonResponse<List<CandidateListWebModel>> resp = new JsonResponse<List<CandidateListWebModel>>();

			try {
				resp = restTemplate.getForObject(env.getLabUrl() + "rest-candidatelist?userid="+userid, JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<CandidateListWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<CandidateListWebModel>>() {
					});
			
			resp.setBody(quotationNewModel);
			if (resp.getMessage() != null && resp.getMessage() != "") {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");

			} else {
				resp.setMessage("Success");
			}
			
			

			logger.info("Method :viewCandidateList ends");
			// System.out.println("RESPONSE" + resp);
			return resp;
		}
		

}
