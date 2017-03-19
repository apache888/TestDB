SELECT companies.name company, customers.name customer FROM companies, customers
  WHERE customers.id_project = companies.id_project AND projects.cost = min(projects.cost);

