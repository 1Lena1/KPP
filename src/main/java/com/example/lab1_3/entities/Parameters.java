package com.example.lab1_3.entities;

import java.util.Objects;

public class Parameters {

    private int real;

    private int imaginable;

    public Parameters(int firstValue, int imaginable) {
        this.real = firstValue;
        this.imaginable = imaginable;
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public int getImaginable() {
        return imaginable;
    }

    public void setImaginable(int secondValue) {
        this.imaginable = imaginable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return real == that.real && imaginable == that.imaginable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginable);
    }
}