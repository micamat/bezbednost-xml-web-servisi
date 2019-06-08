package ftn.uns.ac.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.service.TipSobeService;

@RestController
@RequestMapping("/tip-sobe")
public class TipSobeController {

	@Autowired
	private TipSobeService tipSobeService;

	/*@GetMapping
	public ResponseEntity<List<SifarnikDTO>> getAll(){
		if(tipSobeService.getAll() == null) {
			return new ResponseEntity<List<SifarnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SifarnikDTO>>(tipSobeService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SifarnikDTO> getById(@PathVariable Long id){
		if(tipSobeService.getById(id) == null) {
			return new ResponseEntity<SifarnikDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SifarnikDTO>(tipSobeService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody SifarnikDTO SifarnikDTO){
		if(tipSobeService.add(SifarnikDTO)) {
			return new ResponseEntity<String>("Tip sobe je uspesno dodat!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju tipa sobe!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(tipSobeService.delete(id)) {
			return new ResponseEntity<String>("Tip sobe je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Tip sobe nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}*/

}
