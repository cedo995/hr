package com.hr.demo.controllers;

import java.util.Collection;

import com.hr.demo.models.CandidateSkill;
import com.hr.demo.services.CandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/candidateSkills")
@Api(tags = {"Candidate Skill CRUD"})
public class CandidateSkillController {
    @Autowired
    private CandidateSkillService candidateCandidateSkillService;

    // get all
    @GetMapping("")
    @ApiOperation(value = "get all candidate skills")
    public Collection<CandidateSkill> getAll(){
        return candidateCandidateSkillService.getAll();
    }

    // get one by id
    @GetMapping("/{id}")
    @ApiOperation(value = "get candidate skill by id")
    public CandidateSkill getOne(@PathVariable("id") Integer id){
        return candidateCandidateSkillService.getOne(id);
    }

    // get one by id
    @GetMapping("findByCandidate/{id}")
    @ApiOperation(value = "get candidate skill by candidate id")
    public Collection<CandidateSkill> getByCandidate(@PathVariable("id") Integer id){
        return candidateCandidateSkillService.getByCandidateId(id);
    }

    // insert
    @CrossOrigin
    @PostMapping("")
    @ApiOperation(value = "insert candidate skill")
    public ResponseEntity<CandidateSkill> insert(@RequestBody CandidateSkill obj){
        if (candidateCandidateSkillService.insert(obj) != null) {
            return new ResponseEntity<CandidateSkill> (obj, HttpStatus.OK);
        } else {
            return new ResponseEntity<CandidateSkill> (HttpStatus.NO_CONTENT);
        }
    }

    // delete
    @CrossOrigin
    @DeleteMapping("{id}")
    @ApiOperation(value = "delete candidate skill")
    public ResponseEntity<CandidateSkill> delete(@PathVariable("id") Integer id){
        if (candidateCandidateSkillService.delete(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
