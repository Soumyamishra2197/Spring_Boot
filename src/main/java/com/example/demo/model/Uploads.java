package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Uploads {
    @Id
    String id;

   private String username;
   private String uploadDate;
   private String fileName;
    private String fileDirectory;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate= uploadDate;
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

    public Uploads(String username, String uploadDate, String fileName, String  fileDirectory) {
        this.username = username;
        this.uploadDate = uploadDate;
        this.fileName = fileName;
        this. fileDirectory =  fileDirectory;
    }

    public Uploads() {
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
