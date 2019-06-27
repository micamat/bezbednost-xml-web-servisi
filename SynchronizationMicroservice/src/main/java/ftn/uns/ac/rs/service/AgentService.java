package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Agent;
import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.ShowAgentDTO;
import ftn.uns.ac.rs.repository.AgentRepository;

@Service
public class AgentService {
	
	@Autowired
	private AgentRepository agentRepository;
	
	public List<ShowAgentDTO> getAll(){
		List<ShowAgentDTO> dtos = new ArrayList<ShowAgentDTO>();
		for(Agent a : agentRepository.findAll()) {
			ShowAgentDTO as = new ShowAgentDTO();
			as.setAdresa(a.getAdresa());
			as.setEmail(a.getEmail());
			as.setId(a.getId());
			as.setIme(a.getIme());
			as.setPrezime(a.getPrezime());
			as.setKorisnickoIme(a.getKorisnickoIme());
			as.setPoslovniMaticniBroj(a.getPoslovniMaticniBroj());
			dtos.add(as);
		}
		return dtos;
	}
	
	public int update(AgentDTO d) {
		Agent a = agentRepository.findById(d.getId()).get();
		if(d.getPrethodnaLozinka().equals(a.getLozinka())){
			a.setLozinka(d.getLozinka());
			Agent ar = agentRepository.save(a);
			return ar.getId().intValue();
		}else {
			return -1;
		}
	}

}
