package com.agustinbollati.springcoredemo.rest;

import com.agustinbollati.springcoredemo.common.Coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final Coach myCoach;
	private final Coach anotherCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach myCoach, @Qualifier("cricketCoach") Coach anotherCoach) {
	    System.out.println("In constructor: " + getClass().getSimpleName());
		this.myCoach = myCoach;
		this.anotherCoach = anotherCoach;
	}

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return this.myCoach.getDailyWorkout();
    }

	@GetMapping("/check")
	public String check() {
		return "Comparing beans (myCoach == anotherCoach): " + (myCoach == anotherCoach);
	}
}
