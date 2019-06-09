package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateSobaRequest;
import ftn.uns.ac.rs.model.CreateSobaResponse;
import ftn.uns.ac.rs.model.GetAllSobaRequest;
import ftn.uns.ac.rs.model.GetAllSobaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
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

	/*public List<SobaDTO> getAll(){ 
		return sobaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};*/
	
	//TODO: Implementirati poslovnu logiku ..... cuvanja u bazu kao i 
		public List<SobaDTO> getAllSync(){
			ProducerPortService producerPortService = new ProducerPortService();
			ProducerPort producerPort = producerPortService.getProducerPortSoap11();
			
			GetAllSobaRequest getAllSobaRequest = new GetAllSobaRequest();
			GetAllSobaResponse getSmestajResponse = producerPort.getAllSoba(getAllSobaRequest);
			return getSmestajResponse.getSobaDTO();
		};
		
		
		public int createSync(SobaDTO smd){
			ProducerPortService producerPortService = new ProducerPortService();
			ProducerPort producerPort = producerPortService.getProducerPortSoap11();
			
			CreateSobaRequest getSobaRequest = new CreateSobaRequest();
			getSobaRequest.setId(smd.getId());
			getSobaRequest.setIdSmestaj(smd.getIdSmestaj());
			getSobaRequest.setIdTipSobe(smd.getIdTipSobe());
			getSobaRequest.setNaziv(smd.getNaziv());
			getSobaRequest.setOpis(smd.getOpis());
			getSobaRequest.setSlika(smd.getSlika());
			
			CreateSobaResponse getSmestajResponse = producerPort.createSoba(getSobaRequest);
			return getSmestajResponse.getId();
		};
	
	
	/*
	public SobaDTO getById(Long id) {
		if(!sobaRepository.existsById(id)) {
			return null;
		}
		Soba soba = sobaRepository.findById(id).orElse(null);
		return convertToDTO(soba);
	}
	
	public List<SobaDTO> getBySmestaj(Long smestajId) {
		return sobaRepository.findAll().stream().filter(x -> smestajId.equals(x.getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	
	public boolean add(SobaDTO sobaDTO) {
		sobaDTO.setId(null);
		if(sobaRepository.save(convertToEntity(sobaDTO)) != null) {
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
	
	private SobaDTO convertToDTO(Soba soba) {
		SobaDTO sobaDTO = new SobaDTO();
		sobaDTO.setId(soba.getId());
		sobaDTO.setNaziv(soba.getNaziv());
		sobaDTO.setOpis(soba.getOpis());
		sobaDTO.setSlika(soba.getSlika());
		sobaDTO.setTipSobeId(soba.getTipSobe().getId());
		sobaDTO.setSmestajId(soba.getSmestaj().getId());
		return sobaDTO;
	}
	
	private Soba convertToEntity(SobaDTO sobaDTO) {
		Soba soba = new Soba();
		soba.setId(sobaDTO.getId());
		soba.setNaziv(sobaDTO.getNaziv());
		soba.setOpis(sobaDTO.getOpis());
		soba.setSlika(sobaDTO.getSlika());
		soba.setTipSobe(tipSobeRepository.findById(sobaDTO.getTipSobeId()).orElse(null));
		soba.setSmestaj(smestajRepository.findById(sobaDTO.getSmestajId()).orElse(null));
		return soba;
	}*/
}