package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CandidateListRestModel {
	private String sId;
	private String applicationId;
	private String candidateId;
	private String dob;
	private String gender;
	private String catogary;
	private String applyFor;
	private String action;
	
	public CandidateListRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CandidateListRestModel(Object sId, Object applicationId, Object candidateId, Object dob, Object gender,
			Object catogary, Object applyFor, Object action) {
		super();
		this.sId = (String) sId;
		this.applicationId = (String) applicationId;
		this.candidateId = (String) candidateId;
		this.dob = (String) dob;
		this.gender = (String) gender;
		this.catogary = (String) catogary;
		this.applyFor = (String) applyFor;
		this.action = (String) action;
	}

	public Object getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCatogary() {
		return catogary;
	}

	public void setCatogary(String catogary) {
		this.catogary = catogary;
	}

	public String getApplyFor() {
		return applyFor;
	}

	public void setApplyFor(String applyFor) {
		this.applyFor = applyFor;
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
