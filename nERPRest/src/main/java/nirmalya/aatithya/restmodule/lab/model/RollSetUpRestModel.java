package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RollSetUpRestModel {
	private String rid;
	private String eid;
	private String examType;
	private String locCode;
	private String distCode;
	private String examCode;
	private String centerCode;
	private String serialNumber;
	
	public RollSetUpRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public RollSetUpRestModel(Object rid,Object eid, Object examType, Object locCode, Object distCode, Object examCode,
			Object centerCode, Object serialNumber) {
		super();
		this.rid = (String) rid;
		this.eid = (String) eid;
		this.examType = (String) examType;
		this.locCode = (String) locCode;
		this.distCode = (String) distCode;
		this.examCode = (String) examCode;
		this.centerCode = (String) centerCode;
		this.serialNumber = (String) serialNumber;
	}



	public RollSetUpRestModel(Object examType, Object locCode, Object distCode, Object examCode, Object centerCode,
			Object serialNumber) {
		super();
		this.examType = (String) examType;
		this.locCode = (String) locCode;
		this.distCode = (String) distCode;
		this.examCode = (String) examCode;
		this.centerCode = (String) centerCode;
		this.serialNumber = (String) serialNumber;
	}
	
	
	
	public String getRid() {
		return rid;
	}



	public void setRid(String rid) {
		this.rid = rid;
	}



	public String getExamType() {
		return examType;
	}
	
	

	public String getEid() {
		return eid;
	}



	public void setEid(String eid) {
		this.eid = eid;
	}



	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
