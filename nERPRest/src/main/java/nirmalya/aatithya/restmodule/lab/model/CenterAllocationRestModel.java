package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CenterAllocationRestModel {
	private String examName;
	private String dateTime;
	private String locationName;
	private String processType;
	private String courseName;
	private String status;
	private String rollno;
	private String studentName;
	private String examType;
	private String courseType;
	private String centerName;
	private String examDate;
	private String noofcandidates;
	private String noofcenters;
	private String noofseats;
	private String allocatedcandidates;
	private String remaining;
	private Integer totalPageno;
	private String rollCode;
	
	public CenterAllocationRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CenterAllocationRestModel(Object examName,Object rollCode) {
		super();
		this.examName = (String) examName;
		this.rollCode = (String) rollCode;
		
	}
	
	public CenterAllocationRestModel(String examName, String dateTime, String locationName, String processType) {
		super();
		this.examName = examName;
		this.dateTime = dateTime;
		this.locationName = locationName;
		this.processType = processType;
	}
	public CenterAllocationRestModel(Object rollno,Object studentName,Object examType,Object courseType,Object centerName,Object examDate,Object totalPageno) {
		super();
		this.rollno = (String) rollno;
		this.studentName = (String) studentName;
		this.examType = (String) examType;
		this.courseType = (String) courseType;
		this.centerName = (String) centerName;
		this.examDate = (String) examDate;
		this.totalPageno = (Integer) totalPageno;
	}
	public CenterAllocationRestModel(Object rollno,Object studentName,Object examType,Object courseType,Object centerName,Object examDate) {
		super();
		this.rollno = (String) rollno;
		this.studentName = (String) studentName;
		this.examType = (String) examType;
		this.courseType = (String) courseType;
		this.centerName = (String) centerName;
		this.examDate = (String) examDate;
	}
	public CenterAllocationRestModel(Object noofcandidates,Object noofcenters,Object noofseats,Object allocatedcandidates,Object remaining) {
		super();
		this.noofcandidates = (String) noofcandidates;
		this.noofcenters = (String) noofcenters;
		this.noofseats = (String) noofseats;
		this.allocatedcandidates = (String) allocatedcandidates;
		this.remaining = (String) remaining;
	}
	
	
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	
	public String getNoofcandidates() {
		return noofcandidates;
	}
	public void setNoofcandidates(String noofcandidates) {
		this.noofcandidates = noofcandidates;
	}
	public String getNoofcenters() {
		return noofcenters;
	}
	public void setNoofcenters(String noofcenters) {
		this.noofcenters = noofcenters;
	}
	public String getNoofseats() {
		return noofseats;
	}
	public void setNoofseats(String noofseats) {
		this.noofseats = noofseats;
	}
	public String getAllocatedcandidates() {
		return allocatedcandidates;
	}
	public void setAllocatedcandidates(String allocatedcandidates) {
		this.allocatedcandidates = allocatedcandidates;
	}
	public String getRemaining() {
		return remaining;
	}
	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}
	
	
	
	public Integer getTotalPageno() {
		return totalPageno;
	}
	public void setTotalPageno(Integer totalPageno) {
		this.totalPageno = totalPageno;
	}
	
	public String getRollCode() {
		return rollCode;
	}

	public void setRollCode(String rollCode) {
		this.rollCode = rollCode;
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
