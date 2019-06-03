package adminski.modul.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Korisnik;
import adminski.modul.app.repository.KorisnikRepository;

@RestController
@RequestMapping(value="korisnik")
public class KorisnikController {

	@Autowired
	KorisnikRepository korisnikRepository;
	
	@RequestMapping(value="/block")
	public ResponseEntity<Korisnik> blockKorisnik(@RequestParam String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Korisnik>(HttpStatus.FORBIDDEN);
		} else {
			Korisnik entity = korisnikRepository.findById(Long.getLong(id)).orElse(null);
			
			if (entity != null) {
				entity.setAktivan(false);
				
				korisnikRepository.save(entity);
				return new ResponseEntity<Korisnik>(entity, HttpStatus.OK);
			} else {
			
				return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@RequestMapping(value="/unblock")
	public ResponseEntity<Korisnik> unblockKorisnik(@RequestParam String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Korisnik>(HttpStatus.FORBIDDEN);
		} else {
			Korisnik entity = korisnikRepository.findById(Long.getLong(id)).orElse(null);
			
			if (entity != null) {
				entity.setAktivan(true);
				
				korisnikRepository.save(entity);
				return new ResponseEntity<Korisnik>(entity, HttpStatus.OK);
			} else {
			
				return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@RequestMapping(value="/remove")
	public ResponseEntity<Korisnik> removeKorisnik(@RequestParam String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Korisnik>(HttpStatus.FORBIDDEN);
		} else {
			Korisnik entity = korisnikRepository.findById(Long.getLong(id)).orElse(null);
			
			if (entity != null) {
				korisnikRepository.delete(entity);
				return new ResponseEntity<Korisnik>(entity, HttpStatus.OK);
			} else {
			
				return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
}
