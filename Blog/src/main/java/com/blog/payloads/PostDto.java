package com.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String postImg;
    private String postTitle;
    private String postDescription;
    private UserDto user;
    private CategoryDto category;

}
