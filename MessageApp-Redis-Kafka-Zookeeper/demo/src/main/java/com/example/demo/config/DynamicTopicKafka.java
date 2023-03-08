package com.example.demo.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@Configuration
public class DynamicTopicKafka {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public Map<String, Object> configAdminClient(){
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return props;
    }

    public void createDynamicTopic(String topicName)
    {
        AdminClient adminClient=AdminClient.create(configAdminClient());
        NewTopic topic = new NewTopic(topicName, Collections.emptyMap());
        try {
            adminClient.createTopics(Collections.singletonList(topic)).all().get();
        } catch (ExecutionException | InterruptedException e) {
            if (e.getCause() instanceof TopicExistsException) {
                System.out.println("Topic already exists");
            } else {
                e.printStackTrace();
            }
        }

    }
    public void deleteDynamicTopic(String topicExist)
    {
        try {
            AdminClient adminClient = AdminClient.create(configAdminClient());
            adminClient.deleteTopics(Collections.singletonList(topicExist)).all().get();
        }
        catch (ExecutionException | InterruptedException e){
            if (e.getCause() instanceof TopicExistsException) {
                System.out.println("Topic already exists");
            } else {
                e.printStackTrace();
            }
        }
    }
}
