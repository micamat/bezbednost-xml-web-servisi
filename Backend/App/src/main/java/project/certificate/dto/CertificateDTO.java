package project.certificate.dto;

import project.hierarchy.HierarchyModel;

public class CertificateDTO {

	private String who;
	private Boolean selfSigned;
	private HierarchyModel toWhom;
	private Boolean ca;
	private String keystore;
	private String password;
	
	private String startDate;
	private String endDate;

	public CertificateDTO() {

	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public Boolean getSelfSigned() {
		return selfSigned;
	}

	public void setSelfSigned(Boolean selfSigned) {
		this.selfSigned = selfSigned;
	}

	public HierarchyModel getToWhom() {
		return toWhom;
	}

	public void setToWhom(HierarchyModel toWhom) {
		this.toWhom = toWhom;
	}

	public Boolean getCa() {
		return ca;
	}

	public void setCa(Boolean ca) {
		this.ca = ca;
	}

	public String getKeystore() {
		return keystore;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
