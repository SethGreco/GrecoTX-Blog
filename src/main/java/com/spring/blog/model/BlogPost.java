package com.spring.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogPost {

    private int id;
    private String title;
    private String date;
    private String body;

}
