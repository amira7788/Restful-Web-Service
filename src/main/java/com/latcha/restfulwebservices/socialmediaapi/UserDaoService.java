package com.latcha.restfulwebservices.socialmediaapi;

import com.latcha.restfulwebservices.socialmediaapi.entities.User;
import com.latcha.restfulwebservices.socialmediaapi.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    private UserRepository userRepository;

    public UserDaoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
