package com.ga.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entities.User;
import com.ga.services.UserService;

@RestController
public class UserRestService {

	@Autowired
	private UserService userMetier;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable Long id) {
		userMetier.deleteById(id);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable Long id) {
		User u = userMetier.getById(id);
		return u;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User save(@RequestBody User user) {
		return userMetier.save(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAll() {
		return userMetier.getAll();
	}

	@RequestMapping(value = "/users", method = RequestMethod.PATCH)
	public User update(@RequestBody User user) {
		return userMetier.update(user);
	}

	
}
