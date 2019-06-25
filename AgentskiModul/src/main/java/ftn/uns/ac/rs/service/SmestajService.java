package ftn.uns.ac.rs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateSmestajRequest;
import ftn.uns.ac.rs.model.CreateSmestajResponse;
import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowSmestajDTO;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.repository.AgentRepository;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class SmestajService {
	
	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private LokacijaService lokacijaService;

	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;

	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	

	 private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");

	public List<ShowSmestajDTO> getAll(){ 
		return smestajRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public int createSync(SmestajDTO smestajDTO){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateSmestajRequest getSmestajRequest = new CreateSmestajRequest();
		getSmestajRequest.setSmestajDTO(smestajDTO);
		CreateSmestajResponse getSmestajResponse = producerPort.createSmestaj(getSmestajRequest);
		return getSmestajResponse.getId();
	};
	
	public ShowSmestajDTO getById(Long id) {
		if(!smestajRepository.existsById(id)) {
			return null;
		}
		Smestaj smestaj = smestajRepository.findById(id).orElse(null);
		return convertToDTO(smestaj);
	}
	
	
	public boolean add(SmestajDTO smestajDTO) {
		Smestaj smestaj = new Smestaj();
		ThreadContext.put("userId", "3ss");
		try {
			smestaj = smestajRepository.save(convertToEntity(smestajDTO));
			logger.info(USER, "Dodat smestaj" + smestaj.getId());
			return true;

		} catch (Exception e) {
			logger.error(USER, "Smestaj nije uspesno sacuvan u bazi");
		}
		return false;
	}
	
	public boolean delete(Long id) {
		Smestaj s = new Smestaj();
		ThreadContext.put("user", "Ads");
		try {
			s = smestajRepository.findById(id).orElse(null);
		}catch (Exception e) {
			logger.warn(USER, "Smestaj " + id + " nije pronadjen");
			return false;
		}
		
		try {
			smestajRepository.deleteById(id);
			logger.info(USER, "Smestaj " + id + "obrisan");

			return true;
		} catch (Exception e) {
			logger.error(USER, "Greska prilikom brisanja smestaja " + id + ": " + e.getMessage());
		}

		lokacijaService.delete(id);
	

		return false;
	}
	
	private ShowSmestajDTO convertToDTO(Smestaj smestaj) {
		
		ShowSmestajDTO smestajDTO = new ShowSmestajDTO();
		smestajDTO.setId(smestaj.getId());
		smestajDTO.setNaziv(smestaj.getNaziv());
		smestajDTO.setOpis(smestaj.getOpis());
		smestajDTO.setNazivKategorijaSmestaja(smestaj.getKategorijaSmestaja().getNaziv());
		smestajDTO.setNazivTipSmestaja(smestaj.getTipSmestaja().getNaziv());
		smestajDTO.setDrzava(smestaj.getLokacija().getDrzava());
		smestajDTO.setGrad(smestaj.getLokacija().getGrad());
		smestajDTO.setUlica(smestaj.getLokacija().getUlica());
		smestajDTO.setBroj(smestaj.getLokacija().getBroj());

		smestajDTO.setSlika(smestaj.getSlika());
		
		return smestajDTO;
	}
	
	private Smestaj convertToEntity(SmestajDTO smestajDTO) {
		Lokacija lokacija = new Lokacija();
		
		lokacija.setId(smestajDTO.getId());
		lokacija.setBroj(smestajDTO.getBroj());
		lokacija.setDrzava(smestajDTO.getDrzava());
		lokacija.setGrad(smestajDTO.getGrad());
		lokacija.setUlica(smestajDTO.getUlica());
		lokacija.setIdKoordinate(smestajDTO.getId());
		lokacijaService.add(lokacija);

		Smestaj smestaj = new Smestaj();
		smestaj.setId(smestajDTO.getId());
		smestaj.setTipSmestaja(tipSmestajaRepository.findById(smestajDTO.getIdTipSmestaja()).orElse(null));
		smestaj.setKategorijaSmestaja(kategorijaSmestajaRepository.findById(smestajDTO.getIdKategorijaSmestaja()).orElse(null));
		smestaj.setLokacija(lokacija);
		smestaj.setNaziv(smestajDTO.getNaziv());
		smestaj.setOpis(smestajDTO.getOpis());
		smestaj.setAgent(agentRepository.findById(smestajDTO.getIdAgent()).orElse(null));
		smestaj.setKapacitet(smestajDTO.getKapacitet());
		smestaj.setSlika(smestajDTO.getSlika());
		return smestaj;
	}
}
