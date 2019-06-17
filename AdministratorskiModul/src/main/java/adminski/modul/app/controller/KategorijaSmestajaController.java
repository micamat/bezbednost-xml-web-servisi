package adminski.modul.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import adminski.modul.app.model.KategorijaSmestaja;
import adminski.modul.app.service.KategorijaSmestajaService;

@RestController
@RequestMapping("/kategorijaSmestaja")
public class KategorijaSmestajaController {

	@Autowired
	private KategorijaSmestajaService kategorijaSmestajaService;
	
	@GetMapping("{id}")
	public ResponseEntity<KategorijaSmestaja> getKategorijaSmestaja(@PathVariable Long id, HttpSession session){
		/*if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<KategorijaSmestaja>(HttpStatus.FORBIDDEN);
		} else {*/
			KategorijaSmestaja kategorija = kategorijaSmestajaService.getCategoryAccommodationById(id);
			
			return new ResponseEntity<KategorijaSmestaja>(kategorija, HttpStatus.OK);
		//}
	}
	
	@GetMapping
	public ResponseEntity<List<KategorijaSmestaja>> getAllKategorijaSmestaja(HttpSession session){
		/*if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<List<KategorijaSmestaja>>(HttpStatus.FORBIDDEN);
		} else {*/
			ArrayList<KategorijaSmestaja> kategorije = new ArrayList<>();
			
			for(KategorijaSmestaja kategorija : kategorijaSmestajaService.getAllCategoryAccommodations()) {
				kategorije.add(kategorija);
			}
			
			return new ResponseEntity<List<KategorijaSmestaja>>(kategorije, HttpStatus.OK);
		//}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteTipSmestaja(@PathVariable Long id, HttpSession session){
		/*if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		} else {*/
			if (kategorijaSmestajaService.removeCategoryAccommodation(id)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		//}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<KategorijaSmestaja> updateKategorijaSmestaja(@PathVariable Long id, @RequestBody KategorijaSmestaja kategorijaSmestaja, HttpSession session){
		/*if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<KategorijaSmestaja>(HttpStatus.FORBIDDEN);
		} else {*/
			if (kategorijaSmestajaService.updateCategoryAccommodation(id,kategorijaSmestaja)) {
				return new ResponseEntity<KategorijaSmestaja>(kategorijaSmestajaService.getCategoryAccommodationById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<KategorijaSmestaja>(HttpStatus.BAD_REQUEST);
			}
		//}
	}
	
	@PostMapping
	public ResponseEntity<KategorijaSmestaja> createTipSmestaja(@RequestBody KategorijaSmestaja kategorija, HttpSession session){
		/*if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<KategorijaSmestaja>(HttpStatus.FORBIDDEN);
		} else {*/
			kategorijaSmestajaService.createCategoryAccommodation(kategorija);
			
			return new ResponseEntity<KategorijaSmestaja>(kategorija, HttpStatus.OK);
		//}
	}
}
