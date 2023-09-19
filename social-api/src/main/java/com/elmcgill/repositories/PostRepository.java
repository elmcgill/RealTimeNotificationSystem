package com.elmcgill.repositories;

import com.elmcgill.models.Post;
import com.elmcgill.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Set<Post>> findPostsByAuthor(User author);

}
