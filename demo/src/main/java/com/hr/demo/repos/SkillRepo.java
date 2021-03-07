package com.hr.demo.repos;


import com.hr.demo.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill, Integer> {

}
