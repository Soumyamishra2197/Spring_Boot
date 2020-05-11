package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TotalUploads {

    private String username;
    private long totalUploads;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTotalUploads() {
        return totalUploads;
    }

    public void setTotalUploads(long totalUploads) {
        this.totalUploads = totalUploads;
    }
}
