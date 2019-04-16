package agentski.modul.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentski.modul.model.HotelModel;
import agentski.modul.repository.HotelRepository;


@Service
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepo;
	
	public List<HotelModel> findAll(){
		return hotelRepo.findAll();
	}
	
	public String getNekiString() {
    	return "Ovo je neki string";
    }
	
}
