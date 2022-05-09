package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetLoginDetailsRestModel {
	private String userName;
	private String ipAddress;
	private String browDetails;
	private String event;
	private String eventTime;
	private String logout;
	public GetLoginDetailsRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GetLoginDetailsRestModel(Object userName, Object ipAddress, Object browDetails, Object event,
			Object eventTime, Object logout) {
		super();
		this.userName = (String) userName;
		this.ipAddress = (String) ipAddress;
		this.browDetails = (String) browDetails;
		this.event = (String) event;
		this.eventTime = (String) eventTime;
		this.logout = (String) logout;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getBrowDetails() {
		return browDetails;
	}
	public void setBrowDetails(String browDetails) {
		this.browDetails = browDetails;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getLogout() {
		return logout;
	}
	public void setLogout(String logout) {
		this.logout = logout;
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
