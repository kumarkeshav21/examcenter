package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddExamRestModel {
	private String examName;
	private String status;
	private String authoName;
	private String mobileNo;
	private String emailId;
	private String uploadLogo;
	
	private List<AddMoreExamCourseRestModel> courseNameList;

	public AddExamRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddExamRestModel(Object examName, Object status, Object authoName,
			Object mobileNo, Object emailId, Object uploadLogo) {
		super();
		this.examName = (String) examName;
		this.status = (String) status;
		this.authoName = (String) authoName;
		this.mobileNo = (String) mobileNo;
		this.emailId = (String) emailId;
		this.uploadLogo = (String) uploadLogo;
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

	public String getAuthoName() {
		return authoName;
	}

	public void setAuthoName(String authoName) {
		this.authoName = authoName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUploadLogo() {
		return uploadLogo;
	}

	public void setUploadLogo(String uploadLogo) {
		this.uploadLogo = uploadLogo;
	}
	

	public List<AddMoreExamCourseRestModel> getCourseNameList() {
		return courseNameList;
	}

	public void setCourseNameList(List<AddMoreExamCourseRestModel> courseNameList) {
		this.courseNameList = courseNameList;
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
