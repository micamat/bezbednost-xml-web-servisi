package agentski.modul.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.app.model.smestaj.Smestaj;
import agentski.modul.app.modelDTO.SmestajDTO;
import agentski.modul.app.repository.KategorijaSmestajaRepository;
import agentski.modul.app.repository.LokacijaRepository;
import agentski.modul.app.repository.SmestajRepository;
import agentski.modul.app.repository.TipSmestajaRepository;

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

	public List<SmestajDTO> getAll(){ 
		return smestajRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SmestajDTO getById(Long id) {
		if(!smestajRepository.existsById(id)) {
			return null;
		}
		Smestaj smestaj = smestajRepository.findById(id).orElse(null);
		return convertToDTO(smestaj);
	}
	
	
	public boolean add(SmestajDTO smestajDTO) {
		System.out.println(smestajDTO + "SA" + smestajDTO.getOpis() + smestajDTO.getLokacijaId());
		
		System.out.println("ASDASD"+ smestajDTO.getLokacijaId());
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
	}
}
