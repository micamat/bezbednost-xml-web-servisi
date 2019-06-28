package ftn.uns.ac.rs.security;

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

@Service
public class KorisnikService implements UserDetailsService {
	@Autowired
	KorisnikRepository korisnikRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Korisnik korisnik = korisnikRepo.findByKorisnickoIme(username);
		if (korisnik != null) {
			String authorities = "SECURITY_ADMIN";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
			System.out.println("granted authorities: " + grantedAuthorities);
			return new User(korisnik.getKorisnickoIme(), korisnik.getSifra(), grantedAuthorities);
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
		return k;
	}
}
