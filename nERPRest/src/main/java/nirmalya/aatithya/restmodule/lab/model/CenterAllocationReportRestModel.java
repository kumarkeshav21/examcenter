package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CenterAllocationReportRestModel {
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
	public CenterAllocationReportRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CenterAllocationReportRestModel(Object sId, Object studentName, Object examName, Object centerCode,
			Object centerName, Object location, Object seatNo, Object shift, Object dateTime) {
		super();
		this.sId = (String) sId;
		this.studentName = (String) studentName;
		this.examName = (String) examName;
		this.centerCode = (String) centerCode;
		this.centerName = (String) centerName;
		this.location = (String) location;
		this.seatNo = (String) seatNo;
		this.shift = (String) shift;
		this.dateTime = (String) dateTime;
	}
	
	public CenterAllocationReportRestModel(Object noofappliedcandidate, Object noofallocatedstudent) {
		super();
		this.noofappliedcandidate = (String) noofappliedcandidate;
		this.noofallocatedstudent = (String) noofallocatedstudent;
		
		
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


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
