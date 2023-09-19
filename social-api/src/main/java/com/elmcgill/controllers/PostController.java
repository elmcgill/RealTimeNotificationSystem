package com.elmcgill.controllers;

import com.elmcgill.dto.CreatePostDTO;
import com.elmcgill.models.Post;
import com.elmcgill.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/")
    public Post createPost(@RequestBody CreatePostDTO createPostObject) {
        return postService.createPost(createPostObject);
    }

    @GetMapping("/user/{authorId}")
    public Set<Post> getPostsByAuthor(@PathVariable Long authorId){
        return postService.getPostsByAuthor(authorId);
    }
}
