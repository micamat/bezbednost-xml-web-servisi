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

import agentski.modul.app.modelDTO.KategorijaSmestajaDTO;
import agentski.modul.app.service.KategorijaSmestajaService;

@RestController
@RequestMapping("/kategorija-smestaja")
public class KategorijaSmestajaController {

	@Autowired
	private KategorijaSmestajaService kategorijaSmestajaService;

	@GetMapping
	public ResponseEntity<List<KategorijaSmestajaDTO>> getAll(){
		if(kategorijaSmestajaService.getAll() == null) {
			return new ResponseEntity<List<KategorijaSmestajaDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KategorijaSmestajaDTO>>(kategorijaSmestajaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KategorijaSmestajaDTO> getById(@PathVariable Long id){
		if(kategorijaSmestajaService.getById(id) == null) {
			return new ResponseEntity<KategorijaSmestajaDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<KategorijaSmestajaDTO>(kategorijaSmestajaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody KategorijaSmestajaDTO kategorijaSmestajaDTO){
		if(kategorijaSmestajaService.add(kategorijaSmestajaDTO)) {
			return new ResponseEntity<String>("Kategorija smestaja je uspesno dodat!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju kategorije smestaja!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(kategorijaSmestajaService.delete(id)) {
			return new ResponseEntity<String>("Kategorija smestaja je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Kategorija smestaja nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}


}
