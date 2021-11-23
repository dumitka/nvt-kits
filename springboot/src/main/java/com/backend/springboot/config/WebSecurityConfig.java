package com.backend.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.backend.springboot.security.RestAuthenticationEntryPoint;
import com.backend.springboot.security.TokenAuthenticationFilter;
import com.backend.springboot.service.CustomUserDetailsService;
import com.backend.springboot.util.TokenUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//koristi se za hesiranje lozinke
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//servis koji dobavlja korisnika iz baze
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	//handler za vracanje 401 kada klijent sa neodogovarajucim korisnickim imenom i lozinkom pokusa da pristupi resursu
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	//registrujemo authentication manager koji ce da uradi autentifikaciju korisnika za nas
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//definisemo nacin utvrdjivanja korisnika pri autentifikaciji
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService) //kazemo da koristi customUserDetailsService za dobavljanje podataka
		.passwordEncoder(passwordEncoder()); //te kako da hesira lozinku
	}

	@Autowired
	private TokenUtils tokenUtils;
	
	// koristiti jedan od dole nacina za funkciju u svojim kontrolerima!!!!
	// primjer --> .antMatchers("/admin").hasRole("ADMIN") ili .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.authorizeRequests().antMatchers("/auth/login").permitAll() //svi imaju pristup logovanju
								.antMatchers("/api/hello").permitAll()
			.anyRequest().authenticated().and()
			.cors().and()
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class);
		http.csrf().disable();
	}

	//definisanje konfiguracije koja utice na generalnu bezbednost aplikacije
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
