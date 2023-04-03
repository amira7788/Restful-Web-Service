package com.latcha.restfulwebservices.socialmediaapi;

import com.latcha.restfulwebservices.socialmediaapi.entities.User;
import com.latcha.restfulwebservices.socialmediaapi.exception.UserNotFoundException;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path="/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path="/users/{id}")
    public User retrieveUser(@PathVariable long id) {
        User user = userDaoService.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id:"+id);
        return user;
    }

    @PostMapping (path="/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
         userDaoService.save(user);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="users/{id}")
    public void deleteUser(@PathVariable long id){
        userDaoService.deleteById(id);
    }
}
