package ftn.uns.ac.rs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="keystore")
public class Keystore {


	public Keystore() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="keystoreName", nullable = false)
	private String keystoreName;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="privateKeyPassword", nullable = false)
	private String privateKeyPassword;
	
	@Column(name="role", nullable = false)
	private String role;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeystoreName() {
		return keystoreName;
	}

	public void setKeystoreName(String keystoreName) {
		this.keystoreName = keystoreName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPrivateKeyPassword() {
		return privateKeyPassword;
	}

	public void setPrivateKeyPassword(String privateKeyPassword) {
		this.privateKeyPassword = privateKeyPassword;
	}
		
}
