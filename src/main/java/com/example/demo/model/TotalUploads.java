package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TotalUploads {

    private String username;
    private long totalUploads;
}