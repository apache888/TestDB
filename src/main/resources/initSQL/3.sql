SELECT sum(salary) AS 'Total Salary' FROM developers WHERE id IN (
  SELECT developer_id FROM developers_skills WHERE skill_id = (
    SELECT id FROM skills WHERE name = 'Java'));