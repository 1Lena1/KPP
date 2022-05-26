package com.example.lab1_3.services;

import com.example.lab1_3.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CounterService {
    private static int counter;

    public synchronized void increaseCounter() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        counter++;
        ProgramLogger.log(Level.INFO, "Counter has been increased. New value is " + counter + ".");
    }

    public static int getCounter() {
        return counter;
    }
}
