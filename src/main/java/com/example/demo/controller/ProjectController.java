package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AuthRequest;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = {"user operation api"})
public class ProjectController {
	
	public static String fileDirectory=System.getProperty("user.dir")+"/"+"uploadedDocs";

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation(value = "For uploading documents")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
		
		File convertFile=new File(fileDirectory,file.getOriginalFilename());
		
		convertFile.createNewFile();
		
		FileOutputStream fout=new FileOutputStream(convertFile);
		
		fout.write(file.getBytes());
		
		fout.close();
		
		return new ResponseEntity<Object>("Document uploaded successfully",HttpStatus.OK);
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
	
	
}
