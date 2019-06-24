package ftn.uns.ac.rs.AuthMicroservice.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Korisnik;
import ftn.uns.ac.rs.AuthMicroservice.security.model.KorisnikDTO;
import ftn.uns.ac.rs.AuthMicroservice.security.service.KorisnikService;

@RestController
public class KorisnikController {
	@Autowired
	KorisnikService korisnikService;
	
	@PostMapping("/register")
	public ResponseEntity<Korisnik> save(@RequestBody KorisnikDTO korisnik){
		return new ResponseEntity<Korisnik>(korisnikService.save(korisnik), HttpStatus.OK);
	}
	
	@PutMapping("/logout")
	public ResponseEntity logout(){
		korisnikService.logout();
		return new ResponseEntity(HttpStatus.OK);
	}
}
