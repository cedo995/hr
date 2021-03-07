DROP TABLE IF EXISTS skill CASCADE;
DROP TABLE IF EXISTS candidate CASCADE;
DROP TABLE IF EXISTS candidate_skill CASCADE;
DROP SEQUENCE IF EXISTS skill_seq CASCADE;
DROP SEQUENCE IF EXISTS candidate_seq CASCADE;
DROP SEQUENCE IF EXISTS candidate_skill_seq CASCADE;

CREATE TABLE skill(
	id integer NOT NULL,
    name VARCHAR(100) NOT NULL
);


CREATE TABLE candidate(
	id integer NOT NULL,
    name VARCHAR(50) NOT NULL,
	date_of_birth date NOT NULL,
	contact_number VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL
);

CREATE TABLE candidate_skill(
	id integer NOT null,
	candidate_id integer NOT NULL,
	skill_id integer NOT NULL,
	unique(candidate_id, skill_id)
);

ALTER TABLE skill ADD CONSTRAINT PK_Skill
	PRIMARY KEY(id);
ALTER TABLE candidate ADD CONSTRAINT PK_Candidate
	PRIMARY KEY(id);
ALTER TABLE candidate_skill ADD CONSTRAINT PK_Candidate_Skill
	PRIMARY KEY(id);

ALTER TABLE candidate_skill ADD CONSTRAINT FK_Skill_Candidate_Skill
	FOREIGN KEY (skill_id) REFERENCES skill (id) on delete cascade;
ALTER TABLE candidate_skill ADD CONSTRAINT FK_Skill_Candidate_Candidate
	FOREIGN KEY (candidate_id) REFERENCES candidate (id) on delete cascade;

CREATE INDEX IDXFK_Candidate_Skill_Skill
	ON candidate_skill (skill_id);
CREATE INDEX IDXFK_Candidate_Skill_Candidate
	ON candidate_skill (candidate_id);

CREATE SEQUENCE skill_seq
INCREMENT 1;
CREATE SEQUENCE candidate_seq
INCREMENT 1;
CREATE SEQUENCE candidate_skill_seq
INCREMENT 1;



INSERT INTO "skill" ("id",  "name")
VALUES
    (nextval('skill_seq'), 'C++ programming'),
    (nextval('skill_seq'), 'Java programming'),
	(nextval('skill_seq'), 'Data Analysis'),
	(nextval('skill_seq'), 'English language'),
	(nextval('skill_seq'), 'Russian language'),
	(nextval('skill_seq'), 'Spanish language');




INSERT INTO "candidate" ("id","name","date_of_birth","contact_number", "email")
VALUES
    (nextval('candidate_seq'), 'Djordje Delic', to_date('29.09.1995.', 'dd.mm.yyyy.'),'066290995', 'dzolo@gmail.com'),
    (nextval('candidate_seq'), 'Dusan Acimovic', to_date('14.07.1994.', 'dd.mm.yyyy.'),'062123122', 'duckojedini@gmail.com'),
	(nextval('candidate_seq'), 'Filip Jablanovic', to_date('20.03.1995.', 'dd.mm.yyyy.'),'062123123', 'ficox007@gmail.com'),
	(nextval('candidate_seq'), 'Svetlana Raznatovic', to_date('04.04.1978.', 'dd.mm.yyyy.'),'062123124', 'ceca@gmail.com');


INSERT INTO "candidate_skill" ("id","candidate_id", "skill_id")
VALUES
    (nextval('candidate_skill_seq'),1, 1),
    (nextval('candidate_skill_seq'),1, 3),
	(nextval('candidate_skill_seq'),2, 2),
	(nextval('candidate_skill_seq'),2, 3),
	(nextval('candidate_skill_seq'),3, 1),
	(nextval('candidate_skill_seq'),3, 3),
	(nextval('candidate_skill_seq'),3, 4),
	(nextval('candidate_skill_seq'),4, 3);