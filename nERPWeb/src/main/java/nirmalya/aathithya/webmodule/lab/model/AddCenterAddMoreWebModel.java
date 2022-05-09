package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;

public class AddCenterAddMoreWebModel {
	private String uid;
	private String cid;
	private String courseId;
	private String shiftid;
	private String date;
	private String fDate;
	private String tDate;
	private String noOfSeats;
	private List<DropDownModel> courseList = new ArrayList<DropDownModel>();
	List<DropDownModel> getshiftList = new ArrayList<DropDownModel>();

	public AddCenterAddMoreWebModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getShiftid() {
		return shiftid;
	}

	public void setShiftid(String shiftid) {
		this.shiftid = shiftid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public String getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(String noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public List<DropDownModel> getCourseList() {
		return courseList;
	}


	public void setCourseList(List<DropDownModel> courseList) {
		this.courseList = courseList;
	}


	public List<DropDownModel> getGetshiftList() {
		return getshiftList;
	}


	public void setGetshiftList(List<DropDownModel> getshiftList) {
		this.getshiftList = getshiftList;
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
