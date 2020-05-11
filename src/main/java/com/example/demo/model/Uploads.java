package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Document
public class Uploads {
    @Id
    String id;

   private String username;

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   private LocalDateTime uploadDate;

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    private String fileName;
      private String fileDirectory;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }





    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadedFilePath() {
        return  fileDirectory;
    }

    public void setUploadedFilePath(String uploadedFilePath) {
        this. fileDirectory = uploadedFilePath;
    }

    public Uploads(String username,LocalDateTime uploadDate, String fileName, String  fileDirectory) {
        this.username = username;
        this.uploadDate = uploadDate;
        this.fileName = fileName;
        this. fileDirectory =  fileDirectory;
    }


    @Override
    public String toString() {
        return "Uploads{" +
                "username='" + username + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadedFilePath='" +  fileDirectory + '\'' +
                '}';
    }
}
