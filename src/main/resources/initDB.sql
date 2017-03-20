#CREATE DATABASE IF NOT EXISTS it_test_db;

CREATE TABLE IF NOT EXISTS it_test_db.developers (
  id INT NOT NULL AUTO_INCREMENT,
  name_dev VARCHAR(100) NOT NULL,
  experience INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS it_test_db.skills (
  id INT NOT NULL AUTO_INCREMENT,
  specialty VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS it_test_db.projects (
  id INT NOT NULL AUTO_INCREMENT,
  name_project VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS it_test_db.companies (
  id INT NOT NULL AUTO_INCREMENT,
  name_company VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS it_test_db.customers (
  id INT NOT NULL AUTO_INCREMENT,
  name_customer VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS developers_skills (
  developer_id INT NOT NULL,
  skill_id     INT NOT NULL,
  CONSTRAINT uq_developers_skills UNIQUE (skill_id, developer_id),
  FOREIGN KEY (developer_id) REFERENCES developers (id),
  FOREIGN KEY (skill_id) REFERENCES skills (id));

CREATE TABLE IF NOT EXISTS projects_developers (
  project_id   INT NOT NULL,
  developer_id INT NOT NULL,
  CONSTRAINT uq_projects_developers UNIQUE (developer_id, project_id),
  FOREIGN KEY (project_id) REFERENCES  projects(id),
  FOREIGN KEY (developer_id) REFERENCES developers(id));

CREATE TABLE IF NOT EXISTS companies_projects(
  company_id  INT NOT NULL,
  project_id   INT NOT NULL,
  share INT DEFAULT 100,
  CONSTRAINT uq_companies_projects UNIQUE (company_id, project_id),
  FOREIGN KEY (company_id) REFERENCES companies (id),
  FOREIGN KEY (project_id) REFERENCES projects (id));

CREATE TABLE IF NOT EXISTS customers_projects (
  customer_id  INT NOT NULL,
  project_id   INT NOT NULL,
  CONSTRAINT uq_customers_projects UNIQUE (project_id, customer_id),
  FOREIGN KEY (customer_id) REFERENCES customers (id),
  FOREIGN KEY (project_id) REFERENCES projects (id));
