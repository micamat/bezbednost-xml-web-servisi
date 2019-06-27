package ftn.uns.ac.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.service.AgentService;
import ftn.uns.ac.rs.service.KategorijaSmestajaService;
import ftn.uns.ac.rs.service.KomentarService;
import ftn.uns.ac.rs.service.KorisnikService;
import ftn.uns.ac.rs.service.PorukaService;
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
	
	@Autowired
	AgentService agentService = new AgentService();
	
	@Autowired
	KomentarService komentarService = new KomentarService();
	
	@Autowired
	KorisnikService korisnikService = new KorisnikService();
	
	@Autowired 
	PorukaService porukaService = new PorukaService();
	
	@GetMapping
	public ResponseEntity<String> getAllSync(){
		try {
			kategorijaSmestajaService.getAllSync();
			tipSmestajaService.getAllSync();
			tipSobeService.getAllSync();
			uslugaService.getAllSync();
			agentService.getAllSync();
			komentarService.getAllSync();
			korisnikService.getAllSync();
			//porukaService.getAllSync();
			return new ResponseEntity<String>("Podaci uspesno sinhronizovani!", HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>("Greska pri sinhronizaciji!", HttpStatus.CONFLICT);		
		}
	}
	

}
