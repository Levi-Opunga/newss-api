SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS staff (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  email VARCHAR,
  phone VARCHAR,
  rank VARCHAR,
  staffRole VARCHAR,
  dept_id int,
  department VARCHAR
);

CREATE TABLE IF NOT EXISTS departments(
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  description VARCHAR
);

CREATE TABLE IF NOT EXISTS articles(
  id int PRIMARY KEY auto_increment,
  title VARCHAR,
  message VARCHAR,
  dept_id int,
  department VARCHAR
);

