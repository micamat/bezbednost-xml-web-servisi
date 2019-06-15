package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateSmestajRequest;
import ftn.uns.ac.rs.model.CreateSmestajResponse;
import ftn.uns.ac.rs.model.GetAllSmestajRequest;
import ftn.uns.ac.rs.model.GetAllSmestajResponse;
import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.model.getSmestajDTO;
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

	public List<getSmestajDTO> getAll(){ 
		List<Smestaj> s = smestajRepository.findAll();
		List<getSmestajDTO> sl = new ArrayList<getSmestajDTO>();
		for(Smestaj temp : s) {
			getSmestajDTO dto = new getSmestajDTO();
			dto.setId(temp.getId());
			dto.setNaziv(temp.getNaziv());
			dto.setOpis(temp.getOpis());
			dto.setKategorijaSmestaja(temp.getKategorijaSmestaja().getNaziv());
			dto.setTipSmestaja(temp.getTipSmestaja().getNaziv());
			dto.setDrzava(temp.getLokacija().getDrzava());
			dto.setGrad(temp.getLokacija().getGrad());
			dto.setUlica(temp.getLokacija().getUlica());
			dto.setBroj(temp.getLokacija().getBroj());
			sl.add(dto);
		}
		return sl;
	};
	
	//TODO: Implementirati poslovnu logiku ..... cuvanja u bazu kao i 
	public List<SmestajDTO> getAllSync(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllSmestajRequest getSmestajRequest = new GetAllSmestajRequest();
		GetAllSmestajResponse getSmestajResponse = producerPort.getAllSmestaj(getSmestajRequest);
		return getSmestajResponse.getSmestajDTO();
	};
	
	
	public int createSync(SmestajDTO smd){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateSmestajRequest getSmestajRequest = new CreateSmestajRequest();
		getSmestajRequest.setId(smd.getId());
		getSmestajRequest.setIdKategorijaSmestaja(smd.getIdKategorijaSmestaja());
		//getSmestajRequest.setIdLokacija(smd.getIdLokacija());
		getSmestajRequest.setIdTipSmestaja(smd.getIdTipSmestaja());
		getSmestajRequest.setNaziv(smd.getNaziv());
		getSmestajRequest.setOpis(smd.getOpis());
		//getSmestajRequest.setSlika(smd.getSlika());
		CreateSmestajResponse getSmestajResponse = producerPort.createSmestaj(getSmestajRequest);
		return getSmestajResponse.getId();
	};
	
	public getSmestajDTO getById(Long id) {
		Smestaj s = smestajRepository.findById(id).get();
		
		getSmestajDTO dto = new getSmestajDTO();
		dto.setId(s.getId());
		dto.setNaziv(s.getNaziv());
		dto.setOpis(s.getOpis());
		dto.setKategorijaSmestaja(s.getKategorijaSmestaja().getNaziv());
		dto.setTipSmestaja(s.getTipSmestaja().getNaziv());
		dto.setDrzava(s.getLokacija().getDrzava());
		dto.setGrad(s.getLokacija().getGrad());
		dto.setUlica(s.getLokacija().getUlica());
		dto.setBroj(s.getLokacija().getBroj());
		
		return dto;
	}
	
	
	public boolean add(SmestajDTO smestajDTO) {
		Lokacija l = new Lokacija();
		l.setBroj(smestajDTO.getBroj());
		l.setDrzava(smestajDTO.getDrzava());
		l.setGrad(smestajDTO.getGrad());
		l.setUlica(smestajDTO.getUlica());
		l = lokacijaService.add(l);
		Smestaj s = new Smestaj();
		s.setId(null);
		s.setTipSmestaja(tipSmestajaRepository.findById(smestajDTO.getIdTipSmestaja()).orElse(null));
		s.setKategorijaSmestaja(kategorijaSmestajaRepository.findById(smestajDTO.getIdKategorijaSmestaja()).orElse(null));
		s.setLokacija(l);
		s.setNaziv(smestajDTO.getNaziv());
		s.setOpis(smestajDTO.getOpis());
		if(smestajRepository.save(s) != null) {
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
	
	private SmestajDTO convertToDTO(Smestaj smestaj) {
		SmestajDTO smestajDTO = new SmestajDTO();
		smestajDTO.setId(smestaj.getId());
		smestajDTO.setOpis(smestaj.getOpis());
		//smestajDTO.setSlika(smestaj.getSlika());
		//smestajDTO.setIdLokacija(smestaj.getLokacija().getId());
		smestajDTO.setIdTipSmestaja(smestaj.getTipSmestaja().getId());
		smestajDTO.setIdKategorijaSmestaja(smestaj.getKategorijaSmestaja().getId());
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
