package project.certificate.keystore;

public class KeystoreDTO {

	
	private String keystoreName;
	
	private String password;
	
	private String role;

	public KeystoreDTO() {
		
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
	
}
