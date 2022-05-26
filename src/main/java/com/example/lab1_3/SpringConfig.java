package com.example.lab1_3;

import com.example.lab1_3.cache.InMemoryCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {

    @Bean("cache")
    @Scope(value = "singleton")
    InMemoryCache cache() {
        return new InMemoryCache();
    }

}