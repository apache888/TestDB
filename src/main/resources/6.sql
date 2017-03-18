SELECT avg(salary) FROM developers
WHERE id IN (SELECT id_dev FROM projects WHERE projects.name IN (
  SELECT  projects.name FROM projects /*WHERE cost = min(cost)*/ ORDER BY cost ASC LIMIT 0,1));