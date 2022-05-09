package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddCenterAddMoreRestModel {
	private String uid;
    private String cid;
	private String courseId;
	private String shiftid;
	private String date;
	private String fDate;
	private String tDate;
	private String noOfSeats;

	public AddCenterAddMoreRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddCenterAddMoreRestModel(Object uid,Object cid,Object courseId, Object shiftid, Object date, Object fDate, Object tDate,
			Object noOfSeats) {
		super();
		this.uid=(String) uid;
		this.cid = (String) cid;
		this.courseId = (String) courseId;
		this.shiftid = (String) shiftid;
		this.date = (String) date;
		this.fDate = (String) fDate;
		this.tDate = (String) tDate;
		this.noOfSeats = (String) noOfSeats;
	}
	
	public AddCenterAddMoreRestModel(Object cid,Object courseId, Object shiftid, Object date, Object fDate, Object tDate,
			Object noOfSeats) {
		super();
		this.cid = (String) cid;
		this.courseId = (String) courseId;
		this.shiftid = (String) shiftid;
		this.date = (String) date;
		this.fDate = (String) fDate;
		this.tDate = (String) tDate;
		this.noOfSeats = (String) noOfSeats;
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
