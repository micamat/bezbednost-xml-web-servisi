package project.user.model.dto;


public class UserLoginDTO {
	
	private String password;
	
	private String email;

	public UserLoginDTO() {
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}