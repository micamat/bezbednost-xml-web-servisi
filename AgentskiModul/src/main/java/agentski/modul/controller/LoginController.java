package agentski.modul.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password) {
		String centralniLocation = "http://localhost:8443";
		
		try {
			// Ako bi koristili GET
//			URL url = new URL(centralniLocation + "/user/login?email=" + email + "&password=" + password);
//			
//			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//			
//			return reader.readLine();
			
			System.out.println("Attempt: " + email + " " + password);
			
			URL url = new URL(centralniLocation + "/user/login");
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes("email=" + email + "&password=" + password + "&modul=agentski");
			wr.flush();
			wr.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String resp = reader.readLine();
			
			if (resp.equals("true")) {
				// Testiramo da li smo se lepo ulogovali pokusajem pristupanja ruti /user/all kojoj mogu pristupati samo ulogovani administratori i agenti
				URL url2 = new URL(centralniLocation + "/user/all");
				
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(url2.openStream()));
				
				// Ocekujemo BAD REQUEST 400 ako smo lose ulogovani tj  ako nemamo dozvolu za ovu akciju ("READ_USERS")
				System.out.println("Odgovor na komandu /user/all: " + reader2.readLine()); 
			}
			
			return resp;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "false";
	}
	
}
