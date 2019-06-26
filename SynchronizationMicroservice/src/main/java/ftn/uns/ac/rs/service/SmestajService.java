package ftn.uns.ac.rs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.KategorijaSmestaja;
import ftn.uns.ac.rs.model.Koordinate;
import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.model.Smestaj;
import ftn.uns.ac.rs.model.SmestajDTO;
import ftn.uns.ac.rs.repository.AgentRepository;
import ftn.uns.ac.rs.repository.KategorijaSmestajaRepository;
import ftn.uns.ac.rs.repository.KoordinateRepository;
import ftn.uns.ac.rs.repository.LokacijaRepository;
import ftn.uns.ac.rs.repository.SmestajRepository;
import ftn.uns.ac.rs.repository.TipSmestajaRepository;

@Service
public class SmestajService {
	@Autowired
	private SmestajRepository smestajRepo;
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private KoordinateRepository koordinateRepo;
	
	public int create(SmestajDTO p){
		System.out.println("dasd");
		System.out.println(p.getGrad() + " adf " + p.getUlica());
		System.out.println(p.getIdKategorijaSmestaja() + " adf " + p.getNaziv());
		System.out.println(p.getSlika() + " adf " + p.getOpis());
		System.out.println(p.getIdAgent() + " adf " + p.getIdTipSmestaja());
		Lokacija l = new Lokacija();
		l.setIdKoordinate(p.getId());
		l.setBroj(p.getBroj());
		l.setDrzava(p.getDrzava());
		l.setGrad(p.getGrad());
		l.setUlica(p.getUlica());
		l.setId(p.getId());
		System.out.println("dadasdsd");
		add(l);
		System.out.println("daasfsd");
		
		lokacijaRepository.save(l);
		System.out.println("daadasfasdfsfsd");
		Smestaj sm = smestajToEntity(p);
		int id = -1;
		Smestaj s = smestajRepo.save(sm);
		System.out.println("daa64538465sfsd");
		if(s == null){
			return id;
		}else {
			return s.getId().intValue();
		}
	}
	
	
	public boolean add(Lokacija lokacija) {
		Koordinate koordinate = new Koordinate();
		URL url;

		try {
			// get URL content
			
			String adresa = lokacija.getUlica().replace(" ", "+");
			String grad = lokacija.getGrad().replace(" ", "+");
			System.out.println(lokacija.getGrad() + " " + lokacija.getUlica());
			String a = "https://www.google.com/maps/place/" + adresa + ',' + grad;
			url = new URL(a);
			System.out.println("USAO SAM SIGURNO");
			URLConnection conn = url.openConnection();
			System.out.println(conn.getURL());
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				if (inputLine.contains("INITIALIZATION_STATE")) {
					String[] niz = inputLine.split(",");
					koordinate.setId(lokacija.getId());
					koordinate.setDuzina(Float.parseFloat(niz[2].replace("]", "")));
					koordinate.setSirina(Float.parseFloat(niz[1]));
					
					koordinateRepo.save(koordinate);

				}
				// System.out.println(inputLine);

			}
			br.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("USAO SAM SIGURNO u prvi");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("USAO SAM SIGURNO u drugi");
		}
		try {
			lokacija = lokacijaRepository.save(lokacija);

			// createSync(lokacija);
			//logger.info(USER, "Uspesno sacuvana lokacija");
			return true;
		} catch (Exception e) {
			System.out.println("USAO SAM SIGURNO u trecic");
			//logger.error(USER, "okacija nije sacuvana");
		}
			
		return false;
	}

	
	private Smestaj smestajToEntity(SmestajDTO smestaj) {
		Smestaj sm = new Smestaj();
		sm.setNaziv(smestaj.getNaziv());
		sm.setOpis(smestaj.getOpis());
		sm.setSlika(smestaj.getSlika());
		sm.setKategorijaSmestaja(kategorijaSmestajaRepository.findById(smestaj.getIdKategorijaSmestaja()).get());
		sm.setLokacija(lokacijaRepository.findById(smestaj.getId()).get());
		sm.setTipSmestaja(tipSmestajaRepository.findById(smestaj.getIdTipSmestaja()).get());
		sm.setAgent(agentRepo.findById(smestaj.getIdAgent()).get());
		sm.setKapacitet(smestaj.getKapacitet());
		sm.setId(smestaj.getId());
		return sm;
	}
}
