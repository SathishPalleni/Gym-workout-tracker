package com.Fitnes.Gym.Workout.Tracker.repository;

import com.Fitnes.Gym.Workout.Tracker.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
