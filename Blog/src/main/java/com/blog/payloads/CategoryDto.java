package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer catId;

    @NotBlank(message = "Name of category must be defined properly.")
    private String catName;

    @NotBlank(message = "Description is must for other users to understand the Category properly and it will helps them to interact with your created posts")
    @Size(min = 10, max = 600, message = "The description must be less than 600 words")
    private String catDescription;

    private List<PostDetailDto> post;
}
