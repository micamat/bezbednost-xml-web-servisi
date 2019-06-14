package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateStatusSobeRequest;
import ftn.uns.ac.rs.model.CreateStatusSobeResponse;
import ftn.uns.ac.rs.model.GetAllStatusSobeRequest;
import ftn.uns.ac.rs.model.GetAllStatusSobeResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.SifarnikDTO;
import ftn.uns.ac.rs.model.StatusSobe;
import ftn.uns.ac.rs.repository.StatusSobeRepository;

@Service
public class StatusSobeService {
	@Autowired
	private StatusSobeRepository statusSobeRepository;

	public List<SifarnikDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllStatusSobeRequest getStatusSobeRequest = new GetAllStatusSobeRequest();
		GetAllStatusSobeResponse getStatusSobeResponse = producerPort.getAllStatusSobe(getStatusSobeRequest);
		return getStatusSobeResponse.getStatusSobeDTO();
	};
	
	
	public int createSync(SifarnikDTO smd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateStatusSobeRequest getStatusSobeRequest = new CreateStatusSobeRequest();
		getStatusSobeRequest.setId(smd.getId());
		getStatusSobeRequest.setNaziv(smd.getNaziv());
		getStatusSobeRequest.setOpis(smd.getOpis());
		CreateStatusSobeResponse getStatusSobeResponse = producerPort.createStatusSobe(getStatusSobeRequest);
		return getStatusSobeResponse.getId();
	};
	
	public List<SifarnikDTO> getAll(){ 
		return statusSobeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!statusSobeRepository.existsById(id)) {
			return null;
		}
		StatusSobe statusSobe = statusSobeRepository.findById(id).orElse(null);
		return convertToDTO(statusSobe);
	}
	
	
	public boolean add(SifarnikDTO statusSobeDTO) {
		statusSobeDTO.setId(null);
		if(statusSobeRepository.findAll().stream().filter(x -> statusSobeDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			statusSobeRepository.save(convertToEntity(statusSobeDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(statusSobeRepository.existsById(id)) {
			statusSobeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SifarnikDTO convertToDTO(StatusSobe statusSobe) {
		SifarnikDTO statusSobeDTO = new SifarnikDTO();
		statusSobeDTO.setId(statusSobe.getId());
		statusSobeDTO.setNaziv(statusSobe.getNaziv());
		statusSobeDTO.setOpis(statusSobe.getOpis());
		return statusSobeDTO;
	}
	
	private StatusSobe convertToEntity(SifarnikDTO statusSobeDTO) {
		StatusSobe statusSobe = new StatusSobe();
		statusSobe.setId(statusSobeDTO.getId());
		statusSobe.setNaziv(statusSobeDTO.getNaziv());
		statusSobe.setOpis(statusSobeDTO.getOpis());
		return statusSobe;
	}
}
