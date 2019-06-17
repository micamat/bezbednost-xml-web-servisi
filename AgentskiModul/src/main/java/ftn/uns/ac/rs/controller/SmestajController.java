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

import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.model.getSmestajDTO;
import ftn.uns.ac.rs.service.SmestajService;

@RestController
@RequestMapping("/smestaj")
public class SmestajController {

	@Autowired
	private SmestajService smestajService;

	@GetMapping
	public ResponseEntity<List<getSmestajDTO>> getAll(){
		if(smestajService.getAll() == null) {
			return new ResponseEntity<List<getSmestajDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<getSmestajDTO>>(smestajService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "createSync")
	public ResponseEntity<Integer> createSync(@RequestBody SmestajDTO smestajDTO){
		
		return new ResponseEntity<Integer>(smestajService.createSync(smestajDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<getSmestajDTO> getById(@PathVariable Long id){
		if(smestajService.getById(id) == null) {
			return new ResponseEntity<getSmestajDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<getSmestajDTO>(smestajService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody SmestajDTO smestajDTO){
		if(smestajService.add(smestajDTO)) {
			return new ResponseEntity<String>("Smestaj je uspesno dodat!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju smestaja!", HttpStatus.CONFLICT);
		}
			
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(smestajService.delete(id)) {
			return new ResponseEntity<String>("Smestaj je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Smestaj nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}

}
