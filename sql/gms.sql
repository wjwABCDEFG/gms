-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 39.106.175.70    Database: gms
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `gms`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `gms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `gms`;

--
-- Table structure for table `app_record`
--

DROP TABLE IF EXISTS `app_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contest_app_id` int DEFAULT NULL COMMENT '外键-赛事申请id',
  `venue_app_id` int DEFAULT NULL COMMENT '外键-场地申请id',
  `eqpt_app_id` int unsigned DEFAULT NULL COMMENT '外键-器材申请id',
  PRIMARY KEY (`id`),
  KEY `contest_app_id` (`contest_app_id`),
  KEY `venue_app_id` (`venue_app_id`),
  KEY `eqpt_app_id` (`eqpt_app_id`),
  CONSTRAINT `app_record_ibfk_1` FOREIGN KEY (`contest_app_id`) REFERENCES `ctst_application` (`id`),
  CONSTRAINT `app_record_ibfk_2` FOREIGN KEY (`venue_app_id`) REFERENCES `venue_app_info` (`id`),
  CONSTRAINT `app_record_ibfk_3` FOREIGN KEY (`eqpt_app_id`) REFERENCES `eqptrent` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_record`
--

LOCK TABLES `app_record` WRITE;
/*!40000 ALTER TABLE `app_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctst_annc_info`
--

DROP TABLE IF EXISTS `ctst_annc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctst_annc_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contest_id` int NOT NULL COMMENT '外键-赛事表',
  `content` varchar(200) DEFAULT NULL COMMENT '公告内容',
  PRIMARY KEY (`id`),
  KEY `contest_id` (`contest_id`),
  CONSTRAINT `ctst_annc_info_ibfk_1` FOREIGN KEY (`contest_id`) REFERENCES `ctst_application` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctst_annc_info`
--

LOCK TABLES `ctst_annc_info` WRITE;
/*!40000 ALTER TABLE `ctst_annc_info` DISABLE KEYS */;
INSERT INTO `ctst_annc_info` VALUES (1,2,'精彩绝伦，海大足球对抗赛');
/*!40000 ALTER TABLE `ctst_annc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctst_application`
--

DROP TABLE IF EXISTS `ctst_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctst_application` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contest_id` varchar(20) NOT NULL COMMENT 'GS开头后接四位数字',
  `user_id` int NOT NULL COMMENT '外键-申请人id',
  `activity_type` varchar(50) NOT NULL COMMENT '活动类型',
  `sport_id` int DEFAULT NULL COMMENT '项目类型',
  `contest_name` varchar(50) DEFAULT NULL COMMENT '赛事名称，不一定有',
  `contest_team` varchar(100) DEFAULT NULL COMMENT '比赛队伍，不一定有',
  `activity_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '活动描述和申请理由',
  `contest_time` varchar(20) DEFAULT NULL COMMENT '申请的比赛时间',
  `app_time` varchar(20) DEFAULT NULL COMMENT '申请时间',
  `app_status` varchar(20) DEFAULT NULL COMMENT '状态',
  `app_record_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '审批时间',
  `app_record_id` varchar(20) DEFAULT 'WAIT' COMMENT '审批号，审批通过后由系统给出，Y开头，后接10位',
  `referee_id` int DEFAULT NULL COMMENT '外键-裁判id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `sport_id` (`sport_id`),
  KEY `referee_id` (`referee_id`),
  CONSTRAINT `ctst_application_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `ctst_application_ibfk_2` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`),
  CONSTRAINT `ctst_application_ibfk_3` FOREIGN KEY (`referee_id`) REFERENCES `referee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctst_application`
--

LOCK TABLES `ctst_application` WRITE;
/*!40000 ALTER TABLE `ctst_application` DISABLE KEYS */;
INSERT INTO `ctst_application` VALUES (1,'GS1001',5,'比赛',1,'海大杯第一届篮球赛','软件1171 vs 软件1172','申请举办海大第一届篮球赛','2020-06-04','2020-06-05 12:57:06','ACCEPT_CONTEST','2020-06-23 16:05:40','Y2B47BABC99',1),(2,'GS1002',5,'比赛',2,'第一届足球赛','软件1173 vs 软件1174','申请举办海大第一届足球赛','2020-06-10','2020-06-05 12:57:06','ACCEPT_CONTEST','2020-06-13 16:36:08','Y1822052186',2),(3,'GS1003',1,'比赛',1,'第一届足球赛','软件1173 vs 软件1174','申请举办海大第一届足球赛','2020-06-02','2020-06-05 12:57:06','CANCEL','2020-06-18 02:55:57','Y1815368711',1),(4,'GS1004',5,'比赛',1,'第一届足球赛','软件1173 vs 软件1174','申请举办海大第一届足球赛','2020-06-11','2020-06-05 12:57:06','ACCEPT_CONTEST','2020-06-18 02:55:33','Y1972122358',1),(5,'GS1005',5,'比赛',1,'第一届足球赛','软件1173 vs 软件1174','申请举办海大第一届足球赛','2020-06-11','2020-06-05 12:57:06','ACCEPT_CONTEST','2020-06-18 03:05:05','Y1742912012',1),(6,'GS1006',5,'比赛',6,'乒乓赛','软件1173 vs 软件1174','申请举办海大第一届足球赛','2020-06-04','2020-06-05 12:57:06','REFUSE_CONTEST','2020-06-17 01:20:38','',NULL),(7,'GS1108',2,'上课',6,'乒乒乓乓杯','软件卓越1171 vs 软件1173','冲冲冲！！！！！！','2020-06-12','2020-06-12 15:47:57','ACCEPT_CONTEST','2020-06-17 01:22:46','Y1098807289',5),(8,'GS1885',2,'上课',1,'随便一个杯','软件卓越1171 vs 软件1173','大家加油！！！！！！','2020-06-19','2020-06-13 16:02:25','ACCEPT_CONTEST','2020-06-17 01:23:16','Y1507778682',1),(11,'GS1042',1,'上课',2,'随便两个杯','软件卓越1171 vs 软件1173','大家加油！！！！！！','2020-06-17','2020-06-13 16:48:33','ACCEPT_CONTEST','2020-06-23 16:06:11','Y1E4CE64301',2),(12,'GS1827',1,'比赛',1,'嘿 比赛','软件卓越1171 vs  软件卓越1172','sdfsf','2020-06-29','2020-06-16 02:54:58','DONE','2020-06-18 03:06:15','Y1385234736',1),(13,'GS1951',1,'比赛',1,'软件系篮球赛','软件1111 vs  软件2222','123123','2020-06-30','2020-06-16 12:02:26','REFUSE_CONTEST','2020-06-17 23:07:14','',NULL),(14,'GS1172',1,'比赛',15,'养生赛','软件1171 vs  软件1172','气功养生赛','2020-07-09','2020-06-17 01:59:29','CANCEL','2020-06-17 01:59:30','Y1507542887',NULL),(15,'GS1474',1,'比赛',14,'钓鱼比赛','软件1173 vs  软件1174','一场钓鱼比赛','2020-07-03','2020-06-17 02:04:59','DONE','2020-06-17 02:06:44','Y1238350323',NULL),(16,'GS1754',1,'比赛',2,'足球争霸赛','软件1171 vs  软件1172','精彩的较量','2020-06-25','2020-06-17 02:10:11','ACCEPT_CONTEST','2020-06-23 16:27:45','Y6B02A2E134',2),(17,'GS1022',3,'比赛',13,'普拉提表演赛','软件1171 vs  软件1172','来一场普拉提表演赛','2020-06-23','2020-06-17 02:29:37','ACCEPT_CONTEST','2020-06-18 02:51:22','Y1162612978',4),(18,'GS1022',3,'比赛',13,'普拉提表演赛','软件1171 vs  软件1172','来一场普拉提表演赛','2020-06-23','2020-06-17 02:29:37','ACCEPT_CONTEST','2020-06-18 03:06:32','Y1119730213',4),(20,'GS1022',3,'上课',12,'南拳课','软件1171','打一套南拳','2020-06-18','2020-06-17 02:31:37','CANCEL','','',NULL),(21,'GS1805',1,'上课',11,'游泳课','软件卓越1171','上课','2020-06-28','2020-06-17 02:32:27','REFUSE_CONTEST','2020-06-17 02:32:59',NULL,NULL),(22,'GS1070',1,'比赛',11,'泳池马拉松','软件1171 vs  软件1173','看看谁能坚持','2020-06-24','2020-06-17 02:33:49','DONE','2020-06-17 02:34:43','Y1634335067',9),(23,'GS1951',1,'比赛',13,'软件普拉提比赛','软件1171 vs  软件1172','软件普拉提','2020-06-30','2020-06-17 16:28:13','REFUSE_CONTEST','2020-06-19 09:33:01',NULL,NULL),(24,'GS1812',1,'比赛',13,'2020普拉提比赛','软件1171 vs  软件1172','2020普拉提比赛','2020-06-29','2020-06-17 23:05:42','DONE','2020-06-17 23:07:08','Y1222046899',4),(25,'GS1516',3,'上课',6,'2020乒乒乓乓杯','软件卓越1171','2020乒乒乓乓杯','2020-06-22','2020-06-17 23:07:48','ACCEPT_CONTEST','2020-06-18 02:51:35','YEF7E08E790',3),(26,'GS1144',1,'比赛',14,'2020钓鱼比赛','软件卓越1171 vs  软件1172','上课钓鱼不如现实钓鱼','2020-07-04','2020-06-18 02:39:57','DONE','2020-06-18 03:12:56','Y1187306422',13),(27,'GS1525',1,'比赛',12,'拳脚争霸赛','软件1171 vs  软件1173','拳脚功夫','2020-06-23','2020-06-18 03:10:32','ACCEPT_CONTEST','2020-06-26 15:54:09','Y1B9EEA8254',9),(28,'GS1968',1,'比赛',9,'桌球比赛','软件1173 vs 软件1174','桌球比赛','2020-06-30','2020-06-18 04:19:53','CANCEL',NULL,NULL,NULL),(29,'GS1999',5,'比赛',10,'第一届足球赛','软件1173 vs 软件1174','申请举办海大第一届足球赛','2020-06-10','2020-06-05 12:57:06','ACCEPT_CONTEST','2020-06-18 02:55:57','Y58BB982B54',NULL),(30,'GS1444',1,'比赛',6,'乒乓小将杯','软件1171 vs  软件1172','乒乓小将杯','2020-06-23','2020-06-18 21:36:39','ACCEPT_CONTEST','2020-06-18 21:51:31','Y6F266680AD',5),(31,'GS1214',1,'比赛',9,'桌球小将','软件1171 vs  软件1172','桌球小将','2020-06-30','2020-06-18 21:50:17','REFUSE_CONTEST','2020-06-18 22:14:29',NULL,NULL),(32,'GS1543',1,'比赛',17,'网球比赛','软件卓越1171 vs  软件卓越1172','网球比赛','2020-06-30','2020-06-18 22:14:09','ACCEPT_CONTEST','2020-06-18 22:14:44','Y7092821F83',10),(33,'GS1449',1,'比赛',11,'游泳比赛','软件1171 vs  软件1172','一场游泳比赛','2020-07-02','2020-06-19 08:43:07','CANCEL','2020-06-19 08:44:24','Y1663841822',15),(34,'GS1629',1,'比赛',12,'南拳比赛','软件1171 vs  软件1172','南拳比赛','2020-06-30','2020-06-19 09:28:35','DONE','2020-06-19 09:33:37','Y1940904030',9),(35,'GS1788',3,'比赛',6,'乒乓杯','软件1171 vs  软件1172','挥洒汗水','2020-06-23','2020-06-23 16:26:15','DONE','2020-06-23 16:26:15','Y1348911727',NULL),(36,'GS1596',1,'比赛',2,'中国国足挺进世界杯','软件1172 vs  软件1171','中国国足挺进世界杯','2020-06-30','2020-06-26 11:45:55','ACCEPT_CONTEST','2020-06-26 15:51:35','Y47A4DD93E7',2),(37,'GS1650',1,'比赛',9,'桌球大赛','1 vs 1','桌球大赛','2020-06-27','2020-06-26 15:37:34','REFUSE_CONTEST','2020-06-26 15:54:12',NULL,NULL);
/*!40000 ALTER TABLE `ctst_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deal`
--

DROP TABLE IF EXISTS `deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL COMMENT '处理对象',
  `deal_class_id` int DEFAULT NULL COMMENT '处理类别',
  `deal_time` datetime DEFAULT NULL COMMENT '处理时间',
  `venue_app_id` int DEFAULT NULL COMMENT '申请信息id',
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `deal_class_id` (`deal_class_id`),
  KEY `venue_app_id` (`venue_app_id`),
  CONSTRAINT `deal_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `deal_ibfk_2` FOREIGN KEY (`deal_class_id`) REFERENCES `deal_class` (`id`),
  CONSTRAINT `deal_ibfk_3` FOREIGN KEY (`venue_app_id`) REFERENCES `venue_app_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deal`
--

LOCK TABLES `deal` WRITE;
/*!40000 ALTER TABLE `deal` DISABLE KEYS */;
INSERT INTO `deal` VALUES (9,1,2,'2020-06-18 19:18:07',22),(12,5,2,'2020-06-18 22:55:06',23),(13,5,2,'2020-06-19 09:49:06',31);
/*!40000 ALTER TABLE `deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deal_class`
--

DROP TABLE IF EXISTS `deal_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deal_class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class` int DEFAULT NULL COMMENT '处理天数：3天 10天 30天',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deal_class`
--

LOCK TABLES `deal_class` WRITE;
/*!40000 ALTER TABLE `deal_class` DISABLE KEYS */;
INSERT INTO `deal_class` VALUES (1,1),(2,3),(3,6);
/*!40000 ALTER TABLE `deal_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eqptcompensation`
--

DROP TABLE IF EXISTS `eqptcompensation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eqptcompensation` (
  `CompensateID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `EqptID` char(9) DEFAULT NULL,
  `StudentID` char(12) DEFAULT NULL,
  `CpstStatus` varchar(45) DEFAULT NULL,
  `Indemnity` int DEFAULT NULL,
  `SubmissionTime` date DEFAULT NULL,
  PRIMARY KEY (`CompensateID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eqptcompensation`
--

LOCK TABLES `eqptcompensation` WRITE;
/*!40000 ALTER TABLE `eqptcompensation` DISABLE KEYS */;
INSERT INTO `eqptcompensation` VALUES (0000000001,'EQ1000001','201711701410','已处理',5,'2020-06-18'),(0000000002,'EQ1000002','201701701410','已处理',5,'2020-06-18'),(0000000003,'EQ2000002','201711701410','已处理',5,'2020-06-19'),(0000000004,'EQ6000001','123456','已处理',10,'2020-06-26');
/*!40000 ALTER TABLE `eqptcompensation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eqptmenu`
--

DROP TABLE IF EXISTS `eqptmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eqptmenu` (
  `id` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eqptmenu`
--

LOCK TABLES `eqptmenu` WRITE;
/*!40000 ALTER TABLE `eqptmenu` DISABLE KEYS */;
INSERT INTO `eqptmenu` VALUES (100,'器材管理','/eqptManage'),(200,'库存查询','/eqptyQuery'),(300,'租用审核','/eqptRentalVerify'),(400,'器材回收','/eqptRecycling'),(500,'损坏赔偿','/eqptCompensation'),(600,'器材租用','/eqptRent'),(601,'器材租用费用查询','/eqptRentQuery'),(602,'租用申请查询','/orderQuery ');
/*!40000 ALTER TABLE `eqptmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eqptrent`
--

DROP TABLE IF EXISTS `eqptrent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eqptrent` (
  `OrderID` int(7) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `EqptName` varchar(45) DEFAULT NULL,
  `StudentID` char(12) DEFAULT NULL,
  `Tenant` varchar(45) DEFAULT NULL,
  `Telphone` varchar(11) DEFAULT NULL,
  `Amount` int DEFAULT NULL,
  `PayAmount` int DEFAULT NULL,
  `RentTime` date DEFAULT NULL,
  `ApprTime` date DEFAULT NULL,
  `VerifyState` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eqptrent`
--

LOCK TABLES `eqptrent` WRITE;
/*!40000 ALTER TABLE `eqptrent` DISABLE KEYS */;
INSERT INTO `eqptrent` VALUES (0000001,'篮球','201711701401','李四','13719823727',1,5,'2020-06-18','2020-06-26','驳回'),(0000002,'足球','201711701401','李四','13719823727',1,3,'2020-06-18','2020-06-18','通过'),(0000003,'篮球','201711701410','张三','13543502127',1,5,'2020-06-18','2020-06-18','通过'),(0000004,'乒乓球','201711701410','张三','13543502127',10,10,'2020-06-19','2020-06-19','驳回'),(0000005,'足球','201711701410','张三','13543502127',2,6,'2020-06-19','2020-06-19','通过'),(0000006,'乒乓球','12312312','13123','15219596355',1,1,'2020-06-23','2020-06-23','驳回'),(0000007,'乒乓球','201711701213','asd','15875283702',1,2,'2020-06-26','2020-06-26','通过');
/*!40000 ALTER TABLE `eqptrent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eqpttype`
--

DROP TABLE IF EXISTS `eqpttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eqpttype` (
  `TypeName` varchar(45) NOT NULL,
  `UnitPrice` int DEFAULT NULL,
  PRIMARY KEY (`TypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eqpttype`
--

LOCK TABLES `eqpttype` WRITE;
/*!40000 ALTER TABLE `eqpttype` DISABLE KEYS */;
INSERT INTO `eqpttype` VALUES ('乒乓球',1),('排球',3),('篮球',3),('羽毛球',2),('足球',3);
/*!40000 ALTER TABLE `eqpttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `EqptID` char(9) NOT NULL,
  `EqptName` varchar(45) DEFAULT NULL,
  `RentalStatus` char(6) DEFAULT NULL,
  `EqptStatus` char(4) DEFAULT NULL,
  PRIMARY KEY (`EqptID`),
  KEY `eqptype_idx` (`EqptName`),
  CONSTRAINT `eqptype` FOREIGN KEY (`EqptName`) REFERENCES `eqpttype` (`TypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES ('EQ2000002','足球','未出租','保修'),('EQ2000003','足球','未出租','完好'),('EQ2000004','足球','未出租','完好'),('EQ3000001','乒乓球','未出租','完好'),('EQ4000001','排球','未出租','完好'),('EQ5000001','羽毛球','未出租','完好'),('EQ6000001','篮球','已出租','保修');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mainmenu`
--

DROP TABLE IF EXISTS `mainmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mainmenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=801 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mainmenu`
--

LOCK TABLES `mainmenu` WRITE;
/*!40000 ALTER TABLE `mainmenu` DISABLE KEYS */;
INSERT INTO `mainmenu` VALUES (200,'个人','/user'),(300,'申请','/apply'),(400,'查询','/query'),(500,'用户管理','/useradmin'),(600,'场地管理','/venueadmin'),(700,'赛事管理','/contestadmin'),(800,'器材管理','/equipmentadmin');
/*!40000 ALTER TABLE `mainmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL,
  `parent_id` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_id_UNIQUE` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `period` (
  `id` int NOT NULL AUTO_INCREMENT,
  `period` varchar(50) DEFAULT NULL COMMENT '时间段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
INSERT INTO `period` VALUES (1,'08:10-11:50'),(2,'14:30-18:00'),(3,'19:30-22:00');
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `permisson_id` int NOT NULL DEFAULT '0' COMMENT '自定id,主要供前端展示权限列表分类排序使用.',
  `menu_code` varchar(255) DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(255) DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255) DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` tinyint(1) DEFAULT '2' COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选',
  PRIMARY KEY (`permisson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (601,'user','用户','user:list','列表',1),(602,'user','用户','user:add','新增',2),(603,'user','用户','user:update','修改',2),(604,'user','用户','user:query','查询',1),(701,'role','角色权限','role:list','列表',2),(702,'role','角色权限','role:add','新增',2),(703,'role','角色权限','role:update','修改',2),(704,'role','角色权限','role:delete','删除',2);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referee`
--

DROP TABLE IF EXISTS `referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '裁判姓名',
  `sex` char(1) DEFAULT NULL COMMENT '0男 1女',
  `phone` varchar(200) DEFAULT NULL COMMENT '公告内容',
  `sport_id` int DEFAULT NULL COMMENT '负责项目',
  `description` varchar(200) DEFAULT NULL COMMENT '个人简介',
  `status` int DEFAULT '1' COMMENT '0说明项目被删除',
  PRIMARY KEY (`id`),
  KEY `sport_id` (`sport_id`),
  CONSTRAINT `referee_ibfk_1` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee`
--

LOCK TABLES `referee` WRITE;
/*!40000 ALTER TABLE `referee` DISABLE KEYS */;
INSERT INTO `referee` VALUES (1,'张三','男','110',1,'我是一名篮球教练',1),(2,'李四','男','111',2,'足球裁判，我是专业的',1),(3,'王五','女','112',6,'羽毛球裁判找我就对了',1),(4,'张健','男','1111',13,'专业带队',1),(5,'赵小四','女','117',6,'尼古拉斯赵四',1),(6,'郑苑丹','女','115',15,'气功高手',1),(9,'七七','男','124',12,'南拳健将',1),(10,'老王','男','2323',17,'人称网球王子',1),(11,'刘胡兰','女','456',3,'海大郎平',1),(12,'小明','女','1345',14,'姜子牙钓鱼',1),(13,'高升','男','1245',14,'钓大鱼',1),(14,'赵小四','男','1307627',13,'1111',1),(15,'皮皮','女','111568',11,'游泳教练',1),(16,'小小','女','12345678912',15,'皮球教练',1);
/*!40000 ALTER TABLE `referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL COMMENT '角色id',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int NOT NULL COMMENT '状态：1有效；2删除',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'用户管理员','负责用户模块',1),(2,'场地管理员','负责场地模块',1),(3,'赛事管理员','负责赛事模块',1),(4,'器材管理员','负责器材模块',1),(5,'普通用户','0',1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色id',
  `permission_id` int NOT NULL COMMENT '权限id',
  `status` varchar(1) DEFAULT '1' COMMENT '是否有效 1有效     2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=869 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,601,'1'),(2,1,602,'1'),(3,1,603,'1'),(4,1,604,'1'),(5,1,701,'1'),(6,1,702,'1'),(7,1,703,'1'),(8,1,704,'1'),(9,5,601,'1'),(10,5,604,'1');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_unit`
--

DROP TABLE IF EXISTS `school_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unit_class_id` int DEFAULT NULL COMMENT '单位类型',
  `unit_name` varchar(100) DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`id`),
  KEY `unit_class_id` (`unit_class_id`),
  CONSTRAINT `school_unit_ibfk_1` FOREIGN KEY (`unit_class_id`) REFERENCES `unit_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_unit`
--

LOCK TABLES `school_unit` WRITE;
/*!40000 ALTER TABLE `school_unit` DISABLE KEYS */;
INSERT INTO `school_unit` VALUES (1,1,'初级游泳一班'),(2,1,'网球课二班'),(3,2,'数计院游泳队'),(4,2,'数计院羽毛球队'),(5,1,'高级排球一班'),(6,2,'法学院游泳队');
/*!40000 ALTER TABLE `school_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sport_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int DEFAULT '1' COMMENT '0说明项目被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES (1,'篮球',1),(2,'足球',1),(3,'排球',1),(6,'乒乓球',1),(8,'羽毛球',1),(9,'桌球',1),(10,'跑步',1),(11,'游泳',1),(12,'南拳',1),(13,'普拉提',1),(14,'垂钓',1),(15,'气功',1),(16,'毽球',1),(17,'网球',1),(18,'气球',1),(19,'11',1),(20,'皮球',1);
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submenu`
--

DROP TABLE IF EXISTS `submenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `mid` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=806 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submenu`
--

LOCK TABLES `submenu` WRITE;
/*!40000 ALTER TABLE `submenu` DISABLE KEYS */;
INSERT INTO `submenu` VALUES (201,'用户列表','/userlist',200),(202,'修改个人资料','/changedata',200),(301,'赛事申请','/contestApplication',300),(302,'场地申请','/venueApp',300),(303,'器材申请','/eqptRent',300),(401,'申请记录查询','/appRecordQuery',400),(402,'赛事查询','/contestQuery',400),(403,'裁判查询','/refereeQuery',400),(404,'器材租用费用查询','/eqptRentQuery',400),(405,'租用申请查询','/orderQuery',400),(406,'场地信息查询','/venueQuery',400),(501,' 角色分配','/rolemanage',500),(601,'场地设施管理','/venueManage',600),(602,'场地信息管理','/venueInfoManage',600),(603,'场地申请管理','/venueAppManage',600),(604,'特殊场地信息管理','/specialVenueManage',600),(605,'失约处理','/appMissManage',600),(701,'项目管理','/sportManage',700),(702,'裁判管理','/refereeManage',700),(703,'赛事申请审核','/contestAppManage',700),(801,'库存查询','/eqptQuery',800),(802,'器材管理','/eqptManage',800),(803,'租用审核','/eqptVerify',800),(804,'器材回收','/eqptRecycling',800),(805,'损坏赔偿','/eqptCompensation',800);
/*!40000 ALTER TABLE `submenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_class`
--

DROP TABLE IF EXISTS `unit_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit_class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class` varchar(20) DEFAULT NULL COMMENT '单位类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_class`
--

LOCK TABLES `unit_class` WRITE;
/*!40000 ALTER TABLE `unit_class` DISABLE KEYS */;
INSERT INTO `unit_class` VALUES (1,'学校课程'),(2,'比赛训练');
/*!40000 ALTER TABLE `unit_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `realname` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(128) DEFAULT '1q' COMMENT '加密盐值',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1有效; 2无效',
  `credit` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户信用：1正常; 2失信',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','张三','e10adc3949ba59abbe56e057f20f883e','1q','1234@qq.com','13543502127',1,1),(2,'changdi','李四','e10adc3949ba59abbe56e057f20f883e','1q','12223@qq.com','15271700712',1,1),(3,'saishi','王五','e10adc3949ba59abbe56e057f20f883e','1q','1222qwe3@163.com','17845457511',1,1),(4,'qicai','赵六','e10adc3949ba59abbe56e057f20f883e','1q','qwxcvv@163.com','15245500712',1,1),(5,'ggggg','冯二','e10adc3949ba59abbe56e057f20f883e','1q','pasmv@qq.com','18845057523',1,1),(6,'qweasd','黄一','ecb97ffafc1798cd2f67fcbc37226761','1q','pqwroof@163.com','15952384071',1,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  `user_rolecol` varchar(45) DEFAULT '5',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1,'5'),(2,2,2,'5'),(3,3,3,'5'),(4,4,4,'5'),(5,5,1,'5'),(6,6,5,'5');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue`
--

DROP TABLE IF EXISTS `venue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_id` int DEFAULT NULL COMMENT '场地类型',
  `position_id` int DEFAULT NULL COMMENT '场地位置',
  `charge_person_id` int DEFAULT NULL COMMENT '负责人',
  `status_id` int DEFAULT NULL COMMENT '场地状态：1为可用，2为场地停止运行，9为场地已删除',
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  KEY `position_id` (`position_id`),
  KEY `charge_person_id` (`charge_person_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `venue_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `venue_class` (`id`),
  CONSTRAINT `venue_ibfk_2` FOREIGN KEY (`position_id`) REFERENCES `venue_position` (`id`),
  CONSTRAINT `venue_ibfk_3` FOREIGN KEY (`charge_person_id`) REFERENCES `venue_charge_person` (`id`),
  CONSTRAINT `venue_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `venue_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES (34,1,1,1,1),(35,1,2,1,1),(36,2,3,2,1),(37,2,4,1,1),(38,3,5,2,1),(39,3,6,4,1),(40,6,7,3,1),(41,6,8,5,1),(42,4,9,2,9),(43,5,10,3,9),(44,4,9,1,9),(46,5,9,3,1),(47,5,8,2,9),(48,5,5,2,9),(49,5,6,3,9),(50,6,1,4,1),(51,2,1,4,1);
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_allocation_info`
--

DROP TABLE IF EXISTS `venue_allocation_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_allocation_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `use_unit_id` int DEFAULT NULL COMMENT '使用单位id',
  `venue_info_id` int DEFAULT NULL COMMENT '对应的场地信息',
  `handler_name` varchar(30) DEFAULT NULL COMMENT '处理人信息',
  `week_id` int DEFAULT NULL COMMENT '星期几',
  PRIMARY KEY (`id`),
  KEY `use_unit_id` (`use_unit_id`),
  KEY `venue_info_id` (`venue_info_id`),
  KEY `week_id` (`week_id`),
  CONSTRAINT `venue_allocation_info_ibfk_1` FOREIGN KEY (`use_unit_id`) REFERENCES `school_unit` (`id`),
  CONSTRAINT `venue_allocation_info_ibfk_2` FOREIGN KEY (`venue_info_id`) REFERENCES `venue_info` (`id`),
  CONSTRAINT `venue_allocation_info_ibfk_3` FOREIGN KEY (`week_id`) REFERENCES `week` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_allocation_info`
--

LOCK TABLES `venue_allocation_info` WRITE;
/*!40000 ALTER TABLE `venue_allocation_info` DISABLE KEYS */;
INSERT INTO `venue_allocation_info` VALUES (11,1,23,NULL,3),(12,2,22,NULL,4),(13,5,26,NULL,6),(14,3,23,NULL,4),(15,4,17,NULL,5);
/*!40000 ALTER TABLE `venue_allocation_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_app_info`
--

DROP TABLE IF EXISTS `venue_app_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_app_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `venue_info_id` int DEFAULT NULL COMMENT '场地信息',
  `reason` varchar(100) DEFAULT NULL COMMENT '申请理由',
  `submit_time` datetime DEFAULT NULL COMMENT '提交时间',
  `app_record_code` varchar(50) DEFAULT NULL COMMENT '审批号',
  `processing_time` datetime DEFAULT NULL COMMENT '审批时间',
  `app_status_id` int DEFAULT NULL COMMENT '申请状态',
  `handler_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '审批人姓名',
  PRIMARY KEY (`id`),
  KEY `venue_info_id` (`venue_info_id`),
  KEY `venue_app_info_ibfk_4` (`app_status_id`),
  KEY `approver_id` (`handler_name`),
  CONSTRAINT `venue_app_info_ibfk_1` FOREIGN KEY (`venue_info_id`) REFERENCES `venue_info` (`id`),
  CONSTRAINT `venue_app_info_ibfk_4` FOREIGN KEY (`app_status_id`) REFERENCES `venue_app_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_app_info`
--

LOCK TABLES `venue_app_info` WRITE;
/*!40000 ALTER TABLE `venue_app_info` DISABLE KEYS */;
INSERT INTO `venue_app_info` VALUES (21,21,'数学与计算机学院第一届羽毛球赛使用场地，预计下周举行','2020-06-11 17:39:37','Y1822052186',NULL,3,NULL),(22,20,'数学与计算机学院第一届羽毛球赛使用场地，预计下周举行','2020-06-11 17:40:19','Y1815368711',NULL,3,NULL),(23,18,'数学与计算机学院第一届羽毛球赛使用场地，预计下周举行','2020-06-11 17:40:56','Y1972122358',NULL,3,NULL),(28,20,'数学与计算机学院第一届羽毛球赛使用场地，预计下周举行','2020-06-18 19:17:05','Y1742912045',NULL,3,NULL),(31,25,'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee','2020-06-19 09:41:32','Y1742912012',NULL,3,NULL);
/*!40000 ALTER TABLE `venue_app_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_app_status`
--

DROP TABLE IF EXISTS `venue_app_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_app_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '场地申请状态, 1待支付，2待审批， 3完成，4审批未通过，5申请取消',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_app_status`
--

LOCK TABLES `venue_app_status` WRITE;
/*!40000 ALTER TABLE `venue_app_status` DISABLE KEYS */;
INSERT INTO `venue_app_status` VALUES (1,'待支付'),(2,'待审批'),(3,'完成'),(4,'审批未通过'),(5,'申请取消'),(9,'申请删除');
/*!40000 ALTER TABLE `venue_app_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_charge_person`
--

DROP TABLE IF EXISTS `venue_charge_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_charge_person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '场地管理员姓名',
  `unit` varchar(50) DEFAULT NULL COMMENT '所在单位',
  `work_time` varchar(50) DEFAULT NULL COMMENT '工作时间',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_charge_person`
--

LOCK TABLES `venue_charge_person` WRITE;
/*!40000 ALTER TABLE `venue_charge_person` DISABLE KEYS */;
INSERT INTO `venue_charge_person` VALUES (1,'谢金海','管理部','周一8:10-11:50;周三14:30-18:00','13562147856'),(2,'罗建军','学生处','周三8:10-11:50;周四14:30-18:00','15264258126'),(3,'李泽生','管理部','周一8:10-11:50;周三14:30-18:00','13254823651'),(4,'郑雪军','管理部','周二8:10-11:50;周三14:30-18:00','15863214522'),(5,'张妮妮','学生处','周一8:10-11:50;周三14:30-18:00','14752584256');
/*!40000 ALTER TABLE `venue_charge_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_class`
--

DROP TABLE IF EXISTS `venue_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '场地类型：羽毛球馆、篮球场、游泳池等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_class`
--

LOCK TABLES `venue_class` WRITE;
/*!40000 ALTER TABLE `venue_class` DISABLE KEYS */;
INSERT INTO `venue_class` VALUES (1,'羽毛球场'),(2,'篮球场'),(3,'网球场'),(4,'乒乓球场'),(5,'排球场'),(6,'游泳池');
/*!40000 ALTER TABLE `venue_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_info`
--

DROP TABLE IF EXISTS `venue_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `period_id` int DEFAULT NULL COMMENT '场地可申请时间段',
  `cost` int DEFAULT NULL COMMENT '费用',
  `venue_id` int DEFAULT NULL COMMENT '对应场地',
  `status_id` int DEFAULT NULL COMMENT '场地信息状态：1为可申请状态，2为不可申请状态（作为训练和上课的场地和对应时间段）',
  PRIMARY KEY (`id`),
  KEY `period_id` (`period_id`),
  KEY `venue_id` (`venue_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `venue_info_ibfk_3` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`),
  CONSTRAINT `venue_info_ibfk_4` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`id`),
  CONSTRAINT `venue_info_ibfk_5` FOREIGN KEY (`status_id`) REFERENCES `venue_info_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_info`
--

LOCK TABLES `venue_info` WRITE;
/*!40000 ALTER TABLE `venue_info` DISABLE KEYS */;
INSERT INTO `venue_info` VALUES (17,1,30,34,1),(18,2,32,35,1),(19,3,24,36,1),(20,1,35,37,1),(21,3,37,38,1),(22,1,27,39,9),(23,1,35,40,1),(24,2,35,36,9),(25,1,34,34,1),(26,1,36,46,1),(27,1,36,40,9),(28,3,65,41,1),(29,2,56,38,9),(30,2,78,39,1);
/*!40000 ALTER TABLE `venue_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_info_status`
--

DROP TABLE IF EXISTS `venue_info_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_info_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '场地信息类型，1为可申请，2为已被分配为特殊场地，9为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_info_status`
--

LOCK TABLES `venue_info_status` WRITE;
/*!40000 ALTER TABLE `venue_info_status` DISABLE KEYS */;
INSERT INTO `venue_info_status` VALUES (1,'普通场地'),(2,'特殊场地'),(9,'场地信息已删除');
/*!40000 ALTER TABLE `venue_info_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_position`
--

DROP TABLE IF EXISTS `venue_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `position` varchar(50) DEFAULT NULL COMMENT '体育馆各个位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_position`
--

LOCK TABLES `venue_position` WRITE;
/*!40000 ALTER TABLE `venue_position` DISABLE KEYS */;
INSERT INTO `venue_position` VALUES (1,'体育馆二楼201'),(2,'体育馆二楼202'),(3,'体育馆二楼203'),(4,'体育馆二楼204'),(5,'体育馆二楼205'),(6,'体育馆三楼301'),(7,'体育馆三楼302'),(8,'体育馆三楼303'),(9,'体育馆四楼401'),(10,'体育馆四楼402');
/*!40000 ALTER TABLE `venue_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_status`
--

DROP TABLE IF EXISTS `venue_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '场地类型：1为可用，2为不可用，9为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_status`
--

LOCK TABLES `venue_status` WRITE;
/*!40000 ALTER TABLE `venue_status` DISABLE KEYS */;
INSERT INTO `venue_status` VALUES (1,'场地运行中'),(2,'场地停止运行'),(9,'场地已删除');
/*!40000 ALTER TABLE `venue_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `week`
--

DROP TABLE IF EXISTS `week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `week` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `week`
--

LOCK TABLES `week` WRITE;
/*!40000 ALTER TABLE `week` DISABLE KEYS */;
INSERT INTO `week` VALUES (1,'Sunday'),(2,'Monday'),(3,'Tuesday'),(4,'Wednesday'),(5,'Thursday'),(6,'Friday'),(7,'Saturday');
/*!40000 ALTER TABLE `week` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-27  0:59:22
