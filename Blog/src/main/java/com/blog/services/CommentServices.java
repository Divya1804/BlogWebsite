package com.blog.services;

import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;

import java.util.List;
public interface CommentServices {

    CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
    CommentDto updateComment(CommentDto commentDto, Integer userId, Integer postId, Integer cmtId);
    List<CommentDto> getAllCommentsByPost(Integer postId);

}
