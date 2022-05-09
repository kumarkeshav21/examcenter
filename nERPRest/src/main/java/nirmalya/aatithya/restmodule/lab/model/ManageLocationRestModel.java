package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ManageLocationRestModel {
	private String sId;;
	private String locationName;;
	private String status;
	private String action;
	public ManageLocationRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ManageLocationRestModel(Object sId, Object locationName, Object status, Object action) {
		super();
		this.sId = (String) sId;
		this.locationName = (String) locationName;
		this.status = (String) status;
		this.action = (String) action;
	}


	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
