package com.blog.services;

import com.blog.payloads.UserDto;
import java.util.List;

public interface UserServices {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();

}
