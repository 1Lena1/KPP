package com.example.lab1_3.services;

import com.example.lab1_3.SpringConfig;
import com.example.lab1_3.cache.InMemoryCache;
import com.example.lab1_3.entities.Complex;
import com.example.lab1_3.entities.Parameters;
import com.example.lab1_3.exception.CalculationException;
import com.example.lab1_3.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class CalculationService {

    @Autowired
    private final InMemoryCache inMemoryCache;

    public CalculationService() {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        inMemoryCache = context.getBean("cache", InMemoryCache.class);
        context.close();
    }

    public Parameters defineParameters(String real, String imaginable) {
        int intReal, intImaginable;
        if (real.matches("[-+]?\\d+")) {
            try {
                intReal = Integer.parseInt(real);
            } catch (NumberFormatException e) {
                throw new CalculationException("First value format is wrong!");
            }
        } else {
            throw new CalculationException("Wrong parameter: Real");
        }

        if (imaginable.matches("[-+]?\\d+")) {
            try {
                intImaginable = Integer.parseInt(imaginable);
            } catch (NumberFormatException e) {
                throw new CalculationException("Second value format is wrong!");
            }
        } else {
            throw new CalculationException("Wrong parameter: Imaginable");
        }
        return new Parameters(intReal, intImaginable);
    }

    public Complex calculateComplex(Parameters parameters) {
        Complex temp = inMemoryCache.find(parameters);
        if (temp != null) {
            ProgramLogger.log(Level.WARN, "Result is found in cache!");
            return temp;
        } else {
            Complex root = new Complex(Math.sqrt(Math.pow(parameters.getReal(), 2) + Math.pow(parameters.getImaginable(), 2)),
                    Math.atan2(parameters.getReal(), parameters.getImaginable()));
            inMemoryCache.add(parameters, root);
            return root;
        }
    }

    public OptionalDouble averageOfPositive(IntStream stream) {
        return stream.filter(x -> x > 0).average();
    }

    public Map<Parameters, Complex> getCache() {
        return inMemoryCache.getSolutions();
    }
}