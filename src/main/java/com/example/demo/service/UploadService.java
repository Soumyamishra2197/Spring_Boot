package com.example.demo.service;

import com.example.demo.dao.UploadDao;
import com.example.demo.model.Uploads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UploadService {
    @Autowired
    UploadDao uploadDao;

    public Uploads saveUploadedFileDetails(Uploads uploads){
        return uploadDao.saveUploadedFileDetail(uploads);
    }

    public Collection<Uploads> getUploadDetails(){
        return uploadDao.getUploadDetails();
    }
}
