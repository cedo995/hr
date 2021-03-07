package com.hr.demo.repos;

import com.hr.demo.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CandidateRepo extends JpaRepository<Candidate, Integer> {
    Collection<Candidate> findByNameContainingIgnoreCase(String name);
}
