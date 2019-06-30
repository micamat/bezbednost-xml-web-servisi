package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.DTOs.KomentarDTO;
import ftn.uns.ac.rs.services.KomentarService;

@RestController
@RequestMapping("/komentari")
public class KomentarController {

	@Autowired
	private KomentarService commentService;

	@GetMapping
	public ResponseEntity<List<KomentarDTO>> getAll() {
		if (commentService.getAll() == null) {
			return new ResponseEntity<List<KomentarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KomentarDTO>>(commentService.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/neodobreni")
	public ResponseEntity<List<KomentarDTO>> getNeodobreni() {
		if (commentService.getNeodobreni() == null) {
			return new ResponseEntity<List<KomentarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KomentarDTO>>(commentService.getNeodobreni(), HttpStatus.OK);
	}

	@GetMapping(value = "/odobreni")
	public ResponseEntity<List<KomentarDTO>> getOdobreni() {
		if (commentService.getOdobreni() == null) {
			return new ResponseEntity<List<KomentarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<KomentarDTO>>(commentService.getOdobreni(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<KomentarDTO> changeStatur(@PathVariable("id") Long id) {
		KomentarDTO k = commentService.changeStatus(id);
		if (k != null) {
			return new ResponseEntity<KomentarDTO>(k, HttpStatus.OK);
		}
		return new ResponseEntity<KomentarDTO>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		if (commentService.delete(id)) {
			return new ResponseEntity<String>("Komentar je uspesno obrisan!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Komentar nije pronadjen!", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody KomentarDTO komentar) {
		if (commentService.add(komentar)) {
			return new ResponseEntity<String>("Komentar je uspesno dodat!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Greska pri dodavanju komentara!", HttpStatus.CONFLICT);
		}
	}

}
