CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` longtext ,
  `launch_date` date NOT NULL,
  `price` double NOT NULL,
  `title` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
);