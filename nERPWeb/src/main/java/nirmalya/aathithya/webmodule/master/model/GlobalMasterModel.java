package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;

public class GlobalMasterModel {
	private String globalId;
	private String countryOrderId;
	private String countryCode;
	private String countryName;
	private String stateId;
	private String stateName;
	private String stateCode;
	private String stateOrderId;
	private String cityId;
	private String cityName;
	private String cityCode;
	private String cityOrderId;
	private String districtId;
	private String districtOrderId;
	private String districtCode;
	private String districtName;
	private String districtStatus;
	private String countryStatus;
	private String stateStatus;
	private String cityStatus;
	private String stateCreatedBy;
	private String cityCreatedBy;
	private String createdBy;
	private List<DropDownModel> stateList = new ArrayList<DropDownModel>();
	private List<DropDownModel> cityList = new ArrayList<DropDownModel>();
	private List<DropDownModel> districtList = new ArrayList<DropDownModel>();

	public GlobalMasterModel() {
		super();
	}

	public GlobalMasterModel(String countryOrderId, String countryCode, String countryName,
			String countryStatus) {
		super(); 
		this.countryOrderId = countryOrderId;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.countryStatus = countryStatus;
	}

	public GlobalMasterModel(String globalId, String stateName, String stateCode, String stateOrderId,
			String stateStatus ,String createdBy) { 
		this.globalId = globalId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.stateOrderId = stateOrderId;
		this.stateStatus = stateStatus;
		this.createdBy = createdBy;
	}

	public GlobalMasterModel(String globalId, String stateId, String districtId, String cityName, String cityOrderId,
			String cityStatus ,String createdBy) {
		super();
		this.globalId = globalId;
		this.stateId = stateId;
		this.cityName = cityName;
		this.cityOrderId = cityOrderId;
		this.districtId = districtId;
		this.cityStatus = cityStatus;
		this.createdBy = createdBy;
	}

	
	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateOrderId() {
		return stateOrderId;
	}

	public void setStateOrderId(String stateOrderId) {
		this.stateOrderId = stateOrderId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityOrderId() {
		return cityOrderId;
	}

	public void setCityOrderId(String cityOrderId) {
		this.cityOrderId = cityOrderId;
	}

	public String getCountryOrderId() {
		return countryOrderId;
	}

	public void setCountryOrderId(String countryOrderId) {
		this.countryOrderId = countryOrderId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryStatus() {
		return countryStatus;
	}

	public void setCountryStatus(String countryStatus) {
		this.countryStatus = countryStatus;
	}

	public String getStateStatus() {
		return stateStatus;
	}

	public void setStateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
	}

	public String getCityStatus() {
		return cityStatus;
	}

	public void setCityStatus(String cityStatus) {
		this.cityStatus = cityStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<DropDownModel> getStateList() {
		return stateList;
	}

	public void setStateList(List<DropDownModel> stateList) {
		this.stateList = stateList;
	}

	public List<DropDownModel> getCityList() {
		return cityList;
	}

	public void setCityList(List<DropDownModel> cityList) {
		this.cityList = cityList;
	}

	public String getStateCreatedBy() {
		return stateCreatedBy;
	}

	public void setStateCreatedBy(String stateCreatedBy) {
		this.stateCreatedBy = stateCreatedBy;
	}

	public String getCityCreatedBy() {
		return cityCreatedBy;
	}

	public void setCityCreatedBy(String cityCreatedBy) {
		this.cityCreatedBy = cityCreatedBy;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public List<DropDownModel> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DropDownModel> districtList) {
		this.districtList = districtList;
	}

	public String getDistrictOrderId() {
		return districtOrderId;
	}

	public void setDistrictOrderId(String districtOrderId) {
		this.districtOrderId = districtOrderId;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictStatus() {
		return districtStatus;
	}

	public void setDistrictStatus(String districtStatus) {
		this.districtStatus = districtStatus;
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
