package com.controllers;

import com.entity.ExpressionRoot;
import com.exeption.RootIsOutIntervalException;
import com.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class MainController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String kpp_labs() {
        return "forms";
    }

    @GetMapping("/kpp_labs_html")
    public String kpp_labs(@RequestParam(required = false) int val1, @RequestParam(required = false) int val2, @RequestParam(required = false) int start, @RequestParam(required = false) int end, Model model) {
        model.addAllAttributes(new HashMap<String, Integer>() {
            {
                put("val1", val1);
                put("val2", val2);
                put("start", start);
                put("end", end);
            }
        });
        try {
            ExpressionRoot root = calculatorService.solve(val1, val2, start, end);
            model.addAttribute("root", "Корень " + root.getValue());
        } catch (RootIsOutIntervalException ex) {
            model.addAttribute("root", ex.getMessage());
        }
        return "forms";
    }

    @GetMapping(value = "/kpp_labs")
    @ResponseBody
    public ExpressionRoot mainJSONObject(@RequestParam int val1, @RequestParam int val2, @RequestParam int start, @RequestParam int end) throws RootIsOutIntervalException {
        return calculatorService.solve(val1, val2, start, end);
    }

}
