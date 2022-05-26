package com.example.lab1_3.cache;

import com.example.lab1_3.entities.Complex;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.example.lab1_3.entities.Parameters;
import com.example.lab1_3.logger.ProgramLogger;

import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryCache {

    private static final Map<Parameters, Complex> solutions = new LinkedHashMap<>();

    public void add(@NotNull Parameters params, @NotNull Complex root) {
        if (!solutions.containsKey(params)) {
            solutions.put(params, root);
            ProgramLogger.log(Level.INFO, "Value " + params + "@" + root + " added to cache successfully!");
        }
    }

    public @Nullable Complex find(@NotNull Parameters params) {
        if (solutions.containsKey(params)) {
            return solutions.get(params);
        }
        ProgramLogger.log(Level.WARN, "Value " + params + " not found in cache!");
        return null;
    }

    public Map<Parameters, Complex> getSolutions() {
        return solutions;
    }
}