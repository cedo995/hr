package com.hr.demo.models;
import lombok.*;

import java.io.Serializable;
import javax.persistence.*;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CANDIDATE_ID_GENERATOR",sequenceName="CANDIDATE_SEQ", allocationSize = 1  )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CANDIDATE_ID_GENERATOR")
    private Integer id;

    @Column(name="contact_number")
    private String contactNumber;

    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth")
    private Date dateOfBirth;

    private String email;

    private String name;


    @ManyToMany()
    @JoinTable(
            name = "Candidate_Skill",
            joinColumns = { @JoinColumn(name = "candidate_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    Collection<Skill> skills = new HashSet<>();
    public void addSkill(Skill skill){
        skills.add(skill);
    }

}
