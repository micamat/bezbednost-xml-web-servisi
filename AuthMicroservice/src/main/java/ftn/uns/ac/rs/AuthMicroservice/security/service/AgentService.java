package ftn.uns.ac.rs.AuthMicroservice.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Agent;
import ftn.uns.ac.rs.AuthMicroservice.security.repository.AgentRepository;

@Service
public class AgentService{

	@Autowired
	AgentRepository agentRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Agent save(Agent agent) {
		agent.setLozinka(encoder.encode(agent.getLozinka()));
		return agentRepo.save(agent);
	}
	
	
	
}
