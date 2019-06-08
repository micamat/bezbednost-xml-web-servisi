package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.service.SmestajService;

@RestController
@RequestMapping("/smestaj")
public class SmestajController {

	@Autowired
	private SmestajService smestajService;

	/*@GetMapping
	public ResponseEntity<List<SmestajDTO>> getAll(){
		if(smestajService.getAll() == null) {
			return new ResponseEntity<List<SmestajDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SmestajDTO>>(smestajService.getAll(), HttpStatus.OK);
	}*/
	
	@GetMapping(value = "synchronize")
	public ResponseEntity<List<SmestajDTO>> synchronize(){
		return new ResponseEntity<List<SmestajDTO>>(smestajService.synchronization(), HttpStatus.OK);
	}
	
	/*@GetMapping(value = "/{id}")
	public ResponseEntity<SmestajDTO> getById(@PathVariable Long id){
		if(smestajService.getById(id) == null) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SmestajDTO>(smestajService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody SmestajDTO smestajDTO){
		if(smestajService.add(smestajDTO)) {
			return new ResponseEntity<String>("Smestaj je uspesno dodat!", HttpStatus.CREATED);
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
	}*/

}
