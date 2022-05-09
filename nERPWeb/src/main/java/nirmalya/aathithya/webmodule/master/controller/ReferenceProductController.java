package nirmalya.aathithya.webmodule.master.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.ReferenceProductModel;

@Controller
@RequestMapping(value = "master")
public class ReferenceProductController {

	Logger logger = LoggerFactory.getLogger(ReferenceProductController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("/referenceProduct")
	public String displayPage(Model model, HttpSession session) {
		logger.info("Method : Product starts");

		logger.info("Method : Product ends");
		return "master/referenceProduct";
	}

///////////// Brand Type//////////////

	@SuppressWarnings("unchecked")
	@PostMapping("/view-procurement-add-brand-type")
	public @ResponseBody JsonResponse<Object> addBrandType(@RequestBody ReferenceProductModel brand,
			HttpSession session) {
		logger.info("Method : addBrandType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		brand.setBrandCreatedBy(userId);

		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addBrandType", brand, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : addBrandType starts");
		System.out.println(resp);

		return resp;
	}

//View page for brand types

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-brand-type")
	public @ResponseBody List<ReferenceProductModel> viewBrandType(HttpSession session) {
		logger.info("Method : viewBrandType starts");

		JsonResponse<List<ReferenceProductModel>> resp = new JsonResponse<List<ReferenceProductModel>>();
		List<ReferenceProductModel> brandList = new ArrayList<ReferenceProductModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewBrandType", JsonResponse.class);
			brandList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(brandList);
		logger.info("Method : viewBrandType ends");
		return brandList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-brand-type-delete")
	public @ResponseBody JsonResponse<Object> deletebrandType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deletebrandType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deletebrandType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deletebrandType ends");
		return resp;
	}

/////////////--------------Product Type-----------------//////////////

	@SuppressWarnings("unchecked")
	@PostMapping("/view-procurement-add-product-type")
	public @ResponseBody JsonResponse<Object> addProductType(@RequestBody ReferenceProductModel brand,
			HttpSession session) {

		logger.info("Method : addProductType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		brand.setProductCreatedBy(userId);

		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addProductType", brand, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : addProductType starts");

		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-product-type")
	public @ResponseBody List<ReferenceProductModel> viewProductType(HttpSession session) {
		logger.info("Method : viewProductType starts");

		JsonResponse<List<ReferenceProductModel>> resp = new JsonResponse<List<ReferenceProductModel>>();
		List<ReferenceProductModel> productList = new ArrayList<ReferenceProductModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewProductType", JsonResponse.class);
			productList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(productList);
		logger.info("Method : viewProductType ends");
		return productList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-product-type-delete")
	public @ResponseBody JsonResponse<Object> deleteProductType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteProductType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteProductType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteProductType ends");
		return resp;
	}

	
	
/////////////--------------Variation Type-----------------//////////////

	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-procurement-add-variation-type")
	public @ResponseBody JsonResponse<Object> addVariationType(@RequestBody ReferenceProductModel var,
			HttpSession session) {

		logger.info("Method : addVariationType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		var.setVariationCreatedBy(userId);

		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addVariationType", var, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : addVariationType starts");
		

		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-view-variation-type")
	public @ResponseBody List<ReferenceProductModel> viewVariationType(HttpSession session) {
		logger.info("Method : viewVariationType starts");

		JsonResponse<List<ReferenceProductModel>> resp = new JsonResponse<List<ReferenceProductModel>>();
		List<ReferenceProductModel> productList = new ArrayList<ReferenceProductModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewVariationType", JsonResponse.class);
			productList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(productList);
		logger.info("Method : viewVariationType ends");
		return productList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/view-procurement-variation-type-delete")
	public @ResponseBody JsonResponse<Object> deleteVariationType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteVariationType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteVariationType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteVariationType ends");
		return resp;
	}

}
