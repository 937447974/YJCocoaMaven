#建立库
DROP DATABASE yjcocoa;
CREATE DATABASE yjcocoa;
USE yjcocoa;

#建表
CREATE TABLE user (
  id   INT(32) PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(32) NOT NULL,
  name VARCHAR(50) NOT NULL
);

#建立从库
DROP DATABASE yjcocoa_salve;
CREATE DATABASE yjcocoa_salve;
USE yjcocoa_salve;

#建表
CREATE TABLE user (
  id   INT(32) PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(32) NOT NULL,
  name VARCHAR(50) NOT NULL
);

# insert
INSERT user (code, name)
  VALUE ('937447974', '阳君');