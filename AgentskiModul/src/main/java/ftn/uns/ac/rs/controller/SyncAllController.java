package ftn.uns.ac.rs.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.config.Username;
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
	

	

	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	private Logger logger = LogManager.getLogger();
	
	
	

	
	@GetMapping
	public ResponseEntity<String> getAllSync(){
		ThreadContext.put("user", Username.getLoggedUser());



		try {
			kategorijaSmestajaService.getAllSync();
			tipSmestajaService.getAllSync();
			tipSobeService.getAllSync();
			uslugaService.getAllSync();
			agentService.getAllSync();
			//komentarService.getAllSync();
			//korisnikService.getAllSync();
			//porukaService.getAllSync();

			logger.info(USER, "Podaci uspesno sinhronizovani");
			return new ResponseEntity<String>("Podaci uspesno sinhronizovani!", HttpStatus.OK);
			
		
		}catch(Exception e){
			logger.error(USER, "Podaci nisu sinhronizovani: " + e.getMessage());

			return new ResponseEntity<String>("Greska pri sinhronizaciji!", HttpStatus.CONFLICT);		
		}
	}
	

}
