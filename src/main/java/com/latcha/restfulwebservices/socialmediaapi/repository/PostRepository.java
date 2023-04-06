package com.latcha.restfulwebservices.socialmediaapi.repository;

import com.latcha.restfulwebservices.socialmediaapi.entities.Post;
import com.latcha.restfulwebservices.socialmediaapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
