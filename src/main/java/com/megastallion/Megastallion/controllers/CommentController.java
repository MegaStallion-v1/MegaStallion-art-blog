package com.megastallion.Megastallion.controllers;


import com.megastallion.Megastallion.Service.CommentService;
import com.megastallion.Megastallion.payLoad.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/new")
    public ResponseEntity<String> createComment(@RequestParam("id") Long postId, @RequestBody CommentDto request){
        return ResponseEntity.ok(commentService.createComment(postId, request));
    }


    @GetMapping("/comments")
    public ResponseEntity<Object> fetchAllComments(){
        return ResponseEntity.ok(commentService.fetchAllComments());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam("id") Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

}
