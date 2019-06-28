package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.configuration.Username;
import ftn.uns.ac.rs.model.Agent;
import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.ShowAgentDTO;
import ftn.uns.ac.rs.repository.AgentRepository;

@Service
public class AgentService {
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
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
		ThreadContext.put("user", Username.getLoggedUser());
		Agent a = agentRepository.findByKorisnickoIme(d.getKorisnickoIme());
		if(encoder.matches(d.getPrethodnaLozinka(), a.getLozinka())){
			a.setLozinka(encoder.encode(d.getLozinka()));
			Agent ar = new Agent();
			try {
				ar = agentRepository.save(a);
				logger.info(USER, "Uspesno promenjena lozinka");
			}catch (Exception e) {
				logger.error(USER, "Neuspesna promena lozinke: " + e.getMessage());
			}
			return ar.getId().intValue();
		}else {
			logger.warn(USER, "Greska prilikom promene lozinke: stara lozinka nije ispravno uneta");
			return -1;
		}
	}

}
