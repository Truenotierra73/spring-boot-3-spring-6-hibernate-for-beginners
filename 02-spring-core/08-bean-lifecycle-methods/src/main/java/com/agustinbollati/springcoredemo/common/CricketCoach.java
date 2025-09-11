package com.agustinbollati.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

	public CricketCoach() {
		System.out.println("In constructor: " + getClass().getSimpleName());
	}

	// Definir nuestro método inicializador
	@PostConstruct
	public void init() {
		System.out.println("In init(): " + getClass().getSimpleName());
	}

	// Definir nuestro método destructor
	@PreDestroy
	public void destroy() {
		System.out.println("In destroy(): " + getClass().getSimpleName());
	}

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes.";
    }
}
