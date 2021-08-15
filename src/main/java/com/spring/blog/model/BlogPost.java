package com.spring.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

    @Id
    @Range(min=1, max=10000)
    private int id;

    @Indexed(unique = true)
    @NotEmpty(message = "title cannot be empty or null")
    private String title;

    @NotEmpty(message = "date cannot be empty or null")
    private String date;

    private String body;

}
