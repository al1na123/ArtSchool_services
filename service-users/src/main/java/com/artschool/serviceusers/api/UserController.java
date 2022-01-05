package com.artschool.serviceusers.api;

import com.artschool.serviceusers.repo.model.User;
import com.artschool.serviceusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> index(){
        final List<User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable long id){
        try {
            final User user = userService.fetchById(id);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{id}/{course_id}")
    public ResponseEntity<User> getIds(@PathVariable long id, @PathVariable long course_id){
        try {
            final User user = userService.getIds(id, course_id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.artschool.serviceusers.api.dto.User user){
        final String first_name = user.getFirst_name();
        final String last_name = user.getLast_name();
        final Integer age = user.getAge();
        final long id = userService.create(first_name, last_name, age);
        final String location = String.format("/users/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.artschool.serviceusers.api.dto.User user){
        final String first_name = user.getFirst_name();
        final String last_name = user.getLast_name();
        final Integer age = user.getAge();
        try{
            userService.update(id, first_name, last_name, age);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/add")
    public ResponseEntity<Void> addToCourse(@PathVariable long id, @RequestBody com.artschool.serviceusers.api.dto.User user){
        final long course_id = user.getCourse_id();
        try{
            ResponseEntity<Void> response = new RestTemplate().exchange("http://localhost:8081/courses/get/"+course_id, HttpMethod.GET, null, Void.class);
            response.getStatusCode();
            if (response.getStatusCodeValue() == 200) {
                userService.addToCourse(id, course_id);
                return ResponseEntity.noContent().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/{id}/exit")
    public ResponseEntity<Void> exitCourse(@PathVariable long id, @RequestBody com.artschool.serviceusers.api.dto.User user){
        final long course_id = user.getCourse_id();
        try{
            userService.exitCourse(id, course_id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
           return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
