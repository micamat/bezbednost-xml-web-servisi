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

import ftn.uns.ac.rs.AuthMicroservice.security.model.Agent;

//import com.eureka.common.security.JwtConfig;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Korisnik;
import ftn.uns.ac.rs.AuthMicroservice.security.model.KorisnikDTO;
import ftn.uns.ac.rs.AuthMicroservice.security.model.Permisija;
import ftn.uns.ac.rs.AuthMicroservice.security.repository.AgentRepository;
import ftn.uns.ac.rs.AuthMicroservice.security.repository.KorisnikRepository;
import ftn.uns.ac.rs.AuthMicroservice.security.repository.UlogaRepository;

@Service
public class KorisnikService implements UserDetailsService {
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	AgentRepository agentRepo;

	@Autowired
	UlogaRepository ulogaRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Korisnik korisnik = korisnikRepo.findByKorisnickoIme(username);
		if (korisnik != null) {
			String authorities = korisnik.getUloga().getNaziv() + ",";
			if (!korisnik.getUloga().getPermisija().isEmpty()) {
				for (Permisija p : korisnik.getUloga().getPermisija()) {
					authorities += (p.getNaziv() + ",");
				}
			}
			authorities = authorities.substring(0, authorities.length() - 1);
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
			// "User" klasu sadrzi Spring i tu klasu vraca UserDetailsService, a koristi se
			// pri verifikaciji od strane authManagera
			return new User(korisnik.getKorisnickoIme(), korisnik.getSifra(), grantedAuthorities);
		}
		else {
			Agent agent = agentRepo.findByKorisnickoIme(username);
			if(agent != null) {
				String authorities = "AGENT";
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				return new User(agent.getKorisnickoIme(), agent.getLozinka(), grantedAuthorities);
			}
		}

		throw new UsernameNotFoundException("Korisnik ili agent: " + username + " nije pronadjen");
	}


	public Korisnik save(KorisnikDTO korisnik) {
		return korisnikRepo.save(dtoToKorisnik(korisnik));
	}

	private Korisnik dtoToKorisnik(KorisnikDTO dto) {
		Korisnik k = new Korisnik();
		k.setId(dto.getId());
		k.setIme(dto.getIme());
		k.setPrezime(dto.getPrezime());
		k.setKorisnickoIme(dto.getKorisnickoIme());
		k.setEmail(dto.getEmail());
		k.setAktivan(dto.isAktivan());
		k.setSifra(encoder.encode(dto.getSifra()));
		k.setTelefon(dto.getTelefon());
		k.setUloga(ulogaRepo.findById(dto.getIdUloga()).get());
		return k;
	}
}
