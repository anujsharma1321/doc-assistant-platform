package com.example.docassistant.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/example")
public class example {
@GetMapping("/")
    public String getTime(){
    return "Current Date";
    }
}
