package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.ShowZauzeceDTO;
import ftn.uns.ac.rs.model.Zauzece;
import ftn.uns.ac.rs.model.ZauzeceDTO;
import ftn.uns.ac.rs.service.ZauzeceService;

@RestController
@RequestMapping("/zauzece")
public class ZauzeceController {

	@Autowired
	private ZauzeceService zauzeceService;
	
	@GetMapping
	public ResponseEntity<List<ShowZauzeceDTO>> getAll(){
		if(zauzeceService.getAll() == null) {
			return new ResponseEntity<List<ShowZauzeceDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowZauzeceDTO>>(zauzeceService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowZauzeceDTO> getById(@PathVariable Long id){
		if(zauzeceService.getById(id) == null) {
			return new ResponseEntity<ShowZauzeceDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowZauzeceDTO>(zauzeceService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/smestaj/{smestajId}")
	public ResponseEntity<List<ShowZauzeceDTO>> getBySmestaj(@PathVariable Long smestajId){
		if(zauzeceService.getBySmestaj(smestajId) == null) {
			return new ResponseEntity<List<ShowZauzeceDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowZauzeceDTO>>(zauzeceService.getBySmestaj(smestajId), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<String> add(@RequestBody ZauzeceDTO zauzeceDTO){
		if(zauzeceService.add(zauzeceDTO)) {
			return new ResponseEntity<String>("Zauzece je uspesno dodato!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju zauzeca!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(zauzeceService.delete(id)) {
			return new ResponseEntity<String>("Zauzece je uspesno obrisano!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Zauzece nije pronadjeno!", HttpStatus.NOT_FOUND);
		}
	}

}
