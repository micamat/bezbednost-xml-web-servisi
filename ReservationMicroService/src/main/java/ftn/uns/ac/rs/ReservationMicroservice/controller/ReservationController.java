package ftn.uns.ac.rs.ReservationMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.ReservationMicroservice.dto.RezervacijaDTO;
import ftn.uns.ac.rs.ReservationMicroservice.model.Rezervacija;
import ftn.uns.ac.rs.ReservationMicroservice.repository.ReservationRepository;
import ftn.uns.ac.rs.ReservationMicroservice.service.ReservationService;

@RestController
//@RequestMapping(value="/rezervacije")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationRepository repo;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Rezervacija>> getAll(){
		
		return new ResponseEntity<List<Rezervacija>>(repo.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody RezervacijaDTO rezervacijaDTO){
		if(reservationService.add(rezervacijaDTO)) {
			return new ResponseEntity<String>("Smestaj je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Smestaj nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}

}
