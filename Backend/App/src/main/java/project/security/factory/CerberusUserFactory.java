package project.security.factory;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import project.user.model.User;

public class CerberusUserFactory {
	
	public static CerberusUser create(User user) {
		Collection<? extends GrantedAuthority> authorities;
		try {
			authorities = AuthorityUtils.createAuthorityList(user.getAuthority().toString());
		} catch (Exception e) {
			System.out.println("Doslo je do greske prilikom kreiranja autorizacije.");
			authorities = null;
		}
		System.out.println("Iz faktorija: " + authorities);
		return new CerberusUser(
				user.getId(),
				user.getEmail(),
				user.getUsername(),
				user.getPassword(),
				user.getFirstName(),
				user.getLastName(),
				user.getCity(),
				authorities
		);
	}
}
