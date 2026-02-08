package com.Fitnes.Gym.Workout.Tracker.service;

import com.Fitnes.Gym.Workout.Tracker.dto.WorkoutRequest;
import com.Fitnes.Gym.Workout.Tracker.entity.WorkoutLog;
import java.util.List;
public interface WorkoutLogService {

    void deleteWorkoutLog(Long id);

    WorkoutLog updateWorkoutLog(Long id, WorkoutRequest request);

    WorkoutLog createWorkoutLog(WorkoutRequest request);

    WorkoutLog getWorkoutLogById(Long id);

    List<WorkoutLog> getWorkoutLogsByMemberId(Long memberId);
}
