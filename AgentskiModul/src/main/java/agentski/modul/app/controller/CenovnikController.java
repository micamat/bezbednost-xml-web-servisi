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

import agentski.modul.app.modelDTO.CenovnikDTO;
import agentski.modul.app.service.CenovnikService;

@RestController
@RequestMapping("/cenovnik")
public class CenovnikController {

	@Autowired
	private CenovnikService cenovnikService;

	@GetMapping
	public ResponseEntity<List<CenovnikDTO>> getAll(){
		if(cenovnikService.getAll() == null) {
			return new ResponseEntity<List<CenovnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CenovnikDTO>>(cenovnikService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CenovnikDTO> getById(@PathVariable Long id){
		if(cenovnikService.getById(id) == null) {
			return new ResponseEntity<CenovnikDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<CenovnikDTO>(cenovnikService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/smestaj/{id}")
	public ResponseEntity<List<CenovnikDTO>> getBySmestaj(@PathVariable Long smestajId){
		if(cenovnikService.getBySmestaj(smestajId) == null) {
			return new ResponseEntity<List<CenovnikDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CenovnikDTO>>(cenovnikService.getBySmestaj(smestajId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> add(@RequestBody CenovnikDTO cenovnikDTO){
		if(cenovnikService.add(cenovnikDTO)) {
			return new ResponseEntity<String>("Cenovnik je uspesno dodat!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri dodavanju cenovnika!", HttpStatus.CONFLICT);
		}
			
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		if(cenovnikService.delete(id)) {
			return new ResponseEntity<String>("Cenovnik je uspesno obrisan!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Cenovnik nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}

}
