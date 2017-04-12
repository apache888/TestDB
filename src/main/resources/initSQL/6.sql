SELECT  projects.name, projects.cost, avg(developers.salary) avg
FROM projects
  JOIN projects_developers ON projects.id = projects_developers.project_id
  JOIN developers ON projects_developers.developer_id = developers.id
GROUP BY projects.name
ORDER BY avg ASC LIMIT 0,1;