package ftn.uns.ac.rs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	public List<Lokacija> getAll(){ 
		return lokacijaRepository.findAll().stream().collect(Collectors.toList());
	};
	
	public int createSync(Lokacija lokacija){
		ProducerPortService producerPortService = new ProducerPortService();
		ProducerPort producerPort = producerPortService.getProducerPortSoap11();
		
		CreateLokacijaRequest createLokacijaRequest = new CreateLokacijaRequest();
		createLokacijaRequest.setLokacija(lokacija);
		CreateLokacijaResponse createLokacijaResponse = producerPort.createLokacija(createLokacijaRequest);
		return createLokacijaResponse.getId();
	};
	
	
	
	public Lokacija getById(Long id) {
		if(!lokacijaRepository.existsById(id)) {
			return null;
		}
		return lokacijaRepository.findById(id).orElse(null);
	}
	
	
	public boolean add(Lokacija lokacija) {
		Koordinate koordinate = new Koordinate();
		URL url;

        try {
            // get URL content
        	String adresa = lokacija.getUlica().replace(" ", "+"); 
        	String grad = lokacija.getGrad().replace(" ", "+");
            String a="https://www.google.com/maps/place/" + adresa + ',' + grad;
            url = new URL(a);
            URLConnection conn = url.openConnection();
            System.out.println(conn.getURL());
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
            	if(inputLine.contains("INITIALIZATION_STATE")) {
            		String[] niz = inputLine.split(",");
            		koordinate.setId(lokacija.getId());
            		koordinate.setDuzina(Float.parseFloat(niz[2].replace("]", "")));
            		koordinate.setSirina(Float.parseFloat(niz[1]));
            		koordinateService.add(koordinate);
                    //System.out.println(niz[2] + niz[1]);


            	}
            	//System.out.println(inputLine);

            }
            br.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		lokacija = lokacijaRepository.save(lokacija);
		System.out.println(lokacija.getGrad() + "" + lokacija.getId());
		if(lokacija != null) {
			//createSync(lokacija);
			return true;
		}
		return false;
	}
	
	/*
	 * public Lokacija add(Lokacija lokacija) { lokacija.setId(lokacija.getId());
	 * Lokacija l = lokacijaRepository.save(lokacija); if(l != null) { return l; }
	 * return null; }
	 */
	
	public boolean delete(Long id) {
		if(lokacijaRepository.existsById(id)) {
			lokacijaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
