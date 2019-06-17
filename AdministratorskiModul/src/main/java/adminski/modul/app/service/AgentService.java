package adminski.modul.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminski.modul.app.model.Agent;
import adminski.modul.app.repository.AgentRepository;

@Service
public class AgentService {
	
	@Autowired
	private AgentRepository agentRepository;
	
	public boolean createAgent(Agent agent) {
		agentRepository.save(agent);
		return true;
	}
}
