package com.artschool.serviceusers.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class User {
    private String first_name;
    private String last_name;
    private Integer age;
    private long course_id;
}
