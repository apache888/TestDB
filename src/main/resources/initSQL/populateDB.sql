INSERT INTO developers (name, experience) VALUES ('Ivanov', 2);
INSERT INTO developers (name, experience) VALUES ('Petrov', 3);
INSERT INTO developers (name, experience) VALUES ('Sidorov', 5);
INSERT INTO developers (name, experience) VALUES ('Baranov', 1);
INSERT INTO developers (name, experience) VALUES ('Sushko', 10);
INSERT INTO developers (name, experience) VALUES ('Radko', 2);
INSERT INTO developers (name, experience) VALUES ('Dzubenko', 8);
INSERT INTO developers (name, experience) VALUES ('Sadko', 2);
INSERT INTO developers (name, experience) VALUES ('Golovko', 4);
INSERT INTO developers (name, experience) VALUES ('Buchak', 3);

INSERT INTO skills (name) VALUES ('Java');
INSERT INTO skills (name) VALUES ('Scala');
INSERT INTO skills (name) VALUES ('C++');
INSERT INTO skills (name) VALUES ('C#');
INSERT INTO skills (name) VALUES ('JS');
INSERT INTO skills (name) VALUES ('PHP');

INSERT INTO projects (name) VALUES ('Game');
INSERT INTO projects (name) VALUES ('Aggregator');
INSERT INTO projects (name) VALUES ('Messenger');
INSERT INTO projects (name) VALUES ('Restaurant');
INSERT INTO projects (name) VALUES ('Tip-Tour');
INSERT INTO projects (name) VALUES ('Online Shop');
INSERT INTO projects (name) VALUES ('Store System');
INSERT INTO projects (name) VALUES ('Tickets-online');

INSERT INTO companies (name) VALUES ('IndyGame');
INSERT INTO companies (name) VALUES ('IT-Solutions');
INSERT INTO companies (name) VALUES ('IT-HORECA');
INSERT INTO companies (name) VALUES ('Gold Soft');

INSERT INTO customers (name) VALUES ('Fire Group');
INSERT INTO customers (name) VALUES ('Athic Mobile');
INSERT INTO customers (name) VALUES ('Speed Mail');
INSERT INTO customers (name) VALUES ('Product Group');

INSERT INTO developers_skills VALUES (1, 1);
INSERT INTO developers_skills VALUES (1, 3);
INSERT INTO developers_skills VALUES (2, 1);
INSERT INTO developers_skills VALUES (2, 3);
INSERT INTO developers_skills VALUES (2, 5);
INSERT INTO developers_skills VALUES (3, 1);
INSERT INTO developers_skills VALUES (3, 2);
INSERT INTO developers_skills VALUES (3, 3);
INSERT INTO developers_skills VALUES (4, 5);
INSERT INTO developers_skills VALUES (4, 6);
INSERT INTO developers_skills VALUES (5, 1);
INSERT INTO developers_skills VALUES (5, 2);
INSERT INTO developers_skills VALUES (5, 3);
INSERT INTO developers_skills VALUES (5, 4);
INSERT INTO developers_skills VALUES (5, 5);
INSERT INTO developers_skills VALUES (5, 6);
INSERT INTO developers_skills VALUES (6, 1);
INSERT INTO developers_skills VALUES (6, 5);
INSERT INTO developers_skills VALUES (7, 5);
INSERT INTO developers_skills VALUES (7, 1);
INSERT INTO developers_skills VALUES (7, 2);
INSERT INTO developers_skills VALUES (7, 3);
INSERT INTO developers_skills VALUES (8, 3);
INSERT INTO developers_skills VALUES (8, 4);
INSERT INTO developers_skills VALUES (8, 5);
INSERT INTO developers_skills VALUES (9, 1);
INSERT INTO developers_skills VALUES (9, 2);
INSERT INTO developers_skills VALUES (9, 3);
INSERT INTO developers_skills VALUES (9, 4);
INSERT INTO developers_skills VALUES (10, 3);
INSERT INTO developers_skills VALUES (10, 4);
INSERT INTO developers_skills VALUES (10, 5);
INSERT INTO developers_skills VALUES (10, 6);

INSERT INTO projects_developers VALUES (1, 1);
INSERT INTO projects_developers VALUES (1, 4);
INSERT INTO projects_developers VALUES (1, 8);
INSERT INTO projects_developers VALUES (2, 2);
INSERT INTO projects_developers VALUES (2, 3);
INSERT INTO projects_developers VALUES (2, 10);
INSERT INTO projects_developers VALUES (3, 3);
INSERT INTO projects_developers VALUES (3, 6);
INSERT INTO projects_developers VALUES (3, 9);
INSERT INTO projects_developers VALUES (4, 3);
INSERT INTO projects_developers VALUES (4, 4);
INSERT INTO projects_developers VALUES (4, 5);
INSERT INTO projects_developers VALUES (4, 7);
INSERT INTO projects_developers VALUES (4, 9);
INSERT INTO projects_developers VALUES (5, 2);
INSERT INTO projects_developers VALUES (5, 7);
INSERT INTO projects_developers VALUES (5, 9);
INSERT INTO projects_developers VALUES (6, 1);
INSERT INTO projects_developers VALUES (6, 3);
INSERT INTO projects_developers VALUES (6, 10);
INSERT INTO projects_developers VALUES (7, 5);
INSERT INTO projects_developers VALUES (7, 7);
INSERT INTO projects_developers VALUES (7, 9);
INSERT INTO projects_developers VALUES (7, 8);
INSERT INTO projects_developers VALUES (8, 1);
INSERT INTO projects_developers VALUES (8, 3);
INSERT INTO projects_developers VALUES (8, 5);
INSERT INTO projects_developers VALUES (8, 10);

INSERT INTO companies_projects VALUES (1, 1, 100);
INSERT INTO companies_projects VALUES (1, 5, 30);
INSERT INTO companies_projects VALUES (2, 5, 70);
INSERT INTO companies_projects VALUES (2, 4, 30);
INSERT INTO companies_projects VALUES (2, 7, 50);
INSERT INTO companies_projects VALUES (2, 2, 100);
INSERT INTO companies_projects VALUES (3, 4, 70);
INSERT INTO companies_projects VALUES (3, 7, 50);
INSERT INTO companies_projects VALUES (4, 3, 100);
INSERT INTO companies_projects VALUES (4, 6, 100);
INSERT INTO companies_projects VALUES (4, 8, 100);

INSERT INTO customers_projects  VALUES (2, 1);
INSERT INTO customers_projects  VALUES (2, 8);
INSERT INTO customers_projects  VALUES (3, 3);
INSERT INTO customers_projects  VALUES (4, 4);
INSERT INTO customers_projects  VALUES (4, 6);
INSERT INTO customers_projects  VALUES (4, 7);
INSERT INTO customers_projects  VALUES (1, 2);
INSERT INTO customers_projects  VALUES (1, 5);

