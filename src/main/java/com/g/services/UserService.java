package com.g.services;

import java.util.List;

import com.g.entities.User;

public interface UserService {

	public List<User> getAll();
	public User save(User user);
	public User getById(Long id);
	public User update(User user);
	public void deleteById(Long id);
}
