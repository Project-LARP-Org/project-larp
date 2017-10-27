package com.projectlarp.app.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projectlarp.app.modules.auth.JsonAuthenticationFilter;

/**
 * Actions:. <br/>
 * - secure URLs. <br/>
 * - authent: first by POST JSON {username, password}, RETURN token. <br/>
 * - authent: next with same token. <br/>
 * 
 * @see origin https://spring.io/guides/tutorials/spring-boot-oauth2/
 *
 * @see origin
 *      https://javattitude.com/2014/06/07/spring-security-custom-token-based-
 *      rest-authentication/
 * 
 * @see origin
 *      http://www.baeldung.com/spring-security-authentication-with-a-database
 */
@Configuration
@ConditionalOnClass(DataSource.class)
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.addFilterBefore(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    http.csrf().disable();//
		http //
				// .antMatcher("/**") //
				.authorizeRequests() //
				/// **/ .antMatchers("/api/auth/login").permitAll() //
				/**/ .antMatchers("/api/auth/login").permitAll() //
				/**/ .antMatchers("/").permitAll() //
				/**/ .antMatchers("/favicon.jpg", "/favicon.ico").permitAll() //
				/**/ .antMatchers("/webjars/**").permitAll() //
				/**/ .antMatchers("/angular/**", "/css/**", "/i18n/**", "/img/**", "/js/**").permitAll() //
				/**/ .anyRequest().authenticated() //
		;
	}

	@Bean
	JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
		JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		System.out.println("jsonAuthenticationFilter");
		return filter;
	}

	/**
	 * @see origin
	 *      http://www.baeldung.com/spring-security-authentication-with-a-
	 *      database
	 */
	/*
	 * @Autowired private SpringDataJpaUserDetailsService userDetailsService;
	 */
	/*
	 * @Override public void configure(final AuthenticationManagerBuilder auth)
	 * throws Exception { auth //
	 * .authenticationProvider(authenticationProvider()) //
	 * .userDetailsService(userDetailsService) //
	 * .passwordEncoder(passwordEncoder()) // ; }
	 */
	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 * authProvider.setUserDetailsService(userDetailsService);
	 * authProvider.setPasswordEncoder(passwordEncoder()); return authProvider;
	 * }
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

}
