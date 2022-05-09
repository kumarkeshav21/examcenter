package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ManageCenterRestModel {

	public List<AddCenterAddMoreRestModel> getCenterDetails() {
		return centerDetails;
	}

	public void setCenterDetails(List<AddCenterAddMoreRestModel> centerDetails) {
		this.centerDetails = centerDetails;
	}

	private String sId;
	private String centerCode;
	private String centerName;
	private String locationName;
	private String examType;
	private String address;
	private String inChargeName;
	private String mobile;
	private String email;
	private String status;
	private String locid;
	private String examid;
	
	private List<AddCenterAddMoreRestModel> centerDetails;

	public ManageCenterRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManageCenterRestModel(Object sId, Object centerCode, Object centerName, Object locationName, Object examType, Object address,
			Object inChargeName, Object mobile, Object email, Object status) {
		super();
		this.sId = (String) sId;
		this.centerCode = (String) centerCode;
		this.centerName = (String) centerName;
		this.locationName = (String) locationName;
		this.examType = (String) examType;
		this.address = (String) address;
		this.inChargeName = (String) inChargeName;
		this.mobile = (String) mobile;
		this.email = (String) email;
		this.status = (String) status;
	}
	
	public ManageCenterRestModel(Object sId, Object centerCode, Object centerName, Object locationName, Object examType, Object address,
			Object inChargeName, Object mobile, Object email, Object status,Object locid,Object examid) {
		super();
		this.sId = (String) sId;
		this.centerCode = (String) centerCode;
		this.centerName = (String) centerName;
		this.locationName = (String) locationName;
		this.examType = (String) examType;
		this.address = (String) address;
		this.inChargeName = (String) inChargeName;
		this.mobile = (String) mobile;
		this.email = (String) email;
		this.status = (String) status;
		this.locid = (String) locid;
		this.examid = (String) examid;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInChargeName() {
		return inChargeName;
	}

	public void setInChargeName(String inChargeName) {
		this.inChargeName = inChargeName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getLocid() {
		return locid;
	}

	public void setLocid(String locid) {
		this.locid = locid;
	}

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
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
