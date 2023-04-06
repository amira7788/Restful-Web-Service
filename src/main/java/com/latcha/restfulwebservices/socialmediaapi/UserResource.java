package com.latcha.restfulwebservices.socialmediaapi;

import com.latcha.restfulwebservices.socialmediaapi.entities.Post;
import com.latcha.restfulwebservices.socialmediaapi.entities.User;
import com.latcha.restfulwebservices.socialmediaapi.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
public class UserResource {

    private UserJpaService userJpaService;

    public UserResource(UserJpaService userJpaService) {
        this.userJpaService = userJpaService;
    }

    @GetMapping(path="/users")
    public List<EntityModel<User>> retrieveAllUsers() {

        return userJpaService.findAll().stream().map(user -> {
                    WebMvcLinkBuilder link = linkTo(
                      methodOn(this.getClass()).retrieveUser(user.getId())
                    );

                    return EntityModel.of(user).add(link.withRel("user-by-id"));
                }
        ).collect(Collectors.toList());
//        {
//            WebMvcLinkBuilder link = linkTo(
//                    methodOn(this.getClass()).retrieveUser(user.getId())
//            );
//            return EntityModel.of(user);
//        });
    }
//    @GetMapping(path="/users/{id}")
//    public User retrieveUser(@PathVariable long id) {
//        User user = userDaoService.findOne(id);
//
//        if(user == null)
//            throw new UserNotFoundException("id:"+id);
//        return user;
//    }
    //entityModel to create links and give action access with WebMvcLinkBuilder
    @GetMapping(path="/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable long id) {
        Optional<User> user = userJpaService.findOne(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping (path="/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
         userJpaService.save(user);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="users/{id}")
    public void deleteUser(@PathVariable long id){
        userJpaService.deleteById(id);
    }

    @GetMapping(path="users/{id}/posts")
    public List<Post> retrieveAlPostsForUser(@PathVariable long id){
        Optional<User> user = userJpaService.findOne(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        return user.get().getPosts();
    }

    @PostMapping(path="users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable long id, @Valid @RequestBody Post post){
        Optional<User> user = userJpaService.findOne(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        post.setUser(user.get());
        userJpaService.savePost(post);
        URI responseLocation = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(responseLocation).build();
    }

}
