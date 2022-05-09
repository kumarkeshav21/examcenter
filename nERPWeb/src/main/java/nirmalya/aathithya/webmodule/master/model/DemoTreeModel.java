package nirmalya.aathithya.webmodule.master.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoTreeModel {

	private String demoId;
	private String demoName;
	private String demoParent;
	private String demoSLNo;
	private String demoLevel;
	private String demoStatus;
	private String createdBy;
	private String parentId;
	private BigInteger nodeCount;
	
	public DemoTreeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getParentId() {
		return parentId;
	}

	public BigInteger getNodeCount() {
		return nodeCount;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setNodeCount(BigInteger nodeCount) {
		this.nodeCount = nodeCount;
	}

	public String getDemoId() {
		return demoId;
	}

	public String getDemoName() {
		return demoName;
	}

	public String getDemoParent() {
		return demoParent;
	}

	public String getDemoSLNo() {
		return demoSLNo;
	}

	public String getDemoLevel() {
		return demoLevel;
	}

	public String getDemoStatus() {
		return demoStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setDemoId(String demoId) {
		this.demoId = demoId;
	}

	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	public void setDemoParent(String demoParent) {
		this.demoParent = demoParent;
	}

	public void setDemoSLNo(String demoSLNo) {
		this.demoSLNo = demoSLNo;
	}

	public void setDemoLevel(String demoLevel) {
		this.demoLevel = demoLevel;
	}

	public void setDemoStatus(String demoStatus) {
		this.demoStatus = demoStatus;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
