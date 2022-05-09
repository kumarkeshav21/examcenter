package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ManageExaminationWebModel {
	private String sId;
	private String examName;
	private String status;
	private String authority;
	private String mobile;
	private String email;
	private String logo;
	private String drProfDoc;
	private List<AddMoreExamCourseWebModel> centerDetails;
	public ManageExaminationWebModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDrProfDoc() {
		return drProfDoc;
	}

	public void setDrProfDoc(String drProfDoc) {
		this.drProfDoc = drProfDoc;
	}
	
	

	public List<AddMoreExamCourseWebModel> getCenterDetails() {
		return centerDetails;
	}

	public void setCenterDetails(List<AddMoreExamCourseWebModel> centerDetails) {
		this.centerDetails = centerDetails;
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
