package com.megastallion.Megastallion.Service;

import com.megastallion.Megastallion.payLoad.CommentDto;

public interface CommentService {


    String createComment(Long postId, CommentDto request);
}
