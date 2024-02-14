package com.blog.services.impl;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payloads.UpdatePasswordDto;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User user2 = userRepo.save(user);
        return modelMapper.map(user2, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));

        user.setName(userDto.getName());
        user.setUserId(userId);
        user.setAbout(userDto.getAbout());
        user.setUserName(userDto.getUserName());

        User user2 = userRepo.save(user);
        return modelMapper.map(user2, UserDto.class);
    }

    @Override
    public UserDto updatePassword(UpdatePasswordDto passwordDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("Donor", "Id", userId));
        if(passwordDto.getPassword().equals(user.getPassword())){
            if(passwordDto.getConfirmPassword().equals(passwordDto.getNewPassword())){
                user.setPassword(passwordDto.getConfirmPassword());
                userRepo.save(user);
            }
        }

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(dto -> modelMapper.map(dto, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }
}
