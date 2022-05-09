package nirmalya.aathithya.webmodule.master.controller;

import java.util.Arrays;
import java.util.Base64;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.DemoDocumentModel;

@Controller
@RequestMapping(value = { "master/" })
public class DemoDocumentController {

	Logger logger = LoggerFactory.getLogger(TemplatesMasterController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	FileUpload fileUpload;

	@GetMapping(value = { "demo-document" })
	public String demoDoc(Model model, HttpSession session) {
		logger.info("Method : demoDoc starts");

		try {
			DemoDocumentModel[] temp = restClient.getForObject(env.getMasterUrl() + "getdemoList",
					DemoDocumentModel[].class);
			List<DemoDocumentModel> tempList = Arrays.asList(temp);

			if (tempList != null) {
				for (DemoDocumentModel m : tempList) {
					if (m.getFileName() != null && m.getFileName() != "") {
						String[] extension = m.getFileName().split("\\.");
						if (extension.length == 2) {
							if (extension[1].equals("html")) {
								String docPath = "<i class=\"fa fa-file\" title=\"" + m.getFileName() + "\"></i>";
								m.setAction(docPath);
							}
						} else {
							m.setAction("N/A");
						}
					} else {
						m.setAction("N/A");
					}
					m.setAction("<a href=\"/document/module/" + m.getFileName() + "\" target=\"_blank\">"
							+ m.getAction() + "</a>");
				}
			}

			model.addAttribute("tempList", tempList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : demoDoc ends");
		return "master/treeDemo";
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "demo-document-save")
	public @ResponseBody JsonResponse<Object> saveDemoDocument(@RequestBody DemoDocumentModel tempDtls,
			HttpSession session) {
		logger.info("Method : saveDemoDocument function starts");

		//System.out.println("DATAAAAAAAA======" + tempDtls);
		JsonResponse<Object> res = new JsonResponse<Object>();
		DemoDocumentModel docList = new DemoDocumentModel();

		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {

		}
		tempDtls.setCreatedBy(userId);
		if (tempDtls.getFileName() != null && tempDtls.getFileName() != "") {
			String delimiters = "\\.";
			String[] x = tempDtls.getFileName().split(delimiters);

			if (x[1].contentEquals("png") || x[1].contentEquals("PNG") || x[1].contentEquals("jpg") || x[1].contentEquals("jpeg")  ) {
				for (String s1 : tempDtls.getFile()) {
					try {
						byte[] bytes = Base64.getDecoder().decode(s1);
						String htmlName =  fileUpload.saveAllImage(bytes);
						tempDtls.setFileName(htmlName); 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		//System.out.println("tempDtls " + tempDtls);
		try {
			res = restClient.postForObject(env.getMasterUrl() + "savedemoDocument", tempDtls, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		docList = mapper.convertValue(res.getBody(), new TypeReference<DemoDocumentModel>() {
		});
		if (docList != null) {
			if (docList.getFileName() != null && docList.getFileName() != "") {
				String[] extension = docList.getFileName().split("\\.");
				if (extension.length == 2) {
					if (extension[1].equals("html")) {
						String docPath = "<i class=\"fa fa-file\" title=\"" + docList.getFileName() + "\"></i>";
						docList.setAction(docPath);
					}
				} else {
					docList.setAction("N/A");
				}
			} else {
				docList.setAction("N/A");
			}
			docList.setAction("<a href=\"/document/module/" + docList.getFileName() + "\" target=\"_blank\">"
					+ docList.getAction() + "</a>");
		}
		String message = res.getMessage();
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(docList); 
		logger.info("Method : saveDemoDocument function Ends");
		return res;
	}

}
