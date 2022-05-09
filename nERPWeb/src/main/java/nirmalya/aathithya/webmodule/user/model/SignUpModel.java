package nirmalya.aathithya.webmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SignUpModel {
	private String UserId;
	private String fName;
	private String lName;
	private String companyName;
	private String contactPerson;
	private String email;
	private String mobileNo;
	private String gstNo;
	private String address;
	private String country;
	private String state;
	private String pincode;
	private String status;
	private String passward;
	
	public SignUpModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public String getUserId() {
		return UserId;
	}
	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	public String getCompanyName() {
		return companyName;
	}
	
	public String getEmail() {
		return email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public String getGstNo() {
		return gstNo;
	}
	public String getAddress() {
		return address;
	}
	public String getCountry() {
		return country;
	}
	public String getState() {
		return state;
	}
	public String getPincode() {
		return pincode;
	}
	public String getStatus() {
		return status;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public void setStatus(String status) {
		this.status = status;
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
