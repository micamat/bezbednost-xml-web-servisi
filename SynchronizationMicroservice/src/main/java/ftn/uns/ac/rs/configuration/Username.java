package ftn.uns.ac.rs.configuration;

public class Username {

	private static String username;
	
	public static void setLoggedUser(String username) {
		Username.username = username;
	}
	
	public static String getLoggedUser() {

		return username;

	}
}
