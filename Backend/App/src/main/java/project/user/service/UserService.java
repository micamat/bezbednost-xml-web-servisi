package project.user.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import project.security.factory.CerberusUser;
import project.security.json.AuthenticationRequest;
import project.security.json.AuthenticationResponse;
import project.security.utils.TokenUtils;
import project.user.model.User;
import project.user.model.dto.UserLoginDTO;
import project.user.repository.UserRepository;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("X-Auth-Token")
	private String tokenHeader;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	//@Autowired
	//private PasswordEncoder encoder;
	
	public UserLoginDTO save(UserLoginDTO userDTO) {
		logger.info("Sacuvava korisnika u bazu");
		if(userRepository.existsByEmail(userDTO.getEmail())) {
			throw new NullPointerException("User with email:" + userDTO.getEmail() + " already exists.");
		}
		else if(userRepository.existsByUsername(userDTO.getUsername())) {
			throw new NullPointerException("User with username:" + userDTO.getUsername() + " already exists.");
		}
		userDTO.setId(null);
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		userDTO.setPassword(bc.encode(userDTO.getPassword()));
		User user = userRepository.save(convertToEntity(userDTO));
		
		/*SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(user.getEmail()); 
        message.setSubject("ISA - account"); 
        message.setText("Activate your account on link: http://localhost:4200/activation/" + user.getActivationHash());
        emailSender.send(message);*/
        System.out.println("\nsacuvava: " + user + "\n");
        logger.info("Korisnik sacuvan!");
		return convertToDTO(user);
	}
	
	/*public UserLoginDTO getLogedUser() {
		User user =
		UserLoginDTO u = getById(user.getId());
		System.out.println("PASSWORD : " + u.getPassword());
		return u;
	}*/
	
	/*public UserLoginDTO login(UserLoginDTO userDTO) {
		if(!userRepository.existsByEmail(userDTO.getEmail())) {
			return null;
		}
		User u = userRepository.findByEmail(userDTO.getEmail());
		if(u.getPassword().equals(userDTO.getPassword())) {
			return userDTO;
		}else {
			return null;
		}
	}*/
	
	private User convertToEntity(UserLoginDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
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
		userDTO.setUsername(user.getUsername());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setCity(user.getCity());
		userDTO.setTelephone(user.getTelephone());
		//userDTO.setUserType(user.getUserType().toString());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		
		return userDTO;
	}

	public AuthenticationResponse signin(AuthenticationRequest authenticationRequest) {
		logger.info("logovanje");
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = this.tokenUtils.generateToken(userDetails);
		logger.info("korisnik je ulogovan");
		return new AuthenticationResponse(token);
	}

	public void signout() {
		SecurityContextHolder.clearContext();
	}

	public CerberusUser currentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		CerberusUser userDetails = (CerberusUser) userDetailsService.loadUserByUsername(username);
		return userDetails;
	}
	
}
