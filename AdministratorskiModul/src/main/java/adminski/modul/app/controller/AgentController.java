package adminski.modul.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Agent;
import adminski.modul.app.service.AgentService;
import adminski.modul.app.service.AuthService;

@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AgentService agentService;

	@PostMapping
	public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
		
		agentService.createAgent(agent);

		return new ResponseEntity<Agent>(agent, HttpStatus.OK);
		
	}

	@PreAuthorize("@authService.hasProtectedAccess()")
	@GetMapping
	public ResponseEntity<List<Agent>> getAll() {
		return new ResponseEntity<List<Agent>>(agentService.getAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@GetMapping("/{id}")
	public Agent getById(@PathVariable Long id) {
		return agentService.getById(id);
	}
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Agent novi, @PathVariable Long id){
		try {
			agentService.update(novi, id);
			return ResponseEntity.noContent().build();
		}catch(Exception e){
			return ResponseEntity.notFound().build();
		}
	}
	
	@PreAuthorize("@authService.hasProtectedAccess()")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			agentService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
