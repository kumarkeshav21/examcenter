package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductDetailsModel {

	private String productId;
	private String sku;
	private String model;
	private String manufacture;
	private String variationType;
	private String variationValue;
	private String unit;
	private Double salePrice;
	private Double saleTax;
	private Double saleCess;
	private String createdBy;
	private String createdDate;
	private String isEdit;
	private String vendorId;
	private Double moq;
	private String editSKUId;
	private String editVendorId;
	private String sPrice;
	private String sMoq;
	
	public ProductDetailsModel() {
		super();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getVariationType() {
		return variationType;
	}

	public void setVariationType(String variationType) {
		this.variationType = variationType;
	}

	public String getVariationValue() {
		return variationValue;
	}

	public void setVariationValue(String variationValue) {
		this.variationValue = variationValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(Double saleTax) {
		this.saleTax = saleTax;
	}

	public Double getSaleCess() {
		return saleCess;
	}

	public void setSaleCess(Double saleCess) {
		this.saleCess = saleCess;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Double getMoq() {
		return moq;
	}

	public void setMoq(Double moq) {
		this.moq = moq;
	}

	public String getEditSKUId() {
		return editSKUId;
	}

	public void setEditSKUId(String editSKUId) {
		this.editSKUId = editSKUId;
	}

	public String getEditVendorId() {
		return editVendorId;
	}

	public void setEditVendorId(String editVendorId) {
		this.editVendorId = editVendorId;
	}

	public String getsPrice() {
		return sPrice;
	}

	public void setsPrice(String sPrice) {
		this.sPrice = sPrice;
	}

	public String getsMoq() {
		return sMoq;
	}

	public void setsMoq(String sMoq) {
		this.sMoq = sMoq;
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
