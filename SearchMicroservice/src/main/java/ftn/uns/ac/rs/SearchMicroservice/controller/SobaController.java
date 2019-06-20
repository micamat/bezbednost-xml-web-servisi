package ftn.uns.ac.rs.SearchMicroservice.controller;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.SearchMicroservice.model.Soba;
import ftn.uns.ac.rs.SearchMicroservice.service.SobaService;

@RestController
@RequestMapping(value="/soba")
public class SobaController {
	@Autowired
	SobaService sobaService;
	
	@GetMapping
	public ResponseEntity<List<Soba>> search(@PathParam("tip") String tip, @PathParam("smestaj") Long smestaj, 
			@RequestParam(value = "usluge", required = false) List<Long> usluge,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "datumOd", required = false) Date datumOd,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "datumDo", required = false) Date datumDo){
		return new ResponseEntity<List<Soba>>(sobaService.search(tip, smestaj, usluge, datumOd, datumDo), HttpStatus.OK);
	}
	
	
}
