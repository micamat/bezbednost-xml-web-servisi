package adminski.modul.app.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import adminski.modul.app.model.Admin;
import adminski.modul.app.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService {
	@Autowired
	AdminRepository korisnikRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Admin korisnik = korisnikRepo.findByUsername(username);
		if (korisnik != null) {
			String authorities = "SECURITY_ADMIN";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
			System.out.println("granted authorities: " + grantedAuthorities);
			return new User(korisnik.getUsername(), korisnik.getPassword(), grantedAuthorities);
		}

		throw new UsernameNotFoundException("Korisnik ili agent: " + username + " nije pronadjen");
	}

	public Admin save(Admin korisnik) {
		return korisnikRepo.save(korisnik);
	}

	
}
