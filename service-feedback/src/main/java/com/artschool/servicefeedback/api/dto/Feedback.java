package com.artschool.servicefeedback.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Feedback {
    private Integer rating;
    private String text;
    private long user_id;
    private long course_id;
}
