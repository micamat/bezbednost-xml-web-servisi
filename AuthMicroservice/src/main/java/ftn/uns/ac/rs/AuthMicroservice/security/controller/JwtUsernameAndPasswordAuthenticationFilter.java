package ftn.uns.ac.rs.AuthMicroservice.security.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.eureka.common.security.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import ftn.uns.ac.rs.AuthMicroservice.security.config.JwtConfig;
import ftn.uns.ac.rs.AuthMicroservice.security.model.Korisnik;
import ftn.uns.ac.rs.AuthMicroservice.security.model.KorisnikDTO;
import ftn.uns.ac.rs.AuthMicroservice.security.model.LoggedUser;
import ftn.uns.ac.rs.AuthMicroservice.security.model.UserCredentials;
import ftn.uns.ac.rs.AuthMicroservice.security.service.KorisnikService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	// authManager za validaciju korisnickih kredencijala 
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtConfig jwtConfig;
	
	@Autowired
	KorisnikService korisnikService;
	
	/**
	 * Konstruktor
	 * @param authManager
	 * @param jwtConfig
	 */
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		// po default-u, UsernamePasswordAuthenticationFilter prima zahtev po "/login" putanji,
		// pa se, u slucaju drugacije putanje, mora override-ovati
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/signin", "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			
			// Uzimanje kredencijala iz zahteva
			UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			
			// Kreiranje auth objekta, koji sadrzi kredencijale i koji ce authManager koristiti za validaciju
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), Collections.emptyList());
			return authManager.authenticate(authToken);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Metoda, koja u slucaju uspesne autentifikacije, generise token
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(auth.getName())
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();

		// postavljanje tokena u header odgovora 
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
	}
	
	@PostMapping("/prijava")
	public ResponseEntity<LoggedUser> prijava(@RequestParam String username, @RequestParam String password){
		return new ResponseEntity<LoggedUser>(signin(username, password), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Korisnik> save(@RequestBody KorisnikDTO korisnik){
		return new ResponseEntity<Korisnik>(korisnikService.save(korisnik), HttpStatus.OK);
	}
	
	@PostMapping("/validate")
	public Boolean validate() {
		return true;
	}
	
	private LoggedUser signin(String username, String password) {
		LoggedUser loggedUser = new LoggedUser();
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
		Authentication authentication = authManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(authentication.getName())
			.claim("authorities", authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();
		
		loggedUser.setToken(token);
		loggedUser.setUsername(username);
		return loggedUser;
	}
	
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	    super.setAuthenticationManager(authenticationManager);
	}
}
