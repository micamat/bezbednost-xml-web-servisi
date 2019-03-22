package project.certificate.dto;

import project.hierarchy.HierarchyModel;

public class CertificateDTO {

	private SignedSertificateDTO who;
	private Boolean selfSigned;
	private HierarchyModel toWhom;
	private Boolean ca;
	private String keystore;
	private String password;
	private String privatePassword;
	
	private String startDate;
	private String endDate;

	public CertificateDTO() {

	}

	public SignedSertificateDTO getWho() {
		return who;
	}



	public void setWho(SignedSertificateDTO who) {
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

	public String getPrivatePassword() {
		return privatePassword;
	}

	public void setPrivatePassword(String privatePassword) {
		this.privatePassword = privatePassword;
	}

}
