package com.megastallion.Megastallion.Service.serviceImpls;

import com.megastallion.Megastallion.entities.Category;
import com.megastallion.Megastallion.exceptions.ResourceNotFoundException;
import com.megastallion.Megastallion.payLoad.PostDto;
import com.megastallion.Megastallion.Service.PostService;
import com.megastallion.Megastallion.entities.Post;
import com.megastallion.Megastallion.repositories.CategoryRepository;
import com.megastallion.Megastallion.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
@Autowired
private PostRepository postRepository;
@Autowired
private CategoryRepository categoryRepository;


    @Override
    public PostDto createPost(PostDto postRequestDto, Long categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Not found","Category","Id",categoryId.toString()));

        Post post= Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .imageUrl(postRequestDto.getImageUrl())
                .category(category)
                .build();
       Post newPost= postRepository.save(post);

       PostDto postResponse=new PostDto();
       postResponse.setCategory(category.getName());
       postResponse.setTitle(newPost.getTitle());
       postResponse.setContent(newPost.getContent());
       postResponse.setImageUrl(newPost.getImageUrl());
        return postResponse;
    }
}
