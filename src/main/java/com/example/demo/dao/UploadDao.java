package com.example.demo.dao;

import com.example.demo.model.Uploads;
import com.example.demo.model.Users;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Date;

@Component
public class UploadDao {
    @Autowired
    UploadRepository uploadRepository;
    @Autowired
    JwtUtil jwtUtil;

    public Uploads saveUploadedFileDetail(Uploads uploads){
        return uploadRepository.insert(uploads);
    }
    public Collection<Uploads> getUploadDetails(){
        Collection<Uploads> u= uploadRepository.findAll();
        return u;
    }


}
