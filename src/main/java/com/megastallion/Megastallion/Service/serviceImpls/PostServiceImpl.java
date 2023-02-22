package com.megastallion.Megastallion.Service.serviceImpls;

import com.megastallion.Megastallion.entities.Category;
import com.megastallion.Megastallion.exceptions.ResourceNotFoundException;
import com.megastallion.Megastallion.payLoad.PostDto;
import com.megastallion.Megastallion.Service.PostService;
import com.megastallion.Megastallion.entities.Post;
import com.megastallion.Megastallion.payLoad.PostResponse;
import com.megastallion.Megastallion.payLoad.PostUpdateDto;
import com.megastallion.Megastallion.repositories.CategoryRepository;
import com.megastallion.Megastallion.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
@Autowired
private PostRepository postRepository;
@Autowired
private CategoryRepository categoryRepository;


    @Override
    public PostDto createPost(PostDto postRequestDto, Long categoryId) {
        //TODO =========> GET Authenticated when spring security is ready;
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
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir){

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        //        create pageable instance
        Pageable pageable=PageRequest.of(pageNo,pageSize, sort);

       Page<Post> posts= postRepository.findAll(pageable);

//       get content for page object
        List<Post> listOfPosts=posts.getContent();
        List<PostDto> content= listOfPosts.stream().map(post -> responseMapper(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }
    @Override
    public String deletePost(Long postId){
        //TODO =========> GET Authenticated when spring security is ready;
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Not Found","Post","Id",postId.toString()));
        postRepository.delete(post);
        return "Post Deleted Successfully";

    }


    @Override
    public PostDto fetchPost(Long postId){
        Post post = findPost(postId);
        return responseMapper(post);
    }


    @Override
    public PostDto updatePost(Long postId, PostUpdateDto request){
        //TODO =========> GET Authenticated when spring security is ready;
        Post post = findPost(postId);
        Category category = findCategory(request.getCategoryId());

        post.setTitle(request.getTitle());
        post.setCategory(category);
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        postRepository.save(post);

        return responseMapper(post);
    }



    protected PostDto responseMapper(Post post){
        return PostDto.builder()
                .title(post.getTitle())
                .category(post.getCategory().getName())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .build();
    }


    protected Post findPost(Long postId){
        return postRepository.findById(postId).orElseThrow(()
        -> new ResourceNotFoundException("Not Found","Post","Id",postId.toString()));
    }

    protected Category findCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(()
        -> new ResourceNotFoundException("Not found","Category","Id",categoryId.toString()));
    }

}
