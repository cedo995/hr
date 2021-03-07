package com.hr.demo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hr.demo.models.Candidate;
import com.hr.demo.models.Skill;
import com.hr.demo.repos.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepo candidateRepo;

    public Collection<Candidate> getAll(){
        return candidateRepo.findAll();
    }

    public Candidate getOne(int id) {
        if (candidateRepo.getOne(id) != null) {
            return candidateRepo.getOne(id);
        } else {
            throw new RuntimeException("Candidate with ID:"+id+" not found");
        }
    }

    public Collection<Candidate> getByName(String name) {
        return candidateRepo.findByNameContainingIgnoreCase(name);
    }

    public Collection<Candidate> getBySkills(Collection<Skill> skills) {
        List<Candidate> candidats = candidateRepo.findAll();
        List<Candidate> candidatsForReturn = new ArrayList<Candidate>();
        for(Candidate c : candidats) {
            System.out.println(c.getSkills());
            if(c.getSkills().containsAll(skills))
                candidatsForReturn.add(c);
        }
        return candidatsForReturn;
    }

    public Candidate insert(Candidate candidate) {
        if (!candidateRepo.existsById(candidate.getId())) {
            return candidateRepo.save(candidate);
        } else {
            throw new RuntimeException("Candidate already exists");
        }
    }

    public Candidate update(Candidate candidate) {
        if (candidateRepo.findById(candidate.getId()) != null) {
            return candidateRepo.save(candidate);
        } else {
            throw new RuntimeException("Candidate does not exist");
        }
    }

    public int delete(int id) {
        if(candidateRepo.existsById(id) == true) {
            candidateRepo.deleteById(id);
            return 1;
        } else {
            return -1;
        }
    }
}