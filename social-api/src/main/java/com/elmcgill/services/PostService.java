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

    private final NotificationService notificationService;

    public PostService(PostRepository postRepository, UserService userService, NotificationService notificationService){
        this.postRepository = postRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public Post createPost(CreatePostDTO cpo){
        User author = userService.getUserById(cpo.author());
        Date postedDate = new Date();

        Post post = new Post(0L, author, postedDate, cpo.content(), new HashSet<>(), new HashSet<>());
        Post saved = postRepository.save(post);

        notificationService.publishPostNotification(saved);

        return saved;
    }

    public Set<Post> getPostsByAuthor(Long authorId){
        User author = userService.getUserById(authorId);

        return postRepository.findPostsByAuthor(author).orElse(new HashSet<>());
    }

}
