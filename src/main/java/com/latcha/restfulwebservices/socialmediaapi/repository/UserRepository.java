package com.latcha.restfulwebservices.socialmediaapi.repository;

import com.latcha.restfulwebservices.socialmediaapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
