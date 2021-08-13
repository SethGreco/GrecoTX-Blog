package com.spring.blog.controller;

import com.spring.blog.model.BlogPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.is;



@WebMvcTest(controllers = MongoController.class)
@ActiveProfiles("test")
public class MongoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MongoTemplate mongoTemplate;

    private List<BlogPost> blogs;

    @BeforeEach
    public void setUp() {
        this.blogs = new ArrayList<>();
        this.blogs.add(new BlogPost(1, "title1", "date1", "body1"));
        this.blogs.add(new BlogPost(2, "title2", "date2", "body2"));
        this.blogs.add(new BlogPost(3, "title3", "date3", "body3"));

    }

//    @Test
//    public void returnAllArticles() throws Exception {
//
//
//    }

}
