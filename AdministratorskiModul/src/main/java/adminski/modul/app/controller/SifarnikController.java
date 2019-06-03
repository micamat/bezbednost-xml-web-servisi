package adminski.modul.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.TipSmestaja;
import adminski.modul.app.repository.TipSmestajaRepository;

@RestController
@RequestMapping(value="sifarnik")
public class SifarnikController {
	
	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	@RequestMapping(value="/tipSmestaja/get")
	public ResponseEntity<TipSmestaja> getTipSmestaja(@RequestParam String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.FORBIDDEN);
		} else {
			TipSmestaja entity = tipSmestajaRepository.findById(Long.getLong(id)).orElse(null);
			
			return new ResponseEntity<TipSmestaja>(entity, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/tipSmestaja/getAll")
	public ResponseEntity<List<TipSmestaja>> getAllTipSmestaja(HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<List<TipSmestaja>>(HttpStatus.FORBIDDEN);
		} else {
			ArrayList<TipSmestaja> entity = new ArrayList<>();
			
			for(TipSmestaja ts : tipSmestajaRepository.findAll()) {
				entity.add(ts);
			}
			
			return new ResponseEntity<List<TipSmestaja>>(entity, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/tipSmestaja/delete")
	public ResponseEntity<TipSmestaja> deleteTipSmestaja(@RequestParam String id, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.FORBIDDEN);
		} else {
			TipSmestaja entity = tipSmestajaRepository.findById(Long.getLong(id)).orElse(null);

			if (entity != null) {
				tipSmestajaRepository.deleteById(Long.getLong(id));
				
				return new ResponseEntity<TipSmestaja>(entity, HttpStatus.OK);
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
			TipSmestaja entity = tipSmestajaRepository.findById(Long.getLong(tipSmestaja.getId())).orElse(null);
			
			if (entity != null) {
				entity.setNaziv(tipSmestaja.getNaziv());
				entity.setOpis(tipSmestaja.getOpis());
				
				tipSmestajaRepository.save(entity);
				
				return new ResponseEntity<TipSmestaja>(entity, HttpStatus.OK);
			} else {
				return new ResponseEntity<TipSmestaja>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@RequestMapping(value="/tipSmestaja/create")
	public ResponseEntity<TipSmestaja> createTipSmestaja(@RequestBody TipSmestaja tipSmestaja, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.FORBIDDEN);
		} else {
			tipSmestajaRepository.save(tipSmestaja);
			
			return new ResponseEntity<TipSmestaja>(tipSmestaja, HttpStatus.OK);
		}
	}
}
