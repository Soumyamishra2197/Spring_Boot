package com.example.demo.dao;

import com.example.demo.model.Uploads;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@Component
public class SearchDao {


    @Autowired
    MongoOperations mongoTemplate;

    public Users findByName(String username){
        Query query=new Query();
        query.addCriteria(Criteria.where("username").regex(username));
        return mongoTemplate.findOne(query,Users.class);
    }
    public Users findByFullName(String fullname){
        Query query=new Query();
        query.addCriteria(Criteria.where("fullname").regex(fullname));
        return mongoTemplate.findOne(query,Users.class);
    }

    public Uploads findDocumentByUserName(String username){
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query,Uploads.class);
    }

   // GroupOperation groupByDate=group("uploadTime").addToSet("filename").as("countFile");

  public Uploads findDocumentByTime(String time){
        Query query=new Query();
        query.addCriteria(Criteria.where("uploadTime").regex(time));
        return mongoTemplate.findOne(query,Uploads.class);
  }
}