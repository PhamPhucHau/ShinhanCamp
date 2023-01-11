package com.example.demo.service.DemoServiceImpl;

import com.example.demo.repository.ContentAnimal;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoAnimalServiceImpl implements DemoService {
    @Autowired
    ContentAnimal contentAnimal;
    @Override
    public String getContent() {
        return contentAnimal.getContent();
    }
}
