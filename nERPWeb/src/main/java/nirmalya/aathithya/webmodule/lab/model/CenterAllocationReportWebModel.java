package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CenterAllocationReportWebModel {
	private String sId;
	private String studentName;
	private String examName;
	private String centerCode;
	private String centerName;
	private String location;
	private String seatNo;
	private String shift;
	private String dateTime;
	private String noofappliedcandidate;
	private String noofallocatedstudent;

	public CenterAllocationReportWebModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getNoofappliedcandidate() {
		return noofappliedcandidate;
	}

	public void setNoofappliedcandidate(String noofappliedcandidate) {
		this.noofappliedcandidate = noofappliedcandidate;
	}

	public String getNoofallocatedstudent() {
		return noofallocatedstudent;
	}

	public void setNoofallocatedstudent(String noofallocatedstudent) {
		this.noofallocatedstudent = noofallocatedstudent;
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
