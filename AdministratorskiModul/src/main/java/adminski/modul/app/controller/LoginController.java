package adminski.modul.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.repository.AdminRepository;
import adminski.modul.app.model.Admin;

@RestController
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Admin> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		
		Admin uspesno_ulogovan = null;
		
		for (Admin admin : adminRepository.findAll()) {
			if (admin.getPassword().equals(password) && admin.getUsername().equals(username)) {
				uspesno_ulogovan = admin;
			}
		}
		
		ResponseEntity<Admin> resp = null;
		if (uspesno_ulogovan != null) {
			resp = new ResponseEntity<Admin>(uspesno_ulogovan, HttpStatus.OK);
			
			session.setMaxInactiveInterval(30 * 60 * 60); // sesija traje pola sata
			session.setAttribute("loggedIn", uspesno_ulogovan);
		} 
		
		return resp;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public void logout(HttpSession session) {
		session.removeAttribute("loggedIn");
		session.invalidate();
	}
}
