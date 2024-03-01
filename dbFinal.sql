create database SKYAI_DB;
use SKYAI_DB;
CREATE TABLE Users (
  `username` varchar(25) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `vuelo` varchar(7),
  PRIMARY KEY (`username`),
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
  `country` char(20) NOT NULL,
  PRIMARY KEY (`ICAO_code`),
  KEY `ICAO_code` (`ICAO_code`),
  CONSTRAINT `country_ibfk_1` FOREIGN KEY (`ICAO_code`) REFERENCES `Location` (`ICAO_code`) ON DELETE CASCADE ON UPDATE CASCADE
);
select * from Location;
select * from Fligth;
select * from Users;