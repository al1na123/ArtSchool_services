package com.artschool.servicecourses.service;

import com.artschool.servicecourses.repo.CourseRepo;
import com.artschool.servicecourses.repo.model.Course;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService {

    private  final CourseRepo courseRepo;
    public List<Course> fetchAll(){
        return courseRepo.findAll();
    }

    public Course fetchById(long id) throws IllegalArgumentException{
        final Optional<Course> maybeCourse = courseRepo.findById(id);
        if(maybeCourse.isEmpty()) throw new IllegalArgumentException("Course not found");
        else return maybeCourse.get();
    }

    public Course getId(long id) throws IllegalArgumentException{
        final Optional<Course> maybeCourse = courseRepo.findById(id);
        if(maybeCourse.isEmpty()) throw new IllegalArgumentException("Course not found");
        else  return maybeCourse.get();
    }

    public long create(String name, Integer price, String future_job){
        final Course course = new Course(name, price, future_job);
        final Course savedCourse = courseRepo.save(course);
        return savedCourse.getCourse_id();
    }

    public void update(long id, String name, Integer price, String future_job) throws IllegalArgumentException{
        final Optional<Course> maybeCourse = courseRepo.findById(id);
        if(maybeCourse.isEmpty()) throw new IllegalArgumentException("Course not found");

        final Course course = maybeCourse.get();
        if(name != null && !name.isBlank()) course.setName(name);
        if(price != null) course.setPrice(price);
        if(future_job != null && !future_job.isBlank()) course.setFuture_job(future_job);
        courseRepo.save(course);
    }

    public void delete(long id){
        courseRepo.deleteById(id);
    }
}
