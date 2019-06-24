package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.Agent;
import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.GetAllAgentRequest;
import ftn.uns.ac.rs.model.GetAllAgentResponse;
import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaRequest;
import ftn.uns.ac.rs.model.GetAllKategorijaSmestajaResponse;
import ftn.uns.ac.rs.model.KategorijaSmestaja;
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

	/*
	 * public List<AgentDTO> getAllSync() { ProducerPortService producerPortService
	 * = new ProducerPortService(); ProducerPort producerPort =
	 * producerPortService.getProducerPortSoap11();
	 * 
	 * GetAllAgentRequest getAllAgentRequest = new GetAllAgentRequest();
	 * GetAllAgentResponse getAllAgentResponse =
	 * producerPort.getAllAgent(getAllAgentRequest);
	 * 
	 * for (AgentDTO agentDTO : getAllAgentResponse.getAgentDTO()) {
	 * agentRepository.save(convertToEntity(agentDTO)); } for (Agent agent :
	 * agentRepository.findAll()) { boolean exists = false; for (AgentDTO agentDTO :
	 * getAllAgentResponse.getAgentDTO()) { if (agent.getId() == agentDTO.getId()) {
	 * exists = true; break; } } if (!exists) {
	 * agentRepository.deleteById(agent.getId()); } } return
	 * getAllAgentResponse.getAgentDTO(); };
	 */

	public int updateSync(AgentDTO agentDTO) {
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		UpdateAgentRequest updateAgentRequest = new UpdateAgentRequest();
		updateAgentRequest.setAgentDTO(agentDTO);
		UpdateAgentResponse updateAgentResponse = producerPort.updateAgent(updateAgentRequest);
		return updateAgentResponse.getId();
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
		Agent agent = agentRepository.findById(agentDTO.getId()).orElse(null);
		agent.setId(agentDTO.getId());
		agent.setLozinka(agentDTO.getLozinka());
		agent = agentRepository.save(agent);
		if (agent != null) {
			// createSync(agentDTO);
			return true;
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
	
	/*
	 * private Agent convertToDTO(AgentDTO agentDTO) { Agent agent = new Agent();
	 * agent.setId(agentDTO.getId()); agent.setIme(agentDTO.getIme());
	 * agent.setPrezime(agentDTO.getPrezime());
	 * agent.setAdresa(agentDTO.getAdresa()); agent.setEmail(agentDTO.getEmail());
	 * agent.setKorisnickoIme(agentDTO.getKorisnickoIme());
	 * agent.setPoslovniMaticniBroj(agentDTO.getPoslovniMaticniBroj()); return
	 * agent; }
	 */
}
