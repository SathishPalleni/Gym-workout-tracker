package com.Fitnes.Gym.Workout.Tracker.controller;

import com.Fitnes.Gym.Workout.Tracker.dto.WorkoutRequest;
import com.Fitnes.Gym.Workout.Tracker.entity.WorkoutLog;
import com.Fitnes.Gym.Workout.Tracker.service.WorkoutLogService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/workouts")
public class WorkoutLogController {

    private static final Logger log =
            LoggerFactory.getLogger(WorkoutLogController.class);

    private final WorkoutLogService workoutLogService;

    public WorkoutLogController(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }

    @GetMapping
    public List<WorkoutLog> getWorkoutsByMemberId(
            @RequestParam Long memberId) {

        log.info("Fetching workouts for memberId={}", memberId);

        List<WorkoutLog> workouts =
                workoutLogService.getWorkoutLogsByMemberId(memberId);

        log.info("Fetched {} workouts for memberId={}",
                workouts.size(), memberId);

        return workouts;
    }

    @GetMapping("/{id}")
    public WorkoutLog getWorkoutLogById(@PathVariable Long id) {

        log.info("Fetching workout with id={}", id);

        WorkoutLog workout =
                workoutLogService.getWorkoutLogById(id);

        log.debug("Workout fetched: {}", workout);

        return workout;
    }

    @PostMapping
    public WorkoutLog createWorkoutLog(
            @Valid @RequestBody WorkoutRequest request) {

        log.info("Creating workout for memberId={}", request.getMemberId());
        log.debug("Workout request payload: {}", request);

        WorkoutLog savedWorkout =
                workoutLogService.createWorkoutLog(request);

        return savedWorkout;
    }

    @PutMapping("/{id}")
    public WorkoutLog updateWorkoutLog(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutRequest request) {

        log.info("Updating workout id={}", id);
        log.debug("Update request payload: {}", request);

        WorkoutLog updatedWorkout =
                workoutLogService.updateWorkoutLog(id, request);

        log.info("Workout updated successfully id={}", id);

        return updatedWorkout;
    }

    @DeleteMapping("/{id}")
    public void deleteWorkoutLog(@PathVariable Long id) {

        log.warn("Deleting workout with id={}", id);

        workoutLogService.deleteWorkoutLog(id);

        log.info("Workout deleted successfully id={}", id);
    }
}
