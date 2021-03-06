package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.ShowKomentarDTO;
import ftn.uns.ac.rs.service.KomentarService;

@RestController
@RequestMapping("/komentar")
public class KomentarController {
	@Autowired
	private KomentarService komentarService;

	@GetMapping
	public ResponseEntity<List<ShowKomentarDTO>> getAll(){
		if(komentarService.getAll() == null) {
			return new ResponseEntity<List<ShowKomentarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowKomentarDTO>>(komentarService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowKomentarDTO> getById(@PathVariable Long id){
		if(komentarService.getById(id) == null) {
			return new ResponseEntity<ShowKomentarDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowKomentarDTO>(komentarService.getById(id), HttpStatus.OK);
	}
}
