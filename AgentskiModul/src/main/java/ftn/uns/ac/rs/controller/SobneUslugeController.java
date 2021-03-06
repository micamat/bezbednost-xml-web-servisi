package ftn.uns.ac.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.service.SobaService;
import ftn.uns.ac.rs.service.SobneUslugeService;

@RestController
@RequestMapping("/sobne-usluge")
public class SobneUslugeController {
	@Autowired
	private SobaService sobaService;
	@Autowired
	private SobneUslugeService sobneUslugeService;

	/*
	 * @GetMapping public ResponseEntity<List<ShowSobaDTO>> getAll(){
	 * if(sobaService.getAll() == null) { return new
	 * ResponseEntity<List<ShowSobaDTO>>(HttpStatus.NO_CONTENT); } return new
	 * ResponseEntity<List<ShowSobaDTO>>(sobaService.getAll(), HttpStatus.OK); }
	 * 
	 * @GetMapping(value = "/{id}") public ResponseEntity<ShowSobaDTO>
	 * getById(@PathVariable Long id){ if(sobaService.getById(id) == null) { return
	 * new ResponseEntity<ShowSobaDTO>(HttpStatus.NO_CONTENT); } return new
	 * ResponseEntity<ShowSobaDTO>(sobaService.getById(id), HttpStatus.OK); }
	 * 
	 * @GetMapping(value = "/smestaj/{smestajId}") public
	 * ResponseEntity<List<ShowSobaDTO>> getBySmestaj(@PathVariable Long smestajId){
	 * if(sobaService.getBySmestaj(smestajId) == null) { return new
	 * ResponseEntity<List<ShowSobaDTO>>(HttpStatus.NO_CONTENT); } return new
	 * ResponseEntity<List<ShowSobaDTO>>(sobaService.getBySmestaj(smestajId),
	 * HttpStatus.OK); }
	 * 
	 * @PostMapping public ResponseEntity<String> add(@RequestBody SobaDTO sobaDTO){
	 * if(sobaService.add(sobaDTO)) { return new
	 * ResponseEntity<String>("Soba je uspesno dodata!", HttpStatus.CREATED); }else
	 * { return new ResponseEntity<String>("Greska pri dodavanju sobe!",
	 * HttpStatus.CONFLICT); }
	 * 
	 * }
	 * 
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<String>
	 * delete(@PathVariable Long id){ if(sobaService.delete(id)) { return new
	 * ResponseEntity<String>("Soba je uspesno obrisana!", HttpStatus.OK); }else {
	 * return new ResponseEntity<String>("Soba nije pronadjena!",
	 * HttpStatus.NOT_FOUND); } }
	 */

}
