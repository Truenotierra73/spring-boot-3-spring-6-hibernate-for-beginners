package com.agustinbollati.springcoredemo.rest;

import com.agustinbollati.springcoredemo.common.Coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach myCoach) {
	    System.out.println("In constructor: " + getClass().getSimpleName());
		this.myCoach = myCoach;
	}

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return this.myCoach.getDailyWorkout();
    }
}
