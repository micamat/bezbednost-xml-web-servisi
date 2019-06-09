package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.KategorijaSmestajaDTO;
import ftn.uns.ac.rs.model.KoordinateDTO;
import ftn.uns.ac.rs.service.KategorijaSmestajaService;

@RestController
@RequestMapping("/kategorija-smestaja")
public class KategorijaSmestajaController {

	@Autowired
	private KategorijaSmestajaService kategorijaSmestajaService;

	/*@GetMapping
	public ResponseEntity<List<SifarnikDTO>> getAll(){
		if(kategorijaSmestajaService.getAll() == null) {
			return new ResponseEntity<List<SifarnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SifarnikDTO>>(kategorijaSmestajaService.getAll(), HttpStatus.OK);
	}*/
	
	@GetMapping(value = "getAllSync")
	public ResponseEntity<List<KategorijaSmestajaDTO>> getAllSync(){
		return new ResponseEntity<List<KategorijaSmestajaDTO>>(kategorijaSmestajaService.getAllSync(), HttpStatus.OK);
	}
	
	@PostMapping(value = "createSync")
	public ResponseEntity<Integer> createSync(@RequestBody KategorijaSmestajaDTO kategorijaSmestajaDTO){
		
		return new ResponseEntity<Integer>(kategorijaSmestajaService.createSync(kategorijaSmestajaDTO), HttpStatus.OK);
	}
	
	/*
	@GetMapping(value = "/{id}")
	public ResponseEntity<SifarnikDTO> getById(@PathVariable Long id){
		if(kategorijaSmestajaService.getById(id) == null) {
			return new ResponseEntity<SifarnikDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SifarnikDTO>(kategorijaSmestajaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody SifarnikDTO SifarnikDTO){
		if(kategorijaSmestajaService.add(SifarnikDTO)) {
			return new ResponseEntity<String>("Kategorija smestaja je uspesno dodat!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju kategorije smestaja!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(kategorijaSmestajaService.delete(id)) {
			return new ResponseEntity<String>("Kategorija smestaja je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Kategorija smestaja nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}*/


}
