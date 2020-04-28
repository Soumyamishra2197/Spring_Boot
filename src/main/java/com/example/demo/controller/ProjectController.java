package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Collection;

import com.example.demo.model.Uploads;
import com.example.demo.service.UploadService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AuthRequest;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = {"user operation api"})
public class ProjectController {

	public static String fileDirectory=System.getProperty("user.dir")+File.separator+"uploadedDocs";

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;

	@Autowired
	UploadService uploadService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	@ApiOperation(value = "home")
	public String welcome() {
		return "welcome to home !!!";
	}
	
	@RequestMapping(value = "/add" ,method=RequestMethod.POST,consumes= {"application/json"})
	@ApiOperation(value = "To add new user")
	public Users saveUser(@RequestBody Users user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/userDetails")
	@ApiOperation(value = "to get user details")
	public Collection<Users> getUsers(){
		return userService.getUsers();
	}


	@RequestMapping(value = "/upload", method=RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation(value = "For uploading documents")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{

		String currentToken=request.getHeader("Authorization");
		String fileName="";
		String message="Not available";
		String username="";
		String uploadTime="";

		if(!jwtUtil.isTokenExpired(currentToken)){

			LocalTime time=LocalTime.now();
			uploadTime=time+"";
			username=jwtUtil.extractUserName(currentToken);
			fileName=username+uploadTime+file.getOriginalFilename();
			
			if(uploadService.saveUploadedFileDetails(new Uploads(username,uploadTime,fileName,fileDirectory))!=null){

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
	
	@PostMapping("/authenticate")
	@ApiOperation(value = "For authentication")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		}catch (Exception e) {
			throw new Exception("Invalid credentials");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}

	@GetMapping("/viewUploads")
	@ApiOperation(value = "For getting upload details")
	public Collection<Uploads> getUploadDetails(){
		return uploadService.getUploadDetails();
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




}
