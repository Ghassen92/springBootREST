package com.g.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.g.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
							 .antMatchers("/").permitAll()
							 .antMatchers(HttpMethod.POST, "/login").permitAll()
							 .anyRequest().authenticated()
							 .and()
				// We filter the api/login requests
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in
				// header
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// Create a default account
//		auth.inMemoryAuthentication().withUser("root").password("root").roles("ADMIN");
//		
//		List<User> users=userService.getAll();
//		for (User user : users)
//			auth.inMemoryAuthentication().withUser(user.getUserName()).password(user.getPassword()).roles("ADMIN");
//	}
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//	    Map<String, String> userMap =new HashMap<String, String>()/*= passwordService.getPasswordMap()*/;
//		List<User> users=userService.getAll();
//		for (User user : users)
//			userMap.put(user.getUserName(), user.getPassword());
		auth.inMemoryAuthentication().withUser("test1").password("test1").roles("ADMIN");
//	    for (Map.Entry<String, String> entry : userMap.entrySet()) {
//	        auth.inMemoryAuthentication().withUser(entry.getKey()).password(entry.getValue()).roles("ADMIN");
//	    }
	    // auth.userDetailsService(userDetailsService);

	}
	
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//	        final Properties users = new Properties();
//	        return new InMemoryUserDetailsManager(users);
//	}
//	

}
