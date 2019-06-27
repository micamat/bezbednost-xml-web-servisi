package ftn.uns.ac.rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.SobaRepository;
import ftn.uns.ac.rs.repository.SobneUslugeRepository;

@Service
public class SobneUslugeService {

	@Autowired
	private SobaRepository sobaRepository;

	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private SobneUslugeRepository sobneUslugeRepository;

	/*
	 * public ShowSobaDTO getById(Long id) { if (!sobaRepository.existsById(id)) {
	 * return null; } Soba soba = sobaRepository.findById(id).orElse(null); return
	 * convertToDTO(soba); }
	 * 
	 * public List<ShowSobaDTO> getBySmestaj(Long smestajId) { return
	 * sobaRepository.findAll().stream().filter(x ->
	 * smestajId.equals(x.getSmestaj().getId()))
	 * .map(this::convertToDTO).collect(Collectors.toList()); }
	 * 
	 * public boolean add(SobaDTO sobaDTO) { sobaDTO.setId(sobaDTO.getId()); Soba
	 * soba = sobaRepository.save(convertToEntity(sobaDTO)); if (soba != null) {
	 * createSync(sobaDTO); return true; } return false; }
	 * 
	 * public boolean delete(Long id) { if (sobaRepository.existsById(id)) {
	 * sobaRepository.deleteById(id); return true; } return false; }
	 * 
	 * private ShowSobaDTO convertToDTO(Soba soba) { ShowSobaDTO sobaDTO = new
	 * ShowSobaDTO(); sobaDTO.setId(soba.getId());
	 * sobaDTO.setNaziv(soba.getNaziv()); sobaDTO.setOpis(soba.getOpis());
	 * sobaDTO.setNazivSmestaj(soba.getSmestaj().getNaziv());
	 * sobaDTO.setNazivTipSobe(soba.getTipSobe().getNaziv()); return sobaDTO; }
	 * 
	 * private Soba convertToEntity(SobaDTO sobaDTO) { Soba soba = new Soba();
	 * soba.setId(sobaDTO.getId()); soba.setNaziv(sobaDTO.getNaziv());
	 * soba.setOpis(sobaDTO.getOpis());
	 * soba.setTipSobe(tipSobeRepository.findById(sobaDTO.getIdTipSobe()).orElse(
	 * null));
	 * soba.setSmestaj(smestajRepository.findById(sobaDTO.getIdSmestaj()).orElse(
	 * null)); return soba; }
	 */

}
