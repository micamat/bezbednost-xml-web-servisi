package ftn.uns.ac.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.service.TipSmestajaService;

@RestController
@RequestMapping("/tip-smestaja")
public class TipSmestajaController {
	
	@Autowired
	private TipSmestajaService tipSmestajaService;

	/*@GetMapping
	public ResponseEntity<List<SifarnikDTO>> getAll(){
		if(tipSmestajaService.getAll() == null) {
			return new ResponseEntity<List<SifarnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SifarnikDTO>>(tipSmestajaService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SifarnikDTO> getById(@PathVariable Long id){
		if(tipSmestajaService.getById(id) == null) {
			return new ResponseEntity<SifarnikDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SifarnikDTO>(tipSmestajaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody SifarnikDTO SifarnikDTO){
		if(tipSmestajaService.add(SifarnikDTO)) {
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
	}*/

}
