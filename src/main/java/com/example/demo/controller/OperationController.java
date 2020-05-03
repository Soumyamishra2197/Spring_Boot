package com.example.demo.controller;

import com.example.demo.dao.SearchDao;
import com.example.demo.model.Uploads;
import com.example.demo.model.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"api for getting/searching user/upload details"})
public class OperationController {

    @Autowired
    SearchDao searchDao;

    /*@Autowired
    SearchService searchService;*/

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ApiOperation(value = "Find a user by username")
    public Users findUsers(@RequestParam("username") String username){
        return searchDao.findByName(username);
    }

    @RequestMapping(value = "/findUserByName", method = RequestMethod.GET)
    @ApiOperation(value = "Find a user by fullname")
    public Users findUsersByFullName(@RequestParam("fullname") String fullname){
        return  searchDao.findByFullName(fullname);
    }

    @RequestMapping(value = "/findFileByUploader",method =RequestMethod.GET )
    @ApiOperation(value = "find a document by username")
    public Uploads findUploadByUserName(@RequestParam("username") String username){
        return searchDao.findDocumentByUserName(username);
    }


    @RequestMapping(value = "/findFileByUploadDate",method =RequestMethod.GET )
    @ApiOperation(value = "find a document by upload date")
    public Uploads findUploadByUploadDate(@RequestParam("uploadDate") String date){
       return searchDao.findDocumentByDate(date);
    }

}
