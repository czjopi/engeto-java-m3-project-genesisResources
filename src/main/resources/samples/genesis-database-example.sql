-- Adminer 5.4.1 MySQL 9.5.0 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `person_id` varchar(12) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `person_id` (`person_id`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user` (`id`, `name`, `surname`, `person_id`, `uuid`) VALUES
(1,	'Ferda',	'Mravenec',	'jXa4g3H7oPq2',	'619c4d02-44bf-463b-b2d0-d3e8bc6f2dcf'),
(2,	'Brouk',	'Pytlík',	'yB9fR6tK0wLm',	'ad2b22b9-57ab-4161-b90f-ab5edae00daf'),
(3,	'Beruška',	NULL,	'cN1vZ8pE5sYx',	'208e9abb-8b50-4e65-b175-a0de812a3478');

-- 2026-01-04 21:19:34 UTC