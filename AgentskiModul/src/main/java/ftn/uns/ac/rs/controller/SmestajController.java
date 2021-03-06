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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.ShowSmestajDTO;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.service.SmestajService;
import ftn.uns.ac.rs.service.ValidationService;

@RestController
@RequestMapping("/smestaj")
public class SmestajController {

	@Autowired
	private SmestajService smestajService;

	@GetMapping
	public ResponseEntity<List<ShowSmestajDTO>> getAll(){
		if(smestajService.getAll() == null) {
			return new ResponseEntity<List<ShowSmestajDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowSmestajDTO>>(smestajService.getAll(), HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowSmestajDTO> getById(@PathVariable Long id){
		if(smestajService.getById(id) == null) {
			return new ResponseEntity<ShowSmestajDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowSmestajDTO>(smestajService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{token}")
	public ResponseEntity<String> add(@PathVariable String token, @RequestBody SmestajDTO smestajDTO){
		if (ValidationService.validate(token)) {

			if(smestajService.add(smestajDTO)) {
				return new ResponseEntity<String>("Smestaj je uspesno dodat!", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Greska pri dodavanju smestaja!", HttpStatus.CONFLICT);
			}
		} else {
			return new ResponseEntity<String>("Validacija tokena neuspesna!", HttpStatus.CONFLICT);

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
