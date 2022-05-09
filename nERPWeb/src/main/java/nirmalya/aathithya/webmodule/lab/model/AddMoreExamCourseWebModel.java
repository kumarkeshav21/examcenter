package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;

public class AddMoreExamCourseWebModel {
	private String cid;
	private String courseName;
	private List<DropDownModel> courseList = new ArrayList<DropDownModel>();
	

	public AddMoreExamCourseWebModel() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	


	public List<DropDownModel> getCourseList() {
		return courseList;
	}


	public void setCourseList(List<DropDownModel> courseList) {
		this.courseList = courseList;
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
