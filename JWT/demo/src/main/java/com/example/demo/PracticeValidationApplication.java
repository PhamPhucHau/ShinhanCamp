package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PracticeValidationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PracticeValidationApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
	User user=new User();
	user.setUsername("username");
	user.setPassword(passwordEncoder.encode("1234"));
	userRepository.save(user);
	System.out.println(user);
	}
}
