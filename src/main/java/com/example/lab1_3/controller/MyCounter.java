package com.example.lab1_3.controller;

import com.example.lab1_3.services.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCounter {

    @GetMapping("/counter")
    synchronized public String showCalls() {
        return "Number of calls: " + CounterService.getCounter();
    }
}

