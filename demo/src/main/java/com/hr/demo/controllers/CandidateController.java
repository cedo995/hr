package com.hr.demo.controllers;

import java.util.Collection;

import com.hr.demo.models.Candidate;
import com.hr.demo.models.Skill;
import com.hr.demo.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/candidates")
@Api(tags = {"Candidate CRUD"})
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    // get all
    @GetMapping("")
    @ApiOperation(value = "get all candidates")
    public Collection<Candidate> getAll(){
        return candidateService.getAll();
    }

    // get one by id
    @GetMapping("{id}")
    @ApiOperation(value = "get candidate by id")
    public Candidate getOne(@PathVariable("id") Integer id){
        return candidateService.getOne(id);
    }

    // get all by skills
    @GetMapping("findBySkills")
    @ApiOperation(value = "get candidates by skills (returns candidates which have the input skills)")
    public Collection<Candidate> getBySkills(@RequestParam Collection<Skill> skills){
        return candidateService.getBySkills(skills);
    }

    // get all by name
    @GetMapping("findByName")
    @ApiOperation(value = "get candidates by name")
    public Collection<Candidate> getByName(@RequestParam String name){
        return candidateService.getByName(name);
    }

    // insert
    @CrossOrigin
    @PostMapping("")
    @ApiOperation(value = "insert candidate")
    public ResponseEntity<Candidate> insert(@RequestBody Candidate obj){
        if (candidateService.insert(obj) != null) {
            return new ResponseEntity<Candidate> (obj, HttpStatus.OK);
        } else {
            return new ResponseEntity<Candidate> (HttpStatus.NO_CONTENT);
        }
    }

    // update
    @CrossOrigin
    @PutMapping("")
    @ApiOperation(value = "update candidate")
    public ResponseEntity<Candidate> update(@RequestBody Candidate obj){
        if (candidateService.update(obj) != null) {
            return new ResponseEntity<Candidate> (obj, HttpStatus.OK);
        } else {
            return new ResponseEntity<Candidate> (HttpStatus.NO_CONTENT);
        }
    }

    // delete
    @CrossOrigin
    @DeleteMapping("{id}")
    @ApiOperation(value = "delete candidate")
    public ResponseEntity<Candidate> delete(@PathVariable("id") Integer id){
        if (candidateService.delete(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}