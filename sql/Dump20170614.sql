-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: assistant_familial
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `family_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_category_family` (`name`,`family_id`),
  KEY `fk_category_family_idx` (`family_id`),
  CONSTRAINT `fk_category_family` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Santé','#007da6',1),(2,'Transport','#ae5a7c',1),(3,'Education','#ba4e1d',1),(4,'Maison','#007da6',1),(5,'Jardinage','#bf4258',2),(6,'Vacances','#00891d',2);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `profession` varchar(45) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  `family_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contact_family_idx` (`family_id`),
  CONSTRAINT `fk_contact_family` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'Proust','Marcel','0102030405','marcel.proust@laposte.fr','1 rue des malades','Medecin',NULL,2),(2,'Merlin','Lenchanteur','0203040506','lenchanteur.merlin@gmail.com','1 rue kermeliett','Enchanteur','Magicien',2),(3,'Legrand','Pierre','0130304050','pierre.legrand@laposte.net','3 rue de Moscou',NULL,NULL,2),(6,'Disney','Walt','0120304050','walt.disney@usa.com','12 Main Street','Dessinateur',NULL,2),(7,'Pasteur','Louis','0230405060','louis.pasteur@rage.fr','25 rue du Dr Roux','Ophtalmologiste',NULL,2),(8,'Club','Med','0234567890','club.med@clubmed.com','25 route des Vacances','Voyagiste',NULL,2),(9,'Renault','Garage','0223452345','garage.renault@renault.fr','10 rue de la Metallurgie','Garagiste',NULL,2);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `done` tinyint(4) NOT NULL DEFAULT '0',
  `deadline` timestamp NULL DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `family_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `periodicity_value` int(11) DEFAULT '0',
  `periodicity_id` int(11) DEFAULT '1',
  `priority_id` int(11) DEFAULT '1',
  `contact_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `has_child` tinyint(4) DEFAULT '0',
  `comment` varchar(250) DEFAULT NULL,
  `estimated_budget` float DEFAULT NULL,
  `realized_budget` float DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_event_user_idx` (`user_id`),
  KEY `fk_event_category_idx` (`category_id`),
  KEY `fk_event_project_idx` (`project_id`),
  KEY `fk_event_family_idx` (`family_id`),
  KEY `fk_event_periodicity_idx` (`periodicity_id`),
  KEY `fk_event_priority_idx` (`priority_id`),
  KEY `fk_event_contact_idx` (`contact_id`),
  CONSTRAINT `fk_event_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_contact` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_family` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_periodicity` FOREIGN KEY (`periodicity_id`) REFERENCES `periodicity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_priority` FOREIGN KEY (`priority_id`) REFERENCES `priority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'PRV_Ophtalmologiste',0,NULL,NULL,1,1,NULL,2,5,1,NULL,NULL,1,NULL,NULL,NULL,'2017-06-13 08:24:19'),(2,'Médecin',0,NULL,NULL,1,1,NULL,0,1,1,NULL,NULL,0,NULL,NULL,NULL,'2017-06-13 08:24:19'),(3,'PRV_Contrôle technique',0,NULL,NULL,1,2,NULL,2,5,1,NULL,NULL,1,NULL,NULL,NULL,'2017-06-13 08:24:19'),(4,'PRV_Dentiste',0,NULL,NULL,1,1,NULL,1,5,1,NULL,NULL,1,NULL,NULL,NULL,'2017-06-13 08:31:04'),(5,'PRV_Vaccin (Adulte)',0,NULL,NULL,1,1,NULL,10,5,1,NULL,NULL,1,NULL,NULL,NULL,'2017-06-13 11:47:41'),(6,'Anti-puces chats',0,'2017-06-24 22:00:00',1,2,4,NULL,3,4,1,NULL,NULL,0,NULL,NULL,NULL,'2017-06-13 12:01:15'),(7,'Assurance Maison',0,'2017-09-04 22:00:00',1,2,4,NULL,1,5,2,NULL,NULL,0,NULL,NULL,NULL,'2017-06-13 10:49:13'),(8,'Rdv Club Med',0,'2017-07-16 14:00:00',3,2,6,1,0,1,1,8,NULL,0,'Voyage en Grèce',NULL,NULL,'2017-06-13 11:44:18'),(9,'Semer radis roses',0,'2017-06-17 10:00:00',4,2,5,3,2,3,1,NULL,NULL,0,NULL,NULL,NULL,'2017-06-13 11:44:18'),(10,'PRV_Dentiste',0,'2017-07-29 13:30:00',2,2,1,NULL,1,5,1,NULL,NULL,0,'Visite annuelle',NULL,NULL,'2017-06-13 11:47:41'),(11,'Paiement crèche',0,'2017-07-04 22:00:00',2,2,3,NULL,3,4,1,NULL,NULL,0,NULL,NULL,NULL,'2017-06-13 08:31:04'),(12,'PRV_Garagiste',1,'2017-06-14 22:00:00',3,2,2,2,12,4,2,9,NULL,1,'Révision 25 000 KM',NULL,NULL,'2017-06-13 11:59:10'),(13,'Garagiste',0,'2017-07-25 14:00:00',3,2,2,5,0,1,2,9,12,0,'Révision 25 000 KM',250,NULL,'2017-06-13 11:59:10'),(14,'PRV_Ophtalmologiste',0,'2017-06-22 22:00:00',4,2,1,2,2,5,1,7,NULL,1,NULL,NULL,NULL,'2017-06-13 11:59:10');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family`
--

LOCK TABLES `family` WRITE;
/*!40000 ALTER TABLE `family` DISABLE KEYS */;
INSERT INTO `family` VALUES (1,'Admin'),(3,'Duck'),(2,'Team ACDO');
/*!40000 ALTER TABLE `family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodicity`
--

DROP TABLE IF EXISTS `periodicity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodicity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodicity`
--

LOCK TABLES `periodicity` WRITE;
/*!40000 ALTER TABLE `periodicity` DISABLE KEYS */;
INSERT INTO `periodicity` VALUES (5,'Année'),(1,'Aucune'),(2,'Jour'),(4,'Mois'),(3,'Semaine');
/*!40000 ALTER TABLE `periodicity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `priority`
--

DROP TABLE IF EXISTS `priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `priority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priority`
--

LOCK TABLES `priority` WRITE;
/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
INSERT INTO `priority` VALUES (1,'Aucune'),(2,'Important'),(4,'Important et Urgent'),(3,'Urgent');
/*!40000 ALTER TABLE `priority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `family_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_family` (`name`,`family_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_project_family_idx` (`family_id`),
  CONSTRAINT `fk_project_family` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (3,'Carré Potager',2),(4,'Moto',2),(1,'Vacances 2017',2),(5,'Vélo route',2),(2,'Voiture principale',2);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin Familial'),(2,'Bénéficiaire');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `image` varchar(45) DEFAULT NULL,
  `family_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`email`),
  KEY `fk_user_family_idx` (`family_id`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_family` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Aurélie',NULL,'test@gmail.com','test',1,'Animal_03-32x32.png',2,1),(2,'Clément',NULL,'moi@moi.fr','moi',1,'12-32x32.png',2,1),(3,'Didier',NULL,'did.ray@gmail.com','zz',1,'14-32x32.png',2,1),(4,'Olga',NULL,'olga@gmail.com','test',1,'07-32x32.png',2,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-14 13:41:15
