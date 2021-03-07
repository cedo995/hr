package com.hr.demo.models;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
    private static final long serialVersionUID = 1L;

    public Skill(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @SequenceGenerator(name="SKILL_ID_GENERATOR",sequenceName="SKILL_SEQ", allocationSize = 1  )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SKILL_ID_GENERATOR")
    private Integer id;

    private String name;


    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private Collection<Candidate> candidates = new HashSet<>();


}