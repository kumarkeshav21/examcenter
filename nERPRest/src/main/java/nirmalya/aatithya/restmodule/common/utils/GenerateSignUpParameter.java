package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.user.model.RestSignUpModel;

public class GenerateSignUpParameter {
	public static String getAddSignUpDetailsS(RestSignUpModel details) {

		String s = "";
		
		if (details.getUserId() != null && details.getUserId() != "") {
			s = s + "@p_userId='" + details.getUserId() + "',";
		}
		if (details.getfName() != null && details.getfName() != "") {
			s = s + "@p_fName='" + details.getfName() + "',";
		}
		/*
		 * if (details.getlName()!= null && details.getlName() != "") { s = s +
		 * "@p_lName='" + details.getlName() + "',"; }
		 */
		/*
		 * if (details.getCompanyName() != null && details.getCompanyName() != "") { s =
		 * s + "@p_company='" + details.getCompanyName() + "',"; }
		 */
		if (details.getPassward() != null && details.getPassward() != "") {
			s = s + "@p_password='" + details.getPassward() + "',";
		}
		if (details.getContactPerson() != null && details.getContactPerson() != "") {
			s = s + "@p_contperson='" + details.getContactPerson() + "',";
		}
		if (details.getEmail() != null && details.getEmail() != "") {
			s = s + "@p_email='" + details.getEmail() + "',";
		}
		if (details.getMobileNo() != null && details.getMobileNo() != "") {
			s = s + "@p_mobile='" + details.getMobileNo() + "',";
		}
			if (details.getGstNo() != null && details.getGstNo() != "") {
				s = s + "@p_gstno='" + details.getGstNo() + "',";
			}
			if (details.getAddress() != null && details.getAddress() != "") {
				s = s + "@p_address='" + details.getAddress() + "',";
			}
			if (details.getCountry() != null && details.getCountry() != "") {
				s = s + "@p_country='" + details.getCountry() + "',";
			}
			if (details.getState() != null && details.getState() != "") {
				s = s + "@p_state='" + details.getState() + "',";
			}
			if (details.getPincode() != null && details.getPincode() != "") {
				s = s + "@p_pincode='" + details.getPincode() + "',";
			}
			if (details.getStatus() != null && details.getStatus() != "") {
				s = s + "@p_status='" + details.getStatus() + "',";
			}
			
			

			if (s != "") {
				s = s.substring(0, s.length() - 1);

				s = "SET " + s + ";";
				
			}
			//System.out.println("generate"+s);
		return s;
	}
}
