package adminski.modul.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.model.Agent;
import adminski.modul.app.service.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	private AgentService agentService;
	
	@PostMapping
	public ResponseEntity<Agent> createAgent(@RequestBody Agent agent, HttpSession session){
		if (session.getAttribute("loggedIn") == null) {
			return new ResponseEntity<Agent>(HttpStatus.FORBIDDEN);
		} else {
			agentService.createAgent(agent);
			
			return new ResponseEntity<Agent>(agent, HttpStatus.OK);
		}
	}
}
