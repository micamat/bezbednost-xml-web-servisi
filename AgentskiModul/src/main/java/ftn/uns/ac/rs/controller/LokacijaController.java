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

import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.service.LokacijaService;

@RestController
@RequestMapping("/lokacija")
public class LokacijaController {
	
	@Autowired
	private LokacijaService lokacijaService;

	@GetMapping
	public ResponseEntity<List<Lokacija>> getAll(){
		if(lokacijaService.getAll() == null) {
			return new ResponseEntity<List<Lokacija>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Lokacija>>(lokacijaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Lokacija> getById(@PathVariable Long id){
		if(lokacijaService.getById(id) == null) {
			return new ResponseEntity<Lokacija>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Lokacija>(lokacijaService.getById(id), HttpStatus.OK);
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
