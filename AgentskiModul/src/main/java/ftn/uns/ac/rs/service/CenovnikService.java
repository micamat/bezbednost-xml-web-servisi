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

import ftn.uns.ac.rs.config.Auth;
import ftn.uns.ac.rs.model.Cenovnik;
import ftn.uns.ac.rs.model.CenovnikDTO;
import ftn.uns.ac.rs.model.CreateCenovnikRequest;
import ftn.uns.ac.rs.model.CreateCenovnikResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowCenovnikDTO;
import ftn.uns.ac.rs.repository.CenovnikRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSobeRepository;

@Service
public class CenovnikService {
	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private TipSobeRepository tipSobeRepository;

	@Autowired
	private CenovnikRepository cenovnikRepository;
	

	 private Logger logger = LogManager.getLogger();
	 private static final Marker USER = MarkerManager
			   .getMarker("USER");

	public List<ShowCenovnikDTO> getAll(){ 
		return cenovnikRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	
	public int createSync(CenovnikDTO cenovnikDTO){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();

		Auth.authenticateClient(producerPort);
		CreateCenovnikRequest createCenovnikRequest = new CreateCenovnikRequest();
		createCenovnikRequest.setCenovnikDTO(cenovnikDTO);
		
		CreateCenovnikResponse createCenovnikResponse = producerPort.createCenovnik(createCenovnikRequest);
		return createCenovnikResponse.getId();
	};
	
	public ShowCenovnikDTO getById(Long id) {
		if(!cenovnikRepository.existsById(id)) {
			return null;
		}
		Cenovnik cenovnik = cenovnikRepository.findById(id).orElse(null);
		return convertToDTO(cenovnik);
	}
	
	public List<ShowCenovnikDTO> getBySmestaj(Long smestajId) {
		return cenovnikRepository.findAll().stream().filter(x -> smestajId.equals(x.getSmestaj().getId())).map(this::convertToDTO).collect(Collectors.toList());
	}
	
	
	public boolean add(CenovnikDTO cenovnikDTO) {
		if (cenovnikDTO.getIdTipSobe() == null || cenovnikDTO.getIdSmestaj() == null || cenovnikDTO.getDatumDo() == null || cenovnikDTO.getDatumOd() == null || cenovnikDTO.getCena() == 0) {
				return false;
			}
		cenovnikDTO.setId(cenovnikDTO.getId());

		ThreadContext.put("user", "A");
		try {
			Cenovnik cenovnik = cenovnikRepository.save(convertToEntity(cenovnikDTO));
			cenovnikDTO.setId(cenovnik.getId());
			createSync(cenovnikDTO);
			logger.info(USER,"Dodat cenovnik " + cenovnik.getId());
			return true;

		} catch (Exception e) {

			logger.error(USER,"Greska prilikom upisa cenovnika u bazu: " + e.getMessage());
		}
		
		return false;
	}
	
	public boolean delete(Long id) {
		if(cenovnikRepository.existsById(id)) {
			try {
				cenovnikRepository.deleteById(id);
				logger.info(USER, "Cenovnik " + id + " uspesno izbrisan");
				return true;
			}catch(Exception e) {
				logger.error(USER, "Greska prilikom brisanja cenovnika " + id + ": " + e.getMessage());
			}
			
		} else {
			logger.warn(USER, "Cenovnik " + id + " nije pronadjen u bazi" );
		}
		return false;
	}
	
	private ShowCenovnikDTO convertToDTO(Cenovnik cenovnik) {
		ShowCenovnikDTO cenovnikDTO = new ShowCenovnikDTO();
		cenovnikDTO.setId(cenovnik.getId());
		cenovnikDTO.setDatumOd(cenovnik.getDatumOd());
		cenovnikDTO.setDatumDo(cenovnik.getDatumDo());
		cenovnikDTO.setCena(cenovnik.getCena());
		cenovnikDTO.setNazivTipSobe(cenovnik.getTipSobe().getNaziv());
		cenovnikDTO.setNazivSmestaj(cenovnik.getSmestaj().getNaziv());
		cenovnikDTO.setBrojDanaZaOtkazivanje(cenovnik.getBrojDanaZaOtkazivanje());
		return cenovnikDTO;
	}
	
	private Cenovnik convertToEntity(CenovnikDTO cenovnikDTO) {
		Cenovnik cenovnik = new Cenovnik();
		cenovnik.setId(cenovnikDTO.getId());
		cenovnik.setCena(cenovnikDTO.getCena());
		cenovnik.setDatumOd(cenovnikDTO.getDatumOd());
		cenovnik.setDatumDo(cenovnikDTO.getDatumDo());
		cenovnik.setBrojDanaZaOtkazivanje(cenovnikDTO.getBrojDanaZaOtkazivanje());
		cenovnik.setTipSobe(tipSobeRepository.findById(cenovnikDTO.getIdTipSobe()).orElse(null));
		cenovnik.setSmestaj(smestajRepository.findById(cenovnikDTO.getIdSmestaj()).orElse(null));
		return cenovnik;
	}
}
