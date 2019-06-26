package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.PorukaDTO;
import ftn.uns.ac.rs.model.ShowPorukaDTO;
import ftn.uns.ac.rs.service.PorukaService;

@RestController
@RequestMapping("/poruka")
public class PorukaController {
	@Autowired
	private PorukaService porukaService;

	@GetMapping
	public ResponseEntity<List<ShowPorukaDTO>> getAll(){
		if(porukaService.getAll() == null) {
			return new ResponseEntity<List<ShowPorukaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowPorukaDTO>>(porukaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowPorukaDTO> getById(@PathVariable Long id){
		if(porukaService.getById(id) == null) {
			return new ResponseEntity<ShowPorukaDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowPorukaDTO>(porukaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody PorukaDTO porukaDTO){
		if(porukaService.add(porukaDTO)) {
			return new ResponseEntity<String>("Smestaj je uspesno dodat!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju smestaja!", HttpStatus.CONFLICT);
		}
			
	}
	
}
