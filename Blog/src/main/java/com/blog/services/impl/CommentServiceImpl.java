package com.blog.services.impl;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;
import com.blog.payloads.UserDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.CommentServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentServices {

    @Autowired
    private CommentRepo cmtRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper model;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "Id", userId));
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", postId));
        Comment cmt = model.map(commentDto, Comment.class);

        cmt.setUser(user);
        cmt.setPost(post);
        cmt.setDateTime(LocalDateTime.now());

        Comment cmt1 = cmtRepo.save(cmt);
        CommentDto dto = model.map(cmt1, CommentDto.class);
        return dto;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer userId, Integer postId, Integer cmtId) {
        Comment cmt = cmtRepo.findById(cmtId).orElseThrow(() -> new ResourceNotFound("Comment", "Id", cmtId));
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", postId));

        if (cmt.getUser().equals(user)){
            if (cmt.getPost().equals(post)){
                cmt.setCmtId(cmtId);
                cmt.setDateTime(LocalDateTime.now());
                cmt.setCmtTitle(commentDto.getCmtTitle());
                cmt.setCmtDescription(commentDto.getCmtDescription());
            }
        }

        Comment cmt1 = cmtRepo.save(cmt);
        return model.map(cmt1, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllCommentsByPost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", postId));
        List<Comment> cmts = cmtRepo.findCommentsByPostOrderByDateTimeDesc(post);

        return cmts.stream().map(cmt -> model.map(cmt, CommentDto.class)).collect(Collectors.toList());
    }
}
