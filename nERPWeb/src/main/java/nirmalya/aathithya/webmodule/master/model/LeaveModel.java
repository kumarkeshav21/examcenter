package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LeaveModel {
	private String leaveId;	
	private String leaveName;	
	private Double leavePeriod;
	private String leaveAssgnEmp;
	private String leaveEmpApply;
	private String leaveApplyMore;
	private String leaveAcrEnable;
	private String leaveCarriedForward;
	private Double leaveFrwdPercentage;
	private Double leaveMaxCrdAmount;
	private String leaveAvaiableTime;
	private String leaveProperJdate;
	private String leaveSentEmail;
	private String leaveCreatedOn;
	private String leaveUpdatedOn;
	private String createdBy;
	private String status;
	private String deletedFlag;
	
	//Leave Rule
		private String leaveRuleId;
		private String leaveRuleType;
		private String jobTitle;
		private String leaveEmpStatus;
		private String leaveEmp;
		private String leaveRuleDept;
		private String leaveEmpPeriod;
		private String leavePerPeriod;
		private String leavePerMonth;
		private String adminAsgnEmp;
		private String empApplyLeave;
		private String empApplyMore;
		private String leaveEmpAcrEnable;
		private String leaveEmpFrwd;
		private Double leavePrcntg;
		private Double maxFrwdAmt;
		private String avlblLeavePeriod;
		private String properJoinDate;
		private String createdOn;
		private String leaveupdatedOn;
		private String leaveCreatedBy;
		private String leaveDeletedFlag;

		
	public LeaveModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public String getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public Double getLeavePeriod() {
		return leavePeriod;
	}
	public void setLeavePeriod(Double leavePeriod) {
		this.leavePeriod = leavePeriod;
	}
	public String getLeaveAssgnEmp() {
		return leaveAssgnEmp;
	}
	public void setLeaveAssgnEmp(String leaveAssgnEmp) {
		this.leaveAssgnEmp = leaveAssgnEmp;
	}
	public String getLeaveEmpApply() {
		return leaveEmpApply;
	}
	public void setLeaveEmpApply(String leaveEmpApply) {
		this.leaveEmpApply = leaveEmpApply;
	}
	public String getLeaveApplyMore() {
		return leaveApplyMore;
	}
	public void setLeaveApplyMore(String leaveApplyMore) {
		this.leaveApplyMore = leaveApplyMore;
	}
	public String getLeaveAcrEnable() {
		return leaveAcrEnable;
	}
	public void setLeaveAcrEnable(String leaveAcrEnable) {
		this.leaveAcrEnable = leaveAcrEnable;
	}
	public String getLeaveCarriedForward() {
		return leaveCarriedForward;
	}
	public void setLeaveCarriedForward(String leaveCarriedForward) {
		this.leaveCarriedForward = leaveCarriedForward;
	}
	public Double getLeaveFrwdPercentage() {
		return leaveFrwdPercentage;
	}
	public void setLeaveFrwdPercentage(Double leaveFrwdPercentage) {
		this.leaveFrwdPercentage = leaveFrwdPercentage;
	}
	public Double getLeaveMaxCrdAmount() {
		return leaveMaxCrdAmount;
	}
	public void setLeaveMaxCrdAmount(Double leaveMaxCrdAmount) {
		this.leaveMaxCrdAmount = leaveMaxCrdAmount;
	}
	public String getLeaveAvaiableTime() {
		return leaveAvaiableTime;
	}
	public void setLeaveAvaiableTime(String leaveAvaiableTime) {
		this.leaveAvaiableTime = leaveAvaiableTime;
	}
	public String getLeaveProperJdate() {
		return leaveProperJdate;
	}
	public void setLeaveProperJdate(String leaveProperJdate) {
		this.leaveProperJdate = leaveProperJdate;
	}
	public String getLeaveSentEmail() {
		return leaveSentEmail;
	}
	public void setLeaveSentEmail(String leaveSentEmail) {
		this.leaveSentEmail = leaveSentEmail;
	}
	public String getLeaveCreatedOn() {
		return leaveCreatedOn;
	}
	public void setLeaveCreatedOn(String leaveCreatedOn) {
		this.leaveCreatedOn = leaveCreatedOn;
	}
	public String getLeaveUpdatedOn() {
		return leaveUpdatedOn;
	}
	public void setLeaveUpdatedOn(String leaveUpdatedOn) {
		this.leaveUpdatedOn = leaveUpdatedOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}



	public String getLeaveRuleId() {
		return leaveRuleId;
	}



	public void setLeaveRuleId(String leaveRuleId) {
		this.leaveRuleId = leaveRuleId;
	}



	public String getLeaveRuleType() {
		return leaveRuleType;
	}



	public void setLeaveRuleType(String leaveRuleType) {
		this.leaveRuleType = leaveRuleType;
	}



	public String getJobTitle() {
		return jobTitle;
	}



	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}



	public String getLeaveEmpStatus() {
		return leaveEmpStatus;
	}



	public void setLeaveEmpStatus(String leaveEmpStatus) {
		this.leaveEmpStatus = leaveEmpStatus;
	}



	public String getLeaveEmp() {
		return leaveEmp;
	}



	public void setLeaveEmp(String leaveEmp) {
		this.leaveEmp = leaveEmp;
	}



	public String getLeaveRuleDept() {
		return leaveRuleDept;
	}



	public void setLeaveRuleDept(String leaveRuleDept) {
		this.leaveRuleDept = leaveRuleDept;
	}



	public String getLeaveEmpPeriod() {
		return leaveEmpPeriod;
	}



	public void setLeaveEmpPeriod(String leaveEmpPeriod) {
		this.leaveEmpPeriod = leaveEmpPeriod;
	}

	public String getLeavePerPeriod() {
		return leavePerPeriod;
	}



	public void setLeavePerPeriod(String leavePerPeriod) {
		this.leavePerPeriod = leavePerPeriod;
	}



	public String getLeavePerMonth() {
		return leavePerMonth;
	}



	public void setLeavePerMonth(String leavePerMonth) {
		this.leavePerMonth = leavePerMonth;
	}



	public String getAdminAsgnEmp() {
		return adminAsgnEmp;
	}



	public void setAdminAsgnEmp(String adminAsgnEmp) {
		this.adminAsgnEmp = adminAsgnEmp;
	}



	public String getEmpApplyLeave() {
		return empApplyLeave;
	}



	public void setEmpApplyLeave(String empApplyLeave) {
		this.empApplyLeave = empApplyLeave;
	}



	public String getEmpApplyMore() {
		return empApplyMore;
	}



	public void setEmpApplyMore(String empApplyMore) {
		this.empApplyMore = empApplyMore;
	}



	public String getLeaveEmpAcrEnable() {
		return leaveEmpAcrEnable;
	}



	public void setLeaveEmpAcrEnable(String leaveEmpAcrEnable) {
		this.leaveEmpAcrEnable = leaveEmpAcrEnable;
	}



	public String getLeaveEmpFrwd() {
		return leaveEmpFrwd;
	}



	public void setLeaveEmpFrwd(String leaveEmpFrwd) {
		this.leaveEmpFrwd = leaveEmpFrwd;
	}



	public Double getLeavePrcntg() {
		return leavePrcntg;
	}



	public void setLeavePrcntg(Double leavePrcntg) {
		this.leavePrcntg = leavePrcntg;
	}



	public Double getMaxFrwdAmt() {
		return maxFrwdAmt;
	}



	public void setMaxFrwdAmt(Double maxFrwdAmt) {
		this.maxFrwdAmt = maxFrwdAmt;
	}



	public String getAvlblLeavePeriod() {
		return avlblLeavePeriod;
	}



	public void setAvlblLeavePeriod(String avlblLeavePeriod) {
		this.avlblLeavePeriod = avlblLeavePeriod;
	}



	public String getProperJoinDate() {
		return properJoinDate;
	}



	public void setProperJoinDate(String properJoinDate) {
		this.properJoinDate = properJoinDate;
	}



	public String getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}



	public String getLeaveupdatedOn() {
		return leaveupdatedOn;
	}



	public void setLeaveupdatedOn(String leaveupdatedOn) {
		this.leaveupdatedOn = leaveupdatedOn;
	}



	public String getLeaveCreatedBy() {
		return leaveCreatedBy;
	}



	public void setLeaveCreatedBy(String leaveCreatedBy) {
		this.leaveCreatedBy = leaveCreatedBy;
	}



	public String getLeaveDeletedFlag() {
		return leaveDeletedFlag;
	}



	public void setLeaveDeletedFlag(String leaveDeletedFlag) {
		this.leaveDeletedFlag = leaveDeletedFlag;
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
