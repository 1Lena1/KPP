package com.example.lab1_3.entities;
public class Complex {
    private double module;
    private double phase;

    public Complex(double module, double phase) {
        this.module = module;
        this.phase = phase;
    }

    public Complex(Complex a) {
        this.module = a.module;
        this.phase = a.phase;
    }

    @Override
    public boolean equals(Object obj) {
        Complex test = (Complex) obj;
        if (phase == test.phase && module == test.module) {
            return true;
        }
        else return false;
    }

    public double getModule() {
        return module;
    }

    public void setModule(double module) {
        this.module = module;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }
}