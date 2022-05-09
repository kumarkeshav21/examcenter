package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddUserRestModel {
	private String userType;
	private String userName;
	private String email;
	private String phone;
	private String address;
	private String password;
	
	
	public AddUserRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AddUserRestModel(Object userType, Object userName, Object email, Object phone, Object address,
			Object password) {
		super();
		this.userType = (String) userType;
		this.userName = (String) userName;
		this.email = (String) email;
		this.phone = (String) phone;
		this.address = (String) address;
		this.password = (String) password;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
