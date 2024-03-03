package com.blog.controllers;

import com.blog.payloads.UpdatePasswordDto;
import com.blog.payloads.UserDto;
import com.blog.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto dto = userServices.createUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
        UserDto userDto1 = userServices.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updatepassword/{userId}")
    private ResponseEntity<UserDto> usdatePassword(@Valid @RequestBody UpdatePasswordDto passwordDto, @PathVariable("userId") Integer userId){
        UserDto userDto = userServices.updatePassword(passwordDto, userId);
        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
        UserDto userDto = userServices.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userServices.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

//    @GetMapping("/{userId}/post/{postId}")
//    private ResponseEntity<?> getPostById(@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId){
//        PostDetailDto postDto = userServices.getPostById(userId, postId);
//        return new ResponseEntity<>(postDto, HttpStatus.OK);
//    }

}
