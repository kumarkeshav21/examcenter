package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EditInstructionRestModel {
	private String sId;
	private String exams;
	private String instruction;
	private String position;
	private String status;
	public EditInstructionRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EditInstructionRestModel(Object sId, Object exams, Object instruction, Object position, Object status) {
		super();
		this.sId = (String) sId;
		this.exams = (String) exams;
		this.instruction = (String) instruction;
		this.position = (String) position;
		this.status = (String) status;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getExams() {
		return exams;
	}
	public void setExams(String exams) {
		this.exams = exams;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
