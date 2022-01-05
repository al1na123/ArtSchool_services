package com.artschool.servicecourses.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Course {
    private String name;
    private Integer price;
    private String future_job;
}
