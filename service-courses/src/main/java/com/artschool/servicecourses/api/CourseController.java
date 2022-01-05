package com.artschool.servicecourses.api;
import com.artschool.servicecourses.repo.model.Course;
import com.artschool.servicecourses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/courses")
public final class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<com.artschool.servicecourses.repo.model.Course>> index(){
        final List<com.artschool.servicecourses.repo.model.Course> courses = courseService.fetchAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.artschool.servicecourses.repo.model.Course> show(@PathVariable long id){
        try{
        final com.artschool.servicecourses.repo.model.Course course = courseService.fetchById(id);
        return ResponseEntity.ok(course);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Course> getData(@PathVariable long id){
        try{
            final Course course = courseService.getId(id);
            return ResponseEntity.ok().build();}
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

   @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.artschool.servicecourses.api.dto.Course course){
        final String name = course.getName();
       final Integer price = course.getPrice();
       final String future_job = course.getFuture_job();
       final long id = courseService.create(name, price, future_job);
       final String location = String.format("/courses/%d",id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.artschool.servicecourses.api.dto.Course course){
        final String name = course.getName();
        final Integer price = course.getPrice();
        final String future_job = course.getFuture_job();
        try{
            courseService.update(id, name, price, future_job);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
