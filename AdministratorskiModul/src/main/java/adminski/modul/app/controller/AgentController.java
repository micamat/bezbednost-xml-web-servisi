package adminski.modul.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adminski.modul.app.service.AgentService;

@RestController
@RequestMapping("/")
public class AgentController {
	
	@Autowired
	private AgentService agentService;
	
	
}
