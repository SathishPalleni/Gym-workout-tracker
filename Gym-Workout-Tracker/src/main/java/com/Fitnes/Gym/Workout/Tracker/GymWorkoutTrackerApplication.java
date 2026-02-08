package com.Fitnes.Gym.Workout.Tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.Fitnes.Gym.Workout.Tracker.entity")
public class GymWorkoutTrackerApplication {

	public static void main(String[] args) {

        SpringApplication.run(GymWorkoutTrackerApplication.class, args);
	}

}
