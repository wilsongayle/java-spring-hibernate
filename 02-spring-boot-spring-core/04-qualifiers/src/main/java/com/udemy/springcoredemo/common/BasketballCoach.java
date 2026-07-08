package com.udemy.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Shooting drills";
    }
}
