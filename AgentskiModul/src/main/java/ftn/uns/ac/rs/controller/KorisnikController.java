package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.Komentar;
import ftn.uns.ac.rs.model.Korisnik;
import ftn.uns.ac.rs.service.KorisnikService;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
	@Autowired
	private KorisnikService korisnikService;

	
	
	@GetMapping
	public ResponseEntity<List<Korisnik>> getAll(){
		if(korisnikService.getAll() == null) {
			return new ResponseEntity<List<Korisnik>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Korisnik>>(korisnikService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Korisnik> getById(@PathVariable Long id){
		if(korisnikService.getById(id) == null) {
			return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Korisnik>(korisnikService.getById(id), HttpStatus.OK);
	}
}
