package com.example.lab1_3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultDto {
    @JsonProperty
    Double averageOfReal;

    @JsonProperty
    Double averageOfImaginable;

    @JsonProperty
    List<Complex> resultList;

    public ResultDto() {
    }

    public void setAverageOfReal(Double averageOfReal) {
        this.averageOfReal = averageOfReal;
    }

    public void setAverageOfImaginable(Double averageOfImaginable) {
        this.averageOfImaginable = averageOfImaginable;
    }

    public void setResultList(List<Complex> resultList) {
        this.resultList = resultList;
    }
}
