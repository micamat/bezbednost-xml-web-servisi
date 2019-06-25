package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateSobaRequest;
import ftn.uns.ac.rs.model.CreateSobaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowSobaDTO;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.Soba;
import ftn.uns.ac.rs.model.SobaDTO;
import ftn.uns.ac.rs.model.SobneUsluge;
import ftn.uns.ac.rs.model.Usluga;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.SobaRepository;
import ftn.uns.ac.rs.repository.SobneUslugeRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;
import ftn.uns.ac.rs.repository.UslugaRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;

	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private SobneUslugeRepository sobneUslugeRepository;
	
	@Autowired
	private UslugaRepository uslugaRepository;

	@Autowired
	private TipSobeRepository tipSobeRepository;
	
	private Logger logger = LogManager.getLogger();
	
	private static final Marker USER = MarkerManager
			   .getMarker("USER");
	

	public List<ShowSobaDTO> getAll(){ 
		return sobaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
		
	public int createSync(SobaDTO sobaDTO){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateSobaRequest getSobaRequest = new CreateSobaRequest();
		getSobaRequest.setSobaDTO(sobaDTO);
		
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
		Soba soba = new Soba();
		try {
			soba = sobaRepository.save(convertToEntity(sobaDTO));
			logger.info(USER, "Soba " + soba.getId() + " uspesno dodata");
		} catch (Exception e) {
			logger.error(USER, "Neuspesno dodavanje sobe: " + e.getMessage());
		}
		System.out.println(sobaDTO.getIdUsluga());
		for (Long idUsluga : sobaDTO.getIdUsluga()) {
			System.out.println(idUsluga);
			SobneUsluge sobneUsluge = new SobneUsluge();
			sobneUsluge.setId(null);
			sobneUsluge.setSoba(soba);
			sobneUsluge.setUsluga(uslugaRepository.findById(idUsluga).orElse(null));
			sobneUslugeRepository.save(sobneUsluge);
		}
		if(soba != null) {
			createSync(sobaDTO);
			return true;
		}
		return false;
	}
	
	
	public boolean delete(Long id) {
		if(sobaRepository.existsById(id)) {
			try {
				sobaRepository.deleteById(id);

				logger.info(USER, "Soba " + id + " uspesno obrisana");
			} catch (Exception e) {
				logger.error(USER, "Greska prilikom brisanja sobe " + id + ": " + e.getMessage());
			}
			
			return true;
		} else {
			logger.warn(USER, "Soba " + id + " nije pronadjena");
		}
		return false;
	}
	
	private ShowSobaDTO convertToDTO(Soba soba) {
		ShowSobaDTO sobaDTO = new ShowSobaDTO();
		sobaDTO.setId(soba.getId());
		sobaDTO.setNaziv(soba.getNaziv());
		sobaDTO.setOpis(soba.getOpis());
		sobaDTO.setNazivSmestaj(soba.getSmestaj().getNaziv());
		sobaDTO.setNazivTipSobe(soba.getTipSobe().getNaziv());
		List<SobneUsluge> sobneUsluge = sobneUslugeRepository.findAll().stream().filter(x -> x.getSoba().getId().equals(soba.getId())).collect(Collectors.toList());
		List<Usluga> usluge = new ArrayList<Usluga>();
		for (SobneUsluge su : sobneUsluge) {
			usluge.add(su.getUsluga());
		}
		sobaDTO.setUsluga(usluge);
		return sobaDTO;
	}
	
	private Soba convertToEntity(SobaDTO sobaDTO) {
		Soba soba = new Soba();
		soba.setId(sobaDTO.getId());
		soba.setNaziv(sobaDTO.getNaziv());
		soba.setOpis(sobaDTO.getOpis());
		soba.setTipSobe(tipSobeRepository.findById(sobaDTO.getIdTipSobe()).orElse(null));
		soba.setSmestaj(smestajRepository.findById(sobaDTO.getIdSmestaj()).orElse(null));
		
		return soba;
	}
}
