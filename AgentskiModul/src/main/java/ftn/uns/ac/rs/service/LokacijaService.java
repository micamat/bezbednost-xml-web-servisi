package ftn.uns.ac.rs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.CreateLokacijaRequest;
import ftn.uns.ac.rs.model.CreateLokacijaResponse;
import ftn.uns.ac.rs.model.Koordinate;
import ftn.uns.ac.rs.model.Lokacija;
import ftn.uns.ac.rs.model.ProducerPort;
import ftn.uns.ac.rs.model.ProducerPortService;
import ftn.uns.ac.rs.repository.LokacijaRepository;

@Service
public class LokacijaService {

	@Autowired
	private LokacijaRepository lokacijaRepository;

	@Autowired
	KoordinateService koordinateService;

	private Logger logger = LogManager.getLogger();
	private static final Marker USER = MarkerManager.getMarker("USER");

	public List<Lokacija> getAll() {
		return lokacijaRepository.findAll().stream().collect(Collectors.toList());
	};
	
	public Lokacija getById(Long id) {
		if (!lokacijaRepository.existsById(id)) {
			return null;
		}
		return lokacijaRepository.findById(id).orElse(null);
	}

	public Long add(Lokacija lokacija) {
		Koordinate koordinate = new Koordinate();
		URL url;

		Long id = null;
		try {
			// get URL content
			String adresa = lokacija.getUlica().replace(" ", "+");
			String grad = lokacija.getGrad().replace(" ", "+");
			String a = "https://www.google.com/maps/place/" + adresa + ',' + grad;
			url = new URL(a);
			URLConnection conn = url.openConnection();
			System.out.println(conn.getURL());
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				if (inputLine.contains("INITIALIZATION_STATE")) {
					String[] niz = inputLine.split(",");
					koordinate.setId(null);
					koordinate.setDuzina(Float.parseFloat(niz[2].replace("]", "")));
					koordinate.setSirina(Float.parseFloat(niz[1]));
					id = koordinateService.add(koordinate);
				}
			}
			br.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			lokacija.setId(id);
			lokacija.setIdKoordinate(id);
			lokacija = lokacijaRepository.save(lokacija);
			logger.info(USER, "Uspesno sacuvana lokacija");
			return lokacija.getId();
		} catch (Exception e) {
			logger.error(USER, "okacija nije sacuvana");
		}
			
		return null;
	}

	public boolean delete(Long id) {
		ThreadContext.put("user", "AS");
		if (lokacijaRepository.existsById(id)) {
			try {
				lokacijaRepository.deleteById(id);
				logger.info(USER, "Lokacija" + id + "obrisana");
			} catch (Exception e) {
				logger.error(USER, "Greska prilikom brisanja lokacije " + id + ": " + e.getMessage());
			}
			koordinateService.delete(id);
			return true;
		} else {

			logger.warn(USER, "Lokacija " + id + "ne postoji u bazi");
		}
		return false;
	}

}
