package com.service;

public class Calculator {
    public static String solve(float val1, float val2, float start, float end) {
        if (val2 - val1 >= start && val2 - val1 <= end)
            return Float.toString(val2 - val1);
        return "";
    }
}
