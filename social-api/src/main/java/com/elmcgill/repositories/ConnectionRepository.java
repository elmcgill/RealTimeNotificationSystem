package com.elmcgill.repositories;

import com.elmcgill.models.Connection;
import com.elmcgill.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    Optional<Set<Connection>> findByFollowedUser(User followedUser);

}
