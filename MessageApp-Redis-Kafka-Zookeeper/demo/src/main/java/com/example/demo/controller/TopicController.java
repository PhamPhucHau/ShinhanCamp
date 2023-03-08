package com.example.demo.controller;

import com.example.demo.config.DynamicTopicKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TopicController {
    @Autowired
    private DynamicTopicKafka dynamicTopicKafka;
    @PostMapping("createTopic")
    public ResponseEntity<String> createTopic(@RequestParam String topicName){
        dynamicTopicKafka.createDynamicTopic(topicName);
        return ResponseEntity.ok("Topic " + topicName + " created successfully.");
    }
    @DeleteMapping("deleteTopic")
    public ResponseEntity<String> deleteTopic(@RequestParam String topicName){
        dynamicTopicKafka.deleteDynamicTopic(topicName);
        return ResponseEntity.ok("Topic " + topicName + " Deleted successfully.");
    }
}