package com.service;

import com.entity.ExpressionRoot;
import com.exeption.RootIsOutIntervalException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public ExpressionRoot solve(int val1, int val2, int start, int end) {
        if (val2 - val1 >= start && val2 - val1 <= end) {
            return new ExpressionRoot(val2 - val1);
        } else {
            throw new RootIsOutIntervalException("Root is not included in the interval");
        }
    }
}
