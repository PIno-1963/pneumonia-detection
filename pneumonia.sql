CREATE DATABASE  IF NOT EXISTS `pneumonia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pneumonia`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pneumonia
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `iddoctors` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`iddoctors`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'doctor1@example.com','password1','Dr. John Doe'),(2,'doctor2@example.com','password2','Dr. Jane Smith'),(3,'doctor3@example.com','password3','Dr. Bob Johnson');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `google_drive_link` varchar(45) DEFAULT NULL,
  `doctor_id` varchar(45) DEFAULT NULL,
  `Checking` int DEFAULT NULL,
  `prescription` text,
  `symptoms` text,
  `model_val` int DEFAULT NULL,
  `age` int DEFAULT NULL,
  `xray_image_path` varchar(255) DEFAULT NULL,
  `ai_result` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'omar','sif','omar@gmail.com','$2a$10$vomqZWUTNca1yZXIsYU6rOpoodWHM4HypRdBIgUbedjffu6ak24Na','fefa','fefa','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'saad','adad','dafeazf@gmail.com','$2a$10$uk4lKC3A0fXY4evYvese7OLWJ5qAxruuhrAiwhLkfAYZqxd9N6EPq','fezfa','fzefaz','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'faezf','feazf','omar@gmail.com','$2a$10$Ww26QDhpNwe4hkmSTcFN9O4nzKViEeTmeZ4CDc5va8.w3DP4b9SWC','faezfa','fazef','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'omara','efaz','efaf','$2a$10$xgk1ymtMKwdCdgir1mzjtOH36JC.adRn4JVopzIG7m30yusKHPm3y','feaz','fae','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'opze','fgdg','sif@gmail.com','$2a$10$.1NkmzW7V8LwJfZow1Pip.wqc2QsIuBfsD1jckiBEbElsjFzSdpeq','HKG','VJHBHJ','1',NULL,'uvjvkjolb','frissons,difficultes_respirer',0,12,'C:\\Users\\hp\\Desktop\\image db\\br1.png','no pneumonia'),(6,'kvhkb','khku','igiv@gmail.com','$2a$10$8C6LcU69j7iMn9Qqm336kOae9CUdVNjjWmn0614zSZEmNhB8nL7ze','bjkhb','uohuij','1',1,'hb jb','toux,difficultes_respirer',NULL,13,'C:\\Users\\hp\\Desktop\\image db\\br1.png','no pneumonia');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-26  0:13:34
