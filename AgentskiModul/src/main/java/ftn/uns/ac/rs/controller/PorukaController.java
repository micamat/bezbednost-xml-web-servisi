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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.PorukaDTO;
import ftn.uns.ac.rs.model.ShowPorukaDTO;
import ftn.uns.ac.rs.service.PorukaService;
import ftn.uns.ac.rs.service.ValidationService;

@RestController
@RequestMapping("/poruka")
public class PorukaController {
	@Autowired
	private PorukaService porukaService;


	@GetMapping(value = "/rezervacija/{idRezervacija}")
	public ResponseEntity<List<ShowPorukaDTO>> getAllByRezervacija(@PathVariable Long idRezervacija){
		if(porukaService.getAllByRezervacija(idRezervacija) == null) {
			return new ResponseEntity<List<ShowPorukaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowPorukaDTO>>(porukaService.getAllByRezervacija(idRezervacija), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowPorukaDTO> getById(@PathVariable Long id){
		if(porukaService.getById(id) == null) {
			return new ResponseEntity<ShowPorukaDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowPorukaDTO>(porukaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{token}")
	public ResponseEntity<String> add(@PathVariable String token, @RequestBody PorukaDTO porukaDTO){
		if (ValidationService.validate(token)) {

			if(porukaService.add(porukaDTO)) {
				return new ResponseEntity<String>("Smestaj je uspesno dodat!", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Greska pri dodavanju smestaja!", HttpStatus.CONFLICT);
			}
		} else {
			return new ResponseEntity<String>("Validacija tokena neuspesna", HttpStatus.CONFLICT);

		}
			
	}
	
}
