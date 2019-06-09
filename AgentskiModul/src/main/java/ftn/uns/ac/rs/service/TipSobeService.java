package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateTipSobeRequest;
import ftn.uns.ac.rs.model.CreateTipSobeResponse;
import ftn.uns.ac.rs.model.GetAllTipSobeRequest;
import ftn.uns.ac.rs.model.GetAllTipSobeResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.TipSobeDTO;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class TipSobeService {
	@Autowired
	private TipSobeRepository tipSobeRepository;

	public List<TipSobeDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllTipSobeRequest getTipSobeRequest = new GetAllTipSobeRequest();
		GetAllTipSobeResponse getTipSobeResponse = producerPort.getAllTipSobe(getTipSobeRequest);
		return getTipSobeResponse.getTipSobeDTO();
	};
	
	
	public int createSync(TipSobeDTO smd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateTipSobeRequest getTipSobeRequest = new CreateTipSobeRequest();
		getTipSobeRequest.setId(smd.getId());
		getTipSobeRequest.setNaziv(smd.getNaziv());
		getTipSobeRequest.setOpis(smd.getOpis());
		CreateTipSobeResponse getTipSobeResponse = producerPort.createTipSobe(getTipSobeRequest);
		return getTipSobeResponse.getId();
	};
	
	/*public List<SifarnikDTO> getAll(){ 
		return tipSobeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!tipSobeRepository.existsById(id)) {
			return null;
		}
		TipSobe tipSobeDTO = tipSobeRepository.findById(id).orElse(null);
		return convertToDTO(tipSobeDTO);
	}
	
	
	public boolean add(SifarnikDTO tipSobeDTO) {
		tipSobeDTO.setId(null);
		if(tipSobeRepository.findAll().stream().filter(x -> tipSobeDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			tipSobeRepository.save(convertToEntity(tipSobeDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(tipSobeRepository.existsById(id)) {
			tipSobeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SifarnikDTO convertToDTO(TipSobe tipSobe) {
		SifarnikDTO tipSobeDTO = new SifarnikDTO();
		tipSobeDTO.setId(tipSobe.getId());
		tipSobeDTO.setNaziv(tipSobe.getNaziv());
		tipSobeDTO.setOpis(tipSobe.getOpis());
		return tipSobeDTO;
	}
	
	private TipSobe convertToEntity(SifarnikDTO tipSobeDTO) {
		TipSobe tipSobe = new TipSobe();
		tipSobe.setId(tipSobeDTO.getId());
		tipSobe.setNaziv(tipSobeDTO.getNaziv());
		tipSobe.setOpis(tipSobeDTO.getOpis());
		return tipSobe;
	}*/
}
