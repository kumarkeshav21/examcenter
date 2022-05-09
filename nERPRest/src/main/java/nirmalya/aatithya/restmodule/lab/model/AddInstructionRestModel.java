package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddInstructionRestModel {
	private String examType;
	private String instruction;
	private String position;
	private String status;
	public AddInstructionRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddInstructionRestModel(Object examType,Object instruction, Object position, Object status) {
		super();
		this.examType = (String) examType;
		this.instruction = (String) instruction;
		this.position = (String) position;
		this.status = (String) status;
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
	public void setPosition(String postion) {
		this.position = postion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
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
