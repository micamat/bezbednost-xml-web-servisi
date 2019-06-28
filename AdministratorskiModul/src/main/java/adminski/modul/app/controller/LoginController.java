package adminski.modul.app.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Admin;
import adminski.modul.app.repository.AdminRepository;
import adminski.modul.app.security.TokenUtil;
import adminski.modul.app.security.UserDetailsImpl;

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
	    
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
				
		// Perform the authentication
	    Authentication authentication = this.authenticationManager.authenticate(
	      new UsernamePasswordAuthenticationToken(
	        username,
	        password,
	        authorities
	      )
	    );
	    
	    //System.out.println(authentication.getAuthorities());
	   
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    
    	Admin user = null;
	    for (Admin admin : adminRepository.findAll()) {
			if (admin.getPassword().equals(password) && admin.getUsername().equals(username)) {
				user = admin;
				break;
			}
	    }
	    
	    // Reload password post-authentication so we can generate token
	    UserDetailsImpl details = new UserDetailsImpl();
	    details.setId(user.getId());
	    details.setUsername(user.getUsername());
	    details.setPassword(user.getPassword());
	    details.setAuthorities(authorities);
	    
	    UserDetails userDetails = (UserDetails) details;
	   	String token = this.tokenUtil.generateToken(userDetails);

	   	//System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
	   	
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
