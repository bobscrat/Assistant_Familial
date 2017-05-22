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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Santé','Green',1),(2,'Transport','Purple',1),(3,'Education','Blue',1),(4,'Vacances','Teal',1),(5,'Jardinage','Brown',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'House',NULL,'0102030405',NULL,'1 rue des malades','Medecin',NULL,1),(2,'Merlin',NULL,'0203040506',NULL,'1 rue kermeliett','Enchanteur',NULL,1);
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
  `comment` varchar(250) DEFAULT NULL,
  `deadline` timestamp NULL DEFAULT NULL,
  `periodicity_value` int(11) DEFAULT NULL,
  `estimated_budget` float DEFAULT NULL,
  `realized_budget` float DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `has_child` tinyint(4) DEFAULT '0',
  `category_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `family_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `periodicity_id` int(11) DEFAULT NULL,
  `priority_id` int(11) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_event_category_idx` (`category_id`),
  KEY `fk_event_project_idx` (`project_id`),
  KEY `fk_event_user_idx` (`user_id`),
  KEY `fk_event_family_idx` (`family_id`),
  KEY `fk_event_periodicity_idx` (`periodicity_id`),
  KEY `fk_event_priority_idx` (`priority_id`),
  KEY `fk_event_contact_idx` (`contact_id`),
  CONSTRAINT `fk_event_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_family` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_contact` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_periodicity` FOREIGN KEY (`periodicity_id`) REFERENCES `periodicity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_priority` FOREIGN KEY (`priority_id`) REFERENCES `priority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Medecin Généraliste',0,NULL,'2017-05-12 16:00:00',NULL,NULL,NULL,9,NULL,1,1,2,1,1,1,NULL,'2017-05-10 18:02:01'),(2,'Ophtalmo',0,NULL,'2017-05-10 16:00:00',NULL,NULL,NULL,NULL,NULL,1,1,2,1,NULL,NULL,NULL,'2017-05-10 18:02:01'),(3,'Dentiste',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,NULL,NULL,NULL,NULL,'2017-05-10 18:02:01'),(4,'Controle technique',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,1,1,NULL,NULL,NULL,NULL,'2017-05-10 18:02:01'),(5,'Assurance',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,1,1,NULL,NULL,NULL,NULL,'2017-05-10 18:02:01'),(6,'Cantine',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,2,2,NULL,NULL,NULL,NULL,'2017-05-10 18:02:01'),(7,'Scolarité',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,2,2,NULL,NULL,NULL,NULL,'2017-05-10 18:02:01'),(8,'Ophtalmo',0,NULL,'2017-06-13 16:00:00',NULL,NULL,NULL,NULL,NULL,1,2,2,NULL,NULL,NULL,NULL,'2017-05-10 18:02:01'),(9,'Rdv Médecin',1,NULL,'2017-05-06 22:00:00',NULL,NULL,NULL,NULL,1,1,1,2,NULL,NULL,2,NULL,'2017-05-10 18:15:57'),(10,'Dentiste',0,NULL,'2017-06-01 16:00:00',NULL,NULL,NULL,NULL,NULL,1,5,3,NULL,NULL,1,NULL,'2017-05-14 15:42:24');
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
INSERT INTO `family` VALUES (1,'Admin'),(3,'Duck'),(2,'Mouse');
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
INSERT INTO `periodicity` VALUES (4,'Année'),(5,'Aucun'),(1,'Jour'),(3,'Mois'),(2,'Semaine');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priority`
--

LOCK TABLES `priority` WRITE;
/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
INSERT INTO `priority` VALUES (2,'Important'),(1,'Important et Urgent'),(3,'Urgent');
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
  KEY `fk_project_family_idx` (`family_id`),
  CONSTRAINT `fk_project_family` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Vacances 2017',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Mickey',NULL,'bob@blabla.fr',NULL,1,NULL,2,1),(2,'Minnie',NULL,NULL,NULL,1,NULL,2,2),(3,'Dingo',NULL,NULL,NULL,1,NULL,2,2),(4,'Pluto',NULL,NULL,NULL,0,NULL,2,2),(5,'Donald',NULL,'donald@duck.fr',NULL,1,NULL,3,1),(6,'Daisy',NULL,'daisy@duck.fr',NULL,1,NULL,3,1);
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

-- Dump completed on 2017-05-22 17:46:51
