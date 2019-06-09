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

import ftn.uns.ac.rs.model.StatusSobeDTO;
import ftn.uns.ac.rs.service.StatusSobeService;


@RestController
@RequestMapping("/status-sobe")
public class StatusSobeController {


	@Autowired
	private StatusSobeService statusSobeService;

	@GetMapping(value = "getAllSync")
	public ResponseEntity<List<StatusSobeDTO>> getAllSync(){
		return new ResponseEntity<List<StatusSobeDTO>>(statusSobeService.getAllSync(), HttpStatus.OK);
	}
	
	@PostMapping(value = "createSync")
	public ResponseEntity<Integer> createSync(@RequestBody StatusSobeDTO StatusSobeDTO){
		
		return new ResponseEntity<Integer>(statusSobeService.createSync(StatusSobeDTO), HttpStatus.OK);
	}
	
	/*@GetMapping
	public ResponseEntity<List<SifarnikDTO>> getAll(){
		if(statusSobeService.getAll() == null) {
			return new ResponseEntity<List<SifarnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SifarnikDTO>>(statusSobeService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SifarnikDTO> getById(@PathVariable Long id){
		if(statusSobeService.getById(id) == null) {
			return new ResponseEntity<SifarnikDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SifarnikDTO>(statusSobeService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody SifarnikDTO SifarnikDTO){
		if(statusSobeService.add(SifarnikDTO)) {
			return new ResponseEntity<String>("Status sobe je uspesno dodat!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju statusa sobe!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(statusSobeService.delete(id)) {
			return new ResponseEntity<String>("Status sobe je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Status sobe nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}*/

}