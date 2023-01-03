//Uruchom jako root

CREATE DATABASE IF NOT EXISTS `parking_database`;

USE `parking_database`;

CREATE USER 'parking_user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'parking';

GRANT ALL PRIVILEGES ON * . * TO 'parking_user'@'localhost';
