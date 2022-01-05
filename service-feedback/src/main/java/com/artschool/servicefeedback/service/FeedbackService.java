package com.artschool.servicefeedback.service;

import com.artschool.servicefeedback.repo.FeedbackRepo;
import com.artschool.servicefeedback.repo.model.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private  final FeedbackRepo feedbackRepo;
    public List<Feedback> fetchAll(){
        return feedbackRepo.findAll();
    }

    public Feedback fetchById(long id) throws IllegalArgumentException{
        final Optional<Feedback> maybeFeedback = feedbackRepo.findById(id);
        if(maybeFeedback.isEmpty()) throw new IllegalArgumentException("Feedback not found");
        else return maybeFeedback.get();
    }

    public long create(Integer rating, String text, long user_id, long course_id) {
        final Feedback feedback = new Feedback(rating, text, user_id, course_id);
        final Feedback savedFeedback = feedbackRepo.save(feedback);
        return savedFeedback.getFeedback_id();
    }


    public void update(long id, Integer rating, String text, long user_id, long course_id) throws IllegalArgumentException{
        final Optional<Feedback> maybeFeedback = feedbackRepo.findById(id);
        if(maybeFeedback.isEmpty()) throw new IllegalArgumentException("Feedback not found");

        final Feedback feedback = maybeFeedback.get();
        if(rating != null) feedback.setRating(rating);
        if(text != null && !text.isBlank()) feedback.setText(text);
        if(user_id > 0 ) feedback.setUser_id(user_id);
        if(course_id > 0) feedback.setCourse_id(course_id);

        feedbackRepo.save(feedback);
    }

    public void delete(long id){
        feedbackRepo.deleteById(id);
    }
}
