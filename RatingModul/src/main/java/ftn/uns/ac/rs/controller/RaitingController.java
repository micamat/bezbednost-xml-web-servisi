package ftn.uns.ac.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.DTOs.OcenaDTO;
import ftn.uns.ac.rs.services.OcenaService;

@RestController
@RequestMapping("/ocene")
public class RaitingController {

	@Autowired
	private OcenaService ocenaService;

	//u putanji saljes id smestaja za koji preuzimas ocene
	@GetMapping(value = "/{id}")
	public ResponseEntity<Double> getProsecnaOcena(@PathVariable("id") Long id) {
		if (ocenaService.getProsecnaOcena(id) == -1) {
			return new ResponseEntity<Double>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Double>(ocenaService.getProsecnaOcena(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody OcenaDTO ocena) {
		if (ocenaService.add(ocena)) {
			return new ResponseEntity<String>("Ocena je uspesno dodata!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Greska pri dodavanju ocene!", HttpStatus.CONFLICT);
		}
	}
	
}
