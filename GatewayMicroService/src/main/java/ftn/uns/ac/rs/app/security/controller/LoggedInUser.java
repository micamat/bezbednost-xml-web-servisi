package ftn.uns.ac.rs.app.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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
}
