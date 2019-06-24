package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.KategorijaSmestaja;
import ftn.uns.ac.rs.service.KategorijaSmestajaService;

@RestController
@RequestMapping("/kategorija-smestaja")
public class KategorijaSmestajaController {

	@Autowired
	private KategorijaSmestajaService kategorijaSmestajaService;

	@GetMapping
	public ResponseEntity<List<KategorijaSmestaja>> getAll(){
		if(kategorijaSmestajaService.getAll() == null) {
			return new ResponseEntity<List<KategorijaSmestaja>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KategorijaSmestaja>>(kategorijaSmestajaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllSync")
	public ResponseEntity<List<KategorijaSmestaja>> getAllSync(){
		return new ResponseEntity<List<KategorijaSmestaja>>(kategorijaSmestajaService.getAllSync(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KategorijaSmestaja> getById(@PathVariable Long id){
		if(kategorijaSmestajaService.getById(id) == null) {
			return new ResponseEntity<KategorijaSmestaja>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<KategorijaSmestaja>(kategorijaSmestajaService.getById(id), HttpStatus.OK);
	}

}
