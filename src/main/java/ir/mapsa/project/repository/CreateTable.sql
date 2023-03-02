CREATE DATABASE payment_project;
use payment_project;
CREATE TABLE customer (
         id int primary key AUTO_INCREMENT,
        firstName nvarchar(100),
        lastName nvarchar(100),
        age int,
        balance long,
        cardNo nvarchar(19),
        email nvarchar(100),
        cell  char(10)

);

create  table transaction (
        id int primary key AUTO_INCREMENT,
        date Date,
        senderCardNumber nvarchar(19),
        recieverCardNumber nvarchar(19),
        amount Long

);