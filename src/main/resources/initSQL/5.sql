SELECT company_id, customer_id, projects.id, min(cost*companies_projects.share/100) COST FROM projects
  INNER JOIN (companies_projects
    INNER JOIN customers_projects
      ON companies_projects.project_id = customers_projects.project_id)
    ON projects.id = companies_projects.project_id
GROUP BY company_id ;
#ORDER BY COST ASC ;
