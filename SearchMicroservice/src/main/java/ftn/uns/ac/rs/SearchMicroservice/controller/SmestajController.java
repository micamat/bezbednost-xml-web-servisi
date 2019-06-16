package ftn.uns.ac.rs.SearchMicroservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.SearchMicroservice.model.Smestaj;
import ftn.uns.ac.rs.SearchMicroservice.service.SmestajService;

@RestController
@RequestMapping(value="/smestaj")
public class SmestajController {
	@Autowired
	SmestajService smestajService;
	
	@GetMapping
	public ResponseEntity<List<Smestaj>> search(
			@PathParam(value="naziv") String naziv, @PathParam(value="drzava") String drzava, 
			@PathParam(value="grad") String grad, @PathParam(value="ulica") String ulica, 
			@PathParam(value="tip") String tip, @PathParam(value="kategorija") String kategorija){
		return new ResponseEntity<List<Smestaj>>(smestajService.search(naziv, drzava, grad, ulica, tip, kategorija), HttpStatus.OK);
	}
}
