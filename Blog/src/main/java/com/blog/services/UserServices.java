package com.blog.services;

import com.blog.payloads.PostDetailDto;
import com.blog.payloads.PostDto;
import com.blog.payloads.UpdatePasswordDto;
import com.blog.payloads.UserDto;
import java.util.List;

public interface UserServices {

//    PostDetailDto getPostById(Integer userId, Integer postId);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    UserDto updatePassword(UpdatePasswordDto passwordDto, Integer userId);
}
