package adminski.modul.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Admin;
import adminski.modul.app.repository.AdminRepository;
import adminski.modul.app.security.TokenUtil;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
    private TokenUtil tokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<?> authenticationRequest(@RequestParam String username, @RequestParam String password) throws AuthenticationException {
	    // Perform the authentication
	    Authentication authentication = this.authenticationManager.authenticate(
	      new UsernamePasswordAuthenticationToken(
	        username,
	        password
	      )
	    );
	   
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    
    	Admin user = null;
	    for (Admin admin : adminRepository.findAll()) {
			if (admin.getPassword().equals(password) && admin.getUsername().equals(username)) {
				user = admin;
				break;
			}
	    }
	    
	    // Reload password post-authentication so we can generate token
	    UserDetails userDetails = (UserDetails) user;
	    String token = this.tokenUtil.generateToken(userDetails);

	    // Return the token
	    return ResponseEntity.ok(token);
	  }
	

//	
//	@PostMapping
//	public ResponseEntity<Admin> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
//		
//		Admin uspesno_ulogovan = null;
//		
//		for (Admin admin : adminRepository.findAll()) {
//			if (admin.getPassword().equals(password) && admin.getUsername().equals(username)) {
//				uspesno_ulogovan = admin;
//			}
//		}
//		
//		ResponseEntity<Admin> resp = null;
//		if (uspesno_ulogovan != null) {
//			resp = new ResponseEntity<Admin>(uspesno_ulogovan, HttpStatus.OK);
//			
//			session.setMaxInactiveInterval(30 * 60 * 60); // sesija traje pola sata
//			session.setAttribute("loggedIn", uspesno_ulogovan);
//		} 
//		
//		return resp;
//	}
//	
//	@PostMapping("logout")
//	public void logout(HttpSession session) {
//		session.removeAttribute("loggedIn");
//		session.invalidate();
//	}
}
