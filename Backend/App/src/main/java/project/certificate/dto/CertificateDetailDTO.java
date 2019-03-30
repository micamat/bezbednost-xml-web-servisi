package project.certificate.dto;

public class CertificateDetailDTO {
	
	  String commonNameIssuer; String organizationIssuer; String
	  organizationalUnitIssuer;
	 
	String commonName;
	
	  String organization; String organizationalUnit; String serialNumber;
	  
	  String validityBegins; String validityExpires;
	 
	
	
	public String getCommonNameIssuer() {
		return commonNameIssuer;
	}

	public void setCommonNameIssuer(String commonNameIssuer) {
		this.commonNameIssuer = commonNameIssuer;
	}

	public String getOrganizationIssuer() {
		return organizationIssuer;
	}

	public void setOrganizationIssuer(String organizationIssuer) {
		this.organizationIssuer = organizationIssuer;
	}

	public String getOrganizationalUnitIssuer() {
		return organizationalUnitIssuer;
	}

	public void setOrganizationalUnitIssuer(String organizationalUnitIssuer) {
		this.organizationalUnitIssuer = organizationalUnitIssuer;
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

	public String getOrganizationalUnit() {
		return organizationalUnit;
	}

	public void setOrganizationalUnit(String organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getValidityBegins() {
		return validityBegins;
	}

	public void setValidityBegins(String validityBegins) {
		this.validityBegins = validityBegins;
	}

	public String getValidityExpires() {
		return validityExpires;
	}

	public void setValidityExpires(String validityExpires) {
		this.validityExpires = validityExpires;
	}
	 
}
