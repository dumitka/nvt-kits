package com.backend.springboot.config;

import com.backend.springboot.security.RestAuthenticationEntryPoint;
import com.backend.springboot.security.TokenAuthenticationFilter;
import com.backend.springboot.service.CustomUserDetailsService;
import com.backend.springboot.util.TokenUtils;
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

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//koristi se za hesiranje lozinke
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
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
				.antMatchers("/nvt-stomp-endpoint/**").permitAll()
				.antMatchers("/cook/get").hasAuthority("ROLE_COOK")
				.antMatchers("/chef/get").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getColdAppetizers").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getHotAppetizer").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getMainCourse").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getDesert").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getSalad").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getAppendices").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getOne/id={id}").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/addMeal").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/changeMeal").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/deleteMeal").hasAuthority("ROLE_CHEF")
				.antMatchers("/menu/getMenu").hasAuthority("ROLE_CHEF")
				.antMatchers("/menu/addMealToMenu").hasAuthority("ROLE_CHEF")
				.antMatchers("/menu/changeMealPriceInMenu").hasAuthority("ROLE_CHEF")
				.antMatchers("/menu/deleteMealInMenu").hasAuthority("ROLE_CHEF")
				.antMatchers("/menu/newMenu").hasAuthority("ROLE_CHEF")
				.antMatchers("/menu/getMealPricesNotInMenu").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getColdAppetizerMeals").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getHotAppetizerMeals").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getMainCourseMeals").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getDesertMeals").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getSaladMeals").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getAppendicesMeals").hasAuthority("ROLE_CHEF")
				.antMatchers("/meal/getAllMeals").permitAll()
				.antMatchers("/api/orders/**").hasAuthority("ROLE_WAITER")
				.antMatchers("/api/desks/**").permitAll()
				.antMatchers("/api/drinks/**").permitAll()
				.antMatchers("/api/drinkCards/**").hasAuthority("ROLE_SERVER")
				.antMatchers("api/orderedMeals/notAccepted").hasAnyRole("ROLE_CHEF", "ROLE_COOK")
				.antMatchers("api/orderedMeals/acceptMeal").hasAnyRole("ROLE_CHEF", "ROLE_COOK")
				.antMatchers("api/orderedMeals/accepted/{userId}").hasAnyRole("ROLE_CHEF", "ROLE_COOK")
				.antMatchers("api/orderedMeals/finishMeal").hasAnyRole("ROLE_CHEF", "ROLE_COOK")
				.antMatchers("api/orderedMeals/createOrderedMeal/{orderId}").hasAnyRole("ROLE_WAITER")
				.antMatchers("/api/**").permitAll()
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
