package agentski.modul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agentski.modul.model.HotelModel;
import agentski.modul.services.HotelService;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<HotelModel>> findAll(){
		return new ResponseEntity<List<HotelModel>>(hotelService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/nekistring")
	public ResponseEntity<String> nekiString(){
		return new ResponseEntity<String>(hotelService.getNekiString(), HttpStatus.OK);
	}
}
