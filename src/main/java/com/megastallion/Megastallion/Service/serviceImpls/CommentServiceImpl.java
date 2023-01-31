package com.megastallion.Megastallion.Service.serviceImpls;

import com.megastallion.Megastallion.Service.CommentService;
import com.megastallion.Megastallion.entities.Comment;
import com.megastallion.Megastallion.entities.Post;
import com.megastallion.Megastallion.entities.User;
import com.megastallion.Megastallion.exceptions.ResourceNotFoundException;
import com.megastallion.Megastallion.payLoad.CommentDto;
import com.megastallion.Megastallion.repositories.CommentRepository;
import com.megastallion.Megastallion.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    public String createComment(Long postId, CommentDto request){
        //TODO =========> GET Authenticated when spring security is ready;
        User user = new User();
        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Not Found","Post","Id",postId.toString()));

        Comment comment = Comment.builder()
                .post(post)
                .content(request.getContent())
                .user(user)
                .build();
        commentRepository.save(comment);

        return "Comment posted";
    }


    @Override
    public List<CommentDto> fetchAllComments(){
        return commentRepository.findAll().stream().map(this::commentResponseMapper)
                .collect(Collectors.toList());
    }


    @Override
    public String deleteComment(Long commentId){
        Comment comment = getComment(commentId);
        commentRepository.delete(comment);
        return "Comment deleted";
    }





    protected CommentDto commentResponseMapper(Comment comment){
        return CommentDto.builder()
                .content(comment.getContent())
                .build();
    }


    protected Comment getComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(()
                -> new ResourceNotFoundException("Not Found","Comment","Id",commentId.toString()));
    }


}
