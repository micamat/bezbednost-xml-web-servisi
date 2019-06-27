package ftn.uns.ac.rs.app.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggedInUser {
	@PutMapping("/odjava")
	public Boolean odjava() {
		try {
			System.out.println("ulogovani: " + SecurityContextHolder.getContext().getAuthentication().getName());
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@PostMapping("/validate")
	public Boolean validate() {
		try {
			if(SecurityContextHolder.getContext().getAuthentication().getName() != "anonymousUser") {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		
	}
}
