package com.blog.services.impl;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private ModelMapper model;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = model.map(categoryDto, Category.class);
        Category cat1 = catRepo.save(cat);
        return model.map(cat1, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {

        Category cat = catRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("Category", "Id", catId));

        cat.setCatId(catId);
        cat.setCatName(categoryDto.getCatName());
        cat.setCatDescription(categoryDto.getCatDescription());

        Category cat1 = catRepo.save(cat);
        return model.map(cat1,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        Category cat = catRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("Category", "Id", catId));
        return model.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categories = catRepo.findAll();
        List<CategoryDto> dtos = categories.stream().map(dto -> model.map(dto, CategoryDto.class)).collect(Collectors.toList());

        return dtos;
    }
}
