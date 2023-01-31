package com.megastallion.Megastallion.Service;

import com.megastallion.Megastallion.payLoad.CommentDto;

import java.util.List;

public interface CommentService {


    String createComment(Long postId, CommentDto request);

    List<CommentDto> fetchAllComments();
}
