package com.example.demo.service;

import com.example.demo.dao.SearchDao;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {


    @Autowired
    SearchDao searchDao;

    public Users findByUserName(String username){
        return searchDao.findByName(username);
    }
    public Users findByFullName(String fullname){
        return searchDao.findByFullName(fullname);
    }

}
