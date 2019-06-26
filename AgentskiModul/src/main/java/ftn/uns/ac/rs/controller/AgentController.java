package ftn.uns.ac.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.AgentLoginDTO;
import ftn.uns.ac.rs.model.ShowAgentDTO;
import ftn.uns.ac.rs.service.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	@Autowired
	private AgentService agentService;
	
	@GetMapping
	public ResponseEntity<List<ShowAgentDTO>> getAll(){
		if(agentService.getAll() == null) {
			return new ResponseEntity<List<ShowAgentDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShowAgentDTO>>(agentService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ShowAgentDTO> getById(@PathVariable Long id){
		if(agentService.getById(id) == null) {
			return new ResponseEntity<ShowAgentDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShowAgentDTO>(agentService.getById(id), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody AgentDTO agentDTO){
		if(agentService.updateSync(agentDTO)) {
			return new ResponseEntity<String>("Agent je uspesno azuriran!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Greska pri azuriranju agenta!", HttpStatus.CONFLICT);
		}
			
	}
	
	@PostMapping
	public ResponseEntity<String> login(@RequestBody AgentLoginDTO agentDTO){
		String string = agentService.login(agentDTO);
		if(string != null) {
			return new ResponseEntity<String>(string, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(string, HttpStatus.CONFLICT);
		}
	}
}
