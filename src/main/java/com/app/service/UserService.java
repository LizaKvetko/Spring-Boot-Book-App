package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository ur;
	
	public void insert(User u) {
		ur.save(u);	
	}
	
	public User selectOne(int id) {
		Optional<User> uOpt = ur.findById(id);
		return uOpt.get();
	}
	
	public User validateLogin(String email, String password) {
		User user = ur.findByEmailPassword(email, password);
		return user;
	}

}
