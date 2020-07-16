## Part 1: Test it with SQL

## Part 2: Test it with SQL
SELECT techjobs
FROM employer
WHERE location = "St. Louis City";

## Part 3: Test it with SQL

Drop table  job

## Part 4: Test it with SQL

SELECT distinct name, description From Skill
Inner join job_skills on skill.id = job_skills.skills_is
WHERE jobs_id IS NOT NULL
ORDER BY name ASC