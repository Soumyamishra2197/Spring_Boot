package com.example.demo.controller;

import com.example.demo.ZonedDateTime;
import com.example.demo.model.Uploads;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

@RestController
@Api(tags = {"api for file operations"})
public class FileController {

    public  String fileDirectory=System.getProperty("user.dir")+File.separator+"uploadedDocs";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    UploadService uploadService;

    DateTimeFormat.ISO dateTimeFormat;
    DateTimeFormatter dateTimeFormat2;


    public static DateTimeFormat.ISO DATE_TIME;


    @RequestMapping(value = "/upload", method= RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "For uploading documents")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{

        String currentToken=request.getHeader("Authorization");
        String fileName="";
        String message="Not available";
        String username="";
        LocalDateTime uploadDate;
       // dateTimeFormat= DateTimeFormat.ISO.DATE_TIME;

        dateTimeFormat2=DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime now=LocalDateTime.now();
        if(!jwtUtil.isTokenExpired(currentToken)){


           // uploadDate=dateTimeFormat.format(date);
          //  uploadDate= LocalDateTime.parse(dateTimeFormat2.format(now));
            uploadDate=LocalDateTime.now();
            username=jwtUtil.extractUserName(currentToken);
            fileName=username+dateTimeFormat2.format(now)+file.getOriginalFilename();

            if(uploadService.saveUploadedFileDetails(new Uploads(username,uploadDate,fileName,fileDirectory))!=null){

                File convertFile=new File(fileDirectory,fileName);

                convertFile.createNewFile();

                FileOutputStream fout=new FileOutputStream(convertFile);

                fout.write(file.getBytes());

                fout.close();
                message="Document uploaded successfully!!";
            }else{
                message="Failed to upload";
            }

        }
        return new ResponseEntity<Object>(message, HttpStatus.OK);

    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ApiOperation(value = "Download file using path and filename")
    public ResponseEntity<Object> downloadFile(@RequestParam("path") String path,
                                               @RequestParam("filename") String filename) throws IOException
    {
        String downloadableFile =path+"/"+filename ;
        File file = new File(downloadableFile);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);
        return responseEntity;
    }

    @GetMapping("/viewUploads")
    @ApiOperation(value = "For getting upload details")
    public Collection<Uploads> getUploadDetails(){
        return uploadService.getUploadDetails();
    }
}
