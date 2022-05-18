package com.example.lab1_3.calculations;

import com.example.lab1_3.SpringConfig;
import com.example.lab1_3.cache.Cache;
import com.example.lab1_3.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.lab1_3.entities.Complex;

import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calculation {

    private Cache cache;

    private Param parameters;

    private Complex root;

    public Calculation() {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", Cache.class);
        context.close();
    }

    public Calculation(Param params) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", Cache.class);
        context.close();
        this.parameters = params;
    }

    public void setParameters(Param params){
        this.parameters=params;
    }

    public void calculateComplex() {
        var temp = cache.find(parameters);
        if (temp != null) {
            ProgramLogger.log(Level.WARN, "Result is found in cache!");
            setRoot(temp);
            return;
        }
        Complex tmp=new Complex(Math.sqrt(Math.pow(parameters.getFirstValue(), 2) + Math.pow(parameters.getSecondValue(), 2)),
                Math.atan2(parameters.getFirstValue(), parameters.getSecondValue()));
        setRoot(tmp);
        cache.add(parameters, root);
    }

    public OptionalDouble averageOfPositive(String[] arr){
        IntStream stream = Stream.of(arr).mapToInt(Integer::parseInt);
        return stream.filter(x->x>0).average();
    }

    public Complex getResult() {
        return root;
    }

    public void setRoot(@Nullable Complex root) {
        this.root = root;
    }

}