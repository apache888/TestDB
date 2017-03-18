SELECT companies.name company, customers.name customer FROM customers, companies
  WHERE customers.id_project = companies.id_project AND projects.cost = min(projects.cost);