package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateTipSmestajaRequest;
import ftn.uns.ac.rs.model.CreateTipSmestajaResponse;
import ftn.uns.ac.rs.model.GetAllTipSmestajaRequest;
import ftn.uns.ac.rs.model.GetAllTipSmestajaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.TipSmestajaDTO;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class TipSmestajaService {
	
	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;

	public List<TipSmestajaDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllTipSmestajaRequest getTipSmestajaRequest = new GetAllTipSmestajaRequest();
		GetAllTipSmestajaResponse getTipSmestajaResponse = producerPort.getAllTipSmestaja(getTipSmestajaRequest);
		return getTipSmestajaResponse.getTipSmestajaDTO();
	};
	
	
	public int createSync(TipSmestajaDTO smd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateTipSmestajaRequest getTipSmestajaRequest = new CreateTipSmestajaRequest();
		getTipSmestajaRequest.setId(smd.getId());
		getTipSmestajaRequest.setNaziv(smd.getNaziv());
		getTipSmestajaRequest.setOpis(smd.getOpis());
		CreateTipSmestajaResponse getTipSmestajaResponse = producerPort.createTipSmestaja(getTipSmestajaRequest);
		return getTipSmestajaResponse.getId();
	};
	
	/*public List<SifarnikDTO> getAll(){ 
		return tipSmestajaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SifarnikDTO getById(Long id) {
		if(!tipSmestajaRepository.existsById(id)) {
			return null;
		}
		TipSmestaja tipSmestaja = tipSmestajaRepository.findById(id).orElse(null);
		return convertToDTO(tipSmestaja);
	}
	
	
	public boolean add(SifarnikDTO tipSmestajaDTO) {
		tipSmestajaDTO.setId(null);
		if(tipSmestajaRepository.findAll().stream().filter(x -> tipSmestajaDTO.getNaziv().equals(x.getNaziv())).map(this::convertToDTO).collect(Collectors.toList()).isEmpty()) {
			tipSmestajaRepository.save(convertToEntity(tipSmestajaDTO));
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(tipSmestajaRepository.existsById(id)) {
			tipSmestajaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SifarnikDTO convertToDTO(TipSmestaja tipSmestaja) {
		SifarnikDTO tipSmestajaDTO = new SifarnikDTO();
		tipSmestajaDTO.setId(tipSmestaja.getId());
		tipSmestajaDTO.setNaziv(tipSmestaja.getNaziv());
		tipSmestajaDTO.setOpis(tipSmestaja.getOpis());
		return tipSmestajaDTO;
	}
	
	private TipSmestaja convertToEntity(SifarnikDTO tipSmestajaDTO) {
		TipSmestaja tipSmestaja = new TipSmestaja();
		tipSmestaja.setId(tipSmestajaDTO.getId());
		tipSmestaja.setNaziv(tipSmestajaDTO.getNaziv());
		tipSmestaja.setOpis(tipSmestajaDTO.getOpis());
		return tipSmestaja;
	}*/
}
