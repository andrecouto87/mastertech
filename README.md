# CHALLENGE MASTERTECH - The Point System Electronic

This challenge consists of developing a project that provides some APIs for the user's registration in the electronic point system.

In this challenge, the following technologies were used:
* Java 1.8
* Spring Boot
* Spring Data
* mySql 8

# Database Structure

To create the tables, the scripts are shown below.

## User Table
```SQL
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `registration_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

## Electronic Point Control System Table
```SQL
CREATE TABLE `tb_electronic_point_control` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint DEFAULT NULL,
  `point_record_date` datetime DEFAULT NULL,
  `point_record_type` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60nxdvtq7wpt42am82t9ogvyv` (`id_user`),
  CONSTRAINT `FK60nxdvtq7wpt42am82t9ogvyv` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;