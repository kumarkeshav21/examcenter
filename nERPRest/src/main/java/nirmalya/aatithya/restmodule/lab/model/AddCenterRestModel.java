package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AddCenterRestModel {

	private String centerCode;
	private String centerName;
	private String locationName;
	private String examType;
	private String centerAddress;
	private String status;
	private String remarks;
	private String inchargeName;
	private String mobileNo;
	private String emailId;

	private List<AddCenterAddMoreRestModel> centerDetails;

	public AddCenterRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddCenterRestModel(Object centerCode, Object centerName, Object locationName, Object examType, Object centerAddress,
			Object status, Object remarks, Object inchargeName, Object mobileNo, Object emailId) {
		super();
		this.centerCode = (String) centerCode;
		this.centerName = (String) centerName;
		this.locationName = (String) locationName;
		this.examType = (String) examType;
		this.centerAddress = (String) centerAddress;
		this.status = (String) status;
		this.remarks = (String) remarks;
		this.inchargeName = (String) inchargeName;
		this.mobileNo = (String) mobileNo;
		this.emailId = (String) emailId;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getCenterAddress() {
		return centerAddress;
	}

	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInchargeName() {
		return inchargeName;
	}

	public void setInchargeName(String inchargeName) {
		this.inchargeName = inchargeName;
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

	

	public List<AddCenterAddMoreRestModel> getCenterDetails() {
		return centerDetails;
	}

	public void setCenterDetails(List<AddCenterAddMoreRestModel> centerDetails) {
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
