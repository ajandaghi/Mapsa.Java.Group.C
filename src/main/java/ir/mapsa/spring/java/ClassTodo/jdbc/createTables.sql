CREATE DATABASE jdbc;
SHOW DATABASES;
USE jdbc;

CREATE TABLE student (
                        ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        name NVARCHAR(25),
                        family NVARCHAR(25),
                        passedCourse INT(15),
                        nationalCode CHAR(10) UNICODE,
                        studentId CHAR(10)
);

CREATE TABLE course (
                         ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         name NVARCHAR(25),
                         topic NVARCHAR(25),
                         unit INT(15)

);

