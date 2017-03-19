/*SELECT avg(salary) FROM developers
WHERE id IN (SELECT id_dev FROM projects WHERE projects.name = (
  SELECT  projects.name FROM projects ORDER BY cost ASC LIMIT 0,1));*/

SELECT p.name, avg(d.salary) s  FROM developers d, projects p
WHERE d.id = p.id_dev
GROUP BY p.cost
ORDER BY s asc LIMIT 0,1;