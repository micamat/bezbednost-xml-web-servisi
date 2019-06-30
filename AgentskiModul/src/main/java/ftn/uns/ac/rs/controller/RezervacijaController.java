package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.RezervacijaDTO;
import ftn.uns.ac.rs.model.ShowRezervacijaDTO;
import ftn.uns.ac.rs.model.UpdateRezervisaneSobeDTO;
import ftn.uns.ac.rs.service.RezervacijaService;
import ftn.uns.ac.rs.service.ValidationService;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
	@Autowired
	private RezervacijaService rezervacijaService;

	@GetMapping
	public ResponseEntity<List<ShowRezervacijaDTO>> getAll(){
		if(rezervacijaService.getAll() == null) {
			return new ResponseEntity<List<ShowRezervacijaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowRezervacijaDTO>>(rezervacijaService.getAll(), HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowRezervacijaDTO> getById(@PathVariable Long id){
		if(rezervacijaService.getById(id) == null) {
			return new ResponseEntity<ShowRezervacijaDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowRezervacijaDTO>(rezervacijaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{token}")
	public ResponseEntity<String> add(@PathVariable String token, @RequestBody RezervacijaDTO rezervacijaDTO){
		if(rezervacijaService.add(rezervacijaDTO)) {
			return new ResponseEntity<String>("Rezervacija je uspesno dodata!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju rezervacije!", HttpStatus.CONFLICT);
		}
			
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestParam String token, @RequestBody UpdateRezervisaneSobeDTO updateRezervisaneSobeDTO){
		if (ValidationService.validate(token)) {
			if(rezervacijaService.update(updateRezervisaneSobeDTO)) {
				return new ResponseEntity<String>("Rezervacija je uspesno izmenjena!", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Greska pri izmeni rezervacije!", HttpStatus.CONFLICT);
			}
		} else {
			return new ResponseEntity<String>("Validacija tokena neuspesna!", HttpStatus.CONFLICT);

		}
		
		
			
	}
}
