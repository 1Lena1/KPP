package com.example.lab1_3.controller;

import com.example.lab1_3.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCounter {
    static int counter = 0;

    synchronized public void increaseCounter() {
        counter++;
        ProgramLogger.log(Level.INFO, "Counter has been increased. New value is "+counter+".");
    }

    @GetMapping("/counter")
    synchronized public String showCalls() {
        return "Number of calls: "+counter;
    }
}

