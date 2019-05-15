/**
 * 
 * @author Dejan
 *
 *ZAKOMENTARISANE DELOVE OTKOMENTARISATI KASNIJE , VEZANI SU ZA AUTENTIFIKACIJU I SSECURITY APLIKACIJE
 *
 */
package project.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.security.factory.CerberusUser;
import project.security.json.AuthenticationRequest;
import project.user.model.dto.UserLoginDTO;
import project.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody UserLoginDTO user){
		return ResponseEntity.ok(userService.save(user));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
		return ResponseEntity.ok(userService.signin(authenticationRequest));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/logout")
	public ResponseEntity<?> logout(){
		userService.signout();
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/currentUser")
	@PreAuthorize("hasAuthority('SECURITY_ADMIN')")
	public ResponseEntity<CerberusUser> getCurrentUser() {
		return new ResponseEntity<CerberusUser>(userService.currentUser(), HttpStatus.OK);
	}
}
