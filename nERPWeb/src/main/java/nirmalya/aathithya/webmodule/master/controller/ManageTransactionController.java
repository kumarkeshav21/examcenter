package nirmalya.aathithya.webmodule.master.controller;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;

@Controller
@RequestMapping(value = "master/")

public class ManageTransactionController {
	Logger logger = LoggerFactory.getLogger(ManageTransactionController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("manage-transaction")
	public String manageTransaction(Model model, HttpSession session) {
		logger.info("Method : manageTransaction starts");

		
		logger.info("Method : manageTransaction ends");
		return "master/manage-transaction";
	}
}
