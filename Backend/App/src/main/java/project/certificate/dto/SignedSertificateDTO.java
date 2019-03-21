package project.certificate.dto;

public class SignedSertificateDTO {


	private String alias;
	private String commonName;
	private String organization;

	
	public SignedSertificateDTO() {
		
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getCommonName() {
		return commonName;
	}


	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}


	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
