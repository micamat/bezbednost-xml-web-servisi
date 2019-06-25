package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Agent;
import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.AgentLoginDTO;
import ftn.uns.ac.rs.model.AgentLoginRequest;
import ftn.uns.ac.rs.model.AgentLoginResponse;
import ftn.uns.ac.rs.model.GetAllAgentRequest;
import ftn.uns.ac.rs.model.GetAllAgentResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowAgentDTO;
import ftn.uns.ac.rs.model.UpdateAgentRequest;
import ftn.uns.ac.rs.model.UpdateAgentResponse;
import ftn.uns.ac.rs.repository.AgentRepository;

@Service
public class AgentService {
	@Autowired
	private AgentRepository agentRepository;
	
	private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");

	public List<ShowAgentDTO> getAllSync() {
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		GetAllAgentRequest getAllAgentRequest = new GetAllAgentRequest();
		GetAllAgentResponse getAllAgentResponse = producerPort.getAllAgent(getAllAgentRequest);

		for (ShowAgentDTO agentDTO : getAllAgentResponse.getAgent()) {
			agentRepository.save(convertToEntity(agentDTO));
		}
		for (Agent agent : agentRepository.findAll()) {
			boolean exists = false;
			for (ShowAgentDTO agentDTO : getAllAgentResponse.getAgent()) {
				if (agent.getId() == agentDTO.getId()) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				agentRepository.deleteById(agent.getId());
			}
		}
		return getAllAgentResponse.getAgent();
	};

	public boolean updateSync(AgentDTO agentDTO) {
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		UpdateAgentRequest updateAgentRequest = new UpdateAgentRequest();
		UpdateAgentResponse updateAgentResponse = new UpdateAgentResponse();
		updateAgentResponse = producerPort.updateAgent(updateAgentRequest);
		try {
			Agent agent = agentRepository.findByKorisnickoIme(agentDTO.getKorisnickoIme());
			agent.setToken(updateAgentResponse.getToken());
			agentRepository.save(agent);
			logger.info(USER, "Uspesno logovanje");
			return true;
		} catch (Exception e) {

			logger.error(USER, "Greska prilikom logovanja: " + e.getMessage());
		}
		return false;
	};

	public List<ShowAgentDTO> getAll() {
		return agentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};

	public ShowAgentDTO getById(Long id) {
		if (!agentRepository.existsById(id)) {
			return null;
		}
		Agent agent = agentRepository.findById(id).orElse(null);
		return convertToDTO(agent);
	}

	public boolean update(AgentDTO agentDTO) {
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		UpdateAgentRequest updateAgentRequest = new UpdateAgentRequest();
		UpdateAgentResponse updateAgentResponse = new UpdateAgentResponse();
		updateAgentResponse = producerPort.updateAgent(updateAgentRequest);
		try {
			Agent agent = agentRepository.findByKorisnickoIme(agentDTO.getKorisnickoIme());
			agent.setToken(updateAgentResponse.getToken());
			agentRepository.save(agent);
			logger.info(USER, "Uspesno logovanje");
			return true;
		} catch (Exception e) {

			logger.error(USER, "Greska prilikom logovanja: " + e.getMessage());
		}
		return false;
	}

	public boolean login(AgentLoginDTO agentLoginDTO) {

		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		AgentLoginRequest agentLoginRequest = new AgentLoginRequest();
		AgentLoginResponse agentLoginResponse = new AgentLoginResponse();
		
		agentLoginResponse = producerPort.agentLogin(agentLoginRequest);
		try {
			Agent agent = agentRepository.findByKorisnickoIme(agentLoginDTO.getUsername());
			agent.setToken(agentLoginResponse.getToken());
			agentRepository.save(agent);
			logger.info(USER, "Uspesno logovanje");
		} catch (Exception e) {

			logger.error(USER, "Greska prilikom logovanja: " + e.getMessage());
		}
		return false;
	}

	private ShowAgentDTO convertToDTO(Agent agent) {
		ShowAgentDTO agentDTO = new ShowAgentDTO();
		agentDTO.setId(agent.getId());
		agentDTO.setIme(agentDTO.getIme());
		agentDTO.setPrezime(agent.getPrezime());
		agentDTO.setAdresa(agent.getAdresa());
		agentDTO.setEmail(agent.getEmail());
		agentDTO.setKorisnickoIme(agent.getKorisnickoIme());
		agentDTO.setPoslovniMaticniBroj(agent.getPoslovniMaticniBroj());
		return agentDTO;
	}

	private Agent convertToEntity(ShowAgentDTO agentDTO) {
		Agent agent = new Agent();
		agent.setId(agentDTO.getId());
		agent.setIme(agentDTO.getIme());
		agent.setPrezime(agentDTO.getPrezime());
		agent.setAdresa(agentDTO.getAdresa());
		agent.setEmail(agentDTO.getEmail());
		agent.setKorisnickoIme(agentDTO.getKorisnickoIme());
		agent.setPoslovniMaticniBroj(agentDTO.getPoslovniMaticniBroj());
		return agent;
	}

}
