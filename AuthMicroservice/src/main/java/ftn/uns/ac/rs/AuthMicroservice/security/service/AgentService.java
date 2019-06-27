package ftn.uns.ac.rs.AuthMicroservice.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Agent;
import ftn.uns.ac.rs.AuthMicroservice.security.repository.AgentRepository;

@Service
public class AgentService implements UserDetailsService {

	@Autowired
	AgentRepository agentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Agent korisnik = agentRepo.findByKorisnickoIme(username);
		if (korisnik != null) {
			String authorities = "AGENT";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
			// "User" klasu sadrzi Spring i tu klasu vraca UserDetailsService, a koristi se
			// pri verifikaciji od strane authManagera
			return new User(korisnik.getKorisnickoIme(), korisnik.getLozinka(), grantedAuthorities);
		}

		throw new UsernameNotFoundException("Agent: " + username + " nije pronadjen");
	}
	
	public Agent save(Agent agent) {
		return agentRepo.save(agent);
	}
	
}
