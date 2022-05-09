package nirmalya.aathithya.webmodule.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
@RequestMapping(value = { "lab/" })

public class UserConfigureWebController {
	Logger logger = LoggerFactory.getLogger(AddCenterWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("user-configure")
	public String addcenter(Model model, HttpSession session) {

		logger.info("Method : addcenter starts");
		
	
	
		logger.info("Method : addcenter ends");
		return "lab/user-configure";
	}
	

}
