create database SKYAI_DB;
use SKYAI_DB;
CREATE TABLE Users (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100),
    `lastName` varchar(100),
  `username` varchar(25) NOT NULL UNIQUE,
  `password` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `vuelo` varchar(7),
 `tipo_usuario` ENUM('normal', 'admin'),
  KEY `vuelo` (`vuelo`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`vuelo`) REFERENCES Fligth (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Fligth (
  `id` varchar(10) NOT NULL,
  `fromCity` char(25) NOT NULL,
  `toCity` char(25) NOT NULL,
  `STD` timestamp NOT NULL,
  `STA` timestamp NOT NULL,
  `ATD` timestamp,
  `ATA` timestamp,
  `aerolinea` char(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fromCity` (`fromCity`),
  KEY `toCity` (`toCity`),
  CONSTRAINT `fligth_ibfk_1` FOREIGN KEY (`fromCity`) REFERENCES `Location` (`ICAO_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fligth_ibfk_2` FOREIGN KEY (`toCity`) REFERENCES `Location` (`ICAO_code`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Location (
  `ICAO_code` varchar(4) NOT NULL,
  `IATA_code` varchar(3),
  `city` char(20) NOT NULL,
  `city` char(20) NOT NULL,
  `country` char(20) NOT NULL,
  PRIMARY KEY (`ICAO_code`),
  KEY `ICAO_code` (`ICAO_code`),
  CONSTRAINT `country_ibfk_1` FOREIGN KEY (`ICAO_code`) REFERENCES `Location` (`ICAO_code`) ON DELETE CASCADE ON UPDATE CASCADE
);
alter table Users modify column email varchar(50) NOT NULL;
ALTER TABLE Users add column tipo_usuario ENUM('normal', 'admin');
select * from Location;
select * from Fligth;
select * from Users;
#drop table Users;
update Users set tipo_usuario = 'admin' where id = 1;