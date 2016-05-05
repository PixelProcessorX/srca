-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: rcdb
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `cats_event`
--

DROP TABLE IF EXISTS `cats_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cats_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_fit` int(11) DEFAULT NULL,
  `Name` varchar(45) COLLATE utf8_unicode_ci DEFAULT 'Created_EventCategory',
  `Description` varchar(256) COLLATE utf8_unicode_ci DEFAULT 'Description Not Provided.',
  `Tags` varchar(256) COLLATE utf8_unicode_ci DEFAULT 'EMPTY|NULL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Types of Rec Center events.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cats_event`
--

LOCK TABLES `cats_event` WRITE;
/*!40000 ALTER TABLE `cats_event` DISABLE KEYS */;
INSERT INTO `cats_event` VALUES (1,1,'Wieghtlifting','Lifting of various kinds.','Weights|Dumbells|lbs'),(2,1,'Movement excecises.','...','Flexibility|Muscles|Etc'),(3,2,'Typist\'s Challenges','Try your hand at extreme typing (as if!!)','Keyboards|Mice|Flexible_Fingers'),(4,2,'Slip N Grip','Various pulling related excecises.','Weights|Pull|Grab'),(5,3,'Heart Healthy','Excecises meant to improve heart health.','Heart|Endurance|<3'),(6,3,'Lung Excercises','Various roundabout ways of improving breathing.','Lungs|Oxygen_Intake|Endurance');
/*!40000 ALTER TABLE `cats_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cats_fit`
--

DROP TABLE IF EXISTS `cats_fit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cats_fit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET utf8 DEFAULT 'Created_FitnessType',
  `Description` varchar(256) COLLATE utf8_unicode_ci DEFAULT 'No Description Provided.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cats_fit`
--

LOCK TABLES `cats_fit` WRITE;
/*!40000 ALTER TABLE `cats_fit` DISABLE KEYS */;
INSERT INTO `cats_fit` VALUES (1,'Muscles','Improves muscle areas of the body.'),(2,'Ends','Improves hands etc.'),(3,'Power & Endurance','Improves body capacity for performing tasks.');
/*!40000 ALTER TABLE `cats_fit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cats_impr`
--

DROP TABLE IF EXISTS `cats_impr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cats_impr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_fit` int(11) DEFAULT '0',
  `Name` varchar(45) COLLATE utf8_unicode_ci DEFAULT 'Created_BodyImprovementItem',
  `Description` varchar(256) CHARACTER SET utf8 DEFAULT 'No Description Provided',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cats_impr`
--

LOCK TABLES `cats_impr` WRITE;
/*!40000 ALTER TABLE `cats_impr` DISABLE KEYS */;
INSERT INTO `cats_impr` VALUES (1,1,'Muscles, Arm','Improves lifting performance.'),(2,1,'Muscles, Leg','Improves stride power (endurance).'),(3,3,'Caridovascular','Improves heart heath through excecise.'),(4,1,'Abdominal Muscles','I have no idea what this does.'),(5,2,'Hands, Power','Improves grip strength.'),(6,2,'Fingers','12345 Helps Typing Passwords.');
/*!40000 ALTER TABLE `cats_impr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events_`
--

DROP TABLE IF EXISTS `events_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cat` int(11) DEFAULT '0',
  `id_instructor` int(11) DEFAULT NULL,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Rec Center Event0000',
  `Description` varchar(256) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Description Not Provided. Please Contact Rec Center Administrator.',
  `WhenDay` varchar(256) CHARACTER SET utf8 DEFAULT 'Not Defined',
  `WhenBeg` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `WhenEnd` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `CostCents` int(11) DEFAULT '0',
  `HtmlLink` varchar(4096) CHARACTER SET utf8 DEFAULT 'http://address.undefined.in_database',
  `CalendarID` varchar(1025) CHARACTER SET utf8 DEFAULT 'EMPTY',
  `Status` varchar(45) COLLATE utf8_unicode_ci DEFAULT 'Event Exists in Quantum Black Hole.',
  `CalendarLastUpd` datetime DEFAULT '1900-01-01 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events_`
--

LOCK TABLES `events_` WRITE;
/*!40000 ALTER TABLE `events_` DISABLE KEYS */;
INSERT INTO `events_` VALUES (1,1,NULL,'Event #0','Junk data...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',123,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(2,5,NULL,'Event #1','Junk data dfghbdrst...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',234,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(3,3,NULL,'Event #2','adryhsxtfhbta...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',345,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(4,1,NULL,'Event #3','Juszdrtbyzdthbfghfgha...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',456,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(5,2,NULL,'Event #4','Aced Acrobatics...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',777,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(6,6,NULL,'Event #5','74785675667...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',369,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(7,1,NULL,'Event #6','Testingonetwothree...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',1111,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(8,4,NULL,'Event #7','BigExcercise...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',4444,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(9,1,NULL,'Event #8','Pascal\'s Plethora...','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',12421,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00'),(10,3,NULL,'Event #9','Failed Doomsday','Not Defined','1900-01-01 00:00:00','1900-01-01 00:00:00',2012,'http://address.undefined.in_database','EMPTY','Event Exists in Quantum Black Hole.','1900-01-01 00:00:00');
/*!40000 ALTER TABLE `events_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userevents`
--

DROP TABLE IF EXISTS `userevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userevents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL DEFAULT '-1',
  `id_event` int(11) NOT NULL DEFAULT '-1',
  `IsPrivateListing` bit(1) DEFAULT b'1',
  `IsRegistered` bit(1) DEFAULT b'0',
  `DefinedName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Notify` bit(1) DEFAULT b'0',
  `NotifyWhen` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `X_idx` (`id_user`),
  KEY `Y_idx` (`id_event`),
  CONSTRAINT `X` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Y` FOREIGN KEY (`id_event`) REFERENCES `events_` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userevents`
--

LOCK TABLES `userevents` WRITE;
/*!40000 ALTER TABLE `userevents` DISABLE KEYS */;
INSERT INTO `userevents` VALUES (1,5,8,'','\0','Quartz Task #0','\0','1900-01-01 00:00:00'),(2,5,9,'','\0','Quartz Task #1','\0','1900-01-01 00:00:00'),(3,5,5,'','\0','Quartz Task #2','\0','1900-01-01 00:00:00'),(4,3,7,'','\0','myEventNofification','\0','1900-01-01 00:00:00'),(5,2,2,'','\0','Yoga Class','\0','1900-01-01 00:00:00');
/*!40000 ALTER TABLE `userevents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IsStaffMember` bit(1) NOT NULL DEFAULT b'0',
  `IsInstructor` bit(1) NOT NULL DEFAULT b'0',
  `GoogleID` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GoogleEmail` varchar(256) COLLATE utf8_unicode_ci DEFAULT '',
  `Username` varchar(45) CHARACTER SET utf8 DEFAULT 'Anonymous User',
  `LoginAuthDB` varbinary(4096) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='The users in the database. Contains a minimum of information required to authenticate.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'\0','\0',NULL,'','Jhon Doe','54dc9647601761f0791d1217ce3483e9'),(2,'\0','\0',NULL,'','Jane Doe','9320789ed7fbb2fcef85804ece29abb0'),(3,'\0','\0',NULL,'','Dr. Deqan Delinquist','d2203000ac404b8878a29059e53621b6'),(4,'\0','\0',NULL,'','Fake Oracle America Rep.','cfd70644fa67581a06daca5dba5437c7'),(5,'\0','\0',NULL,'','Quartztet Qulaervich','d9d8611e8dec8e4b9e00751140c01ab9');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'rcdb'
--

--
-- Dumping routines for database 'rcdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `DeleteLoggedInUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteLoggedInUser`(
IN user_id INT(11), 
IN login_token VARCHAR(4096))
BEGIN
# This enforces database security by requiring that the application know (over SSL)
# what the randomly generated security token for the user is. Only the logged in user can 
# read or know it, and it cannot be accessed otherwise unless from an admin account.
SELECT LoginAuthDB INTO @pAUTH FROM users WHERE users.id = user_id; # THIS DOESN'T RETURN A RESULTSET!!!
IF @pAUTH = login_token THEN 
	UPDATE events_ set id_instructor = NULL WHERE events_.id_instructor = user_id; # No invalid instructors!!
	DELETE FROM userevents WHERE userevents.id = user_id; # Remove a user's registered events.
	DELETE FROM users WHERE users.id = user_id; # Finally, delete the user.
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeleteLoggedInUserEvent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteLoggedInUserEvent`(
IN user_event_id INT(11), 
IN user_id INT(11), 
IN login_token VARCHAR(4096))
BEGIN
# This enforces database security by requiring that the application know (over SSL)
# what the randomly generated security token for the user is. Only the logged in user can 
# read or know it, and it cannot be accessed otherwise unless from an admin account.
SELECT LoginAuthDB INTO @pAUTH FROM users WHERE users.id = user_id; # THIS DOESN'T RETURN A RESULTSET!!!
IF @pAUTH = login_token THEN 
	DELETE FROM userevents WHERE userevents.id = user_event_id;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeleteLoggedInUserEvents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteLoggedInUserEvents`(
IN user_id INT(11), 
IN login_token VARCHAR(4096))
BEGIN
# This enforces database security by requiring that the application know (over SSL)
# what the randomly generated security token for the user is. Only the logged in user can 
# read or know it, and it cannot be accessed otherwise unless from an admin account.
SELECT LoginAuthDB INTO @pAUTH FROM users WHERE users.id = user_id; # THIS DOESN'T RETURN A RESULTSET!!!
IF @pAUTH = login_token THEN 
	DELETE FROM userevents WHERE userevents.id_user = user_id;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetCatsEvent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCatsEvent`(
IN p_cat_id INT(11), 
IN p_cat_impr_id INT(11), 
IN p_cat_fit_id INT(11), 
IN p_name_contains VARCHAR(45), 
IN p_desc_contains VARCHAR(45), 
IN p_tags_contains VARCHAR(45))
BEGIN
if(p_cat_id = -1) then set p_cat_id = NULL; END IF;
if(p_cat_fit_id = -1) then set p_cat_fit_id = NULL; END IF;
if(p_cat_impr_id = -1) then set p_cat_impr_id = NULL; END IF;
if(p_name_contains = "NULL") then set p_name_contains = NULL; END IF;
if(p_desc_contains = "NULL") then set p_desc_contains = NULL; END IF;
if(p_tags_contains = "NULL") then set p_tags_contains = NULL; END IF;
SELECT DISTINCT cats_event.* FROM cats_event, cats_fit, cats_impr WHERE 
	(p_cat_id IS NULL OR p_cat_id = cats_event.id) AND 
	(p_cat_fit_id IS NULL OR p_cat_fit_id = cats_fit.id) AND
	(p_cat_impr_id IS NULL OR p_cat_impr_id = cats_impr.id) AND
	((p_name_contains IS NULL) OR (cats_event.`Name`        LIKE CONCAT('%',p_name_contains,'%'))) AND
	((p_desc_contains IS NULL) OR (cats_event.`Description` LIKE CONCAT('%',p_desc_contains,'%'))) AND
	((p_tags_contains IS NULL) OR (cats_event.`Tags`        LIKE CONCAT('%',p_tags_contains,'%')));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetCatsFitness` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCatsFitness`(
IN p_cat_id INT(11), 
IN p_cat_impr_id INT(11), 
IN p_name_contains VARCHAR(45), 
IN p_desc_contains VARCHAR(45))
BEGIN
if(p_cat_id = -1) then set p_cat_id = NULL; END IF;
if(p_cat_impr_id = -1) then set p_cat_impr_id = NULL; END IF;
if(p_name_contains = "NULL") then set p_name_contains = NULL; END IF;
if(p_desc_contains = "NULL") then set p_desc_contains = NULL; END IF;
SELECT DISTINCT cats_fit.* FROM cats_fit, cats_impr WHERE 
	(p_cat_id IS NULL OR p_cat_id = cats_fit.id) AND 
	(p_cat_impr_id IS NULL OR p_cat_impr_id = cats_impr.id) AND 
	(p_name_contains IS NULL OR cats_fit.`Name` LIKE CONCAT('%',p_name_contains COLLATE utf8_unicode_ci,'%')) AND
	(p_desc_contains IS NULL OR cats_fit.`Description` LIKE CONCAT('%',p_desc_contains COLLATE utf8_unicode_ci,'%'));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetCatsImprovement` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCatsImprovement`(
IN p_cat_id INT(11), 
IN p_name_contains VARCHAR(45), 
IN p_desc_contains VARCHAR(45))
BEGIN
if(p_cat_id = -1) then set p_cat_id = NULL; END IF;
if(p_name_contains = "NULL") then set p_name_contains = NULL; END IF;
if(p_desc_contains = "NULL") then set p_desc_contains = NULL; END IF;
SELECT DISTINCT cats_impr.* FROM cats_impr WHERE 
	(p_cat_id IS NULL OR p_cat_id = cats_impr.id) AND 
	(p_name_contains IS NULL OR cats_impr.`Name` LIKE CONCAT('%',p_name_contains COLLATE utf8_unicode_ci,'%')) AND
	(p_desc_contains IS NULL OR cats_impr.`Description` LIKE CONCAT('%',p_desc_contains COLLATE utf8_unicode_ci,'%'));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetEvents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetEvents`()
BEGIN
SELECT DISTINCT events_.* FROM events_ WHERE true;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetEventsWhere` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetEventsWhere`(
IN p_id INT, 
IN p_category INT, 
IN p_instructor INT, 
IN p_name_contains VARCHAR(45), 
IN p_whenbeg_min DATETIME, 
IN p_whenbeg_max DATETIME, 
IN p_whenend_min DATETIME, 
IN p_whenend_max DATETIME, 
IN p_cost_min INT, 
IN p_cost_max INT, 
IN p_fitness_cat INT, 
IN p_improve_cat INT)
BEGIN
if(p_id = -1) then set p_id = NULL; END IF;
if(p_name_contains = "NULL") then set p_name_contains = NULL; END IF;
if(p_category = -1) then set p_category = NULL; END IF;
if(p_instructor = -1) then set p_instructor = NULL; END IF;
if(p_name_contains = "NULL") then set p_name_contains = NULL; END IF;
#The DATETIME fields CANNOT be nulled, as the values can be any time value (they form a range).
#The events_.CostCents fields also have this property of no-nulls. However NULL can be passed.
if(p_fitness_cat = -1) then set p_fitness_cat = NULL; END IF;
if(p_improve_cat = -1) then set p_improve_cat = NULL; END IF;
SELECT DISTINCT events_.* FROM events_, cats_event, cats_fit, cats_impr WHERE
	(p_id IS NULL OR  events_.id = p_id) AND
	(p_category IS NULL OR cats_event.id = p_category) AND 
	(p_instructor IS NULL OR events_.id_instructor =  p_instructor) AND 
	(p_name_contains IS NULL OR events_.`Name` LIKE CONCAT('%',p_name_contains COLLATE utf8_unicode_ci,'%')) AND 
	(events_.WhenBeg IS NULL OR p_whenbeg_min IS NULL OR events_.WhenBeg >= p_whenbeg_min) AND 
	(events_.WhenBeg IS NULL OR p_whenbeg_max IS NULL OR events_.WhenBeg <= p_whenbeg_max) AND 
	(events_.WhenEnd IS NULL OR p_whenend_min IS NULL OR events_.WhenEnd >= p_whenend_min) AND 
	(events_.WhenEnd IS NULL OR p_whenend_max IS NULL OR events_.WhenEnd <= p_whenend_max) AND 
	(p_cost_min IS NULL OR events_.CostCents >= p_cost_min) AND 
	(p_cost_max IS NULL OR events_.CostCents <= p_cost_max) AND 
	(p_fitness_cat IS NULL OR cats_fit.id = p_fitness_cat) AND 
	(p_improve_cat IS NULL OR cats_impr.id = p_improve_cat);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetUserEvents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetUserEvents`(
IN p_user_id INT(11),
IN login_token VARCHAR(4096))
BEGIN
SELECT LoginAuthDB INTO @pAUTH FROM users WHERE users.id = p_user_id; # THIS DOESN'T RETURN A RESULTSET!!!
IF @pAUTH = login_token THEN 
	#Cannot arbitrarily return all userevents, must be user limited.
	SELECT DISTINCT userevents.* FROM users, events_, userevents WHERE (p_user_id = userevents.id_user);
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `LoginFunction` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `LoginFunction`(IN auth_googleid VARCHAR(4096))
BEGIN
SELECT DISTINCT users.* FROM users WHERE users.GoogleID = auth_googleid LIMIT 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `NewUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `NewUser`(
IN p2 VARCHAR(4096),
IN p3 VARCHAR(256),
IN p5 VARCHAR(45))
BEGIN
SET @P6 =  CONCAT(MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()),
				  MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()),
				  MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()),
				  MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()),
				  MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()),
				  MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()),
				  MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()),
				  MD5(RAND()),MD5(RAND()),MD5(RAND()),MD5(RAND()));
INSERT INTO users (
users.IsStaffMember, 
users.IsInstructor, 
users.GoogleID, 
users.GoogleEmail, 
users.Username,
users.LoginAuthDB) 
VALUES (0, 0, p2, p3, p5, @P6);
SELECT DISTINCT users.* FROM users WHERE users.LoginAuthDB = @P6 LIMIT 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `NewUserEvent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `NewUserEvent`(
IN p0 INT(11), 
IN p1 INT(11), 
IN p2 BIT, 
IN p3 BIT, 
IN p3x VARCHAR(45), 
IN p4 BIT, 
IN p5 DATETIME,
IN pID INT(11), 
IN login_token VARCHAR(4096))
BEGIN
# This enforces database security by requiring that the application know (over SSL)
# what the randomly generated security token for the user is. Only the logged in user can 
# read or know it, and it cannot be accessed otherwise unless from an admin account.
SELECT LoginAuthDB INTO @pAUTH FROM users WHERE users.id = pID; # THIS DOESN'T RETURN A RESULTSET!!!
IF @pAUTH = login_token THEN 
	INSERT INTO userevents (
	userevents.id_user, 
	userevents.id_event, 
	userevents.IsPrivateListing, 
	userevents.IsRegistered,
	userevents.DefinedName, 
	userevents.Notify,
	userevents.NotifyWhen) 
	VALUES (p0, p1, p2, p3, p3x, p4, p5);
END IF;
SELECT DISTINCT userevents.* FROM userevents WHERE userevents.id_user = p0 AND userevents.id_event = p1 LIMIT 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-04 23:09:07
