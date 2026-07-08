package com.udemy.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Run a 5K";
    }

    @PostConstruct
    public void startupProcess() {
        System.out.println("[TrackCoach] Start Up");
    }

    @PreDestroy
    public void cleanupProcess() {
        System.out.println("[TrackCoach] Clean Up");
    }
}
