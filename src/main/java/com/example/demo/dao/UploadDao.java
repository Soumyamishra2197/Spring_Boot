package com.example.demo.dao;

import com.example.demo.model.Uploads;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UploadDao {
    @Autowired
    UploadRepository uploadRepository;

    public Uploads saveUploadedFileDetail(Uploads uploads){
        return uploadRepository.insert(uploads);
    }
    public Collection<Uploads> getUploadDetails(){
        return uploadRepository.findAll();
    }

}
