package project.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.user.model.User;
import project.user.model.dto.UserLoginDTO;
import project.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserLoginDTO login(UserLoginDTO userDTO) {
		if(!userRepository.existsByEmail(userDTO.getEmail())) {
			return null;
		}
		User u = userRepository.findByEmail(userDTO.getEmail());
		if(u.getPassword().equals(userDTO.getPassword())) {
			return userDTO;
		}else {
			return null;
		}
	}


	
}
