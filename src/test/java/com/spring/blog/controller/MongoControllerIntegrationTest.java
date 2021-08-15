//package com.spring.blog.controller;
//
//import com.mongodb.BasicDBList;
//import com.mongodb.BasicDBObjectBuilder;
//import com.mongodb.DBObject;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.*;
//
//
//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//public class MongoControllerIntegrationTest {
//
//    @Test
//    public void getAllArticles(@Autowired MongoTemplate mongoTemplate) {
//
//        DBObject object = BasicDBObjectBuilder.start()
//                .add("key", "value").get();
//
//        mongoTemplate.save(object, "collection");
//
//        // then
//        assertThat(mongoTemplate.find(DBObject.class, "collection")).extracting("key")
//                .containsOnly("value");
//    }
//    }
//}
