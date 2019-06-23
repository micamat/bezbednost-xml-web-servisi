package ftn.uns.ac.rs.AuthMicroservice.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.AuthMicroservice.security.service.KorisnikService;
import ftn.uns.ac.rs.AuthMicroservice.security.user.Korisnik;

@RestController
public class KorisnikController {
	@Autowired
	KorisnikService korisnikService;
	
	@PostMapping("auth/register")
	public ResponseEntity<Korisnik> save(@RequestBody Korisnik korisnik){
		return new ResponseEntity<Korisnik>(korisnikService.save(korisnik), HttpStatus.OK);
	}
}
