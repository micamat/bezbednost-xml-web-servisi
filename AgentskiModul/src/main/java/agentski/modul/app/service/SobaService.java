package agentski.modul.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.app.model.soba.Soba;
import agentski.modul.app.modelDTO.SobaDTO;
import agentski.modul.app.repository.SmestajRepository;
import agentski.modul.app.repository.SobaRepository;
import agentski.modul.app.repository.TipSobeRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;

	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private TipSobeRepository tipSobeRepository;

	public List<SobaDTO> getAll(){ 
		return sobaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	};
	
	public SobaDTO getById(Long id) {
		if(!sobaRepository.existsById(id)) {
			return null;
		}
		Soba soba = sobaRepository.findById(id).orElse(null);
		return convertToDTO(soba);
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
	}
}
