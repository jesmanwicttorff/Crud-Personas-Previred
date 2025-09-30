# HeidiSQL Dump 
#
# --------------------------------------------------------
# Host:                 127.0.0.1
# Database:             personas_db
# Server version:       10.4.32-MariaDB
# Server OS:            Win64
# Target-Compatibility: MySQL 4.0
# max_allowed_packet:   1048576
# HeidiSQL version:     3.2 Revision: 1129
# --------------------------------------------------------

/*!40100 SET CHARACTER SET latin1*/;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0*/;


#
# Database structure for database 'personas_db'
#

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `personas_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `personas_db`;


#
# Table structure for table 'personas'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ `personas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rut` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion_calle` varchar(255) NOT NULL,
  `direccion_comuna` varchar(255) NOT NULL,
  `direccion_region` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rut` (`rut`),
  UNIQUE KEY `UKk2f5nurjaq15ot26q7954nk8p` (`rut`)
) TYPE=InnoDB AUTO_INCREMENT=7 /*!40100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci*/;



#
# Dumping data for table 'personas'
#

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS*/;
REPLACE INTO `personas` (`id`, `rut`, `nombre`, `apellido`, `fecha_nacimiento`, `direccion_calle`, `direccion_comuna`, `direccion_region`) VALUES
	('2','22222222-2','María','González','1985-03-20','Calle Los Álamos 123','Puente Alto','Metropolitana'),
	('4','44444444-4','Ana','Torres','1995-07-10','Camino del Inca 789','Ñuñoa','Metropolitana'),
	('5','25.828.869-6','Jesman','Wicttorff','2009-02-03','Caracas Valle Vzla','Libertador','Dtt Capital'),
	('6','999999999','Maria Luisa','Turiel','1978-09-19','Caracas','Libertador','Dtt Capital');
/*!40000 ALTER TABLE `personas` ENABLE KEYS*/;
UNLOCK TABLES;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS*/;
