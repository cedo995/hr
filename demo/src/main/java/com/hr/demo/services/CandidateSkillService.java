package com.hr.demo.services;


import com.hr.demo.models.CandidateSkill;
import com.hr.demo.repos.CandidateSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CandidateSkillService {
    @Autowired
    private CandidateSkillRepo candidateSkillRepository;

    public Collection<CandidateSkill> getAll() {
        return candidateSkillRepository.findAll();
    }

    public Collection<CandidateSkill> getByCandidateId(Integer id) {
        return candidateSkillRepository.findByCandidateId(id);
    }

    public CandidateSkill getOne(Integer id) {
        return candidateSkillRepository.getOne(id);
    }

    public CandidateSkill insert(CandidateSkill candidateSkill) {
        if (!candidateSkillRepository.existsById(candidateSkill.getId())) {
            return candidateSkillRepository.save(candidateSkill);
        } else {
            throw new RuntimeException("Candidate with that Skill exists");
        }
    }

    public int delete(Integer id) {
        if(candidateSkillRepository.existsById(id) == true) {
            candidateSkillRepository.deleteById(id);
            return 1;
        } else {
            return -1;
        }
    }
}