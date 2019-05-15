package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import project.security.utils.AuthenticationTokenFilter;
import project.security.utils.EntryPointUnauthorizedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	  private EntryPointUnauthorizedHandler unauthorizedHandler;

	  @Autowired
	  private UserDetailsService userDetailsService;
	  
	  public WebSecurityConfig() {
		// TODO Auto-generated constructor stub
	  }

	  @Autowired
	  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	    authenticationManagerBuilder
	      .userDetailsService(this.userDetailsService)
	        .passwordEncoder(passwordEncoder());
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }

	  @Bean
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }

	  @Bean
	  public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
	    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
	    authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
	    return authenticationTokenFilter;
	  }

	  @Override
	  protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity
	      .csrf()
	        .disable()
	      .exceptionHandling()
	        .authenticationEntryPoint(this.unauthorizedHandler)
	        .and()
	      .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	      .authorizeRequests()
	      	.antMatchers("/**").permitAll()
	      	.antMatchers(HttpMethod.GET, "/**").permitAll()
	      	.antMatchers(HttpMethod.POST, "/user/signin").permitAll()
	      	.antMatchers(HttpMethod.POST, "/user").permitAll()
	      	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	        .anyRequest().authenticated();
	    httpSecurity
	    	.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	  }

}
