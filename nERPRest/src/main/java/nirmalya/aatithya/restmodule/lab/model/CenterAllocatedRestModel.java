package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CenterAllocatedRestModel {

	private String roll;
	private String appno;
	private String candiName;
	private String firCenter;
	private String secCenter;
	private String thirCenter;
	private String allocCent;
	private String examName;
	private String courName;
	
	
	private String noofCandi;
	private String noofallocandi;
	private String noofcent;
	private String remainCandi;
	private String noofseas;

	public CenterAllocatedRestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CenterAllocatedRestModel(Object roll, Object appno, Object candiName, Object firCenter, Object secCenter,
			Object thirCenter, Object allocCent, Object examName, Object courName) {
		super();
		this.roll = (String) roll;
		this.appno = (String) appno;
		this.candiName = (String) candiName;
		this.firCenter = (String) firCenter;
		this.secCenter = (String) secCenter;
		this.thirCenter = (String) thirCenter;
		this.allocCent = (String) allocCent;
		this.examName = (String) examName;
		this.courName = (String) courName;
	}
	
	
	
	

	public CenterAllocatedRestModel(Object noofCandi, Object noofallocandi, Object noofcent, Object remainCandi,
			Object noofseas) {
		super();
		this.noofCandi = (String) noofCandi;
		this.noofallocandi = (String) noofallocandi;
		this.noofcent = (String) noofcent;
		this.remainCandi = (String) remainCandi;
		this.noofseas = (String) noofseas;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public String getCandiName() {
		return candiName;
	}

	public void setCandiName(String candiName) {
		this.candiName = candiName;
	}

	public String getFirCenter() {
		return firCenter;
	}

	public void setFirCenter(String firCenter) {
		this.firCenter = firCenter;
	}

	public String getSecCenter() {
		return secCenter;
	}

	public void setSecCenter(String secCenter) {
		this.secCenter = secCenter;
	}

	public String getThirCenter() {
		return thirCenter;
	}

	public void setThirCenter(String thirCenter) {
		this.thirCenter = thirCenter;
	}
	

	public String getAllocCent() {
		return allocCent;
	}

	public void setAllocCent(String allocCent) {
		this.allocCent = allocCent;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getCourName() {
		return courName;
	}

	public void setCourName(String courName) {
		this.courName = courName;
	}
	
	

	public String getNoofCandi() {
		return noofCandi;
	}

	public void setNoofCandi(String noofCandi) {
		this.noofCandi = noofCandi;
	}

	public String getNoofallocandi() {
		return noofallocandi;
	}

	public void setNoofallocandi(String noofallocandi) {
		this.noofallocandi = noofallocandi;
	}

	public String getNoofcent() {
		return noofcent;
	}

	public void setNoofcent(String noofcent) {
		this.noofcent = noofcent;
	}

	public String getRemainCandi() {
		return remainCandi;
	}

	public void setRemainCandi(String remainCandi) {
		this.remainCandi = remainCandi;
	}

	public String getNoofseas() {
		return noofseas;
	}

	public void setNoofseas(String noofseas) {
		this.noofseas = noofseas;
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
