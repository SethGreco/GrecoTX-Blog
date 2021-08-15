package com.spring.blog.controller;

import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;
import com.spring.blog.model.BlogPost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MongoController {

    @Autowired
    MongoTemplate mongoTemplate;


// REST ENDPOINT for retrieving all articles
    @GetMapping("/articles")
    public List<BlogPost> getArticles() {
        try{
            return mongoTemplate.findAll(BlogPost.class, "blogposts");
        }catch (MongoException e){
            log.error("Mongo failed to find all records: " + e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
    }

// REST ENDPOINT for retrieving single article
    @GetMapping("/articles/{title}")
    public BlogPost getSingleArticle(
            @PathVariable("title") String title) {
        Criteria criteria = Criteria.where("title").is(title);
        Query query = Query.query(criteria);
        try {
            return mongoTemplate.findOne(query, BlogPost.class, "blogposts");
        } catch(MongoException e){
            log.error("Mongo failed to find target article");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", e);
        }
    }

//    REST ENDPOINT for inserting a new record into DB
    @PostMapping("/articles")
    public ResponseEntity<HttpStatus> postArticle(@Valid @RequestBody BlogPost blogPost){
        try{
            log.info("Mongo Record being inserted");
            mongoTemplate.insert(blogPost, "blogposts");
        }catch (DuplicateKeyException e){
            log.error("Mongo failed to insert record: " + e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Duplicate key in database.  Cannot create new record", e);
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

