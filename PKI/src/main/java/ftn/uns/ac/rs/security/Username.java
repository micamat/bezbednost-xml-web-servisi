package ftn.uns.ac.rs.security;

public class Username {
	private static String username;
	
	public static void setLoggedUser(String username) {
		Username.username = username;
	}
	
	public static String getLoggedUser() {
		return Username.username;
	}
}
