package com.example.lab1_3.calculations;

import org.springframework.lang.Nullable;
import java.util.Objects;

public class Param {

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Param params = (Param) obj;

        return  Objects.equals(firstValue,  params.firstValue)  &&
                Objects.equals(secondValue, params.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    private @Nullable Integer firstValue;

    private @Nullable Integer secondValue;


    public Param(
            @Nullable Integer firstValue,
            @Nullable Integer secondValue
    ) {
        if (firstValue == null && secondValue == null)
            throw new IllegalArgumentException("Values not found!");

        if (firstValue == null)
            throw new IllegalArgumentException("First value not found!");

        if (secondValue == null)
            throw new IllegalArgumentException("Second value not found!");

        this.firstValue  = firstValue;
        this.secondValue = secondValue;
    }

    public Integer getFirstValue() {
        assert firstValue != null;
        return firstValue;
    }

    public Integer getSecondValue() {
        assert secondValue != null;
        return secondValue;
    }

    public void setFirstValue(@Nullable Integer firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(@Nullable Integer secondValue) {
        this.secondValue = secondValue;
    }

}