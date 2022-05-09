package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AdmitCardWebModel {
	private String roll;
	private String applicationNo;
	private String candiName;
	private String candiFatherName;
	private String gender;
	private String dateOfBirth;
	private String catogary;
	private String personWithDisablity;
	private String barCode;
	private String signature;
	private String quesPaperMedium;
	private String dateOfExam;
	private String reportingTime;
	private String gateClosingTime;
	private String testCenterName;
	private String testCenterAddress;
	private String seniorDirectorSignature;

	public AdmitCardWebModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getCandiName() {
		return candiName;
	}

	public void setCandiName(String candiName) {
		this.candiName = candiName;
	}

	public String getCandiFatherName() {
		return candiFatherName;
	}

	public void setCandiFatherName(String candiFatherName) {
		this.candiFatherName = candiFatherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCatogary() {
		return catogary;
	}

	public void setCatogary(String catogary) {
		this.catogary = catogary;
	}

	public String getPersonWithDisablity() {
		return personWithDisablity;
	}

	public void setPersonWithDisablity(String personWithDisablity) {
		this.personWithDisablity = personWithDisablity;
	}


	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getQuesPaperMedium() {
		return quesPaperMedium;
	}

	public void setQuesPaperMedium(String quesPaperMedium) {
		this.quesPaperMedium = quesPaperMedium;
	}

	public String getDateOfExam() {
		return dateOfExam;
	}

	public void setDateOfExam(String dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public String getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}

	public String getGateClosingTime() {
		return gateClosingTime;
	}

	public void setGateClosingTime(String gateClosingTime) {
		this.gateClosingTime = gateClosingTime;
	}

	public String getTestCenterName() {
		return testCenterName;
	}

	public void setTestCenterName(String testCenterName) {
		this.testCenterName = testCenterName;
	}

	public String getTestCenterAddress() {
		return testCenterAddress;
	}

	public void setTestCenterAddress(String testCenterAddress) {
		this.testCenterAddress = testCenterAddress;
	}

	public String getSeniorDirectorSignature() {
		return seniorDirectorSignature;
	}

	public void setSeniorDirectorSignature(String seniorDirectorSignature) {
		this.seniorDirectorSignature = seniorDirectorSignature;
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
