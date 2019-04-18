package centralni.modul.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import centralni.modul.model.UserModel;
import centralni.modul.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RestTemplate restTemplate;

	private UserModel loggedUser;
	
	@GetMapping(value = "/tekst")
    public String getNekiString() {
    	return restTemplate.getForObject("https://localhost:8444/hotels/nekistring", String.class);
    }
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<UserModel>> findAll(){
		if(loggedUser.hasPrivilege("READ_USERS")){
			return new ResponseEntity<List<UserModel>>(userService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserModel>>(new ArrayList<UserModel>(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/login")
	public boolean login(@RequestParam String email, @RequestParam String password, @RequestParam String modul) {
		String encryptedPassword = getEncryptedPassword(password);
		
		List<UserModel> users = userService.findAll();
		
		for (UserModel u : users) {
			if (u.getPassword().equals(encryptedPassword) && u.getEmail().equals(email)) {
				
				if (modul.equals("agentski") && u.getRole().getRolename().equals("agent")) {
					System.out.println("Logged in: " + u.getEmail() + ", " + u.getRole().getRolename());
					loggedUser = u;
					return true;
				} else if (modul.equals("administratorski") && u.getRole().getRolename().equals("admin")) {
					System.out.println("Logged in: " + u.getEmail() + ", " + u.getRole().getRolename());
					loggedUser = u;
					return true;
				} else if (modul.equals("korisnicki") && u.getRole().getRolename().equals("user")) {
					System.out.println("Logged in: " + u.getEmail() + ", " + u.getRole().getRolename());
					loggedUser = u;
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	// Testna funkcija, vraca hash sifre koja se pruzi kao parametar pass
//	@GetMapping(value = "/hash")
//	public String hash(@RequestParam String pass) {
//		return getEncryptedPassword(pass);
//	}
	
    private String getEncryptedPassword(String password)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
}
