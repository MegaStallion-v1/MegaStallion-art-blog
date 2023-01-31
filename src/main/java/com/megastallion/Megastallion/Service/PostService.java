package com.megastallion.Megastallion.Service;

import com.megastallion.Megastallion.entities.Category;
import com.megastallion.Megastallion.payLoad.PostDto;
import com.megastallion.Megastallion.entities.Post;

public interface PostService {


    PostDto createPost(PostDto postRequestDto,Long categoryId);

    String deletePost(Long postId);
}
