package com.example.demo.controller;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController("")
public class DemoController {
    @Autowired
    DemoService demoService;
    @GetMapping(value="/")
    String getDemo()
   {
       return demoService.getContent();
   }
}
