package com.megastallion.Megastallion.controllers;

import com.megastallion.Megastallion.entities.Post;
import com.megastallion.Megastallion.payLoad.PostDto;
import com.megastallion.Megastallion.Service.PostService;
import com.megastallion.Megastallion.payLoad.PostResponse;
import com.megastallion.Megastallion.payLoad.PostUpdateDto;
import com.megastallion.Megastallion.repositories.PostRepository;
import com.megastallion.Megastallion.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
@Autowired
    final PostService postService;
    @Autowired
    private PostRepository postRepository;


    @PostMapping("/create-post/{id}")
    public ResponseEntity<PostDto> createPost(@Valid  @RequestBody PostDto postRequestDto, @PathVariable(name="id") Long categoryId){
        return new ResponseEntity<>(postService.createPost(postRequestDto,categoryId),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") Long postId){
        return  ResponseEntity.ok (postService.deletePost(postId));
    }

    @GetMapping("/get")
    public ResponseEntity<PostDto> fetchPost(@RequestParam("id") Long postId){
        return ResponseEntity.ok(postService.fetchPost(postId));
    }

    @PutMapping("/update")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestParam("id") Long postId, PostUpdateDto request){
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }
    @GetMapping("/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
            @RequestParam(value = "PageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue =AppConstants.DEFAULT_SORT_BY,required = false ) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
            ){
      return postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
    }
}

