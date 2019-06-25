package adminski.modul.app.service;

import java.util.List;

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
	
	public List<Agent> getAll(){
		return agentRepository.findAll();
	}
	
	public Agent getById(Long id) {
		return agentRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		agentRepository.delete(agentRepository.findById(id).get());
	}
	
	public void update(Agent novi, Long id) {
		Agent stari = agentRepository.findById(id).get();
		stari.setIme(novi.getIme());
		stari.setPrezime(novi.getPrezime());
		stari.setAdresa(novi.getAdresa());
		stari.setPoslovniMaticniBroj(novi.getPoslovniMaticniBroj());
		stari.setLozinka(novi.getLozinka());
		stari.setEmail(novi.getEmail());
		stari.setKorisnickoIme(novi.getKorisnickoIme());
		
		agentRepository.save(stari);
	}
}
