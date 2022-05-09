package nirmalya.aathithya.webmodule.master.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.DemoTreeModel;

	@Controller
	@RequestMapping(value = { "master/" })
	public class DemoTreeController {
		Logger logger = LoggerFactory.getLogger(DemoTreeController.class);
	
		@Autowired
		RestTemplate restClient;
	
		@Autowired
		EnvironmentVaribles env;
	
		@GetMapping(value = { "demo" })
		public String demoTree(Model model, HttpSession session) {
			logger.info("Method : demoTree starts");
			
			logger.info("Method : demoTree ends");
			return "master/treeDemo";
		}
		
	@SuppressWarnings("unchecked")
	@PostMapping("demo-save")
	public @ResponseBody JsonResponse<Object> saveDemo(@RequestBody DemoTreeModel demo, HttpSession session) {
		logger.info("Method : saveDemo starts");
		
		//System.out.println(demo);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		demo.setCreatedBy(userId);
		
		try {
			resp = restClient.postForObject(env.getMasterUrl() + "savedemo", demo,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : saveDemo ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("demo-get-total-list")
	public @ResponseBody JsonResponse<List<DemoTreeModel>> getdemoList(HttpSession session) {
		logger.info("Method : getdemoList starts");
		
		JsonResponse<List<DemoTreeModel>> resp = new JsonResponse<List<DemoTreeModel>>();
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "getdemolist",
					JsonResponse.class);
			
			//resp = restClient.getForObject(env.getInventoryUrl() + "getdemolist",
					//JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("Success");
		}
		//System.out.println(resp);
		logger.info("Method : getdemoList ends");
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("demo-subdemo-save")
	public @ResponseBody JsonResponse<Object> saveSubDemo(@RequestBody DemoTreeModel category, HttpSession session) {
		logger.info("Method : saveSubDemo starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		category.setCreatedBy(userId);
		
		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveSubDemo", category,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : saveSubDemo starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("demo-get-dtls-by-id")
	public @ResponseBody JsonResponse<DemoTreeModel> getDemoById(@RequestBody String id,HttpSession session) {
		
		logger.info("Method : getDemoById starts");
		//System.out.println("IDDDDDD"+id);
		
		JsonResponse<DemoTreeModel> resp = new JsonResponse<DemoTreeModel>();
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "getDemoById?id="+id,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : getDemoById starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("demo-delete")
	public @ResponseBody JsonResponse<Object> deleteDemo(@RequestBody String id, HttpSession session) {
		logger.info("Method : deleteDemo starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			resp = restClient.getForObject(env.getMasterUrl() + "deletedemo?id="+id+"&createdBy="+userId,
					JsonResponse.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String message = resp.getMessage();
		
		if (message != null && message != "") {
			
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : deleteDemo starts");
		return resp;
	}
}


