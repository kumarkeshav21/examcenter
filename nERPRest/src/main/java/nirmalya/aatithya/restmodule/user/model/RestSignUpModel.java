package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestSignUpModel {
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
	
	public RestSignUpModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestSignUpModel(Object userId, Object fName, Object lName, Object companyName, Object contactPerson,
			Object email, Object mobileNo, Object gstNo, Object address, Object country, Object state, Object pincode,
			Object status,Object passward) {
		super();
		this.UserId = (String) userId;
		this.fName = (String) fName;
		this.lName = (String) lName;
		this.companyName = (String) companyName;
		this.contactPerson = (String) contactPerson;
		this.email = (String) email;
		this.mobileNo = (String) mobileNo;
		this.gstNo = (String) gstNo;
		this.address = (String) address;
		this.country = (String) country;
		this.state = (String) state;
		this.pincode = (String) pincode;
		this.status = (String) status;
		this.passward = (String) passward;
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

	public String getContactPerson() {
		return contactPerson;
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

	public String getPassward() {
		return passward;
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

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public void setPassward(String passward) {
		this.passward = passward;
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
