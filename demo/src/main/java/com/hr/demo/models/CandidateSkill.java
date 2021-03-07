package com.hr.demo.models;
import lombok.*;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name="candidate_skill")
@NamedQuery(name="CandidateSkill.findAll", query="SELECT c FROM CandidateSkill c")
public class CandidateSkill implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CANDIDATE_SKILL_ID_GENERATOR",sequenceName="CANDIDATE_SKILL_SEQ", allocationSize = 1  )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CANDIDATE_SKILL_ID_GENERATOR")
    private Integer id;

    //bi-directional many-to-one association to Candidate
    @ManyToOne
    private Candidate candidate;

    //bi-directional many-to-one association to Skill
    @ManyToOne
    private Skill skill;



}
