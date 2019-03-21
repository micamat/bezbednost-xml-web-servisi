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
	
	
	//@Autowired
	//private PasswordEncoder encoder;
	
	public UserLoginDTO save(UserLoginDTO userDTO) {
		if(userRepository.existsByEmail(userDTO.getEmail())) {
			throw new NullPointerException("User with email:" + userDTO.getEmail() + " already exists.");
		}
		userDTO.setId(null);
		//String hash = encoder.encode(userDTO.getPassword());
		//userDTO.setPassword(hash);
		//userDTO.setUserType(UserType.SECURITY_ADMIN.toString());
		User user = userRepository.save(convertToEntity(userDTO));
		
		/*SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(user.getEmail()); 
        message.setSubject("ISA - account"); 
        message.setText("Activate your account on link: http://localhost:4200/activation/" + user.getActivationHash());
        emailSender.send(message);*/
        
		return convertToDTO(user);
	}
	
	/*public UserLoginDTO getLogedUser() {
		User user =
		UserLoginDTO u = getById(user.getId());
		System.out.println("PASSWORD : " + u.getPassword());
		return u;
	}*/
	
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
	
	private User convertToEntity(UserLoginDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setCity(userDTO.getCity());
		user.setTelephone(userDTO.getTelephone());
		//user.setUserType(UserType.valueOf(userDTO.getUserType()));
		//user.setActivationHash(UUID.randomUUID().toString());
		return user;
	}
	
	private UserLoginDTO convertToDTO(User user) {
		UserLoginDTO userDTO = new UserLoginDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setCity(user.getCity());
		userDTO.setTelephone(user.getTelephone());
		//userDTO.setUserType(user.getUserType().toString());
		userDTO.setPassword(user.getPassword());
		
		return userDTO;
	}


	
}
