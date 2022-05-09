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
import nirmalya.aathithya.webmodule.lab.model.ManageInstructionWebModel;
import nirmalya.aathithya.webmodule.lab.model.ManageLocationWebModel;

@Controller
@RequestMapping(value = { "lab/" })
public class ManageLocationWebController {

	Logger logger = LoggerFactory.getLogger(ManageLocationWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("manage-location")
	public String addcenter(Model model, HttpSession session) {

		logger.info("Method : labDashboard starts");
	
		logger.info("Method : labDashboard ends");
		return "lab/manage-location";
	}
	
	//for manage location 
			@SuppressWarnings("unchecked")
			
			@GetMapping("manage-location-table")
			public @ResponseBody JsonResponse<List<ManageLocationWebModel>> viewManageLocation(Model model,HttpSession session){
				logger.info("Method :viewManageLocation starts");
				String userid = (String)session.getAttribute("USER_ID");
				
				System.out.println("########################"+userid);
				JsonResponse<List<ManageLocationWebModel>> resp = new JsonResponse<List<ManageLocationWebModel>>();

				try {
					resp = 
					restTemplate.getForObject(env.getLabUrl() + "rest-manage-location-table?userid="+userid, JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				List<ManageLocationWebModel> quotationNewModel = mapper.convertValue(resp.getBody(),
						new TypeReference<List<ManageLocationWebModel>>() {
						});
				for(ManageLocationWebModel m:quotationNewModel) {
					
					if (m.getStatus().equals("1")) {
						m.setStatus("Active");
					} else {
						m.setStatus("InActive");
					}


				}
				resp.setBody(quotationNewModel);
				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");

				} else {
					resp.setMessage("Success");
				}
				
				

				logger.info("Method :viewManageLocation ends");
				// System.out.println("RESPONSE" + resp);
				return resp;
			}
			


}
