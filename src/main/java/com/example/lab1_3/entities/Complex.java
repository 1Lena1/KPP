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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.module, module) == 0 && Double.compare(complex.phase, phase) == 0;
    }

}