package com.hr.demo.services;

import java.util.Collection;

import com.hr.demo.models.Skill;
import com.hr.demo.repos.CandidateRepo;
import com.hr.demo.repos.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SkillService {
    @Autowired
    private SkillRepo skillRep;

    @Autowired
    private CandidateRepo candidateRep;

    public Collection<Skill> getAll(){
        return skillRep.findAll();
    }

    public Skill getOne(Integer id) {
        if (skillRep.getOne(id) != null) {
            return skillRep.getOne(id);
        } else {
            throw new RuntimeException("Skill with ID:"+id+" not found");
        }
    }

    public Collection<Skill> getWhichCandidateDoesntHave(int id){
        Collection<Skill> skillsToReturn = skillRep.findAll();
        skillsToReturn.removeAll(candidateRep.getOne(id).getSkills());
        return skillsToReturn;
    }

    public Skill insert(Skill skill) {
        if (!skillRep.existsById(skill.getId())) {
            return skillRep.save(skill);
        } else {
            throw new RuntimeException("Skill already exists");
        }
    }

    public Skill update(Skill skill) {
        if (skillRep.getOne(skill.getId()) != null) {
            return skillRep.save(skill);
        } else {
            throw new RuntimeException("Skill does not exist");
        }
    }

    public int delete(Integer id) {
        if(skillRep.existsById(id) == true) {
            skillRep.deleteById(id);
            return 1;
        } else {
            return -1;
        }
    }
}