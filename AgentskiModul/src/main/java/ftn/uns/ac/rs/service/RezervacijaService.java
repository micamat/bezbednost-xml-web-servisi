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

import ftn.uns.ac.rs.config.Auth;
import ftn.uns.ac.rs.model.CreateRezervacijaRequest;
import ftn.uns.ac.rs.model.CreateRezervacijaResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.Rezervacija;
import ftn.uns.ac.rs.model.RezervacijaDTO;
import ftn.uns.ac.rs.model.RezervisaneSobe;
import ftn.uns.ac.rs.model.RezervisaneSobeDTO;
import ftn.uns.ac.rs.model.ShowRezervacijaDTO;
import ftn.uns.ac.rs.model.ShowRezervisaneSobeDTO;
import ftn.uns.ac.rs.model.UpdateRezervisaneSobeDTO;
import ftn.uns.ac.rs.repository.KorisnikRepository;
import ftn.uns.ac.rs.repository.RezervacijaRepository;
import ftn.uns.ac.rs.repository.RezervisaneSobeRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.SobaRepository;

@Service
public class RezervacijaService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	

	@Autowired
	private RezervisaneSobeRepository rezervisaneSobeRepository;
	@Autowired
	private RezervisaneSobeService rezervisaneSobeService;
	
	@Autowired
	private SobaRepository sobaRepository;
	
	private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");
	
	
	public int createSync(RezervacijaDTO rezervaicijaDTO){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		Auth.authenticateClient(producerPort);
		CreateRezervacijaRequest getRezervacijaRequest = new CreateRezervacijaRequest();
		getRezervacijaRequest.setRezervacijaDTO(rezervaicijaDTO);
		CreateRezervacijaResponse getZauzeceResponse = producerPort.createRezervacija(getRezervacijaRequest);
		return getZauzeceResponse.getId();
	};
	
	public List<ShowRezervacijaDTO> getAll(){ 
		return rezervacijaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public ShowRezervacijaDTO getById(Long id) {
		if(!rezervacijaRepository.existsById(id)) {
			return null;
		}
		Rezervacija rezervacija = rezervacijaRepository.findById(id).orElse(null);
		return convertToDTO(rezervacija);
	}
	
	public List<ShowRezervacijaDTO> getBySmestaj(Long smestajId) {
		return rezervacijaRepository.findAll().stream().filter(x -> smestajId.equals(x.getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public boolean update(UpdateRezervisaneSobeDTO updateRezervisaneSobeDTO) {
		try {
			List<RezervisaneSobe> rezervisaneSobe = rezervisaneSobeRepository.findAll().stream().filter(x -> updateRezervisaneSobeDTO.getIdRezervacija().equals(x.getRezervacija().getId())).collect(Collectors.toList());
		
			for (RezervisaneSobe rs : rezervisaneSobe) {
				rs.setStatusRezervacije(updateRezervisaneSobeDTO.getStatusRezervacije());
				rezervisaneSobeRepository.save(rs);
			}
			
			return true;
		}catch (Exception e) {
		
		}
		return false;
	}
	
	public boolean add(RezervacijaDTO rezervacijaDTO) {
		ThreadContext.put("user", "A");
		rezervacijaDTO.setId(null);
		float cena = 0;
		for (RezervisaneSobeDTO rsDTO : rezervacijaDTO.getRezervisaneSobeDTO()) {
			cena += rsDTO.getCena();
		}
		rezervacijaDTO.setCena(cena);
		try {
			Rezervacija rezervacija = rezervacijaRepository.save(convertToEntity(rezervacijaDTO));
			logger.info(USER, "Dodata rezervacija " + rezervacija.getId());
			for (RezervisaneSobeDTO rezervisaneSobeDTO : rezervacijaDTO.getRezervisaneSobeDTO()) {

				RezervisaneSobe rezervisaneSobe = new RezervisaneSobe();
				rezervisaneSobe.setId(null);
				rezervisaneSobe.setCena(0);
				rezervisaneSobe.setRezervacija(rezervacijaRepository.findById(rezervacija.getId()).orElse(null));
				rezervisaneSobe.setSoba(sobaRepository.findById(rezervisaneSobeDTO.getIdSoba()).orElse(null));
				rezervisaneSobe.setStatusRezervacije(rezervisaneSobeDTO.getStatusRezervacije());
				rezervisaneSobeService.add(rezervisaneSobe);
				
			}
			rezervacijaDTO.setId(rezervacija.getId());
			createSync(rezervacijaDTO);
			return true;
		} catch (Exception e){
			logger.error(USER, "Neuspesno dodavanje rezervacije: " + e.getMessage());
		}
		return false;
		
	}
	
	private ShowRezervacijaDTO convertToDTO(Rezervacija rezervacija) {
		ShowRezervacijaDTO rezervacijaDTO = new ShowRezervacijaDTO();
		rezervacijaDTO.setId(rezervacija.getId());
		rezervacijaDTO.setDatumOd(rezervacija.getDatumOd());
		rezervacijaDTO.setDatumDo(rezervacija.getDatumDo());
		rezervacijaDTO.setBrojSoba(rezervacija.getBrojSoba());
		rezervacijaDTO.setCena(rezervacija.getCena());
		rezervacijaDTO.setNazivSmestaj(rezervacija.getSmestaj().getNaziv());
		if (rezervacija.getKorisnik() == null) {
			rezervacijaDTO.setImePrezimeKorisnik(null);
		} else {
			rezervacijaDTO.setImePrezimeKorisnik(rezervacija.getKorisnik().getKorisnickoIme());
		}
		
		List<ShowRezervisaneSobeDTO> rsDTO = new ArrayList<ShowRezervisaneSobeDTO>();
		for (RezervisaneSobe rezervisaneSobe : rezervacija.getRezervisaneSobe()) {
			rsDTO.add(rezervisaneSobeService.convertToDTO(rezervisaneSobe));
		}
		rezervacijaDTO.setShowRezervisaneSobeDTO(rsDTO);
		return rezervacijaDTO;
	}
	
	private Rezervacija convertToEntity(RezervacijaDTO rezervacijaDTO) {
		
		
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setId(rezervacijaDTO.getId());
		rezervacija.setDatumOd(rezervacijaDTO.getDatumOd());
		rezervacija.setDatumDo(rezervacijaDTO.getDatumDo());
		rezervacija.setBrojSoba(rezervacijaDTO.getRezervisaneSobeDTO().size());
		rezervacija.setCena(rezervacijaDTO.getCena());
		rezervacija.setKorisnik(null);
		rezervacija.setSmestaj(smestajRepository.findById(rezervacijaDTO.getIdSmestaj()).orElse(null));
		return rezervacija;
	}
}