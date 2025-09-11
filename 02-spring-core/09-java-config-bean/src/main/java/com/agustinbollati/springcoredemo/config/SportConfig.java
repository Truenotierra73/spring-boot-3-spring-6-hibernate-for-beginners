package com.agustinbollati.springcoredemo.config;

import com.agustinbollati.springcoredemo.common.Coach;
import com.agustinbollati.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

	@Bean
	public Coach swimCoach() {
		return new SwimCoach();
	}
}
