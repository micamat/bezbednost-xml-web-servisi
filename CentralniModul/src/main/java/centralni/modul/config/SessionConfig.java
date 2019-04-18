package centralni.modul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import centralni.modul.model.UserModel;


@Configuration
public class SessionConfig {
	@Bean
	@Scope(
	  value = WebApplicationContext.SCOPE_SESSION, 
	  proxyMode = ScopedProxyMode.TARGET_CLASS)
	public UserModel loggedUser() {
	    return new UserModel();
	}
}