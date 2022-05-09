package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LeavePolicyModel {
	
	private String leaveId;
	private String empID;
	private String empName;
	private String leaveApplyDate;
	private String status;
	private String leaveTypeId;
	private String leaveType;
	private String fromDate;
	private String toDate;
	private Double totalLeave;
	private String reason;
	private Double availableLeave;
	private String createdBy;
	private Integer slNo;
	
	
	
	public LeavePolicyModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	public String getLeaveTypeId() {
		return leaveTypeId;
	}




	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}




	public Integer getSlNo() {
		return slNo;
	}




	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}




	public String getLeaveId() {
		return leaveId;
	}




	public String getEmpID() {
		return empID;
	}




	public String getEmpName() {
		return empName;
	}




	public String getLeaveApplyDate() {
		return leaveApplyDate;
	}




	public String getStatus() {
		return status;
	}




	public String getLeaveType() {
		return leaveType;
	}




	public String getFromDate() {
		return fromDate;
	}




	public String getToDate() {
		return toDate;
	}




	public Double getTotalLeave() {
		return totalLeave;
	}




	public String getReason() {
		return reason;
	}




	public Double getAvailableLeave() {
		return availableLeave;
	}




	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}




	public void setEmpID(String empID) {
		this.empID = empID;
	}




	public void setEmpName(String empName) {
		this.empName = empName;
	}




	public void setLeaveApplyDate(String leaveApplyDate) {
		this.leaveApplyDate = leaveApplyDate;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}




	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}




	public void setToDate(String toDate) {
		this.toDate = toDate;
	}




	public void setTotalLeave(Double totalLeave) {
		this.totalLeave = totalLeave;
	}




	public void setReason(String reason) {
		this.reason = reason;
	}




	public void setAvailableLeave(Double availableLeave) {
		this.availableLeave = availableLeave;
	}




	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
