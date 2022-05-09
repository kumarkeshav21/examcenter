package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserAuditTrialDetailsRestModel {
	private String userId;
	private String userName;
	private String status;
	private String browser;
	private String event;
	private String eventTime;
	private String logoutTime;

	public UserAuditTrialDetailsRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAuditTrialDetailsRestModel(Object userId, Object userName, Object status, Object browser, Object event,
			Object eventTime, Object logoutTime) {
		super();
		this.userId = (String) userId;
		this.userName = (String) userName;
		this.status = (String) status;
		this.browser = (String) browser;
		this.event = (String) event;
		this.eventTime = (String) eventTime;

		this.logoutTime = (String) logoutTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
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

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
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
