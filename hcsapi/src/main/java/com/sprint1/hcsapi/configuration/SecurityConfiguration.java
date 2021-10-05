package com.sprint1.hcsapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This SecurityConfig class is to override the configuration methods and to set how configuration will work. 
 * @author devendra
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	/**
	 * This method is initializing the authenticationManagerBean.
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	/**
	 *This method is used to configure api authorities,resister and validate api will be accessible by everyone. 
	 *and all other api will require authorization.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf() 
		.disable()
		.authorizeRequests()
		.antMatchers("/api/users/register").permitAll()
		.antMatchers("/api/users/validate").permitAll()
		.anyRequest().authenticated();
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
		
	}
	
	/**This method is used for password encoding
	 * @return the encoded password
	 */
	@Bean
	public PasswordEncoder passEncoder(){
		return new BCryptPasswordEncoder(12);
	}
	
	
}
