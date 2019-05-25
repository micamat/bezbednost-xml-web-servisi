package agentski.modul.app.controller;

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

import agentski.modul.app.modelDTO.TipSmestajaDTO;
import agentski.modul.app.service.TipSmestajaService;

@RestController
@RequestMapping("/tip-smestaja")
public class TipSmestajaController {
	
	@Autowired
	private TipSmestajaService tipSmestajaService;

	@GetMapping
	public ResponseEntity<List<TipSmestajaDTO>> getAll(){
		if(tipSmestajaService.getAll() == null) {
			return new ResponseEntity<List<TipSmestajaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TipSmestajaDTO>>(tipSmestajaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipSmestajaDTO> getById(@PathVariable Long id){
		if(tipSmestajaService.getById(id) == null) {
			return new ResponseEntity<TipSmestajaDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TipSmestajaDTO>(tipSmestajaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody TipSmestajaDTO tipSmestajaDTO){
		if(tipSmestajaService.add(tipSmestajaDTO)) {
			return new ResponseEntity<String>("Tip smestaja je uspesno dodat!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju tipa smestaja!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(tipSmestajaService.delete(id)) {
			return new ResponseEntity<String>("Tip smestaja je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Tip smestaja nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}

}
