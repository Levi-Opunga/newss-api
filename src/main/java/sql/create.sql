CREATE DATABASE washington_post;
\c washington_post;
CREATE TABLE staff(
  ID  SERIAL PRIMARY KEY,
  Name VARCHAR,
  email VARCHAR;
  phone VARCHAR;
  Rank VARCHAR,
  staffRoles VARCHAR,
  dept_id int
);

CREATE TABLE departments(
 id serial PRIMARY KEY,
 name VARCHAR,
 description VARCHAR
);

CREATE TABLE articles(
 id serial PRIMARY KEY,
 title VARCHAR;
 message VARCHAR;
dept_id int
);

