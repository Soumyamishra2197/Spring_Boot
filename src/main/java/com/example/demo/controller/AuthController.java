package com.example.demo.controller;

import com.example.demo.model.AuthRequest;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = {"sign-up and auth api"})
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/add" ,method= RequestMethod.POST,consumes= {"application/json"})
    @ApiOperation(value = "To add new user")
    public Users saveUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @PostMapping("/authenticate")
    @ApiOperation(value = "For authentication")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        }catch (Exception e) {
            throw   new Exception("Invalid credentials");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
    @GetMapping("/")
    @ApiOperation(value = "home")
    public String welcome() {
        return "welcome to home !!!";
    }

    @GetMapping("/userDetails")
    @ApiOperation(value = "to get user details")
    public Collection<Users> getUsers(){
        return userService.getUsers();
    }
}
