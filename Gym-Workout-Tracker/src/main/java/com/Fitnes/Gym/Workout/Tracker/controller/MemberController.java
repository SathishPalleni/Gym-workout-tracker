package com.Fitnes.Gym.Workout.Tracker.controller;

import com.Fitnes.Gym.Workout.Tracker.entity.Member;
import com.Fitnes.Gym.Workout.Tracker.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/member")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        log.info("GET /api/member - Fetching all members");

        try {
            List<Member> members = memberService.getAllMembers();
            log.info("Successfully fetched {} members", members.size());
            return members;
        } catch (Exception e) {
            log.error("Error while fetching members", e);
            throw e;
        }
    }
}
