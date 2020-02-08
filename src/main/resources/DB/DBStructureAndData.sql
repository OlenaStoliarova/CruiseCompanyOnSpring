CREATE DATABASE  IF NOT EXISTS `cruise_company` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cruise_company`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: cruise_company
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `cruise`
--

DROP TABLE IF EXISTS `cruise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cruise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) NOT NULL,
  `starting_date` date NOT NULL,
  `vacancies` int(11) NOT NULL,
  `ship_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5hj9m7df6sup2k5bv2cifsrxj` (`starting_date`,`ship_id`),
  KEY `FK1jfpl5hydhqqf6011wsnak3bw` (`ship_id`),
  CONSTRAINT `FK1jfpl5hydhqqf6011wsnak3bw` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cruise`
--

LOCK TABLES `cruise` WRITE;
/*!40000 ALTER TABLE `cruise` DISABLE KEYS */;
INSERT INTO `cruise` VALUES (1,927.10,'2020-03-08',80,1),(2,1010.65,'2020-03-15',379,1),(3,1087.42,'2020-03-22',380,1),(4,969.28,'2020-03-29',170,1),(5,816.23,'2020-04-05',380,1),(6,996.06,'2020-04-12',380,1),(7,1079.07,'2020-04-19',380,1),(8,1154.46,'2020-04-26',380,1),(9,1144.29,'2020-04-02',1442,2),(10,1427.71,'2020-04-12',1452,2),(11,1267.15,'2020-04-22',1452,2),(12,1417.66,'2020-05-02',1452,2),(13,1349.41,'2020-05-12',1452,2),(14,1118.92,'2020-05-22',1452,2),(15,1375.58,'2020-06-01',1452,2),(16,1295.55,'2020-06-11',1452,2),(17,1515.58,'2020-05-15',920,3),(18,1792.19,'2020-05-29',920,3),(19,1998.38,'2020-06-12',920,3),(20,1671.12,'2020-06-26',920,3),(21,2052.57,'2020-07-10',920,3),(22,1638.84,'2020-07-24',920,3),(23,1676.79,'2020-08-07',920,3),(24,1685.95,'2020-08-21',920,3),(25,1326.54,'2020-05-22',450,4),(26,1741.59,'2020-06-05',450,4),(27,1752.34,'2020-06-19',450,4),(28,1459.55,'2020-07-03',450,4),(29,1723.59,'2020-07-17',450,4),(30,1364.92,'2020-07-31',450,4),(31,1370.23,'2020-08-14',450,4),(32,1262.31,'2020-08-28',450,4),(33,1846.68,'2020-06-01',2200,5),(34,1885.66,'2020-06-11',2200,5),(35,1863.68,'2020-06-21',2200,5),(36,1618.91,'2020-07-01',2200,5),(37,1869.05,'2020-07-11',2200,5),(38,1805.24,'2020-07-21',2200,5),(39,1895.89,'2020-07-31',2200,5),(40,2010.50,'2020-08-10',2200,5),(41,1360.01,'2020-06-07',2280,6),(42,1321.91,'2020-06-17',2280,6),(43,1299.97,'2020-06-27',2280,6),(44,1606.18,'2020-07-07',2280,6),(45,1535.23,'2020-07-17',2280,6),(46,1531.15,'2020-07-27',2280,6),(47,1578.74,'2020-08-06',2280,6),(48,1528.94,'2020-08-16',2280,6);
/*!40000 ALTER TABLE `cruise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `excursion`
--

DROP TABLE IF EXISTS `excursion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `excursion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approximate_duration_hr` bigint(20) NOT NULL,
  `description_en` longtext NOT NULL,
  `description_ukr` longtext NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `name_ukr` varchar(255) NOT NULL,
  `priceusd` decimal(19,2) NOT NULL,
  `seaport_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9dkirt2k0yds370aip5ntethr` (`seaport_id`),
  CONSTRAINT `FK9dkirt2k0yds370aip5ntethr` FOREIGN KEY (`seaport_id`) REFERENCES `seaport` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `excursion`
--

LOCK TABLES `excursion` WRITE;
/*!40000 ALTER TABLE `excursion` DISABLE KEYS */;
INSERT INTO `excursion` VALUES (1,4,'Explore the ruins of the ancient city of Ephesus. Visit the terrace houses, the House of the Virgin Mary, St. John’s Basilica, and other sites. Experience the world’s most spectacular open-air museum on a guided tour from Izmir.','Дослідіть руїни стародавнього міста Ефес. Відвідайте терасові будинки, Будинок Діви Марії, базиліку Святого Іоанна та інші об\'єкти. Відвідайте найбільш вражаючий в світі музей під відкритим небом під час екскурсії з Ізміра.','Ephesus','Ефес',21.00,1),(2,4,'Explore Athens’ famous sights, the Acropolis and the Acropolis museum at your own pace skipping the long lines. Save time and energy.','Досліджуйте відомі пам\'ятки Афін, Акрополь і Новий музей Акрополя, в зручному для вас темпі зі входом позачергово. Збережіть час і енергію.','Acropolis and Museum','Акрополь та музей',24.00,3),(3,6,'Jump, slide and abseil down waterfalls accompanied by the ICOpro-certified team of instructors for a a fun-filled learning experience on Mount Olympus.','Стрибайте, ковзайте і спускайтесь по водоспадам у супроводі сертифікованої ICOpro команди інструкторів  на горі Олімп.','Half-Day Canyoning Trip to Mount Olympus','Поїздка на півдня каньйонами на гору Олімп',96.50,2),(6,6,'Visit Park Guell, one of Gaudi’s major works in Barcelona. Take in spectacular views of Barcelona and explore this stunning green space that’s surrounded by modernist architecture.','Відвідайте парк Гуель - одне з великих творінь Гауді в Барселоні. Помилуйтеся захоплюючим видом на Барселону і огляньте приголомшливу зелену територію в оточенні модерністської архітектури.','Park Guell','Парк Гуель',18.00,4),(7,2,'Immerse yourself in the sensuous rhythms of Spanish flamenco at a 1-hour show at Barcelona’s Palacio del Flamenco. ','Пориньте в чуттєві ритми іспанського фламенко в годинному шоу в Palacio del Flamenco.','Flamenco Show','Шоу фламенко',42.00,4),(8,3,'Get a comprehensive overview of the old town of Copenhagen, ideal for those who are short on time. See many of the old town\'s most historic sights and neighborhoods.','Отримайте вичерпний огляд старого міста Копенгагена, який ідеально підходить тим, кому не вистачає часу. Подивіться на більшість історичних пам\'яток і кварталів старого міста.','Copenhagen Old Town Walk','Прогулянка по старому місту Копенгагена',39.00,13),(9,2,'See the sights of Copenhagen on a 1-hour boat tour along the main harbor and adjoining canals. Depart from Nyhavn and see lovely houses, ancient castles, and beautiful churches along the way.','Огляньте пам\'ятки Копенгагена на човні. Відправляйтеся в круїз з Ньюхавн і по дорозі побачите прекрасні будинки, старовинні замки і красиві церкви.','Canal Cruise from Nyhavn','Круїз по каналах з Ньюхавн',15.50,13),(10,3,'ABBA The Museum is no ordinary museum. Here you will get to walk in the footsteps of ABBA, the world’s most successful pop group, and become the 5th member.','Музей ABBA - це не просто музей. Тут ви зможете пройти по стопах ABBA, найуспішнішою поп-групи в світі, і стати її п\'ятим учасником.','ABBA The Museum','Музей АВВА',26.00,14),(11,6,'Board a maxi-catamaran in Marseille and set sail on an unforgettable cruise along the creeks and archipelagos of the Calanques National Park. Marvel at the stunning beauty of the landscapes, stop to swim and snorkel and enjoy a special lunch.','Сідайте на борт великого катамарана в Марселі і вирушайте в незабутній круїз по бухтах і архіпелагу національного парку Каланк. Помилуйтеся приголомшливою красою пейзажів, зупиніться, щоб скупатися і попірнати, а також оцініть особливий обід.','Catamaran Cruise with Lunch in the Calanques National Park','Національний парк Каланк-круїз на катамарані і обід',78.00,7),(12,5,'Discover the medieval village of Eze, Monte Carlo, and the Principality of Monaco on this 5-hour tour. On this tour you will admire the breathtaking views, discover the rich history, and gain an insight into the culture of this luxurious region.','Познайомтеся із середньовічним селом Ез, Монте-Карло і князівством Монако в рамках 5-годинного туру. Ви вдосталь налюбуєтесь захоплюючими краєвидами, відкриєте для себе багату історію і дізнаєтеся більше про культуру цього фешенебельного регіону.','Eze Village, Monaco and Monte Carlo','Ез, Монако и Монте-Карло',54.00,11);
/*!40000 ALTER TABLE `excursion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extras`
--

DROP TABLE IF EXISTS `extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extras` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(255) NOT NULL,
  `name_ukr` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK7o6a5mf7hsggthtw3ftvcnbm1` (`name_en`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extras`
--

LOCK TABLES `extras` WRITE;
/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
INSERT INTO `extras` VALUES (1,'Gym','Спортивна зала'),(2,'Swimming pool','Басейн'),(3,'Cinema hall','Кінозала'),(4,'SPA','Спа-салон'),(6,'Theater','Театр'),(7,'Disco','Дискотека'),(8,'Beauty salon','Салон краси'),(9,'Sauna','Сауна'),(10,'Jacuzzi','Джакузі'),(11,'Casino','Казино'),(12,'Water slide','Водна гірка'),(13,'Mini golf','Міні-гольф'),(14,'Surf simulator','Симулятор серфінгу'),(15,'Children room','Дитяча кімната'),(16,'Children\'s pool','Дитячий басейн');
/*!40000 ALTER TABLE `extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_excursion`
--

DROP TABLE IF EXISTS `order_excursion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_excursion` (
  `order_id` bigint(20) NOT NULL,
  `excursion_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`,`excursion_id`),
  KEY `FKsawlpwu9cffdyc40t1u6ag358` (`excursion_id`),
  CONSTRAINT `FK85g193rr40xe5vw3fax0alrso` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKsawlpwu9cffdyc40t1u6ag358` FOREIGN KEY (`excursion_id`) REFERENCES `excursion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_excursion`
--

LOCK TABLES `order_excursion` WRITE;
/*!40000 ALTER TABLE `order_excursion` DISABLE KEYS */;
INSERT INTO `order_excursion` VALUES (11,1),(9,2),(11,2),(11,3),(18,3);
/*!40000 ALTER TABLE `order_excursion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_extras`
--

DROP TABLE IF EXISTS `order_extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_extras` (
  `order_id` bigint(20) NOT NULL,
  `extra_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`,`extra_id`),
  KEY `FKmwixxtc09sx320amrdblu934e` (`extra_id`),
  CONSTRAINT `FK8507ifuoe281bwa6gi0v0853d` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKmwixxtc09sx320amrdblu934e` FOREIGN KEY (`extra_id`) REFERENCES `extras` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_extras`
--

LOCK TABLES `order_extras` WRITE;
/*!40000 ALTER TABLE `order_extras` DISABLE KEYS */;
INSERT INTO `order_extras` VALUES (18,1),(9,2),(18,2),(9,6),(18,6),(9,7),(18,9);
/*!40000 ALTER TABLE `order_extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` date NOT NULL,
  `quantity` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` decimal(19,2) NOT NULL,
  `cruise_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp3bgw2qfi3pf2kidnmjdj7myc` (`cruise_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKp3bgw2qfi3pf2kidnmjdj7myc` FOREIGN KEY (`cruise_id`) REFERENCES `cruise` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2020-01-14',300,'NEW',278130.00,1,10),(7,'2020-01-14',80,'CANCELED',74168.00,1,10),(9,'2020-01-14',1,'EXTRAS_ADDED',1010.65,2,10),(10,'2020-01-15',2,'EXCURSIONS_ADDED',1938.56,4,10),(11,'2020-01-15',8,'EXCURSIONS_ADDED',7754.24,4,9),(12,'2020-01-15',10,'PAID',11442.90,9,9),(15,'2020-01-15',450,'CANCELED',596943.00,25,9),(17,'2020-01-17',300,'CANCELED',290784.00,4,10),(18,'2020-01-17',200,'EXTRAS_ADDED',193856.00,4,10);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seaport`
--

DROP TABLE IF EXISTS `seaport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seaport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_en` varchar(255) NOT NULL,
  `country_ukr` varchar(255) NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `name_ukr` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKntsxh1y0rpawtgkdu31mohef1` (`name_en`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seaport`
--

LOCK TABLES `seaport` WRITE;
/*!40000 ALTER TABLE `seaport` DISABLE KEYS */;
INSERT INTO `seaport` VALUES (1,'Turkey','Турція','Izmir','Ізмір'),(2,'Greece','Греція','Thessaloniki','Салоніки'),(3,'Greece','Греція','Athens','Афіни'),(4,'Spain','Іспанія','Barcelona','Барселона'),(5,'Spain','Іспанія','Valencia','Валенсія'),(6,'Spain','Іспанія','Palma de Mallorca','Пальма-де-Майорка'),(7,'France','Франція','Marseille','Марсель'),(10,'France','Франція','Nice','Ніцца'),(11,'Monaco','Монако','Monte-Carlo','Монте-Карло'),(12,'Norway','Норвегія','Oslo','Осло'),(13,'Denmark','Данія','Copenhagen','Копенгаген'),(14,'Sweden','Швеція','Stockholm','Стокгольм'),(36,'Finland','Фінляндія','Helsinki','Хельсінкі'),(37,'Poland','Польща','Gdynia','Ґдиня');
/*!40000 ALTER TABLE `seaport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship`
--

DROP TABLE IF EXISTS `ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `one_trip_duration_days` int(11) NOT NULL,
  `route_name_en` varchar(255) NOT NULL,
  `route_name_ukr` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKs1m9g2daf9l5poias03r67vb` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship`
--

LOCK TABLES `ship` WRITE;
/*!40000 ALTER TABLE `ship` DISABLE KEYS */;
INSERT INTO `ship` VALUES (1,380,'Aegean Odyssey',6,'Aegean Sea','Егейське море'),(2,1452,'Magellan',8,'Vamonos','Вамонос'),(3,920,'Viking Star',12,'Three northern capitals','Три північні столиці'),(4,450,'Nordkapp',12,'Three northern capitals','Три північні столиці'),(5,2200,'Costa Mediterranea',9,'South coast of France plus Monaco','Південне узбережжя Франції плюс Монако'),(6,2280,'Monarch',7,'South coast of France plus Monaco','Південне узбережжя Франції плюс Монако');
/*!40000 ALTER TABLE `ship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship_extras`
--

DROP TABLE IF EXISTS `ship_extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ship_extras` (
  `ship_id` bigint(20) NOT NULL,
  `extra_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ship_id`,`extra_id`),
  KEY `FKe6tfsg04vyl662qrxjdjeby2f` (`extra_id`),
  CONSTRAINT `FKe6tfsg04vyl662qrxjdjeby2f` FOREIGN KEY (`extra_id`) REFERENCES `extras` (`id`),
  CONSTRAINT `FKrgxeo1ht8kki8hwdlc2km0fsc` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship_extras`
--

LOCK TABLES `ship_extras` WRITE;
/*!40000 ALTER TABLE `ship_extras` DISABLE KEYS */;
INSERT INTO `ship_extras` VALUES (1,1),(2,1),(3,1),(4,1),(1,2),(2,2),(5,2),(6,2),(3,3),(4,3),(1,4),(2,4),(5,4),(1,6),(2,6),(3,6),(5,6),(1,7),(2,7),(3,7),(4,7),(5,7),(6,7),(1,8),(2,8),(5,8),(6,8),(1,9),(2,9),(3,9),(4,9),(1,10),(2,10),(3,10),(2,11),(5,11),(6,11),(5,12),(6,12),(3,13),(6,14),(2,15),(5,15),(6,15),(2,16),(5,16),(6,16);
/*!40000 ALTER TABLE `ship_extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ship_route_seaport`
--

DROP TABLE IF EXISTS `ship_route_seaport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ship_route_seaport` (
  `ship_id` bigint(20) NOT NULL,
  `seaport_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ship_id`,`seaport_id`),
  KEY `FKm9culkfu9e9pqhv4w176gft55` (`seaport_id`),
  CONSTRAINT `FKeo12ywxip0wtufee82ay1siyx` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`id`),
  CONSTRAINT `FKm9culkfu9e9pqhv4w176gft55` FOREIGN KEY (`seaport_id`) REFERENCES `seaport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship_route_seaport`
--

LOCK TABLES `ship_route_seaport` WRITE;
/*!40000 ALTER TABLE `ship_route_seaport` DISABLE KEYS */;
INSERT INTO `ship_route_seaport` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(2,6),(5,7),(6,7),(5,10),(6,10),(5,11),(6,11),(3,12),(4,12),(3,13),(4,13),(3,14),(4,14);
/*!40000 ALTER TABLE `ship_route_seaport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name_en` varchar(255) NOT NULL,
  `first_name_native` varchar(255) NOT NULL,
  `last_name_en` varchar(255) NOT NULL,
  `last_name_native` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@a.a','Olena','Олена','Stoliarova','Столярова','$2a$10$QV..vHia1IZrre13cyB/JODRDjYlci6OwtgTlk/faH2TSVFtQecxi','ROLE_ADMIN'),(4,'boss@c.ua','Maria','Марія','Terehova','Терехова','$2a$10$FIhIQJTCCTX7w7hWV8MF.euPaQ4VKwXGZoMfoB0TCX0Pi3/qSCuUe','ROLE_TRAVEL_AGENT'),(9,'misha@b.ua','Mykhailo','Михайло','Vedmid','Ведмідь','$2a$10$Q02.WfzXEhpbBjIND64rZe1TAnllhj.uUjhdZfH3Hr7S.YfRTI09u','ROLE_TOURIST'),(10,'a@b.ua','Kosya','Кося','Vyhan','Вухань','$2a$10$0RXEMds.H3x/i4RcDAV5MOVc48./J6viHyAqFZbZww.MeK29zfqfm','ROLE_TOURIST');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-08  3:12:13
