#CREATE DATABASE it_test_db;

CREATE TABLE it_test_db.developers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE,
  experience INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE it_test_db.skills (
  id INT NOT NULL AUTO_INCREMENT,
  id_dev INT REFERENCES it_test_db.developers (id) NOT NULL,
  specialty VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE it_test_db.projects (
  id INT NOT NULL AUTO_INCREMENT,
  id_dev INT REFERENCES it_test_db.developers (id) NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE it_test_db.companies (
  id INT NOT NULL AUTO_INCREMENT,
  id_project INT REFERENCES it_test_db.projects (id) NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE it_test_db.customers (
  id INT NOT NULL AUTO_INCREMENT,
  id_project INT REFERENCES it_test_db.projects (id) NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));
