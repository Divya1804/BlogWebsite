package com.blog.services;

import com.blog.payloads.PostDto;
import java.util.List;

public interface PostServices {

    PostDto createPost(PostDto postDto, Integer userId, Integer catId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer id);
    void deletePost(Integer id);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer catId);
    List<PostDto> getAllPosts();
}
