package com.g.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.g.entities.User;
import com.g.services.UserService;

@RestController
public class UserRestService {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable Long id) {
		userService.deleteById(id);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable Long id) {
		User u = userService.getById(id);
		return u;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User save(@RequestBody User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		 //UserDetails userDetails =user;
		 Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
				SecurityContextHolder.getContext().getAuthentication().getCredentials(), authorities));
		return userService.save(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAll() {
		return userService.getAll();
	}


	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public User update(@RequestBody User user) {
		return userService.update(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PATCH)
	public User patch(@RequestBody User user) {
		User userOriginal = userService.getById(user.getId());
		if (user.getFirstName() != null)
			userOriginal.setFirstName(user.getFirstName());
		if (user.getLastName() != null)
			userOriginal.setLastName(user.getLastName());
		if (user.getPassword() != null)
			userOriginal.setPassword(user.getPassword());
		if (user.getUserName() != null)
			userOriginal.setUserName(user.getUserName());

		return userService.update(userOriginal);
	}

}
