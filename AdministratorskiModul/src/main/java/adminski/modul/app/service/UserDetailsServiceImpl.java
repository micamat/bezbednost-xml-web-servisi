package adminski.modul.app.service;

import adminski.modul.app.model.Admin;
import adminski.modul.app.repository.AdminRepository;
import adminski.modul.app.security.UserDetailsImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private AdminRepository adminRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Admin user = null;
    
    for (Admin a :this.adminRepository.findAll()) {
    	if (a.getUsername().equals(username)) {
    		user = a;
    		break;
    	}
    }

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
    	UserDetailsImpl details = new UserDetailsImpl();
    	details.setId(user.getId());
    	details.setPassword(user.getPassword());
    	details.setUsername(user.getUsername());
    	details.setAuthorities(new ArrayList<SimpleGrantedAuthority>() {{add(new SimpleGrantedAuthority("ADMIN"));}});
      return (UserDetails) details;
    }
  }

}
