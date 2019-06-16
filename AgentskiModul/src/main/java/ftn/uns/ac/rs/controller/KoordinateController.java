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

import ftn.uns.ac.rs.model.KoordinateDTO;
import ftn.uns.ac.rs.service.KoordinateService;

@RestController
@RequestMapping("/koordinate")
public class KoordinateController {
	
	@Autowired
	private KoordinateService koordinateaService;

	@GetMapping
	public ResponseEntity<List<KoordinateDTO>> getAll(){
		if(koordinateaService.getAll() == null) {
			return new ResponseEntity<List<KoordinateDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KoordinateDTO>>(koordinateaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllSync")
	public ResponseEntity<List<KoordinateDTO>> getAllSync(){
		return new ResponseEntity<List<KoordinateDTO>>(koordinateaService.getAllSync(), HttpStatus.OK);
	}
	
	@PostMapping(value = "createSync")
	public ResponseEntity<Integer> createSync(@RequestBody KoordinateDTO koordinateDTO){
		
		return new ResponseEntity<Integer>(koordinateaService.createSync(koordinateDTO), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KoordinateDTO> getById(@PathVariable Long id){
		if(koordinateaService.getById(id) == null) {
			return new ResponseEntity<KoordinateDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<KoordinateDTO>(koordinateaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody KoordinateDTO koordinateDTO){
		if(koordinateaService.add(koordinateDTO)) {
			return new ResponseEntity<String>("Koordinate su uspesno dodate!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju koordinata!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(koordinateaService.delete(id)) {
			return new ResponseEntity<String>("Koordinate su uspesno obrisane!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Koordinate nisu pronadjene!", HttpStatus.NOT_FOUND);
		}
	}

}
