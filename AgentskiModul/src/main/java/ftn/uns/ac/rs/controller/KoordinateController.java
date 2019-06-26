package ftn.uns.ac.rs.controller;

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

import ftn.uns.ac.rs.model.Koordinate;
import ftn.uns.ac.rs.service.KoordinateService;

@RestController
@RequestMapping("/koordinate")
public class KoordinateController {
	
	@Autowired
	private KoordinateService koordinateService;
	
	@PostMapping(value = "createSync")
	public ResponseEntity<Integer> createSync(@RequestBody Koordinate koordinateDTO){
		
		return new ResponseEntity<Integer>(koordinateService.createSync(koordinateDTO), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Koordinate> getById(@PathVariable Long id){
		if(koordinateService.getById(id) == null) {
			return new ResponseEntity<Koordinate>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Koordinate>(koordinateService.getById(id), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(koordinateService.delete(id)) {
			return new ResponseEntity<String>("Koordinate su uspesno obrisane!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Koordinate nisu pronadjene!", HttpStatus.NOT_FOUND);
		}
	}

}
