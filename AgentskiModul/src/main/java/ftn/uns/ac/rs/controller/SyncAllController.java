package ftn.uns.ac.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.service.KategorijaSmestajaService;
import ftn.uns.ac.rs.service.TipSmestajaService;
import ftn.uns.ac.rs.service.TipSobeService;
import ftn.uns.ac.rs.service.UslugaService;

@RestController
@RequestMapping("/syncAll")
public class SyncAllController {
	@Autowired
	KategorijaSmestajaService kategorijaSmestajaService = new KategorijaSmestajaService();
	
	@Autowired
	TipSmestajaService tipSmestajaService = new TipSmestajaService();   
	
	@Autowired
	TipSobeService tipSobeService = new TipSobeService();
	
	@Autowired
	UslugaService uslugaService = new UslugaService();
	
	@GetMapping
	public ResponseEntity<String> getAllSync(){
		try {
			kategorijaSmestajaService.getAllSync();
			tipSmestajaService.getAllSync();
			tipSobeService.getAllSync();
			uslugaService.getAllSync();
			return new ResponseEntity<String>("Podaci uspesno sinhronizovani!", HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>("Greska pri sinhronizaciji!", HttpStatus.CONFLICT);		
		}
	}
	

}
