package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabDashboardModel {
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
	public LabDashboardModel() {
		super();
		// TODO Auto-generated constructor stub
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
