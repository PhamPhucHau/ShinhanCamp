package com.example.demo.service.DemoServiceImpl;

import com.example.demo.repository.ContentUser;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    ContentUser contentUser;
   public String getContent()
    {
            return contentUser.getContent();
    }
}
