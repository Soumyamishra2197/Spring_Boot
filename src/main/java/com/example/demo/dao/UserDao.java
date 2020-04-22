package com.example.demo.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Users;

@Component
public class UserDao {

	@Autowired 
	UserRepository userRepository;
	
	public Collection<Users> getUser(){
		return userRepository.findAll();
	}
	/*
	public Users loginUser(String username,String password) {
		Users user1=userRepository.loginUser(username, password);
		return user1;
	}*/
	public Users saveUsers(Users users) {
		return userRepository.insert(users);
	}
	
}
