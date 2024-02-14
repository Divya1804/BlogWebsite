package com.blog.controllers;

import com.blog.payloads.UpdatePasswordDto;
import com.blog.payloads.UserDto;
import com.blog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/")
    private ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        UserDto dto = userServices.createUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
        UserDto userDto1 = userServices.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updatepassword/{userId}")
    private ResponseEntity<?> usdatePassword(@RequestBody UpdatePasswordDto passwordDto, @PathVariable("userId") Integer userId){
        UserDto userDto = userServices.updatePassword(passwordDto, userId);
        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId){
        UserDto userDto = userServices.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<?> getAllUsers(){
        List<UserDto> userDtos = userServices.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

}
