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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.user.model.User;
import project.user.model.dto.UserLoginDTO;
import project.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private boolean ulogovani  = false;
	
	@PostMapping
	public ResponseEntity<UserLoginDTO> create(@RequestBody UserLoginDTO userDTO) { 
		return new ResponseEntity<UserLoginDTO>(userService.save(userDTO), HttpStatus.CREATED);	
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLoginDTO userDTO){
		if(userService.login(userDTO) != null) {
			ulogovani = true;
			return new ResponseEntity<String>("User is loged!",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("User fail to login!",HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/loged")
	public boolean loged() {
		return ulogovani;
	}
	
}
