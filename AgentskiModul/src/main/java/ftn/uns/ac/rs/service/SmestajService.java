package ftn.uns.ac.rs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateLokacijaRequest;
import ftn.uns.ac.rs.model.CreateSmestajRequest;
import ftn.uns.ac.rs.model.CreateSmestajResponse;
import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.ShowSmestajDTO;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
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

	public List<ShowSmestajDTO> getAll(){ 
		return smestajRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public int createSync(Smestaj smestaj, Lokacija lokacija){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		CreateLokacijaRequest getLokacijaRequest = new CreateLokacijaRequest();
		getLokacijaRequest.setId(lokacija.getId());
		getLokacijaRequest.setDrzava(lokacija.getDrzava());
		getLokacijaRequest.setGrad(lokacija.getGrad());
		getLokacijaRequest.setUlica(lokacija.getUlica());
		getLokacijaRequest.setBroj(lokacija.getBroj());
		producerPort.createLokacija(getLokacijaRequest);
		CreateSmestajRequest getSmestajRequest = new CreateSmestajRequest();
		getSmestajRequest.setId(smestaj.getId());
		getSmestajRequest.setIdKategorijaSmestaja(smestaj.getKategorijaSmestaja().getId());
		//getSmestajRequest.setIdLokacija(smd.getIdLokacija());
		getSmestajRequest.setIdTipSmestaja(smestaj.getTipSmestaja().getId());
		getSmestajRequest.setNaziv(smestaj.getNaziv());
		getSmestajRequest.setOpis(smestaj.getOpis());
		//getSmestajRequest.setSlika(smd.getSlika());
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
		Lokacija l = new Lokacija();
		
		l.setId(smestajDTO.getIdLokacija());
		l.setBroj(smestajDTO.getBroj());
		l.setDrzava(smestajDTO.getDrzava());
		l.setGrad(smestajDTO.getGrad());
		l.setUlica(smestajDTO.getUlica());
		l = lokacijaService.add(l);
		Smestaj s = new Smestaj();
		s.setId(smestajDTO.getId());
		s.setTipSmestaja(tipSmestajaRepository.findById(smestajDTO.getIdTipSmestaja()).orElse(null));
		s.setKategorijaSmestaja(kategorijaSmestajaRepository.findById(smestajDTO.getIdKategorijaSmestaja()).orElse(null));
		s.setLokacija(l);
		s.setNaziv(smestajDTO.getNaziv());
		s.setOpis(smestajDTO.getOpis());
		s = smestajRepository.save(s);
		if(s != null && l != null) {
			createSync(s, l);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		Smestaj s = smestajRepository.findById(id).get();
		if(s != null) {
			smestajRepository.deleteById(id);
			lokacijaService.delete(s.getLokacija().getId());
			return true;
		}
		return false;
	}
	
	private ShowSmestajDTO convertToDTO(Smestaj smestaj) {
		
		ShowSmestajDTO smestajDTO = new ShowSmestajDTO();
		smestajDTO.setId(smestaj.getId());
		smestajDTO.setNaziv(smestaj.getNaziv());
		smestajDTO.setOpis(smestaj.getOpis());
		smestajDTO.setKategorijaSmestaja(smestaj.getKategorijaSmestaja().getNaziv());
		smestajDTO.setTipSmestaja(smestaj.getTipSmestaja().getNaziv());
		smestajDTO.setDrzava(smestaj.getLokacija().getDrzava());
		smestajDTO.setGrad(smestaj.getLokacija().getGrad());
		smestajDTO.setUlica(smestaj.getLokacija().getUlica());
		smestajDTO.setBroj(smestaj.getLokacija().getBroj());
		smestajDTO.setIdLokacija(smestaj.getLokacija().getId());
		
		return smestajDTO;
	}
	
	private Smestaj convertToEntity(SmestajDTO smestajDTO) {
		Smestaj smestaj = new Smestaj();
		smestaj.setId(smestajDTO.getId());
		smestaj.setOpis(smestajDTO.getOpis());
		//smestaj.setSlika(smestajDTO.getSlika());
		//smestaj.setLokacija(lokacijaRepository.findById(smestajDTO.getIdLokacija()).orElse(null));
		smestaj.setTipSmestaja(tipSmestajaRepository.findById(smestajDTO.getIdTipSmestaja()).orElse(null));
		smestaj.setKategorijaSmestaja(kategorijaSmestajaRepository.findById(smestajDTO.getIdKategorijaSmestaja()).orElse(null));
		return smestaj;
	}
}
