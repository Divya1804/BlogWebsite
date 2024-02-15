package com.blog.controllers;

import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryServices catServices;

    @PostMapping("/")
    private ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto catDto){
        CategoryDto dto = catServices.createCategory(catDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    private ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto catDto, @PathVariable("catId") Integer catId){
        CategoryDto categoryDto = catServices.updateCategory(catDto, catId);
        return new ResponseEntity<>(categoryDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{catId}")
    private ResponseEntity<?> getCategoryById(@PathVariable("catId") Integer catId){
        CategoryDto dto = catServices.getCategoryById(catId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<?> getAllCategory(){
        List<CategoryDto> dtos = catServices.getAllCategory();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
