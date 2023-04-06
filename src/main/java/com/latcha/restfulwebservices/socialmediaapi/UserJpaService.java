package com.latcha.restfulwebservices.socialmediaapi;

import com.latcha.restfulwebservices.socialmediaapi.entities.Post;
import com.latcha.restfulwebservices.socialmediaapi.entities.User;
import com.latcha.restfulwebservices.socialmediaapi.repository.PostRepository;
import com.latcha.restfulwebservices.socialmediaapi.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserJpaService {

    private UserRepository userRepository;

    private PostRepository postRepository;
    public UserJpaService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findOne(long id) {
        return userRepository.findById(id);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }
}
