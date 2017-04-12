
SELECT  projects.name, sum(developers.salary) s
FROM projects
  JOIN projects_developers ON projects.id = projects_developers.project_id
  JOIN developers on projects_developers.developer_id = developers.id
GROUP BY projects.name
ORDER BY s DESC LIMIT 0,1;

