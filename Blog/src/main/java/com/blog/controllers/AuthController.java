package com.blog.controllers;

import com.blog.entities.JwtRequest;
import com.blog.entities.JwtResponse;
import com.blog.jwt.JwtTokenHelper;
import com.blog.payloads.UserDto;
import com.blog.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody JwtRequest request){
        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setUsername(userDetails.getUsername());
        return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password){
        System.out.println("Done !!..");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try{
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username and Password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userServices.registerNewUser(userDto), HttpStatus.CREATED);
    }

}
