package com.blog.repositories;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByPostOrderByDateTimeDesc(Post post);
}
