package com.Fitnes.Gym.Workout.Tracker.service.impl;

import com.Fitnes.Gym.Workout.Tracker.entity.Member;
import com.Fitnes.Gym.Workout.Tracker.repository.MemberRepository;
import com.Fitnes.Gym.Workout.Tracker.service.MemberService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private static final Logger logger =
            LoggerFactory.getLogger(MemberServiceImpl.class);

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMembers() {
        logger.info("Fetching all members from database");

        return memberRepository.findAll();
    }


}
