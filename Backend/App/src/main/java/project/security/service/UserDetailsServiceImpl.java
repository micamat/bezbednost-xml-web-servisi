package project.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.security.factory.CerberusUserFactory;
import project.user.model.User;
import project.user.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	/*@Autowired
	private UsersAdminRepository userAdminsRepository;
	
	@Autowired
	private CompanyAdminRepository companyAdminRepository;*/
	
	public UserDetailsServiceImpl() {
		
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username+"+++++++++++++++++++");
		User user = null;
		if(repo.findByUsername(username) != null)
			user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			/*if(user instanceof User && user.getState().equals(UserState.INACTIVE))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not activated. Activate it with link.");*/
			return CerberusUserFactory.create(user);
		}
	}

}
