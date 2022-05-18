package com.example.lab1_3.cache;

import com.example.lab1_3.entities.Complex;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.example.lab1_3.calculations.Param;
import com.example.lab1_3.logger.ProgramLogger;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static final Map<Param, Complex> solutions = new HashMap<>();

    public void add(@NotNull Param params, @NotNull Complex root) {
        if (!solutions.containsKey(params)) {
            solutions.put(params, root);
            ProgramLogger.log(Level.INFO, "Value " + params + "@" + root + " added to cache successfully!");
        }
    }

    public @Nullable Complex find(@NotNull Param params) {
        if (solutions.containsKey(params))
            return solutions.get(params);

        ProgramLogger.log(Level.WARN, "Value " + params + " not found in cache!");
        return null;
    }
    public @NotNull String getStringCache() {
        return ("Cache:\n" + solutions);
    }
    public static @NotNull String getStaticStringCache() {
        return ("Cache:\n" + solutions);
    }
    public Map<Param, Complex> getSolutions() {
        return solutions;
    }
}