package com.example.demo.controller;

import com.example.demo.model.GroupedUploads;
import com.example.demo.model.TotalUploads;
import com.example.demo.model.Uploads;
import com.example.demo.model.Users;
import com.example.demo.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = {"api for getting/searching user/upload details"})
public class OperationController {

   @Autowired
   SearchService searchService;

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ApiOperation(value = "Find a user by username")
    public Users findUsers(@RequestParam("username") String username){
        return searchService.findByUserName(username);
    }

    @RequestMapping(value = "/findUserByName", method = RequestMethod.GET)
    @ApiOperation(value = "Find a user by fullname")
    public Users findUsersByFullName(@RequestParam("fullname") String fullname){
        return  searchService.findByFullName(fullname);
    }

    @RequestMapping(value = "/findFileByUploader",method =RequestMethod.GET )
    @ApiOperation(value = "find a document by username")
    public Uploads findUploadByUserName(@RequestParam("username") String username){
        return searchService.findDocumentByUserName(username);
    }


    @RequestMapping(value = "/findFileByUploadDate",method =RequestMethod.GET )
    @ApiOperation(value = "find a document by upload date")
    public Uploads findUploadByUploadDate(@RequestParam("uploadDate") LocalDateTime date){
       return searchService.findDocumentByDate(date);
    }

    @RequestMapping(value = "getTotalUploads",method = RequestMethod.GET)
    @ApiOperation(value ="find number of documents uploaded" )
    public String countUploadedDocs(){
        return searchService.countUploadedDocs();
    }

    @RequestMapping(value = "getDetailsByDate",method = RequestMethod.GET)
    @ApiOperation(value = "get grouped upload data by date")
    public Collection<TotalUploads> getGroupedUploadDetails(@RequestParam("uploadDate") LocalDateTime date){
        Collection<TotalUploads> l=searchService.getUploadDetailsByDate(date);
        return l;
    }

    @RequestMapping(value = "getGroupedUploads" ,method=RequestMethod.GET)
    @ApiOperation( value="find grouped records of upload")
    public List<GroupedUploads> getGroupedUploads(){
        return searchService.getGroupedUploadData();
    }

}
