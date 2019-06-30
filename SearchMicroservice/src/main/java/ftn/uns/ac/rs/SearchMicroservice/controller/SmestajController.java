package ftn.uns.ac.rs.SearchMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.SearchMicroservice.dto.SearchDTO;
import ftn.uns.ac.rs.SearchMicroservice.model.Smestaj;
import ftn.uns.ac.rs.SearchMicroservice.repository.SmestajRepository;
import ftn.uns.ac.rs.SearchMicroservice.service.SearchService;

@RestController
@RequestMapping(value="/smestaj")
public class SmestajController {
	@Autowired
	SearchService smestajService;
	
	@Autowired
	SmestajRepository repo;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Smestaj>> getAll(){
		return new ResponseEntity<List<Smestaj>>(repo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Smestaj>> search(@RequestBody SearchDTO dto){
		return new ResponseEntity<List<Smestaj>>(smestajService.findSmestaj(dto), HttpStatus.OK);
	}
}
