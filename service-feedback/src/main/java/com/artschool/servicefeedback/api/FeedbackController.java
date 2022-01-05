package com.artschool.servicefeedback.api;

import com.artschool.servicefeedback.repo.model.Feedback;
import com.artschool.servicefeedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> index(){
        final List<Feedback> feedbacks = feedbackService.fetchAll();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> show(@PathVariable long id){
        try {
            final Feedback feedback = feedbackService.fetchById(id);
            return ResponseEntity.ok(feedback);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post/{user_id}/{course_id}")
    public ResponseEntity<Void> create(@RequestBody com.artschool.servicefeedback.api.dto.Feedback feedback){
        final Integer rating = feedback.getRating();
        final String text = feedback.getText();
        final long user_id = feedback.getUser_id();
        final long course_id = feedback.getCourse_id();
        ResponseEntity<Void> response = new RestTemplate().exchange("http://localhost:8082/users/get/"+user_id+"/"+course_id, HttpMethod.GET, null, Void.class);
        response.getStatusCode();
        if(response.getStatusCodeValue() == 200) {
                final long id = feedbackService.create(rating, text, user_id, course_id);
                final String location = String.format("/feedback/%d", id);
                return ResponseEntity.created(URI.create(location)).build();
            }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.artschool.servicefeedback.api.dto.Feedback feedback){
        final Integer rating = feedback.getRating();
        final String text = feedback.getText();
        final long user_id = feedback.getUser_id();
        final long course_id = feedback.getCourse_id();
        try{
            feedbackService.update(id, rating, text, user_id, course_id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
