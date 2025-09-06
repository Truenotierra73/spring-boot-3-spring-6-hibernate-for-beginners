package com.agustinbollati.springcoredemo.rest;

import com.agustinbollati.springcoredemo.common.Coach;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final Coach myCoach;

    // Si tienes un solo constructor es opcional colocar @Autowired
    public DemoController(Coach theCoach) {
        this.myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return this.myCoach.getDailyWorkout();
    }
}
