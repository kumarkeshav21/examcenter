package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddMoreExamCourseRestModel {
	private String cid;
	private String courseName;

	public AddMoreExamCourseRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddMoreExamCourseRestModel(Object courseName) {
		super();
		this.courseName = (String) courseName;
	}
	
	public AddMoreExamCourseRestModel(Object cid, Object courseName) {
		super();
		this.cid = (String) cid;
		this.courseName = (String) courseName;
	}
	
	
	

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
