package com.blog.services;

import com.blog.payloads.CategoryDto;
import java.util.List;

public interface CategoryServices {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);
    CategoryDto getCategoryById(Integer catId);
    List<CategoryDto> getAllCategory();

}
