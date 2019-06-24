package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.TipSobe;
import ftn.uns.ac.rs.service.TipSobeService;

@RestController
@RequestMapping("/tip-sobe")
public class TipSobeController {

	@Autowired
	private TipSobeService tipSobeService;

	
	
	@GetMapping
	public ResponseEntity<List<TipSobe>> getAll(){
		if(tipSobeService.getAll() == null) {
			return new ResponseEntity<List<TipSobe>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TipSobe>>(tipSobeService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipSobe> getById(@PathVariable Long id){
		if(tipSobeService.getById(id) == null) {
			return new ResponseEntity<TipSobe>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TipSobe>(tipSobeService.getById(id), HttpStatus.OK);
	}

}
