package nirmalya.aatithya.restmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLabDashboardModel {
	public String drRegd;
	public String hospitalRegd;
	public String clinicRegd;
	public String pharmacyRegd;
	public String ambulanceRegd;
	public String bldBnkRegd;
	public String labRegd;
	public String homeHealth;
	public String insuranceCmpny;
	public String diagnoseLabRegd;
	public String organRegd;
	public String bldDonorRegd;
	public String patientName;
	public String mobileNo;
	public String age;
	public String gender;
	public String status;
	public RestLabDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestLabDashboardModel(Object drRegd, Object hospitalRegd, Object clinicRegd, Object pharmacyRegd,
			Object ambulanceRegd, Object bldBnkRegd, Object labRegd, Object homeHealth, Object insuranceCmpny,
			Object diagnoseLabRegd, Object organRegd, Object bldDonorRegd) {
		super();
		this.drRegd = (String)drRegd;
		this.hospitalRegd = (String)hospitalRegd;
		this.clinicRegd = (String)clinicRegd;
		this.pharmacyRegd = (String)pharmacyRegd;
		this.ambulanceRegd = (String)ambulanceRegd;
		this.bldBnkRegd = (String)bldBnkRegd;
		this.labRegd = (String)labRegd;
		this.homeHealth = (String)homeHealth;
		this.insuranceCmpny = (String)insuranceCmpny;
		this.diagnoseLabRegd = (String)diagnoseLabRegd;
		this.organRegd = (String)organRegd;
		this.bldDonorRegd = (String)bldDonorRegd;
	}
	
	
	public RestLabDashboardModel(Object patientName, Object mobileNo, Object age, Object gender, Object status) {
		super();
		this.patientName = (String)patientName;
		this.mobileNo = (String)mobileNo;
		this.age = (String)age;
		this.gender = (String)gender;
		this.status = (String)status;
	}
	
	
	public String getDrRegd() {
		return drRegd;
	}
	public void setDrRegd(String drRegd) {
		this.drRegd = drRegd;
	}
	public String getHospitalRegd() {
		return hospitalRegd;
	}
	public void setHospitalRegd(String hospitalRegd) {
		this.hospitalRegd = hospitalRegd;
	}
	public String getClinicRegd() {
		return clinicRegd;
	}
	public void setClinicRegd(String clinicRegd) {
		this.clinicRegd = clinicRegd;
	}
	public String getPharmacyRegd() {
		return pharmacyRegd;
	}
	public void setPharmacyRegd(String pharmacyRegd) {
		this.pharmacyRegd = pharmacyRegd;
	}
	public String getAmbulanceRegd() {
		return ambulanceRegd;
	}
	public void setAmbulanceRegd(String ambulanceRegd) {
		this.ambulanceRegd = ambulanceRegd;
	}
	public String getBldBnkRegd() {
		return bldBnkRegd;
	}
	public void setBldBnkRegd(String bldBnkRegd) {
		this.bldBnkRegd = bldBnkRegd;
	}
	public String getLabRegd() {
		return labRegd;
	}
	public void setLabRegd(String labRegd) {
		this.labRegd = labRegd;
	}
	public String getHomeHealth() {
		return homeHealth;
	}
	public void setHomeHealth(String homeHealth) {
		this.homeHealth = homeHealth;
	}
	public String getInsuranceCmpny() {
		return insuranceCmpny;
	}
	public void setInsuranceCmpny(String insuranceCmpny) {
		this.insuranceCmpny = insuranceCmpny;
	}
	public String getDiagnoseLabRegd() {
		return diagnoseLabRegd;
	}
	public void setDiagnoseLabRegd(String diagnoseLabRegd) {
		this.diagnoseLabRegd = diagnoseLabRegd;
	}
	public String getOrganRegd() {
		return organRegd;
	}
	public void setOrganRegd(String organRegd) {
		this.organRegd = organRegd;
	}
	public String getBldDonorRegd() {
		return bldDonorRegd;
	}
	public void setBldDonorRegd(String bldDonorRegd) {
		this.bldDonorRegd = bldDonorRegd;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
