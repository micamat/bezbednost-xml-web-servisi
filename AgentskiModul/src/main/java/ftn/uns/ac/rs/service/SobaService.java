package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateSobaRequest;
import ftn.uns.ac.rs.model.CreateSobaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowSobaDTO;
import ftn.uns.ac.rs.model.Soba;
import ftn.uns.ac.rs.model.SobaDTO;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.SobaRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;

	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private TipSobeRepository tipSobeRepository;

	public List<ShowSobaDTO> getAll(){ 
		return sobaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
		
	public int createSync(Soba soba){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateSobaRequest getSobaRequest = new CreateSobaRequest();
		getSobaRequest.setId(soba.getId());
		getSobaRequest.setIdSmestaj(soba.getSmestaj().getId());
		getSobaRequest.setIdTipSobe(soba.getTipSobe().getId());
		getSobaRequest.setNaziv(soba.getNaziv());
		getSobaRequest.setOpis(soba.getOpis());
		getSobaRequest.setSlika(soba.getSlika());
		
		CreateSobaResponse getSmestajResponse = producerPort.createSoba(getSobaRequest);
		return getSmestajResponse.getId();
	};
	
	
	
	public ShowSobaDTO getById(Long id) {
		if(!sobaRepository.existsById(id)) {
			return null;
		}
		Soba soba = sobaRepository.findById(id).orElse(null);
		return convertToDTO(soba);
	}
	
	public List<ShowSobaDTO> getBySmestaj(Long smestajId) {
		return sobaRepository.findAll().stream().filter(x -> smestajId.equals(x.getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	
	public boolean add(SobaDTO sobaDTO) {
		sobaDTO.setId(sobaDTO.getId());
		Soba soba = sobaRepository.save(convertToEntity(sobaDTO));
		if(soba != null) {
			createSync(soba);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(sobaRepository.existsById(id)) {
			sobaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private ShowSobaDTO convertToDTO(Soba soba) {
		ShowSobaDTO sobaDTO = new ShowSobaDTO();
		sobaDTO.setId(soba.getId());
		sobaDTO.setNaziv(soba.getNaziv());
		sobaDTO.setOpis(soba.getOpis());
		sobaDTO.setSlika(soba.getSlika());
		sobaDTO.setNazivSmestaja(soba.getSmestaj().getNaziv());
		sobaDTO.setNazivTipaSobe(soba.getTipSobe().getNaziv());
		sobaDTO.setOpisTipaSobe(soba.getTipSobe().getOpis());
		return sobaDTO;
	}
	
	private Soba convertToEntity(SobaDTO sobaDTO) {
		Soba soba = new Soba();
		soba.setId(sobaDTO.getId());
		soba.setNaziv(sobaDTO.getNaziv());
		soba.setOpis(sobaDTO.getOpis());
		soba.setSlika(sobaDTO.getSlika());
		soba.setTipSobe(tipSobeRepository.findById(sobaDTO.getIdTipSobe()).orElse(null));
		soba.setSmestaj(smestajRepository.findById(sobaDTO.getIdSmestaj()).orElse(null));
		return soba;
	}
}
