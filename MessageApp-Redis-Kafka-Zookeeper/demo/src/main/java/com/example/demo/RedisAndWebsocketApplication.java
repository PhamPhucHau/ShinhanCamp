package com.example.demo;

import com.example.demo.config.KafkaProducerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RedisAndWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisAndWebsocketApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,String> kafkaTemplate){
		return args->{
			kafkaTemplate.send("likelion","Hello kafka");
		};
	}
//	@Autowired
//	private RedisTemplate redisTemplate;
//	@Bean
//	CommandLineRunner commandLineRunner(){
//		return args->{
//			listExample();
//		};
//	}
//	public void listExample()
//	{
//		List<String> listString=new ArrayList<>();
//		listString.add("Hello");
//		listString.add("Redis");
//		redisTemplate.opsForList().rightPushAll("likelion_list",listString);
//		System.out.println("Size of key likelion:"+redisTemplate.opsForList().size("likelion_list"));
//
//
//	}
}
