package com.megastallion.Megastallion.Service.serviceImpls;

import com.megastallion.Megastallion.Service.CategoryService;
import com.megastallion.Megastallion.entities.Category;
import com.megastallion.Megastallion.payLoad.CategoryDto;
import com.megastallion.Megastallion.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        //TODO =========> GET Authenticated when spring security is ready;
        Category category=Category.builder()
                .name(categoryDto.getName())
                .build();
        Category savedCategory=categoryRepository.save(category);
        CategoryDto categoryResponse=new CategoryDto();
        categoryResponse.setName(savedCategory.getName());
        return categoryResponse;
    }
}
