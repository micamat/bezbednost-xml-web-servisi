package ftn.uns.ac.rs.ReservationMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.ReservationMicroservice.dto.ReservationDTO;
import ftn.uns.ac.rs.ReservationMicroservice.service.ReservationService;

@RestController
@RequestMapping(value="/rezervacije")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody ReservationDTO reservationDTO){
		if(reservationService.add(reservationDTO)) {
			return new ResponseEntity<String>("Smestaj je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Smestaj nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}

}
