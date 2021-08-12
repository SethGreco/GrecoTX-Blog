package com.spring.blog.controller;

import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;
import com.spring.blog.model.BlogPost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MongoController {

    @Autowired
    MongoTemplate mongoTemplate;


// REST ENDPOINT for retrieving all articles
    @GetMapping("/articles")
    public ResponseEntity<List<BlogPost>> getArticles() {
        try{
            List<BlogPost> allArticles = mongoTemplate.findAll(BlogPost.class, "blogposts");
            return ResponseEntity.ok(allArticles);
        }catch (MongoException e){
            log.error("Mongo failed to find all records: " + e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
    }

// REST ENDPOINT for retrieving single article
    @GetMapping("/articles/{title}")
    public ResponseEntity<BlogPost> getSingleArticle(
            @PathVariable("title") String title) {
        Criteria criteria = Criteria.where("title").is(title);
        Query query = Query.query(criteria);
        try {
            BlogPost blogPost = mongoTemplate.findOne(query, BlogPost.class, "blogposts");

            return ResponseEntity.ok(blogPost);
        } catch(MongoException e){
            log.error("Mongo failed to find target article");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
    }

//    REST ENDPOINT for inserting a new record into DB
    @PostMapping("/articles")
    public ResponseEntity<HttpStatus> postArticle(@RequestBody(required = true) BlogPost blogPost){
        try{
            log.info("Mongo Record being inserted");
            mongoTemplate.insert(blogPost, "blogposts");

        }catch (MongoException e){
            log.error("Mongo failed to insert record: " + e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

//    TODO: 1. Endpoint for editing current records
    // need to be able to send edits for fields sent to us in the payload
    @PutMapping("/articles/{title}")
    public ResponseEntity<String> updateArticle(
            @PathVariable("title") String title,
            @RequestBody Map<String, String> updates){
        Criteria criteria = Criteria.where("title").is(title);
        Query query = Query.query(criteria);


//        Update update = new Update().set()
        return ResponseEntity.ok("OK");
    }

    // REST ENDPOINT for deleting specific article
    @DeleteMapping("/articles/{title}")
    public ResponseEntity<DeleteResult> deleteArticle(
            @PathVariable("title") String title){

        Criteria criteria = Criteria.where("title").is(title);
        Query query = Query.query(criteria);
        try {
            DeleteResult deleteResult = mongoTemplate.remove(query, BlogPost.class, "blogposts");
            return ResponseEntity.ok(deleteResult);
        }catch(MongoException e){
            log.error("Mongo failed to delete article: " + e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
    }
}

