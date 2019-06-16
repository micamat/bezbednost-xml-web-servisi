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

import ftn.uns.ac.rs.model.LokacijaDTO;
import ftn.uns.ac.rs.service.LokacijaService;

@RestController
@RequestMapping("/lokacija")
public class LokacijaController {
	
	@Autowired
	private LokacijaService lokacijaService;

	@GetMapping
	public ResponseEntity<List<LokacijaDTO>> getAll(){
		if(lokacijaService.getAll() == null) {
			return new ResponseEntity<List<LokacijaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<LokacijaDTO>>(lokacijaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllSync")
	public ResponseEntity<List<LokacijaDTO>> getAllSync(){
		return new ResponseEntity<List<LokacijaDTO>>(lokacijaService.getAllSync(), HttpStatus.OK);
	}
	
	@PostMapping(value = "createSync")
	public ResponseEntity<Integer> createSync(@RequestBody LokacijaDTO lokacijaDTO){
		
		return new ResponseEntity<Integer>(lokacijaService.createSync(lokacijaDTO), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LokacijaDTO> getById(@PathVariable Long id){
		if(lokacijaService.getById(id) == null) {
			return new ResponseEntity<LokacijaDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<LokacijaDTO>(lokacijaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody LokacijaDTO lokacijaDTO){
		if(lokacijaService.add(lokacijaDTO)) {
			return new ResponseEntity<String>("Lokacija je uspesno dodata!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju lokacije!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(lokacijaService.delete(id)) {
			return new ResponseEntity<String>("Lokacija je uspesno obrisana!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Lokacija nije pronadjena!", HttpStatus.NOT_FOUND);
		}
	}

}
