package com.elmcgill.services;

import com.elmcgill.dto.CreatePostDTO;
import com.elmcgill.models.Post;
import com.elmcgill.models.User;
import com.elmcgill.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post createPost(CreatePostDTO cpo){
        User author = userService.getUserById(cpo.author());
        Date postedDate = new Date();

        Post post = new Post(0L, author, postedDate, cpo.content(), new HashSet<>(), new HashSet<>());

        return postRepository.save(post);
    }

    public Set<Post> getPostsByAuthor(Long authorId){
        User author = userService.getUserById(authorId);

        return postRepository.findPostsByAuthor(author).orElse(new HashSet<>());
    }

}
