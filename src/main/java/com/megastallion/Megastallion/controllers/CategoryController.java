package com.megastallion.Megastallion.controllers;

import com.megastallion.Megastallion.Service.CategoryService;
import com.megastallion.Megastallion.payLoad.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    final CategoryService categoryService;
    @PostMapping("/create-category/{id}")
    public ResponseEntity<CategoryDto> addCategory(@PathVariable(name = "id") CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }
}
