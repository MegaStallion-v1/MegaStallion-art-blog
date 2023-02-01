package com.megastallion.Megastallion.controllers;

import com.megastallion.Megastallion.payLoad.PostDto;
import com.megastallion.Megastallion.Service.PostService;
import com.megastallion.Megastallion.payLoad.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
@Autowired
    final PostService postService;


    @PostMapping("/create-post/{id}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postRequestDto,@PathVariable(name="id") Long categoryId){
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
    public ResponseEntity<PostDto> updatePost(@RequestParam("id") Long postId, PostUpdateDto request){
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }
}

