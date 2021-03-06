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

import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.model.ShowCenovnikDTO;
import ftn.uns.ac.rs.service.CenovnikService;
import ftn.uns.ac.rs.service.ValidationService;

@RestController
@RequestMapping("/cenovnik")
public class CenovnikController {

	@Autowired
	private CenovnikService cenovnikService;

	@GetMapping
	public ResponseEntity<List<ShowCenovnikDTO>> getAll(){
		if(cenovnikService.getAll() == null) {
			return new ResponseEntity<List<ShowCenovnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowCenovnikDTO>>(cenovnikService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowCenovnikDTO> getById(@PathVariable Long id){
		if(cenovnikService.getById(id) == null) {
			return new ResponseEntity<ShowCenovnikDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowCenovnikDTO>(cenovnikService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/smestaj/{smestajId}")
	public ResponseEntity<List<ShowCenovnikDTO>> getBySmestaj(@PathVariable Long smestajId){
		if(cenovnikService.getBySmestaj(smestajId) == null) {
			return new ResponseEntity<List<ShowCenovnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowCenovnikDTO>>(cenovnikService.getBySmestaj(smestajId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{token}")
	public ResponseEntity<String> add(@PathVariable String token, @RequestBody CenovnikDTO cenovnikDTO){
		if (ValidationService.validate(token)) {
				
			if(cenovnikService.add(cenovnikDTO)) {
				return new ResponseEntity<String>("Cenovnik je uspesno dodat!", HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("Greska pri dodavanju cenovnika!", HttpStatus.CONFLICT);
			}
		} else {

			return new ResponseEntity<String>("Validacija tokena neuspesna!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(cenovnikService.delete(id)) {
			return new ResponseEntity<String>("Cenovnik je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Cenovnik nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}

}
