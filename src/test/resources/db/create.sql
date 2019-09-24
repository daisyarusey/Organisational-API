    SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 description VARCHAR,
 total_employees int
 );

CREATE TABLE IF NOT EXISTS employees(
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
role vARCHAR,
department_id int
);

CREATE TABLE IF NOT EXISTS news(
id int PRIMARY KEY auto_increment,
title VARCHAR,
content VARCHAR,
writtenBy VARCHAR,
department_id int
);