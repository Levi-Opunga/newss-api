CREATE DATABASE washington_post;
\c washington_post;
CREATE TABLE staff(
ID  SERIAL PRIMARY KEY,
Name VARCHAR,
email VARCHAR;
phone VARCHAR;
Rank VARCHAR,
staffRole VARCHAR,
dept_id int
);

CREATE TABLE departments(6
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

