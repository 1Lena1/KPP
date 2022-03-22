package com.controllers;

import com.service.Calculator;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/kpp_labs")
    public String kpp_labs() {
       return "forms";
    }

    @PostMapping("/kpp_labs")
    public String kpp_labs(@RequestParam float val1,
                           @RequestParam float val2,
                           @RequestParam float start,
                           @RequestParam float end,
                           Model model) {
        model.addAttribute("root", Calculator.solve(val1, val2, start, end));
        return "forms";
    }

}
