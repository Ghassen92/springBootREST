package com.g.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g.dao.UserRepository;
import com.g.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User update(User user) {
		User u= userRepository.findOne(user.getId()) ;
		if(u!=null) {
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setPassword(user.getPassword());
			return userRepository.saveAndFlush(u);
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		 userRepository.delete(id);
	}

}
