-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: nutriplan
-- ------------------------------------------------------
-- Server version	8.0.43
#create database NutriPlan;
#use NutriPlan;

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
-- Table structure for table `actividad_fisica`
--

DROP TABLE IF EXISTS `actividad_fisica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad_fisica` (
  `id_actividad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `factor` decimal(3,2) NOT NULL,
  PRIMARY KEY (`id_actividad`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

#UPDATE actividad_fisica SET factor = 2.2 WHERE id_actividad = 5;
--
-- Dumping data for table `actividad_fisica`
--

LOCK TABLES `actividad_fisica` WRITE;
/*!40000 ALTER TABLE `actividad_fisica` DISABLE KEYS */;
INSERT INTO `actividad_fisica` VALUES (1,'Muy sedentario','Mayor parte del día sentado o de pie; caminatas cortas; actividades domésticas muy ligeras; trabajo de oficina o similar.','1.40 - 1.49'),(2,'Sedentario/Actividad Ligera','Algunas caminatas diarias; tareas domésticas ligeras; poca o ninguna actividad deportiva; usar transporte activo ocasional.','1.50 - 1.59'),(3,'Moderadamente Activo','Caminatas regulares; actividad física ligera a moderada diaria; trabajo que implica movimiento; 30–60 min de ejercicio moderado/día.','1.60 - 1.74'),(4,'Activo / Vigoroso','Actividad física intensa diaria; trabajo exigente físicamente; ejercicio o deportes >1 h/día; desplazamientos largos a pie o en bicicleta.','1.75 - 1.99'),(5,'Muy activo','Trabajo físico pesado + deporte intenso diario; transporte activo largo + esfuerzo adicional (ej. construcción, agricultura sin mecanización).','2.00 - 2.40');
/*!40000 ALTER TABLE `actividad_fisica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alimentos`
--

DROP TABLE IF EXISTS `alimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alimentos` (
  `id_alimento` int NOT NULL AUTO_INCREMENT,
  `nombre_alimento` varchar(150) NOT NULL,
  `id_grupo` int DEFAULT NULL,
  `gramos_por_porcion` decimal(7,2) DEFAULT NULL,
  `porcion_estandar_desc` varchar(80) DEFAULT NULL,
  `kcal_por_porcion` decimal(7,2) DEFAULT NULL,
  `prote_por_porcion` decimal(6,2) DEFAULT NULL,
  `grasa_por_porcion` decimal(6,2) DEFAULT NULL,
  `carbo_por_porcion` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id_alimento`),
  KEY `id_grupo` (`id_grupo`),
  CONSTRAINT `alimentos_ibfk_1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo_alimento` (`id_grupo`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alimentos`

--

LOCK TABLES `alimentos` WRITE;
/*!40000 ALTER TABLE `alimentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `alimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_nutricionales_usuario`
--

DROP TABLE IF EXISTS `datos_nutricionales_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_nutricionales_usuario` (
  `id_dato` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `peso_kg` decimal(7,2) NOT NULL,
  `estatura_m` decimal(4,2) NOT NULL,
  `edad` int NOT NULL,
  `genero` enum('M','F','Otro') DEFAULT 'Otro',
  `tmb` decimal(9,2) NOT NULL,
  `id_actividad` int NOT NULL,
  `factor_af` decimal(4,3) NOT NULL,
  `requerimiento_calorico` decimal(9,2) NOT NULL,
  `imc` decimal(6,2) NOT NULL,
  `fecha_registro` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_dato`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_actividad` (`id_actividad`),
  CONSTRAINT `datos_nutricionales_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE,
  CONSTRAINT `datos_nutricionales_usuario_ibfk_2` FOREIGN KEY (`id_actividad`) REFERENCES `actividad_fisica` (`id_actividad`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_nutricionales_usuario`
--

LOCK TABLES `datos_nutricionales_usuario` WRITE;
/*!40000 ALTER TABLE `datos_nutricionales_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `datos_nutricionales_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_alimento`
--

DROP TABLE IF EXISTS `grupo_alimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_alimento` (
  `id_grupo` int NOT NULL AUTO_INCREMENT,
  `nombre_grupo` varchar(120) NOT NULL,
  `id_macro` int DEFAULT NULL,
  `kcal_promedio` decimal(7,2) NOT NULL,
  `prote_promedio_g` decimal(6,2) NOT NULL,
  `grasa_promedio_g` decimal(6,2) NOT NULL,
  `carbo_promedio_g` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id_grupo`),
  KEY `id_macro` (`id_macro`),
  CONSTRAINT `grupo_alimento_ibfk_1` FOREIGN KEY (`id_macro`) REFERENCES `macro_clasificacion` (`id_macro`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_alimento`
--

LOCK TABLES `grupo_alimento` WRITE;
/*!40000 ALTER TABLE `grupo_alimento` DISABLE KEYS */;
INSERT INTO `grupo_alimento` VALUES (1,'Leches enteras frescas y fermentadas niños y adultos',2,139.00,6.70,6.70,13.00),(2,'Leches semidescremadas frescas y fermentadas niños y adultos',2,95.00,5.40,2.10,13.50),(3,'Leches descremadas frescas y fermentadas niños y adultos',2,82.00,9.00,0.70,10.00),(4,'Sustitutos niños y adultos',2,77.00,5.80,5.50,1.10),(5,'Carnes magras crudas y proteína texturizada niños y adultos',2,108.00,19.10,3.10,1.10),(6,'Carnes crudas altas en lípidos niños y adultos',2,139.00,15.60,7.90,1.30),(7,'Leguminosas cocidas adultos',2,158.00,9.80,1.70,25.90),(8,'Cereales adultos',1,94.00,2.50,1.00,18.70),(9,'Raíces, tubérculos y plátanos adultos',1,1.00,0.10,0.00,1.90),(10,'Promedio \"harinas\" adultos',1,95.00,2.00,0.60,20.50),(11,'Grasas poliinsaturadas niños y adultos',3,42.00,0.00,4.50,0.30),(12,'Grasas monoinsaturadas niños y adultos',3,47.00,0.40,4.60,1.00),(13,'Grasas saturadas niños y adultos',3,45.00,0.30,4.80,0.30),(14,'Promedio total de grasas niños y adultos',3,45.00,0.30,4.60,0.50),(15,'Productos con reducción de grasa',3,40.00,1.30,3.30,1.10),(16,'Frutas niños y adultos',NULL,59.00,1.00,0.30,13.30),(17,'Verduras y hortalizas niños y adultos',NULL,30.00,1.20,0.30,5.50),(18,'Nueces niños y adultos',3,57.00,1.30,4.80,2.00),(19,'Semillas niños y adultos',3,58.00,2.30,4.10,3.10),(20,'Azúcares y dulces adultos',1,90.00,0.90,1.10,19.10);
/*!40000 ALTER TABLE `grupo_alimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `macro_clasificacion`
--

DROP TABLE IF EXISTS `macro_clasificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `macro_clasificacion` (
  `id_macro` int NOT NULL AUTO_INCREMENT,
  `nombre_macro` varchar(50) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_macro`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `macro_clasificacion`
--

LOCK TABLES `macro_clasificacion` WRITE;
/*!40000 ALTER TABLE `macro_clasificacion` DISABLE KEYS */;
INSERT INTO `macro_clasificacion` VALUES (1,'Carbohidratos','Cereales, Raices, Tuberculos y Platanos, Azucares y Dulces.'),(2,'Proteinas','Grupo de lacteos, Sustitutos, Carnes, Leguminosas.'),(3,'Grasas','Grasas poliinsaturadas, Grasas monoinsaturadas, Grasas saturadas, Productos con reducción de grasas, Nueces, Semillas.');
/*!40000 ALTER TABLE `macro_clasificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetivo`
--

DROP TABLE IF EXISTS `objetivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objetivo` (
  `id_objetivo` int NOT NULL AUTO_INCREMENT,
  `nombre_objetivo` varchar(100) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `carbohidratos_pct` decimal(5,2) NOT NULL,
  `proteinas_pct` decimal(5,2) NOT NULL,
  `grasas_pct` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id_objetivo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivo`
--

LOCK TABLES `objetivo` WRITE;
/*!40000 ALTER TABLE `objetivo` DISABLE KEYS */;
INSERT INTO `objetivo` VALUES (1,'Mantener','Objetivo para un adulto saludable que desea mantener un peso adecuado',0.65,0.15,0.20);
/*!40000 ALTER TABLE `objetivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_usuario`
--

DROP TABLE IF EXISTS `plan_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_usuario` (
  `id_plan_usuario` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_objetivo` int DEFAULT NULL,
  `calorias_totales` decimal(9,2) NOT NULL,
  `carbohidratos_g` decimal(9,2) NOT NULL,
  `proteinas_g` decimal(9,2) NOT NULL,
  `grasas_g` decimal(9,2) NOT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_plan_usuario`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_objetivo` (`id_objetivo`),
  CONSTRAINT `plan_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE,
  CONSTRAINT `plan_usuario_ibfk_2` FOREIGN KEY (`id_objetivo`) REFERENCES `objetivo` (`id_objetivo`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_usuario`
--

LOCK TABLES `plan_usuario` WRITE;
/*!40000 ALTER TABLE `plan_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progreso`
--

DROP TABLE IF EXISTS `progreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progreso` (
  `id_progreso` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `fecha_registro` date DEFAULT (curdate()),
  `peso_kg` decimal(7,2) DEFAULT NULL,
  `imc` decimal(6,2) DEFAULT NULL,
  `observaciones` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id_progreso`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `progreso_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progreso`
--

LOCK TABLES `progreso` WRITE;
/*!40000 ALTER TABLE `progreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `progreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(150) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-29  0:41:58