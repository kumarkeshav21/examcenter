package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPackageSubscriptionModel {
	private String packageId;
	private String packageName;
	private Double amount;
	private Double validityDate;
	private String createdBy;
	private String fromDate;
	private String toDate;
	private String custId;
	private String rechargeId;
	
	public RestPackageSubscriptionModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestPackageSubscriptionModel(Object packageId, Object packageName, Object amount, Object validityDate,
			Object createdBy,Object fromDate,Object toDate,Object custId,Object rechargeId) {
		super();
		this.packageId = (String) packageId;
		this.packageName = (String) packageName;
		this.amount = (Double) amount;
		this.validityDate = (Double) validityDate;
		this.createdBy = (String) createdBy;
		this.fromDate = (String) fromDate;
		this.toDate = (String) toDate;
		this.custId = (String) custId;
		this.rechargeId = (String) rechargeId;
	}

	public String getCustId() {
		return custId;
	}

	public String getRechargeId() {
		return rechargeId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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

	public Double getValidityDate() {
		return validityDate;
	}

	public String getCreatedBy() {
		return createdBy;
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

	public void setValidityDate(Double validityDate) {
		this.validityDate = validityDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
