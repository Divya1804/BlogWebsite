package com.blog.services.impl;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostServices {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private ModelMapper model;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
        Category cat = catRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("Category", "Id", catId));

        Post post = model.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(cat);
        Post post1 = postRepo.save(post);
        return model.map(post1, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post oldPost = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", postId));

        oldPost.setPostImg(postDto.getPostImg());
        oldPost.setPostTitle(postDto.getPostTitle());
        oldPost.setPostDescription(postDto.getPostDescription());
        oldPost.setPostId(postId);

        Post newPost = postRepo.save(oldPost);
        return model.map(newPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "Id", id));
        return model.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "Id", id));
        this.postRepo.deleteById(id);
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
        List<Post> posts = postRepo.findPostsByUser(user);
        return posts.stream().map(dto -> model.map(dto, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByCategory(Integer catId) {
        Category cat = catRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("Category", "Id", catId));
        List<Post> categories = postRepo.findPostsByCategory(cat);
        return categories.stream().map(dto -> model.map(dto, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize) {

        Pageable page = PageRequest.of(pageNumber, pageSize);

        Page<Post> posts = postRepo.findAll(page);
        return posts.stream().map(dto -> model.map(dto, PostDto.class)).collect(Collectors.toList());
    }
}
