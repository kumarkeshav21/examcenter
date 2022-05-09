package nirmalya.aathithya.webmodule.customer.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDashboardModel {
	private String customerId;
	private String name;
	private String companyName;
	private String contactPerson;
	private String email;
	private String mobileNo;
	private String gstNo;
	private String address;
	private String country;
	private String state;
	private String pincode;
	private String packageId;
	private String packageName;
	private Double amount;
	private Double validity;
	private String fromDate;
	private String toDate;
	private String status;
	
	public CustomerDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
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

	public String getPackageId() {
		return packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public Double getAmount() {
		return amount;
	}

	public Double getValidity() {
		return validity;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setValidity(Double validity) {
		this.validity = validity;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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
