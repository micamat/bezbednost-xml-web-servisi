package ftn.uns.ac.rs.AuthMicroservice.security.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eureka.common.security.JwtConfig;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Korisnik;
import ftn.uns.ac.rs.AuthMicroservice.security.model.KorisnikDTO;
import ftn.uns.ac.rs.AuthMicroservice.security.model.Permisija;
import ftn.uns.ac.rs.AuthMicroservice.security.repository.KorisnikRepository;
import ftn.uns.ac.rs.AuthMicroservice.security.repository.UlogaRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class KorisnikService implements UserDetailsService {
	@Autowired
	KorisnikRepository korisnikRepo;

	@Autowired
	UlogaRepository ulogaRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private final JwtConfig jwtConfig = new JwtConfig();

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
			System.out.println("authorities: " + authorities);
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
			// "User" klasu sadrzi Spring i tu klasu vraca UserDetailsService, a koristi se
			// pri verifikaciji od strane authManagera
			return new User(korisnik.getKorisnickoIme(), korisnik.getSifra(), grantedAuthorities);
		}

		throw new UsernameNotFoundException("Korisnik: " + username + " nije pronadjen");
	}
	
	public String signin(String username, String password) {
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(authentication.getName())
			.claim("authorities", authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();
		
		return token;
	}


	public Korisnik save(KorisnikDTO korisnik) {
		return korisnikRepo.save(dtoToKorisnik(korisnik));
	}
	
	public void logout() {
		SecurityContextHolder.clearContext();
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
