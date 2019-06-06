package adminski.modul.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Korisnik;
import adminski.modul.app.service.KorisnikService;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	
	@GetMapping("block/{id}")
	public ResponseEntity<Korisnik> blockKorisnik(@PathVariable String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Korisnik>(HttpStatus.FORBIDDEN);
		} else {
			if (korisnikService.blockById(id)) {
				return new ResponseEntity<Korisnik>(korisnikService.findById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@GetMapping("unblock/{id}")
	public ResponseEntity<Korisnik> unblockKorisnik(@PathVariable String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Korisnik>(HttpStatus.FORBIDDEN);
		} else {
			if (korisnikService.unblockById(id)) {
				return new ResponseEntity<Korisnik>(korisnikService.findById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Korisnik> removeKorisnik(@PathVariable String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Korisnik>(HttpStatus.FORBIDDEN);
		} else {
			if (korisnikService.removeById(id)) {
				return new ResponseEntity<Korisnik>(HttpStatus.OK);
			} else {
			
				return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
}
