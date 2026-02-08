package com.Fitnes.Gym.Workout.Tracker.repository;

import com.Fitnes.Gym.Workout.Tracker.entity.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLog, Long> {

    List<WorkoutLog> findByMemberIdOrderByWorkoutDateDesc(Long memberId);

}
