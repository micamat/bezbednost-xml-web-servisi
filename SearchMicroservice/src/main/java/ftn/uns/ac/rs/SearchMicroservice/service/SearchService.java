package ftn.uns.ac.rs.SearchMicroservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.SearchMicroservice.dto.SearchDTO;
import ftn.uns.ac.rs.SearchMicroservice.model.Rezervacija;
import ftn.uns.ac.rs.SearchMicroservice.model.RezervisaneSobe;
import ftn.uns.ac.rs.SearchMicroservice.model.Smestaj;
import ftn.uns.ac.rs.SearchMicroservice.model.Soba;
import ftn.uns.ac.rs.SearchMicroservice.model.SobneUsluge;
import ftn.uns.ac.rs.SearchMicroservice.repository.RezervacijaRepository;
import ftn.uns.ac.rs.SearchMicroservice.repository.SmestajRepository;
import ftn.uns.ac.rs.SearchMicroservice.repository.SobaRepository;
import ftn.uns.ac.rs.SearchMicroservice.repository.SobneUslugeRepository;
import ftn.uns.ac.rs.SearchMicroservice.specifications.RezervacijaSpecifications;
import ftn.uns.ac.rs.SearchMicroservice.specifications.SmestajSpecifications;
import ftn.uns.ac.rs.SearchMicroservice.specifications.SobneUslugeSpecifications;

@Service
public class SearchService {
	@Autowired
	SobneUslugeRepository sobneUslugeRepo;

	@Autowired
	RezervacijaRepository rezervacijaRepo;

	@Autowired
	SmestajRepository smestajRepo;

	@Autowired
	SobaRepository sobaRepo;

	private boolean isNotHere(List<Smestaj> lista, Smestaj sm) {
		for (Smestaj s : lista) {
			if (s.getId().equals(sm.getId()))
				return false;
		}
		return true;
	}
	
	private boolean isNotHere(List<Soba> lista, Soba sm) {
		for (Soba s : lista) {
			if (s.getId() == sm.getId())
				return false;
		}
		return true;
	}

	private List<Smestaj> overlap(List<Smestaj> lista1, List<Smestaj> lista2) {
		List<Smestaj> ret = new ArrayList<Smestaj>();
		for (Smestaj s1 : lista1) {
			for (Smestaj s2 : lista2) {
				if (s1.getId().equals(s2.getId())) {
					if (isNotHere(ret, s1))
						ret.add(s1);
				}
			}
		}
		return ret;
	}

	public List<Smestaj> findSmestaj(SearchDTO dto) {
		System.out.println(dto);
		List<Smestaj> smestaji = smestajRepo.findAll(SmestajSpecifications.findByLokacijaTipKategorija(dto.getDrzava(),
				dto.getGrad(), dto.getUlica(), dto.getTip(), dto.getKategorija(), dto.getBrojOsoba()));
		System.out.println(smestaji);

		boolean datumi = false;
		boolean usluge = false;
		List<Smestaj> sviOsimUsluga = new ArrayList<Smestaj>();
		List<Smestaj> saUslugama = new ArrayList<Smestaj>();
		
		if (dto.getDatumDo() != null && dto.getDatumOd() != null) {
			datumi = true;
			System.out.println("datumi: " + datumi);
			List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
			List<Smestaj> smestajiSaSlobodnimSobama = new ArrayList<Smestaj>();
			
			rezervacije = rezervacijaRepo.findAll(RezervacijaSpecifications.findByDate(dto.getDatumOd(), dto.getDatumDo()));
			System.out.println(rezervacije);

			/*List<Smestaj> smestajiSaSlobodnimSobama = new ArrayList<>();
			for (Rezervacija r : rezervacije) {
				if (dto.getBrojOsoba() != null) {
					if ((r.getSmestaj().getKapacitet() - r.getRezervisaneSobe().size()) > Integer
							.parseInt(dto.getBrojOsoba())) {
						if (isNotHere(smestajiSaSlobodnimSobama, r.getSmestaj())) {
							smestajiSaSlobodnimSobama.add(r.getSmestaj());
						}
					}
				}
			}*/
			List<Soba> zauzeteSobe = new ArrayList<Soba>();
			for(Rezervacija r : rezervacije) {
				for(RezervisaneSobe s : r.getRezervisaneSobe()) {
					zauzeteSobe.add(s.getSoba());
				}
			}
			for(Soba s : sobaRepo.findAll()) {
				if(isNotHere(zauzeteSobe, s)) {
					smestajiSaSlobodnimSobama.add(s.getSmestaj());
				}
			}
			
			System.out.println(smestajiSaSlobodnimSobama);
			sviOsimUsluga = overlap(smestaji, smestajiSaSlobodnimSobama);
			
			System.out.println(sviOsimUsluga);
		}
		
		if(dto.getUsluge() != null) {
			usluge = true;
			System.out.println("usluge: " + usluge);
			List<SobneUsluge> sobneUsluge = new ArrayList<SobneUsluge>();
			for (Smestaj sm : smestaji) {
				for (Soba so : sm.getSoba()) {
					if (dto.getUsluge() != null) {
						for (String nazivUsluge : dto.getUsluge())
							sobneUsluge.addAll(sobneUslugeRepo.findAll(SobneUslugeSpecifications.findBySobaUsluga(so.getId(), nazivUsluge)));
					}
				}
			}
			
			for (SobneUsluge su : sobneUsluge) {
				if (isNotHere(saUslugama, su.getSoba().getSmestaj())) {
					saUslugama.add(su.getSoba().getSmestaj());
				}
			}
			System.out.println("saUslugama: " + saUslugama);
		}
		
		if(datumi == true && usluge == false)
			return sviOsimUsluga;
		else if(datumi == true && usluge == true)
			return overlap(sviOsimUsluga, saUslugama);
		else if(datumi == false && usluge == true)
			return overlap(smestaji, saUslugama);
		else return smestaji;
	}
}
