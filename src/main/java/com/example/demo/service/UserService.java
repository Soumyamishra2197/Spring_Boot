package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.Users;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public Collection<Users> getUsers(){
		return userDao.getUser();
	}
	public Users saveUser(Users users) {
		
		return userDao.saveUsers(users);
		
	}

}
