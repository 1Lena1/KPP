package com.example.lab1_3.controller;

import com.example.lab1_3.entities.Complex;
import com.example.lab1_3.entities.Parameters;
import com.example.lab1_3.entities.ResultDto;
import com.example.lab1_3.exception.CalculationException;
import com.example.lab1_3.services.CalculationService;
import com.example.lab1_3.services.CounterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@RestController
@Validated
public class ComplexController {

    private static final Logger logger = LogManager.getLogger(ComplexController.class);

    @Autowired
    private CalculationService service;

    @Autowired
    private CounterService counterService;

    @GetMapping("/complex")
    public Complex complexCalculation(@RequestParam(value = "real", defaultValue = "0") String real,
                                      @RequestParam(value = "imaginable", defaultValue = "0") String imaginable) throws CalculationException {
        counterService.increaseCounter();
        return service.calculateComplex(service.defineParameters(real, imaginable));
    }

    @GetMapping("/cache")
    public ResponseEntity<Object> printCache() {
        return new ResponseEntity<>(service.getCache(), HttpStatus.OK);
    }

    @PostMapping(value = "/calculation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> alternativeCalculation(@RequestBody List<Parameters> parameters) {
        if (parameters.isEmpty()) {
            return ResponseEntity.ok(new ResultDto());
        }
        List<Complex> resultList = new ArrayList<>();
        ResultDto dto = new ResultDto();
        parameters.forEach((currentElement) -> {
            try {
                resultList.add(service.calculateComplex(new Parameters(currentElement.getReal(), currentElement.getImaginable())));
            } catch (IllegalArgumentException e) {
                logger.error("Error while PostMapping!");
            }
        });
        dto.setResultList(resultList);
        OptionalDouble averageOfReal = service.averageOfPositive(parameters.stream().mapToInt(Parameters::getReal));
        OptionalDouble averageOfImaginable = service.averageOfPositive(parameters.stream().mapToInt(Parameters::getReal));
        if (averageOfReal.isPresent()) {
            dto.setAverageOfReal(averageOfReal.getAsDouble());
        }
        if (averageOfImaginable.isPresent()) {
            dto.setAverageOfImaginable(averageOfImaginable.getAsDouble());
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}