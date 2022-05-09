package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabDashboardWebModel {
	private String angul;
	private String balangir;
	private String balasor;
	private String bargarh;
	private String baripada;
	private String berhampur;
	private String bhadrak;
	private String bhubaneswar;
	private String cuttack;
	private String dhenkanal;
	private String jagatsinghpur;
	private String jajpur;
	private String jeypore;
	private String jharsuguda;
	private String keonjhar;
	private String kolkata;
	private String nayagarh;
	private String paralakhemundi;
	private String patna;
	private String phulbani;
	private String ranchi;
	private String rayagada;
	private String rourkela;
	private String sambalpur;
	public LabDashboardWebModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getAngul() {
		return angul;
	}


	public void setAngul(String angul) {
		this.angul = angul;
	}


	public String getBalangir() {
		return balangir;
	}


	public void setBalangir(String balangir) {
		this.balangir = balangir;
	}


	public String getBalasor() {
		return balasor;
	}


	public void setBalasor(String balasor) {
		this.balasor = balasor;
	}


	public String getBargarh() {
		return bargarh;
	}


	public void setBargarh(String bargarh) {
		this.bargarh = bargarh;
	}


	public String getBaripada() {
		return baripada;
	}


	public void setBaripada(String baripada) {
		this.baripada = baripada;
	}


	public String getBerhampur() {
		return berhampur;
	}


	public void setBerhampur(String berhampur) {
		this.berhampur = berhampur;
	}


	public String getBhadrak() {
		return bhadrak;
	}


	public void setBhadrak(String bhadrak) {
		this.bhadrak = bhadrak;
	}


	public String getBhubaneswar() {
		return bhubaneswar;
	}


	public void setBhubaneswar(String bhubaneswar) {
		this.bhubaneswar = bhubaneswar;
	}


	public String getCuttack() {
		return cuttack;
	}


	public void setCuttack(String cuttack) {
		this.cuttack = cuttack;
	}


	public String getDhenkanal() {
		return dhenkanal;
	}


	public void setDhenkanal(String dhenkanal) {
		this.dhenkanal = dhenkanal;
	}


	public String getJagatsinghpur() {
		return jagatsinghpur;
	}


	public void setJagatsinghpur(String jagatsinghpur) {
		this.jagatsinghpur = jagatsinghpur;
	}


	public String getJajpur() {
		return jajpur;
	}


	public void setJajpur(String jajpur) {
		this.jajpur = jajpur;
	}


	public String getJeypore() {
		return jeypore;
	}


	public void setJeypore(String jeypore) {
		this.jeypore = jeypore;
	}


	public String getJharsuguda() {
		return jharsuguda;
	}


	public void setJharsuguda(String jharsuguda) {
		this.jharsuguda = jharsuguda;
	}


	public String getKeonjhar() {
		return keonjhar;
	}


	public void setKeonjhar(String keonjhar) {
		this.keonjhar = keonjhar;
	}


	public String getKolkata() {
		return kolkata;
	}


	public void setKolkata(String kolkata) {
		this.kolkata = kolkata;
	}


	public String getNayagarh() {
		return nayagarh;
	}


	public void setNayagarh(String nayagarh) {
		this.nayagarh = nayagarh;
	}


	public String getParalakhemundi() {
		return paralakhemundi;
	}


	public void setParalakhemundi(String paralakhemundi) {
		this.paralakhemundi = paralakhemundi;
	}


	public String getPatna() {
		return patna;
	}


	public void setPatna(String patna) {
		this.patna = patna;
	}


	public String getPhulbani() {
		return phulbani;
	}


	public void setPhulbani(String phulbani) {
		this.phulbani = phulbani;
	}


	public String getRanchi() {
		return ranchi;
	}


	public void setRanchi(String ranchi) {
		this.ranchi = ranchi;
	}


	public String getRayagada() {
		return rayagada;
	}


	public void setRayagada(String rayagada) {
		this.rayagada = rayagada;
	}


	public String getRourkela() {
		return rourkela;
	}


	public void setRourkela(String rourkela) {
		this.rourkela = rourkela;
	}


	public String getSambalpur() {
		return sambalpur;
	}


	public void setSambalpur(String sambalpur) {
		this.sambalpur = sambalpur;
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
