#建立库
DROP DATABASE yjcocoa;
CREATE DATABASE yjcocoa;
USE yjcocoa;

#建表
CREATE TABLE user (
  id   INT(32) PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(32) NOT NULL UNIQUE,
  name VARCHAR(50) NOT NULL
);

# 查看表结构
DESCRIBE user;

# insert
INSERT user (code, name)
  VALUE ('937447974', '阳君');