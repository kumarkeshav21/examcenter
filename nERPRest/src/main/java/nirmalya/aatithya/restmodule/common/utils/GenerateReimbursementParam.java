package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

public class GenerateReimbursementParam {

	/*
	 * public static String getReimbursementParam(ReimbursementModel
	 * reimbursementModel) {
	 * 
	 * String s = "";
	 * 
	 * if (reimbursementModel.getReimbursementReqId() != null &&
	 * reimbursementModel.getReimbursementReqId() != "") { s = s +
	 * "@p_reimbursementReqId='" + reimbursementModel.getReimbursementReqId() +
	 * "',"; }
	 * 
	 * if (reimbursementModel.getPlaceName() != null &&
	 * reimbursementModel.getPlaceName() != "") { s = s + "@p_placeName='" +
	 * reimbursementModel.getPlaceName() + "',"; }
	 * 
	 * if (reimbursementModel.getFromDate() != null &&
	 * reimbursementModel.getFromDate() != "") { s = s + "@p_fromDate='" +
	 * reimbursementModel.getFromDate() + "',"; }
	 * 
	 * if (reimbursementModel.getToDate() != null || reimbursementModel.getToDate()
	 * != "") { s = s + "@p_toDate='" + reimbursementModel.getToDate() + "',"; }
	 * 
	 * if (reimbursementModel.getPurpose() != null &&
	 * reimbursementModel.getPurpose() != "") { s = s + "@p_purpose='" +
	 * reimbursementModel.getPurpose() + "',"; }
	 * 
	 * 
	 * if (reimbursementModel.getAdvanceReq() != null ||
	 * reimbursementModel.getAdvanceReq() != "") { s = s + "@p_advanceReq='" +
	 * reimbursementModel.getAdvanceReq() + "',"; }
	 * 
	 * 
	 * if (reimbursementModel.getAdvanceAmount() != null) { s = s +
	 * "@p_advanceAmount='" + reimbursementModel.getAdvanceAmount() + "',"; }
	 * 
	 * 
	 * if(reimbursementModel.getApproveStatus() != null ||
	 * reimbursementModel.getApproveStatus() != "") { s = s + "@p_approveStatus='" +
	 * reimbursementModel.getApproveStatus() + "',"; }
	 * 
	 * 
	 * if (reimbursementModel.getCreatedBy() != null ||
	 * reimbursementModel.getCreatedBy() != "") { s = s + "@p_createdBy='" +
	 * reimbursementModel.getCreatedBy() + "',"; }
	 * 
	 * if (s != "") { s = s.substring(0, s.length() - 1);
	 * 
	 * s = "SET " + s + ";"; } return s;
	 * 
	 * }
	 */

	/*
	 * public static String getRequisitionParam(List<ReimbursementModel>
	 * restReimbursementModel) { String s = ""; //String litem = ""; //String
	 * comments = ""; String document = ""; String reimbursementReqId = ""; String
	 * reimTypeId = ""; String reimPolicyId = ""; String expenseDate = ""; String
	 * expenseDesc = ""; String expenseAmount = ""; String referenceNo = ""; String
	 * createdBy = ""; for (ReimbursementModel m : restReimbursementModel) {
	 * reimbursementReqId = m.getReimbursementReqId(); reimTypeId =
	 * m.getReimTypeId(); reimPolicyId = m.getReimPolicyId(); // expenseDate =
	 * m.DateFormatter.getStringDate(m.getExpenseDate()); expenseDesc =
	 * m.getExpenseDesc(); expenseAmount = m.getExpenseAmount(); referenceNo =
	 * m.getReferenceNo(); createdBy = m.getCreatedBy(); } s = s +
	 * "@p_reimbursementReqId='" + reimbursementReqId + "',"; s = s +
	 * "@p_reimTypeId='" + reimTypeId + "',"; s = s + "@p_reimPolicyId='" +
	 * reimPolicyId + "',"; s = s + "@p_expenseDate='" + expenseDate + "',";
	 * 
	 * s = s + "@p_expenseDesc='" + expenseDesc + "',"; s = s + "@p_expenseAmount='"
	 * + expenseAmount + "',"; s = s + "@p_referenceNo='" + referenceNo + "',"; s =
	 * s + "@p_createdBy='" + createdBy + "',";
	 * 
	 * 
	 * for (ReimbursementModel m : restReimbursementModel) {
	 *
	 * litem = litem + "(@p_reimbursementReqId,\"" + m.getItemId() + "\",\"" +
	 * m.getSku() + "\",\"" + m.getLocationId() + "\",\"" + m.getQty() + "\",\"" +
	 * m.getUomId() + "\",\"" + m.getEstimatedTotalPrice() + "\",\"" +
	 * m.getEstimatedPrice() + "\",\"" + m.getCreatedBy() + "\"),";
	 *
	 * } litem = litem.substring(0, litem.length() - 1);
	 *
	 * s = s + "@p_litemSubQuery='" + litem + "',";
	 * 
	 * 
	 * if (!document.isEmpty()) { document = document.substring(0, document.length()
	 * - 1); s = s + "@p_vendorDocuments='" + document + "',"; }
	 * 
	 * if (s != "") { s = s.substring(0, s.length() - 1);
	 * 
	 * s = "SET " + s + ";"; }
	 * 
	 * return s; }
	 */
}