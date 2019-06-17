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

import adminski.modul.app.model.TipUsluge;
import adminski.modul.app.service.TipUslugeService;

@RestController
@RequestMapping("/tipUsluge")
public class TipUslugeController {
	
	@Autowired
	private TipUslugeService tipUslugeService;

	@GetMapping("{id}")
	public ResponseEntity<TipUsluge> getTipUsluge(@PathVariable Long id, HttpSession session){
		/*if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipUsluge>(HttpStatus.FORBIDDEN);
		} else {*/
			TipUsluge usluga = tipUslugeService.getTypeServiceById(id);
			
			return new ResponseEntity<TipUsluge>(usluga, HttpStatus.OK);
		//}
	}
	
	@GetMapping
	public ResponseEntity<List<TipUsluge>> getAllTipUsluge(HttpSession session){
		/*if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<List<TipUsluge>>(HttpStatus.FORBIDDEN);
		} else {*/
			ArrayList<TipUsluge> tipovi = new ArrayList<>();
			
			for(TipUsluge tipUsluge : tipUslugeService.getAllTypesServices()) {
				tipovi.add(tipUsluge);
			}
			
			return new ResponseEntity<List<TipUsluge>>(tipovi, HttpStatus.OK);
		//}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteTipUsluge(@PathVariable Long id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		} else {
			if (tipUslugeService.removeTypeService(id)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TipUsluge> updateTipUsluge(@PathVariable Long id, @RequestBody TipUsluge tipUsluge, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipUsluge>(HttpStatus.FORBIDDEN);
		} else {
			if (tipUslugeService.updateTypeService(id, tipUsluge)) {
				return new ResponseEntity<TipUsluge>(tipUslugeService.getTypeServiceById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<TipUsluge>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping
	public ResponseEntity<TipUsluge> createTipUsluge(@RequestBody TipUsluge tipUsluge, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipUsluge>(HttpStatus.FORBIDDEN);
		} else {
			tipUslugeService.createTypeService(tipUsluge);
			
			return new ResponseEntity<TipUsluge>(tipUsluge, HttpStatus.OK);
		}
	}
}
