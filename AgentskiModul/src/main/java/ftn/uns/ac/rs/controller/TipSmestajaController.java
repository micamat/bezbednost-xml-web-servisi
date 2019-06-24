package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.TipSmestaja;
import ftn.uns.ac.rs.service.TipSmestajaService;

@RestController
@RequestMapping("/tip-smestaja")
public class TipSmestajaController {
	
	@Autowired
	private TipSmestajaService tipSmestajaService;

	
	@GetMapping
	public ResponseEntity<List<TipSmestaja>> getAll(){
		if(tipSmestajaService.getAll() == null) {
			return new ResponseEntity<List<TipSmestaja>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TipSmestaja>>(tipSmestajaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipSmestaja> getById(@PathVariable Long id){
		if(tipSmestajaService.getById(id) == null) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TipSmestaja>(tipSmestajaService.getById(id), HttpStatus.OK);
	}


}
