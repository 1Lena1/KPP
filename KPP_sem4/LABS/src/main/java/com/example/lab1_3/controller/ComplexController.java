package com.example.lab1_3.controller;

import com.example.lab1_3.exception.CalculationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.lab1_3.entities.Complex;
import com.example.lab1_3.calculations.Param;
import com.example.lab1_3.calculations.Calculation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.lab1_3.cache.Cache;


@RestController
public class ComplexController {
    private Calculation calculation=new Calculation();
    private MyCounter counter=new MyCounter();
    @GetMapping("/complex")
    public Complex complexCalculation(@RequestParam(value="real", defaultValue="0") String real,
                                                                @RequestParam(value="imaginable", defaultValue="0") String imaginable) throws CalculationException {
        counter.increaseCounter();
        int intReal;
        int intImaginable;
        if (real.matches("[-+]?\\d+") ){
            intReal = Integer.parseInt(real);
        }
        else throw new CalculationException("Wrong parameter: Real");
        if(imaginable.matches("[-+]?\\d+")) {
            intImaginable = Integer.parseInt(imaginable);
        }
        else throw new CalculationException("Wrong parameter: Imaginable");
        calculation.setParameters(new Param(intReal,intImaginable));
        calculation.calculateComplex();
        return new Complex(calculation.getResult());
    }

    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(Cache.getStaticStringCache(), HttpStatus.OK);
    }

    @PostMapping(value = "/calculation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity alternativeCalculation(@RequestBody String[] array) {
        return new ResponseEntity<>(calculation.averageOfPositive(array), HttpStatus.OK);
    }
}