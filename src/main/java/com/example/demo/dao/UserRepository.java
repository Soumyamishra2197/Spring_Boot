package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;
@Repository
public interface UserRepository extends MongoRepository<Users,String>{

	public Users findByUsername(String username);

}
