package com.spring.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

    @Range(min=1, max=10000)
    private int id;

    @NotEmpty(message = "title cannot be empty or null")
    private String title;

    @NotEmpty(message = "date cannot be empty or null")
    private String date;

    private String body;

    public BlogPost(int id, String title, String date, String body) {
        this.id = getId();
        this.title = getTitle();
        this.date = getDate();
        this.body = getBody();
    }
}
