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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.TipSmestaja;
import adminski.modul.app.repository.TipSmestajaRepository;
import adminski.modul.app.service.SifarnikService;

@RestController
@RequestMapping("/sifarnik")
public class SifarnikController {
	
	@Autowired
	private SifarnikService sifarnikService;
	
	@GetMapping("{id}")
	public ResponseEntity<TipSmestaja> getTipSmestaja(@PathVariable String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.FORBIDDEN);
		} else {
			TipSmestaja entity = sifarnikService.getTipSmestajaById(id);
			
			return new ResponseEntity<TipSmestaja>(entity, HttpStatus.OK);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<TipSmestaja>> getAllTipSmestaja(HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<List<TipSmestaja>>(HttpStatus.FORBIDDEN);
		} else {
			ArrayList<TipSmestaja> entity = new ArrayList<>();
			
			for(TipSmestaja ts : sifarnikService.getAllTipSmestaja()) {
				entity.add(ts);
			}
			
			return new ResponseEntity<List<TipSmestaja>>(entity, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<TipSmestaja> deleteTipSmestaja(@PathVariable String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.FORBIDDEN);
		} else {
			if (sifarnikService.removeTipSmestaja(id)) {
				return new ResponseEntity<TipSmestaja>(HttpStatus.OK);
			} else {
				return new ResponseEntity<TipSmestaja>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@RequestMapping(value="/tipSmestaja/update")
	public ResponseEntity<TipSmestaja> updateTipSmestaja(@RequestBody TipSmestaja tipSmestaja, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.FORBIDDEN);
		} else {
			if (sifarnikService.updateTipSmestaja(tipSmestaja)) {
				return new ResponseEntity<TipSmestaja>(sifarnikService.getTipSmestajaById(tipSmestaja.getId()), HttpStatus.OK);
			} else {
				return new ResponseEntity<TipSmestaja>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping
	public ResponseEntity<TipSmestaja> createTipSmestaja(@RequestBody TipSmestaja tipSmestaja, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.FORBIDDEN);
		} else {
			sifarnikService.createTipSmestaja(tipSmestaja);
			
			return new ResponseEntity<TipSmestaja>(tipSmestaja, HttpStatus.OK);
		}
	}
}
