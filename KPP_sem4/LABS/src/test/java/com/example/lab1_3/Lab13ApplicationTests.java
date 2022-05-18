package com.example.lab1_3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.lab1_3.controller.ComplexController;
import com.example.lab1_3.exception.CalculationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.lab1_3.entities.Complex;


@SpringBootTest
class ApplicationTests {

    private final ComplexController calculationController = new ComplexController();
    @Test

    void test1() throws CalculationException {
        Complex result = calculationController.complexCalculation("1", "0");
        Complex expected=new Complex(1.0,1.5707963267948966);
        assertEquals(expected, result);
    }
}
