package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.SifarnikDTO;
import ftn.uns.ac.rs.service.TipSmestajaService;

@RestController
@RequestMapping("/tip-smestaja")
public class TipSmestajaController {
	
	@Autowired
	private TipSmestajaService tipSmestajaService;

	
	@GetMapping
	public ResponseEntity<List<SifarnikDTO>> getAll(){
		if(tipSmestajaService.getAll() == null) {
			return new ResponseEntity<List<SifarnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SifarnikDTO>>(tipSmestajaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SifarnikDTO> getById(@PathVariable Long id){
		if(tipSmestajaService.getById(id) == null) {
			return new ResponseEntity<SifarnikDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SifarnikDTO>(tipSmestajaService.getById(id), HttpStatus.OK);
	}


}
