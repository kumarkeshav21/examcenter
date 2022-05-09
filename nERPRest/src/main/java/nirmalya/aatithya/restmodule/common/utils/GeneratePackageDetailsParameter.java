package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.user.model.RestPackageSubscriptionModel;

public class GeneratePackageDetailsParameter {
	public static String addPackageDetails(RestPackageSubscriptionModel details) {
		String s = "";

		if (details.getRechargeId() != null && details.getRechargeId() != "") {
			s = s + "@p_rechargeId='" + details.getRechargeId() + "',";
		}
		if (details.getCustId() != null && details.getCustId() != "") {
			s = s + "@p_custId='" + details.getCustId() + "',";
		}

		if (details.getPackageId() != null && details.getPackageId() != "") {
			s = s + "@p_packageId='" + details.getPackageId() + "',";
		}
		if (details.getAmount() != null) {
			s = s + "@p_amount='" + details.getAmount() + "',";
		}
		if (details.getFromDate() != null && details.getFromDate() != "") {
			s = s + "@p_fromDate='" + details.getFromDate() + "',";
		}
		if (details.getToDate() != null && details.getToDate() != "") {
			s = s + "@p_toDate='" + details.getToDate() + "',";
		}
		if (details.getCreatedBy() != null && details.getCreatedBy() != "") {
			s = s + "@p_createdBy='" + details.getCreatedBy() + "',";
		}

		/*
		 * //child table for (LeaveApplyRestModel m : leave) {
		 * 
		 * sitem = sitem + "(@p_leaveId,\"" + m.getLeaveTypeId() + "\",\"" +
		 * m.getFromDate() +"\",\"" + m.getToDate() + "\",\"" + m.getTotalLeave() +
		 * "\",\"" + m.getReason() + "\",\"" + m.getCreatedBy() + "\"),"; } sitem =
		 * sitem.substring(0, sitem.length() - 1);
		 * 
		 * s = s + "@p_itemSubQuery='" + sitem + "',";
		 * 
		 * if (s != "") { s = s.substring(0, s.length() - 1);
		 * 
		 * s = "SET " + s + ";"; }
		 * 
		 * System.out.println("Generate Parameter"+s);
		 * 
		 * return s;
		 */

		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";

		}
		System.out.println("generate" + s);
		return s;

	}

}
