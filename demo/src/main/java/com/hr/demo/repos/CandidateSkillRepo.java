package com.hr.demo.repos;

import com.hr.demo.models.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CandidateSkillRepo extends JpaRepository<CandidateSkill, Integer> {
    Collection<CandidateSkill> findByCandidateId(int candidate);
}
