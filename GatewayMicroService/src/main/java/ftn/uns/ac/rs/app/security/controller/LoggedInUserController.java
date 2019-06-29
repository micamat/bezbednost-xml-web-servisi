package ftn.uns.ac.rs.app.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ac.rs.app.security.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
public class LoggedInUserController {
	
	@Autowired
	JwtConfig jwtConfig;
	
	@PutMapping("/odjava")
	public Boolean odjava() {
		try {
			System.out.println("ulogovani: " + SecurityContextHolder.getContext().getAuthentication().getName());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("/validate")
	public Boolean validate(HttpServletRequest request) {
		// preuzimanje headera zahteva
		String header = request.getHeader(jwtConfig.getHeader());
				
				
				// If there is no token provided and hence the user won't be authenticated. 
				// It's Ok. Maybe the user accessing a public path or asking for a token.
				
				// All secured paths that needs a token are already defined and secured in config class.
				// And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.
				
				String token = header.replace(jwtConfig.getPrefix(), "");
		try {
			System.out.println("USO U TRY");
			Claims claims = Jwts.parser()
					.setSigningKey(jwtConfig.getSecret().getBytes())
					.parseClaimsJws(token)
					.getBody();
			System.out.println("claims: " + claims);
			String username = claims.getSubject();
			/*if(username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims.get("authorities");
				//System.out.println("BITNO JAKO: " + authorities);
				
				// 5. Create auth object
				// UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
				// It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
				 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
								 username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			}*/
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
