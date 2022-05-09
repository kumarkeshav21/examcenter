package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.user.model.RestProcessMasterModel;

public class GenerateAuthorityParameter {

	public static String saveProcessMaster(RestProcessMasterModel id) {
		String s = "";
		if(id.gettProcess()!=null && id.gettProcess()!="") {
			s = s + "@P_Process='" + id.gettProcess() + "',";
		}
		if(id.gettProcessName()!=null && id.gettProcessName()!="") {
			s = s + "@P_ProcessName='" + id.gettProcessName() + "',";
		}
		if(id.gettProcessDescription()!=null && id.gettProcessDescription()!="") {
			s = s + "@P_ProcessDesc='" + id.gettProcessDescription() + "',";
		}
		if(id.gettProcessCreatedBy()!=null && id.gettProcessCreatedBy()!="") {
			s = s + "@P_CreatedBy='" + id.gettProcessCreatedBy() + "',";
		}
		if(id.gettProcessStatus()!=null) {
			s = s + "@P_Status=" + id.gettProcessStatus() + ",";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		
		System.out.println(s);
		
		return s;
	}

}
