-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: MedBoxDB
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `licensenumber` varchar(18) NOT NULL,
  `medicinename` varchar(50) DEFAULT NULL,
  `activeingredient` text,
  `medcharacter` varchar(255) DEFAULT NULL,
  `dose` varchar(50) DEFAULT NULL,
  `dosage` varchar(255) DEFAULT NULL,
  `contraindication` varchar(255) DEFAULT NULL,
  `indication` text,
  `dosagefromdoc` varchar(255) DEFAULT NULL,
  `untowardeffect` varchar(255) DEFAULT NULL,
  `druginteraction` varchar(255) DEFAULT NULL,
  `periodvalidity` varchar(10) DEFAULT NULL,
  `manufacturer` varchar(40) DEFAULT NULL,
  `storageconditions` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`licensenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES ('1','2','12','12','2','12','12','12','2','2','1','2','2','2'),('国药准字Z45021599','桂林西瓜霜','西瓜霜、锻硼砂、黄柏、黄连、山豆根、射干、浙贝母、青黛、冰片、大黄、木汉果（炭）黄芩、甘草、薄荷脑','本品为灰黄绿色的粉末；气香，味咸、微苦而辛凉','每瓶装2.5克','喷（吹）敷患处，一次适量，一日数次。\n重症者兼服，一次1～2g，次数视情况而定。','忌烟酒、辛辣、鱼腥食物。','口腔溃疡','喷（吹）敷患处，一次适量，一日数次。','尚不明确','与其他药物可能发生相互作用,请询问医师','36个月','桂林三金药业股份有限公司','密闭，防潮。');
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-21  3:52:35
