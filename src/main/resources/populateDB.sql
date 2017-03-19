INSERT INTO developers (name, experience) VALUES ('Ivanov', 2);
INSERT INTO developers (name, experience) VALUES ('Petrov', 3);
INSERT INTO developers (name, experience) VALUES ('Sidorov', 5);
INSERT INTO developers (name, experience) VALUES ('Baranov', 1);
INSERT INTO developers (name, experience) VALUES ('Sushko', 10);
INSERT INTO developers (name, experience) VALUES ('Radko', 2);
INSERT INTO developers (name, experience) VALUES ('Tokovenko', 8);
INSERT INTO developers (name, experience) VALUES ('Sadko', 2);

INSERT INTO skills (id_dev, specialty) VALUES (1, 'Java');
INSERT INTO skills (id_dev, specialty) VALUES (1, 'C++');
INSERT INTO skills (id_dev, specialty) VALUES (1, 'JS');

INSERT INTO skills (id_dev, specialty) VALUES (2, 'JS');
INSERT INTO skills (id_dev, specialty) VALUES (2, 'Java');
INSERT INTO skills (id_dev, specialty) VALUES (2, 'C#');

INSERT INTO skills (id_dev, specialty) VALUES (3, 'Java');
INSERT INTO skills (id_dev, specialty) VALUES (3, 'C++');
INSERT INTO skills (id_dev, specialty) VALUES (3, 'C#');
INSERT INTO skills (id_dev, specialty) VALUES (3, 'JS');
INSERT INTO skills (id_dev, specialty) VALUES (3, 'PHP');

INSERT INTO skills (id_dev, specialty) VALUES (4, 'PHP');
INSERT INTO skills (id_dev, specialty) VALUES (4, 'JS');

INSERT INTO skills (id_dev, specialty) VALUES (5, 'Java');
INSERT INTO skills (id_dev, specialty) VALUES (5, 'C++');
INSERT INTO skills (id_dev, specialty) VALUES (5, 'C#');
INSERT INTO skills (id_dev, specialty) VALUES (5, 'JS');
INSERT INTO skills (id_dev, specialty) VALUES (5, 'PHP');

INSERT INTO skills (id_dev, specialty) VALUES (6, 'PHP');
INSERT INTO skills (id_dev, specialty) VALUES (6, 'JS');
INSERT INTO skills (id_dev, specialty) VALUES (6, 'Java');

INSERT INTO skills (id_dev, specialty) VALUES (7, 'Java');
INSERT INTO skills (id_dev, specialty) VALUES (7, 'C++');
INSERT INTO skills (id_dev, specialty) VALUES (7, 'C#');

INSERT INTO skills (id_dev, specialty) VALUES (8, 'C++');

INSERT INTO projects (id_dev, name) VALUES ( 1, 'Game');
INSERT INTO projects (id_dev, name) VALUES (3, 'Game');
INSERT INTO projects (id_dev, name) VALUES (4, 'Game');
INSERT INTO projects (id_dev, name) VALUES (6, 'Game');

INSERT INTO projects (id_dev, name) VALUES (6, 'Aggregator');
INSERT INTO projects (id_dev, name) VALUES (7, 'Aggregator');
INSERT INTO projects (id_dev, name) VALUES (8, 'Aggregator');

INSERT INTO projects (id_dev, name) VALUES (8, 'Messenger');
INSERT INTO projects (id_dev, name) VALUES (2, 'Messenger');
INSERT INTO projects (id_dev, name) VALUES (5, 'Messenger');
INSERT INTO projects (id_dev, name) VALUES (1, 'Messenger');

INSERT INTO projects (id_dev, name) VALUES (1, 'Restaurant');
INSERT INTO projects (id_dev, name) VALUES (3, 'Restaurant');
INSERT INTO projects (id_dev, name) VALUES (7, 'Restaurant');
INSERT INTO projects (id_dev, name) VALUES (4, 'Restaurant');
INSERT INTO projects (id_dev, name) VALUES (6, 'Restaurant');
INSERT INTO projects (id_dev, name) VALUES (8, 'Restaurant');

INSERT INTO projects (id_dev, name) VALUES (8, 'Tip-Tour');
INSERT INTO projects (id_dev, name) VALUES (4, 'Tip-Tour');
INSERT INTO projects (id_dev, name) VALUES (2, 'Tip-Tour');

INSERT INTO companies (id_project, name) VALUES (
  (SELECT DISTINCT id FROM projects WHERE projects.name = 'Game'), 'IndyGame');
INSERT INTO companies (id_project, name) VALUES (8, 'IndyGame');

INSERT INTO companies (id_project, name) VALUES (5, 'IT-Solutions');
INSERT INTO companies (id_project, name) VALUES (12, 'IT-Solutions');

INSERT INTO companies (id_project, name) VALUES (12, 'IT-HORECA');
INSERT INTO companies (id_project, name) VALUES (18, 'IT-HORECA');

INSERT INTO companies (id_project, name) VALUES (18, 'Gold Soft');
INSERT INTO companies (id_project, name) VALUES (8, 'Gold Soft');

INSERT INTO customers (id_project, name) VALUES (12, 'Fire Group');
INSERT INTO customers (id_project, name) VALUES (5, 'Fire Group');
INSERT INTO customers (id_project, name) VALUES (18, 'Fire Group');
INSERT INTO customers (id_project, name) VALUES (1, 'Athic Mobile');
INSERT INTO customers (id_project, name) VALUES (8, 'Speed Mail');
