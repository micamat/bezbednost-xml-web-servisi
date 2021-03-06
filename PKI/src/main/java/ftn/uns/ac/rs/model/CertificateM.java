package ftn.uns.ac.rs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CertificateM {
	
	public CertificateM() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	String alias;
	
	@Column(nullable = false)
	String keyStore;
	
	@Column(nullable = false)
	String trustStore;
	
	@Column(nullable = false)
	Boolean revoked;
	
	@Column(nullable = false)
	String revokedReason;
	
	@Column(nullable = false)
	Boolean ca;

	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getKeyStore() {
		return keyStore;
	}
	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}
	public Boolean getRevoked() {
		return revoked;
	}
	public void setRevoked(Boolean revoked) {
		this.revoked = revoked;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getCa() {
		return ca;
	}
	public void setCa(Boolean ca) {
		this.ca = ca;
	}
	public String getTrustStore() {
		return trustStore;
	}
	public void setTrustStore(String trustStore) {
		this.trustStore = trustStore;
	}
	public String getRevokedReason() {
		return revokedReason;
	}
	public void setRevokedReason(String revokedReason) {
		this.revokedReason = revokedReason;
	}
	
	
}
