IF EXISTS(select * from sys.databases where name='task1db')
DROP DATABASE task1db;

CREATE DATABASE task1db;
CREATE USER 'user1'@'localhost' IDENTIFIED BY 'pass1';
GRANT ALL ON task1db.* TO 'user1'@'localhost';

CREATE TABLE `task1db`.`departments` (
  `name` VARCHAR(100) NOT NULL,
  `info` VARCHAR(128) NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

  CREATE TABLE `task1db`.`employees` (
  `email` VARCHAR(64) NOT NULL,
  `name` VARCHAR(64) NULL,
  `birthday` DATE NULL,
  `room` INT NULL,
  `department` VARCHAR(64) NULL,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_employees_idx` (`department` ASC),
  CONSTRAINT `fk_employees`
    FOREIGN KEY (`department`)
    REFERENCES `task1db`.`departments` (`name`)
    ON DELETE RESTRICT
ON UPDATE CASCADE);
use task1db;
