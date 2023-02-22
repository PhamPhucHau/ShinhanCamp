package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("")
@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(@RequestParam(name="name",required=false,defaultValue="") String name, Model model)
    {
        model.addAttribute("name",name);
        model.addAttribute("text","name");
        return "hello";
    }
}
