package com.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userId;

    @NotEmpty(message = "Name must be valid and provided.")
    @Size(min = 2, message = "Name must be grater than 1 character")
    private String name;

    @NotEmpty(message = "Email must be entered")
    @Email(message = "Email is invalid.")
    private String email;

    @NotEmpty(message = "username must be unique and filled.")
    private String userName;

    @Size(min = 8,max = 16,message = "Must contain valid password having more then 8 characters and less then 16 characters")
    @NotEmpty(message = "Must contain proper value")
    private String password;

    private String about;

    private List<PostDetailDto> post;

    private Set<RoleDto> roles = new HashSet<>();

}
