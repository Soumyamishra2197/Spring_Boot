package com.example.demo.dao;

import com.example.demo.model.TotalUploads;
import com.example.demo.model.Uploads;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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


  public Uploads findDocumentByDate(String date){
        Query query=new Query();
        query.addCriteria(Criteria.where("uploadDate").is(date));
        return mongoTemplate.findOne(query,Uploads.class);
  }

  public String countUploadedDocs(){

        Query query=new Query();
        query.addCriteria(Criteria.where("fileName").exists(true));
        long a=mongoTemplate.count(query,Uploads.class);
        return "Total "+a+" documents are uploaded..";
  }

  public List<TotalUploads> getUploadsByDate(String date){
      Aggregation aggregation=newAggregation(match(Criteria.where("uploadDate").is(date)),group("username","fileName").count().as("totalUploads"),project("totalUploads").and("username").previousOperation(),sort(Sort.Direction.DESC,"totalUploads"));


      AggregationResults<TotalUploads> groupResults=mongoTemplate.aggregate(aggregation,"uploads",TotalUploads.class);

      System.out.println(groupResults.getRawResults());
      List<TotalUploads> result=groupResults.getMappedResults();


      return result;
  }

}

