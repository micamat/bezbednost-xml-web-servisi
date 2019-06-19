package adminski.modul.app.controller;

import java.util.ArrayList;
import java.util.List;

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

import adminski.modul.app.model.TipSmestaja;
import adminski.modul.app.service.TipSmestajaService;

@RestController
@RequestMapping("/tipSmestaja")
public class TipSmestajaController {
	
	@Autowired
	private TipSmestajaService tipSmestajaService;
	
	@GetMapping("{id}")
	public ResponseEntity<TipSmestaja> getTipSmestaja(@PathVariable Long id){
		
			TipSmestaja entity = tipSmestajaService.getTipSmestajaById(id);
			
			return new ResponseEntity<TipSmestaja>(entity, HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<TipSmestaja>> getAllTipSmestaja(){
		
			ArrayList<TipSmestaja> entity = new ArrayList<>();
			
			for(TipSmestaja ts : tipSmestajaService.getAllTipSmestaja()) {
				entity.add(ts);
			}
			
			return new ResponseEntity<List<TipSmestaja>>(entity, HttpStatus.OK);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<TipSmestaja> deleteTipSmestaja(@PathVariable Long id){
		
			if (tipSmestajaService.removeTipSmestaja(id)) {
				return new ResponseEntity<TipSmestaja>(HttpStatus.OK);
			} else {
				return new ResponseEntity<TipSmestaja>(HttpStatus.BAD_REQUEST);
			}
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TipSmestaja> updateTipSmestaja(@PathVariable Long id, @RequestBody TipSmestaja tipSmestaja){
		
			if (tipSmestajaService.updateTipSmestaja(id,tipSmestaja)) {
				return new ResponseEntity<TipSmestaja>(tipSmestajaService.getTipSmestajaById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<TipSmestaja>(HttpStatus.BAD_REQUEST);
			}
		
	}
	
	@PostMapping
	public ResponseEntity<TipSmestaja> createTipSmestaja(@RequestBody TipSmestaja tipSmestaja){
		
			tipSmestajaService.createTipSmestaja(tipSmestaja);
			
			return new ResponseEntity<TipSmestaja>(tipSmestaja, HttpStatus.OK);
		
	}
}
