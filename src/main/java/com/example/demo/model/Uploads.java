package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Uploads {
    @Id
    String id;

   private String username;
   private String uploadTime;
   private String fileName;
    private String fileDirectory;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
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

    public Uploads(String username, String uploadTime, String fileName, String  fileDirectory) {
        this.username = username;
        this.uploadTime = uploadTime;
        this.fileName = fileName;
        this. fileDirectory =  fileDirectory;
    }

    public Uploads() {
    }

    @Override
    public String toString() {
        return "Uploads{" +
                "username='" + username + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadedFilePath='" +  fileDirectory + '\'' +
                '}';
    }
}
