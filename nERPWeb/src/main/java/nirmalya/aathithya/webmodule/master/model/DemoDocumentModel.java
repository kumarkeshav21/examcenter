package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoDocumentModel {

	private String docId;
	private String docName;
	private String fileName;
	private String createdBy;
	private String action;
	List<String> file;

	public DemoDocumentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDocId() {
		return docId;
	}

	public String getDocName() {
		return docName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<String> getFile() {
		return file;
	}

	public void setFile(List<String> file) {
		this.file = file;
	}

	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}
}
