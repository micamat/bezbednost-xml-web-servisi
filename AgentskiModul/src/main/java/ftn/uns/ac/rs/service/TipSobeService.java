package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.GetAllTipSobeRequest;
import ftn.uns.ac.rs.model.GetAllTipSobeResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.SifarnikDTO;
import ftn.uns.ac.rs.model.TipSobe;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class TipSobeService {
	@Autowired
	private TipSobeRepository tipSobeRepository;

	public List<SifarnikDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllTipSobeRequest getTipSobeRequest = new GetAllTipSobeRequest();
		GetAllTipSobeResponse getTipSobeResponse = producerPort.getAllTipSobe(getTipSobeRequest);
		
		for (SifarnikDTO tipSobeDTO : getTipSobeResponse.getTipSobeDTO()) {
			tipSobeRepository.save(convertToEntity(tipSobeDTO));
		}
		for (TipSobe tipSobe : tipSobeRepository.findAll()) {
			boolean exists = false;
			for (SifarnikDTO tipSobeDTO : getTipSobeResponse.getTipSobeDTO()) {
				if (tipSobe.getId().equals(tipSobeDTO.getId())){
					exists = true;
					break;
				}
			}
			if (!exists) {
				tipSobeRepository.deleteById(tipSobe.getId());
			}
		}
		return getTipSobeResponse.getTipSobeDTO();
	};
	
	public List<SifarnikDTO> getAll(){ 
		return tipSobeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!tipSobeRepository.existsById(id)) {
			return null;
		}
		TipSobe tipSobeDTO = tipSobeRepository.findById(id).orElse(null);
		return convertToDTO(tipSobeDTO);
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
	}
}
