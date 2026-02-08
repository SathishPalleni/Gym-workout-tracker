package com.Fitnes.Gym.Workout.Tracker.service.impl;

import com.Fitnes.Gym.Workout.Tracker.dto.WorkoutRequest;
import com.Fitnes.Gym.Workout.Tracker.entity.Member;
import com.Fitnes.Gym.Workout.Tracker.entity.WorkoutLog;
import com.Fitnes.Gym.Workout.Tracker.repository.MemberRepository;
import com.Fitnes.Gym.Workout.Tracker.repository.WorkoutLogRepository;
import com.Fitnes.Gym.Workout.Tracker.service.WorkoutLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutLogServiceImpl implements WorkoutLogService {

    private static final Logger logger =
            LoggerFactory.getLogger(WorkoutLogServiceImpl.class);

    private final WorkoutLogRepository workoutLogRepository;
    private final MemberRepository memberRepository;

    public WorkoutLogServiceImpl(WorkoutLogRepository workoutLogRepository,
                                 MemberRepository memberRepository) {
        this.workoutLogRepository = workoutLogRepository;
        this.memberRepository = memberRepository;
        logger.info("WorkoutLogServiceImpl initialized");
    }

    @Override
    public WorkoutLog createWorkoutLog(WorkoutRequest request) {

        logger.info("Creating workout log for memberId={}", request.getMemberId());

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> {
                    logger.error("Member not found with id={}", request.getMemberId());
                    return new RuntimeException(
                            "Member not found with id: " + request.getMemberId());
                });

        WorkoutLog log = new WorkoutLog();
        log.setMember(member);
        log.setWorkoutDate(request.getWorkoutDate());
        log.setExerciseName(request.getExerciseName());
        log.setSets(request.getSets());
        log.setReps(request.getReps());
        log.setWeight(request.getWeight());
        log.setNotes(request.getNotes());

        WorkoutLog savedLog = workoutLogRepository.save(log);

        return savedLog;
    }

    @Override
    public WorkoutLog updateWorkoutLog(Long id, WorkoutRequest request) {

        logger.info("Updating workout log id={}", id);

        WorkoutLog existingLog = workoutLogRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Workout log not found with id={}", id);
                    return new RuntimeException(
                            "Workout log not found with id: " + id);
                });

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> {
                    logger.error("Member not found with id={}", request.getMemberId());
                    return new RuntimeException(
                            "Member not found with id: " + request.getMemberId());
                });

        existingLog.setMember(member);
        existingLog.setWorkoutDate(request.getWorkoutDate());
        existingLog.setExerciseName(request.getExerciseName());
        existingLog.setSets(request.getSets());
        existingLog.setReps(request.getReps());
        existingLog.setWeight(request.getWeight());
        existingLog.setNotes(request.getNotes());

        WorkoutLog updatedLog = workoutLogRepository.save(existingLog);


        return updatedLog;
    }

    @Override
    public void deleteWorkoutLog(Long id) {

        logger.warn("Deleting workout log id={}", id);

        WorkoutLog existingLog = workoutLogRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Workout log not found with id={}", id);
                    return new RuntimeException(
                            "Workout log not found with id: " + id);
                });

        workoutLogRepository.delete(existingLog);

        logger.info("Workout log deleted successfully id={}", id);
    }

    @Override
    public List<WorkoutLog> getWorkoutLogsByMemberId(Long memberId) {

        logger.info("Fetching workout logs for memberId={}", memberId);

        List<WorkoutLog> logs =
                workoutLogRepository.findByMemberIdOrderByWorkoutDateDesc(memberId);

        logger.info("Found {} workout logs for memberId={}", logs.size(), memberId);

        return logs;
    }

    @Override
    public WorkoutLog getWorkoutLogById(Long id) {

        logger.info("Fetching workout log by id={}", id);

        return workoutLogRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Workout log not found with id={}", id);
                    return new RuntimeException(
                            "Workout log not found with id: " + id);
                });
    }
}
