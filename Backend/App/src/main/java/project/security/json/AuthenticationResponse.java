package project.security.json;

public class AuthenticationResponse extends UserModelBase {

	private static final long serialVersionUID = -6624726180748515507L;
	private String token;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
