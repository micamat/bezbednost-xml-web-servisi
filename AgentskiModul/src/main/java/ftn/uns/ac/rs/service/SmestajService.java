package ftn.uns.ac.rs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.GetAllSmestajRequest;
import ftn.uns.ac.rs.model.GetAllSmestajResponse;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;
import ftn.uns.ac.rs.repository.LokacijaRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class SmestajService {
	
	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private LokacijaRepository lokacijaRepository;

	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;

	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;

	/*public List<SmestajDTO> getAll(){ 
		return smestajRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};*/
	
	public List<SmestajDTO> synchronization(){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		GetAllSmestajRequest getSmestajRequest = new GetAllSmestajRequest();
		GetAllSmestajResponse getSmestajResponse = producerPort.getAllSmestaj(getSmestajRequest);
		return getSmestajResponse.getSmestajDTO();
	};
	
	/*public SmestajDTO getById(Long id) {
		if(!smestajRepository.existsById(id)) {
			return null;
		}
		Smestaj smestaj = smestajRepository.findById(id).orElse(null);
		return convertToDTO(smestaj);
	}
	
	
	public boolean add(SmestajDTO smestajDTO) {
		smestajDTO.setId(null);
		if(smestajRepository.save(convertToEntity(smestajDTO)) != null) {
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		if(smestajRepository.existsById(id)) {
			smestajRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private SmestajDTO convertToDTO(Smestaj smestaj) {
		SmestajDTO smestajDTO = new SmestajDTO();
		smestajDTO.setId(smestaj.getId());
		smestajDTO.setOpis(smestaj.getOpis());
		smestajDTO.setSlika(smestaj.getSlika());
		smestajDTO.setLokacijaId(smestaj.getLokacija().getId());
		smestajDTO.setTipSmestajaId(smestaj.getTipSmestaja().getId());
		smestajDTO.setKategorijaSmestajaId(smestaj.getKategorijaSmestaja().getId());
		return smestajDTO;
	}
	
	private Smestaj convertToEntity(SmestajDTO smestajDTO) {
		Smestaj smestaj = new Smestaj();
		smestaj.setId(smestajDTO.getId());
		smestaj.setOpis(smestajDTO.getOpis());
		smestaj.setSlika(smestajDTO.getSlika());
		System.out.println(smestajDTO.getLokacijaId());
		smestaj.setLokacija(lokacijaRepository.findById(smestajDTO.getLokacijaId()).orElse(null));
		smestaj.setTipSmestaja(tipSmestajaRepository.findById(smestajDTO.getTipSmestajaId()).orElse(null));
		smestaj.setKategorijaSmestaja(kategorijaSmestajaRepository.findById(smestajDTO.getKategorijaSmestajaId()).orElse(null));
		return smestaj;
	}*/
}
