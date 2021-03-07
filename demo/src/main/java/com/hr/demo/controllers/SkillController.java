package com.hr.demo.controllers;

import java.util.Collection;

import com.hr.demo.models.Skill;
import com.hr.demo.services.SkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/skills")
@Api(tags = {"Skill CRUD"})
public class SkillController {
    @Autowired
    private SkillService skillService;

    // get all
    @GetMapping("")
    @ApiOperation(value = "get all skills")
    public Collection<Skill> getAll(){
        return skillService.getAll();
    }

    // get one by id
    @GetMapping("{id}")
    @ApiOperation(value = "get skill by id")
    public Skill getOne(@PathVariable("id") Integer id){
        return skillService.getOne(id);
    }


    // get all which doesn't have candidate with path id
    @GetMapping("findWhichCandidateDoesntHave/{id}")
    @ApiOperation(value = "get skills which candidate doesnt have (returns skills which input cancicate doesnt have)")
    public Collection<Skill> getWhichCandidateDoesntHave(@PathVariable("id") Integer id){
        return skillService.getWhichCandidateDoesntHave(id);
    }

    // insert
    @CrossOrigin
    @PostMapping("")
    @ApiOperation(value = "insert skill")
    public ResponseEntity<Skill> insert(@RequestBody Skill obj){
        if (skillService.insert(obj) != null) {
            return new ResponseEntity<Skill> (obj, HttpStatus.OK);
        } else {
            return new ResponseEntity<Skill> (HttpStatus.NO_CONTENT);
        }
    }

    // update
    @CrossOrigin
    @PutMapping("")
    @ApiOperation(value = "update skill")
    public ResponseEntity<Skill> update(@RequestBody Skill obj){
        if (skillService.update(obj) != null) {
            return new ResponseEntity<Skill> (obj, HttpStatus.OK);
        } else {
            return new ResponseEntity<Skill> (HttpStatus.NO_CONTENT);
        }
    }

    // delete
    @CrossOrigin
    @DeleteMapping("{id}")
    @ApiOperation(value = "delete skill")
    public ResponseEntity<Skill> delete(@PathVariable("id") Integer id){
        if (skillService.delete(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
