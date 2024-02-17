package com.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cmtId;

    @Column(name = "comment_title")
    private String cmtTitle;

    @Column(name = "comment_desc", length = 500, nullable = false)
    private String cmtDescription;;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "post_time")
    private LocalDateTime dateTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

}
