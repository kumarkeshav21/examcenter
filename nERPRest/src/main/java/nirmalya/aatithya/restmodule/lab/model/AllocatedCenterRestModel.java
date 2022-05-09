package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AllocatedCenterRestModel {
	private String appNumber;
	private String candiName;
	private String firstCenter;
	private String secondCenter;
	private String thirdCenter;
	private String alloCenter;
	
	public AllocatedCenterRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AllocatedCenterRestModel(Object appNumber, Object candiName, Object firstCenter, Object secondCenter,
			Object thirdCenter, Object alloCenter) {
		super();
		this.appNumber = (String) appNumber;
		this.candiName = (String) candiName;
		this.firstCenter = (String) firstCenter;
		this.secondCenter = (String) secondCenter;
		this.thirdCenter = (String) thirdCenter;
		this.alloCenter = (String) alloCenter;
	}

	public String getAppNumber() {
		return appNumber;
	}

	public void setAppNumber(String appNumber) {
		this.appNumber = appNumber;
	}

	public String getCandiName() {
		return candiName;
	}

	public void setCandiName(String candiName) {
		this.candiName = candiName;
	}

	public String getFirstCenter() {
		return firstCenter;
	}

	public void setFirstCenter(String firstCenter) {
		this.firstCenter = firstCenter;
	}

	public String getSecondCenter() {
		return secondCenter;
	}

	public void setSecondCenter(String secondCenter) {
		this.secondCenter = secondCenter;
	}

	public String getThirdCenter() {
		return thirdCenter;
	}

	public void setThirdCenter(String thirdCenter) {
		this.thirdCenter = thirdCenter;
	}

	public String getAlloCenter() {
		return alloCenter;
	}

	public void setAlloCenter(String alloCenter) {
		this.alloCenter = alloCenter;
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
