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

import agentski.modul.app.modelDTO.ZauzeceDTO;
import agentski.modul.app.service.ZauzeceService;

@RestController
@RequestMapping("/zauzece")
public class ZauzeceController {

	@Autowired
	private ZauzeceService zauzeceService;

	@GetMapping
	public ResponseEntity<List<ZauzeceDTO>> getAll(){
		if(zauzeceService.getAll() == null) {
			return new ResponseEntity<List<ZauzeceDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ZauzeceDTO>>(zauzeceService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ZauzeceDTO> getById(@PathVariable Long id){
		if(zauzeceService.getById(id) == null) {
			return new ResponseEntity<ZauzeceDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ZauzeceDTO>(zauzeceService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/smestaj/{id}")
	public ResponseEntity<List<ZauzeceDTO>> getBySmestaj(@PathVariable Long smestajId){
		if(zauzeceService.getBySmestaj(smestajId) == null) {
			return new ResponseEntity<List<ZauzeceDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ZauzeceDTO>>(zauzeceService.getBySmestaj(smestajId), HttpStatus.OK);
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
