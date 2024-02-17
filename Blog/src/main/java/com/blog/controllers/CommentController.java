package com.blog.controllers;

import com.blog.payloads.CommentDto;
import com.blog.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentServices cmtService;

    @PostMapping("/user/{userId}/post/{postId}/comment")
    private ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, @PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId){
        CommentDto cmt = cmtService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(cmt, HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}/post/{postId}/comment/{commentId}")
    private ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto, @PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId){
        CommentDto cmt = cmtService.updateComment(commentDto, userId, postId, commentId);
        return new ResponseEntity<>(cmt, HttpStatus.ACCEPTED);
    }

    @GetMapping("/post/{postId}/comments")
    private ResponseEntity<?> getAllCommentsByPost(@PathVariable("postId") Integer postId){
        List<CommentDto> cmt = cmtService.getAllCommentsByPost(postId);
        return new ResponseEntity<>(cmt , HttpStatus.OK);
    }

}
