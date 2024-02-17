package com.blog.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class CommentDto {

    private Integer cmtId;

    @NotBlank(message = "Title must be proper")
    private String cmtTitle;

    @NotBlank(message = "Description must be greater than 20 characters")
    @Min(value = 20 ,message = "Must be greater than 20 characters")
    @Max(value = 400 , message = "Must be less than 400 characters")
    private String cmtDescription;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private UserDto user;
    private PostDetailDto post;
}
