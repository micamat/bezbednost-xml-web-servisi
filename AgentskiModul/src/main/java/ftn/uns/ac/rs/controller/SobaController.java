package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.model.SobaDTO;
import ftn.uns.ac.rs.service.SobaService;

@RestController
@RequestMapping("/soba")
public class SobaController {

	@Autowired
	private SobaService sobaService;

	/*@GetMapping
	public ResponseEntity<List<SobaDTO>> getAll(){
		if(sobaService.getAll() == null) {
			return new ResponseEntity<List<SobaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SobaDTO>>(sobaService.getAll(), HttpStatus.OK);
	}*/
	
	@GetMapping(value = "getAllSync")
	public ResponseEntity<List<SobaDTO>> getAllSync(){
		return new ResponseEntity<List<SobaDTO>>(sobaService.getAllSync(), HttpStatus.OK);
	}
	
	@PostMapping(value = "createSync")
	public ResponseEntity<Integer> createSync(@RequestBody SobaDTO sobaDTO){
		return new ResponseEntity<Integer>(sobaService.createSync(sobaDTO), HttpStatus.OK);
	}
	
	
	/*
	@GetMapping(value = "/{id}")
	public ResponseEntity<SobaDTO> getById(@PathVariable Long id){
		if(sobaService.getById(id) == null) {
			return new ResponseEntity<SobaDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SobaDTO>(sobaService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/smestaj/{smestajId}")
	public ResponseEntity<List<SobaDTO>> getBySmestaj(@PathVariable Long smestajId){
		if(sobaService.getBySmestaj(smestajId) == null) {
			return new ResponseEntity<List<SobaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SobaDTO>>(sobaService.getBySmestaj(smestajId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody SobaDTO sobaDTO){
		if(sobaService.add(sobaDTO)) {
			return new ResponseEntity<String>("Soba je uspesno dodata!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju sobe!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(sobaService.delete(id)) {
			return new ResponseEntity<String>("Soba je uspesno obrisana!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Soba nije pronadjena!", HttpStatus.NOT_FOUND);
		}
	}*/

}