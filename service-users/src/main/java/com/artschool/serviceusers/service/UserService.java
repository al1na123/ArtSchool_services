package com.artschool.serviceusers.service;

import com.artschool.serviceusers.repo.UserRepo;
import com.artschool.serviceusers.repo.model.User;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private  final UserRepo userRepo;
    public List<User> fetchAll(){
        return userRepo.findAll();
    }

    public User fetchById(long id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else return maybeUser.get();
    }

    public User getIds(long id, long course_id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else{
            final Optional<User> maybe_user = userRepo.findById(course_id);
            if(maybe_user.isEmpty()) throw new IllegalArgumentException("User not found");
            else return maybeUser.get();
        }
    }

    public long create(String first_name, String last_name, Integer age) {
        final User user = new User(first_name, last_name, age, 0);
        final User savedUser = userRepo.save(user);
        return savedUser.getUser_id();
    }


    public void update(long id, String first_name, String last_name,Integer age) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");

        final User user = maybeUser.get();
        if(first_name != null && !first_name.isBlank()) user.setFirst_name(first_name);
        if(last_name != null && !last_name.isBlank()) user.setLast_name(last_name);
        if(age != null) user.setAge(age);

        userRepo.save(user);
    }

    public void addToCourse(long id, long course_id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else{
            final User user = maybeUser.get();
            user.setCourse_id(course_id);
            userRepo.save(user);
        }
    }

    public void exitCourse(long id, long course_id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else{
            final User user = maybeUser.get();
            if(course_id != 0 ) user.setCourse_id(0);
            userRepo.save(user);
        }
    }

    public void delete(long id){
        userRepo.deleteById(id);
    }
}