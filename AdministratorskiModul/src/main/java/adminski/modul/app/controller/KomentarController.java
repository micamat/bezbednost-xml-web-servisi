package adminski.modul.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Komentar;
import adminski.modul.app.service.AuthService;
import adminski.modul.app.service.KomentarService;

@RestController
@RequestMapping("/komentar")
public class KomentarController {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private KomentarService komentarService;
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@GetMapping("{id}")
	public ResponseEntity<Komentar> getComment(@PathVariable Long id){
		
			Komentar komentar = komentarService.getCommentById(id);
			
			if (komentar != null) {
				return new ResponseEntity<Komentar>(komentar, HttpStatus.OK);
			} else {
				return new ResponseEntity<Komentar>(HttpStatus.BAD_REQUEST);
			}
		
	}
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@GetMapping
	public ResponseEntity<List<Komentar>> getAllComments(){
		
			ArrayList<Komentar> komentari = new ArrayList<>();
			
			for(Komentar komentar : komentarService.getAllComments()) {
				komentari.add(komentar);
			}
			
			return new ResponseEntity<List<Komentar>>(komentari, HttpStatus.OK);
		
	}
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id){
		
			if (komentarService.removeComment(id)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		
	}
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@PutMapping("{id}")
	public ResponseEntity<Komentar> updateKomentar(@PathVariable Long id, @RequestBody Komentar komentar){
		
			if (komentarService.updateComment(id,komentar)) {
				return new ResponseEntity<Komentar>(komentarService.getCommentById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<Komentar>(HttpStatus.BAD_REQUEST);
			}
		
	}
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@PostMapping
	public ResponseEntity<Komentar> createKomentar(@RequestBody Komentar komentar){
		
			komentarService.createComment(komentar);
			
			return new ResponseEntity<Komentar>(komentar, HttpStatus.OK);
		
	}
	
	@GetMapping("/hide/{id}")
	public void hideComment(@PathVariable Long id){
		System.out.println("ID2: " + id);
		komentarService.hideComment(id);
	}
}
