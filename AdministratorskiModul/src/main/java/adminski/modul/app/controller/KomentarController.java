package adminski.modul.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Komentar;
import adminski.modul.app.service.KomentarService;

@RestController
@RequestMapping("/komentar")
public class KomentarController {
	
	@Autowired
	private KomentarService komentarService;
	

	@GetMapping("{id}")
	public ResponseEntity<Komentar> getComment(@PathVariable String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Komentar>(HttpStatus.FORBIDDEN);
		} else {
			Komentar komentar = komentarService.getCommentById(id);
			
			return new ResponseEntity<Komentar>(komentar, HttpStatus.OK);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Komentar>> getAllComments(HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<List<Komentar>>(HttpStatus.FORBIDDEN);
		} else {
			ArrayList<Komentar> komentari = new ArrayList<>();
			
			for(Komentar komentar : komentarService.getAllComments()) {
				komentari.add(komentar);
			}
			
			return new ResponseEntity<List<Komentar>>(komentari, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		} else {
			if (komentarService.removeComment(id)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Komentar> updateKomentar(@PathVariable String id, @RequestBody Komentar komentar, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Komentar>(HttpStatus.FORBIDDEN);
		} else {
			if (komentarService.updateComment(id,komentar)) {
				return new ResponseEntity<Komentar>(komentarService.getCommentById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<Komentar>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping
	public ResponseEntity<Komentar> createTipSmestaja(@RequestBody Komentar komentar, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Komentar>(HttpStatus.FORBIDDEN);
		} else {
			komentarService.createComment(komentar);
			
			return new ResponseEntity<Komentar>(komentar, HttpStatus.OK);
		}
	}
}
