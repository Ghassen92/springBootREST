package com.g.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
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
		if (user.getUsername() != null)
			userOriginal.setUsername(user.getUsername());

		return userService.update(userOriginal);
	}
	
	
	@RequestMapping(value="/users/me", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Authentication me(){
 		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request){
		System.out.println("logout");
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		SecurityContextHolder.getContext().setAuthentication(null);
		String authHeader = request.getHeader("Authorization");
	        if (authHeader != null) {
	            String tokenValue = authHeader.replace("Bearer", "").trim();
//	            OAUTH2ACCESSTOKEN ACCESSTOKEN = TOKENSTORE.READACCESSTOKEN(TOKENVALUE);
//	            TOKENSTORE.REMOVEACCESSTOKEN(ACCESSTOKEN);
	            System.out.println(tokenValue);
	        }
	}

}
