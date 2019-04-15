package centralni.modul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import centralni.modul.model.UserModel;
import centralni.modul.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<UserModel> findAll(){
		return userRepo.findAll();
	}
}
