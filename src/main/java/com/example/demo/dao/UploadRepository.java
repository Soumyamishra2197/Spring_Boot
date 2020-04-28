package com.example.demo.dao;

import com.example.demo.model.Uploads;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends MongoRepository<Uploads,String> {

}
