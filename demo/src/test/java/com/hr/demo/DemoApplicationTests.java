package com.hr.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import com.hr.demo.models.Candidate;
import com.hr.demo.models.CandidateSkill;
import com.hr.demo.models.Skill;
import com.hr.demo.repos.CandidateRepo;
import com.hr.demo.repos.CandidateSkillRepo;
import com.hr.demo.repos.SkillRepo;
import com.hr.demo.services.CandidateService;
import com.hr.demo.services.CandidateSkillService;
import com.hr.demo.services.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Mock
	private CandidateRepo candidateRep;
	@Mock
	private SkillRepo skillRep;
	@Mock
	private CandidateSkillRepo candidateSkillRep;

	@InjectMocks
	private CandidateService candidateService;
	@InjectMocks
	private SkillService skillService;
	@InjectMocks
	private CandidateSkillService candidateSkillService;

	private ArrayList<Candidate> candidates = new ArrayList<>();
	private ArrayList<Skill> skills = new ArrayList<>();
	private ArrayList<CandidateSkill> candidateSkills = new ArrayList<>();

	@Before
	public void init() {
		Candidate c1 = new Candidate(1,"1532523463464", new Date() , " djoledelic@gmail.com","Djole",new ArrayList<>());
		Candidate c2 = new Candidate(2,"1532565465474", new Date() , " Fico@gmail.com","Filip Visnjic",new ArrayList<>());

		Skill s1 = new Skill(1, "Java programming");
		Skill s2 = new Skill(2, "C++ programming");
		Skill s3 = new Skill(3, "Database design");

		CandidateSkill cs1 = new CandidateSkill(1,c1,s1);
		CandidateSkill cs2 = new CandidateSkill(2,c1,s2);
		CandidateSkill cs3 = new CandidateSkill(3,c2,s1);
		CandidateSkill cs4 = new CandidateSkill(4,c2,s3);

		candidates.add(c1);
		candidates.add(c2);
		c1.addSkill(s1);
		c1.addSkill(s2);
		c2.addSkill(s1);
		c2.addSkill(s3);

		skills.add(s1);
		skills.add(s2);
		skills.add(s3);

		candidateSkills.add(cs1);
		candidateSkills.add(cs2);
		candidateSkills.add(cs3);
		candidateSkills.add(cs4);
	}

	// Candidate service tests
	@Test
	public void getAllCandidates() {
		Mockito.when(candidateRep.findAll()).thenReturn(candidates);
		assertThat(candidateService.getAll()).isEqualTo(candidates);
	}

	@Test
	public void getCandidateById() {
		Mockito.when(candidateRep.getOne(1)).thenReturn(candidates.get(0));
		assertThat(candidateService.getOne(1)).isEqualTo(candidates.get(0));
	}

	@Test
	public void getCandidatesByName() {
		Collection <Candidate> candidatesWithNameJohn = new ArrayList <Candidate>();
		candidatesWithNameJohn.add(candidates.get(0));
		Mockito.when(candidateRep.findByNameContainingIgnoreCase("Djole")).thenReturn(candidatesWithNameJohn);
		assertThat(candidateService.getByName("Djole")).isEqualTo(candidatesWithNameJohn);
	}

	@Test
	public void getCandidatesBySkills() {
		List<Skill> skillsParam = new ArrayList<Skill>();
		skillsParam.add(skills.get(0));
		skillsParam.add(skills.get(1));

		List<Candidate> expectedCandidates = new ArrayList<Candidate>();
		expectedCandidates.add(candidates.get(0));

		Mockito.when(candidateRep.findAll()).thenReturn(candidates);
		assertThat(candidateService.getBySkills(skillsParam)).isEqualTo(expectedCandidates);
	}

	@Test
	public void insertCandidate() {
		Mockito.when(candidateRep.save(candidates.get(0))).thenReturn(candidates.get(0));
		assertThat(candidateService.insert(candidates.get(0))).isEqualTo(candidates.get(0));
	}

	@Test
	public void updateCandidate() {
		Candidate updatedCandidate = candidates.get(0);
		Mockito.when(candidateRep.getOne(1)).thenReturn(updatedCandidate);
		updatedCandidate.setEmail("email");
		Mockito.when(candidateRep.save(updatedCandidate)).thenReturn(updatedCandidate);
		assertThat(candidateService.update(updatedCandidate)).isEqualTo(updatedCandidate);
	}

	@Test
	public void deleteCandidateByID() {
		Candidate cand = candidates.get(0);
		Mockito.when(candidateRep.existsById(1)).thenReturn(true);
		assertThat(candidateService.delete(cand.getId())).isEqualTo(1);
	}

	// Skill service tests
	@Test
	public void getAllSkills() {
		Mockito.when(skillRep.findAll()).thenReturn(skills);
		assertThat(skillService.getAll()).isEqualTo(skills);
	}

	@Test
	public void getSkillById() {
		Mockito.when(skillRep.getOne(1)).thenReturn(skills.get(0));
		assertThat(skillService.getOne(1)).isEqualTo(skills.get(0));
	}

	@Test
	public void geSkillstWhichCandidateDoesntHave() {
		List<Skill> skillsCandidateDosentHave = new ArrayList<Skill>();
		skillsCandidateDosentHave.add(skills.get(2));
		Mockito.when(skillRep.findAll()).thenReturn(skills);
		Mockito.when(candidateRep.getOne(1)).thenReturn(candidates.get(0));
		assertThat(skillService.getWhichCandidateDoesntHave(1)).isEqualTo(skillsCandidateDosentHave);
	}

	@Test
	public void insertSkill() {
		Mockito.when(skillRep.save(skills.get(0))).thenReturn(skills.get(0));
		assertThat(skillService.insert(skills.get(0))).isEqualTo(skills.get(0));
	}

	@Test
	public void updateSkill() {
		Skill updatedSkill = skills.get(0);
		Mockito.when(skillRep.getOne(1)).thenReturn(updatedSkill);
		updatedSkill.setName("updateName");
		Mockito.when(skillRep.save(updatedSkill)).thenReturn(updatedSkill);
		assertThat(skillService.update(updatedSkill)).isEqualTo(updatedSkill);
	}

	@Test
	public void deleteSkillByID() {
		Skill skill = skills.get(0);
		Mockito.when(skillRep.existsById(1)).thenReturn(true);
		assertThat(skillService.delete(skill.getId())).isEqualTo(1);
	}


	// Candidate skill service tests
	@Test
	public void getAllCandidateSkills() {
		Mockito.when(candidateSkillRep.findAll()).thenReturn(candidateSkills);
		assertThat(candidateSkillService.getAll()).isEqualTo(candidateSkills);
	}

	@Test
	public void getCandidateSkillById() {
		Mockito.when(candidateSkillRep.getOne(1)).thenReturn(candidateSkills.get(0));
		assertThat(candidateSkillService.getOne(1)).isEqualTo(candidateSkills.get(0));
	}

	@Test
	public void getCandidateSkillByCandidateId() {
		ArrayList<CandidateSkill> candSkills = new ArrayList<>();
		candSkills.add(candidateSkills.get(0));
		candSkills.add(candidateSkills.get(1));
		Mockito.when(candidateSkillRep.findByCandidateId(1)).thenReturn(candSkills);
		assertThat(candidateSkillService.getByCandidateId(1)).isEqualTo(candSkills);
	}

	@Test
	public void insertCandidateSkill() {
		Mockito.when(candidateSkillRep.save(candidateSkills.get(0))).thenReturn(candidateSkills.get(0));
		assertThat(candidateSkillService.insert(candidateSkills.get(0))).isEqualTo(candidateSkills.get(0));
	}

	@Test
	public void deleteCandidateSkillByID() {
		CandidateSkill candidateSkill = candidateSkills.get(0);
		Mockito.when(candidateSkillRep.existsById(1)).thenReturn(true);
		assertThat(candidateSkillService.delete(candidateSkill.getId())).isEqualTo(1);
	}
}