package com.g.rest;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.g.entities.User;
import com.g.services.UserService;

@CrossOrigin(origins = "*")
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

	@PostConstruct
	public void init() {
//		List<Account> accounts=new ArrayList<Account>();
//		accounts.add(new Account(445, 555));
//		User user=new User("g","g","g","g");
//		user.setAccounts(accounts);
//		userMetier.save(user);
	}

}
