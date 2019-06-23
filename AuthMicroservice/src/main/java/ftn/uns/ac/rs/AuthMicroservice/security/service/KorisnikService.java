package ftn.uns.ac.rs.AuthMicroservice.security.service;

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

import ftn.uns.ac.rs.AuthMicroservice.security.repository.KorisnikRepository;
import ftn.uns.ac.rs.AuthMicroservice.security.user.Korisnik;

@Service
public class KorisnikService implements UserDetailsService{
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Korisnik korisnik = korisnikRepo.findByKorisnickoIme(username);
		if(korisnik != null) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + korisnik.getUloga());
			// "User" klasu sadrzi Spring i tu klasu vraca UserDetailsService, a koristi se pri verifikaciji od strane authManagera
			return new User(korisnik.getKorisnickoIme(), korisnik.getSifra(), grantedAuthorities);
		}
		
		throw new UsernameNotFoundException("Korisnik: " + username + " nije pronadjen");
	}
	
	public Korisnik save(Korisnik korisnik) {
		korisnik.setSifra(encoder.encode(korisnik.getSifra()));
		return korisnikRepo.save(korisnik); 
	}
}
