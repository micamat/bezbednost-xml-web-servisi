package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.config.Username;
import ftn.uns.ac.rs.config.Auth;
import ftn.uns.ac.rs.model.Agent;
import ftn.uns.ac.rs.model.AgentDTO;
import ftn.uns.ac.rs.model.AgentLoginDTO;
import ftn.uns.ac.rs.model.AgentLoginRequest;
import ftn.uns.ac.rs.model.AgentLoginResponse;
import ftn.uns.ac.rs.model.AgentLogoutRequest;
import ftn.uns.ac.rs.model.AgentLogoutResponse;
import ftn.uns.ac.rs.model.GetAllAgentRequest;
import ftn.uns.ac.rs.model.GetAllAgentResponse;
import ftn.uns.ac.rs.model.LoggedUser;
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

		Auth.authenticateClient(producerPort);
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
		System.out.println(agentDTO);
		if (agentDTO.getKorisnickoIme() == null || agentDTO.getLozinka() == null || agentDTO.getPrethodnaLozinka() == null) {
			return false;
		}
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		Auth.authenticateClient(producerPort);
		UpdateAgentRequest updateAgentRequest = new UpdateAgentRequest();
		UpdateAgentResponse updateAgentResponse = new UpdateAgentResponse();
		updateAgentRequest.setAgentDTO(agentDTO);
		try {

			updateAgentResponse = producerPort.updateAgent(updateAgentRequest);
			ThreadContext.put("user", agentDTO.getKorisnickoIme());
			if(updateAgentResponse.isSuccessful()) {
				logger.info(USER, "Uspesno promenjena lozinka");
				return true;
			} else {
				logger.warn(USER, "Lozinka nije promenjena");
			}
		}catch (Exception e) {
			logger.error(USER, "Greska prilikom izmene lozinke: " + e.getMessage());
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


	public LoggedUser login(AgentLoginDTO agentLoginDTO) {
		if (agentLoginDTO.getUsername() == null || agentLoginDTO.getPassword() == null) {
			return null;
		}
		System.out.println(agentLoginDTO.getUsername() + " asdufhsdiufhsdoiufh");
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		// autentifikacija pomocu sertifikata
		//Auth.authenticateClient(producerPort);
		AgentLoginRequest agentLoginRequest = new AgentLoginRequest();
		AgentLoginResponse agentLoginResponse = new AgentLoginResponse();
		agentLoginRequest.setusername(agentLoginDTO.getUsername());
		agentLoginRequest.setpassword(agentLoginDTO.getPassword());
		ThreadContext.put("user", agentLoginDTO.getUsername());

		try {
			ThreadContext.put("user", agentLoginDTO.getUsername());

			agentLoginResponse = producerPort.agentLogin(agentLoginRequest);
			LoggedUser loggedUser = new LoggedUser();
			if (agentLoginResponse.getToken() == null || agentLoginResponse.getUsername() == null) {

				logger.error(USER, "Agent neuspesno logovan");
				return null;
			}else {
				loggedUser.setToken(agentLoginResponse.getToken());
				loggedUser.setUsername(agentLoginResponse.getUsername());
				Username.setLoggedUser(loggedUser.getUsername());
				logger.info(USER, "Uspesno logovanje");
				return loggedUser;
			}
		} catch (Exception e) {
			ThreadContext.put("user", agentLoginDTO.getUsername());

			e.printStackTrace();
			logger.error(USER, "Greska prilikom logovanja: " + e.getMessage());
		}
		return null;
	}
	
	public boolean logout(String username) {
		if (username  == null) {
			return false;
		}
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		// autentifikacija pomocu sertifikata
		Auth.authenticateClient(producerPort);
		AgentLogoutRequest agentLogoutRequest = new AgentLogoutRequest();
		AgentLogoutResponse agentLogoutResponse = new AgentLogoutResponse();
		agentLogoutRequest.setusername(username);
		
		try {

			agentLogoutResponse = producerPort.agentLogout(agentLogoutRequest);
			ThreadContext.put("user", username);
			if (!agentLogoutResponse.isSuccessful()) {

				logger.error(USER, "Agent nije izlogovan");
				return false;
			}else {

				logger.info(USER, "Uspesno izlogovan");
				return true;
			}
		} catch (Exception e) {

			logger.error(USER, "Greska prilikom izlogovanja: " + e.getMessage());
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
