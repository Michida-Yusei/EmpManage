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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `DEPARTMENTNO` int(2) NOT NULL,
  `DEPARTMENTNAME` varchar(14) NOT NULL,
  `ADDRESS` varchar(13) NOT NULL,
  PRIMARY KEY (`DEPARTMENTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'人事','東京都中野'),(2,'技術-九州','福岡県福岡市'),(3,'技術-関西','大阪府大阪市'),(4,'技術-名古屋','愛知県名古屋市'),(5,'技術-北海道','北海道札幌市');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EMPLOYEENO` int(4) NOT NULL,
  `EMPLOYEENAME` varchar(10) NOT NULL,
  `JOB` varchar(9) NOT NULL,
  `MANAGERNO` int(4) DEFAULT NULL,
  `JOININGCOMPANYDATE` date NOT NULL,
  `SALARY` float(9,2) NOT NULL,
  `ALLOWANCE` float(9,2) DEFAULT '0.00',
  `DEPARTMENT` int(2) NOT NULL,
  PRIMARY KEY (`EMPLOYEENO`),
  KEY `DEPARTMENT` (`DEPARTMENT`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`DEPARTMENT`) REFERENCES `department` (`DEPARTMENTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (8009,'岩崎','PM',0,'1975-04-01',32.00,98.00,1),(8010,'本田','PM',0,'1974-04-01',35.00,105.00,2),(8011,'田中','SE',8010,'1980-04-01',28.00,84.00,2),(8012,'上田','PG',0,'1988-04-01',22.00,66.00,3),(8020,'梶原','PG',8012,'1988-04-01',19.00,57.00,4),(8030,'大泉','SE',0,'1995-10-01',29.00,87.00,5);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `GRADE` int(1) NOT NULL,
  `MINSALARY` float(9,2) NOT NULL,
  `MAXSALARY` float(9,2) NOT NULL,
  PRIMARY KEY (`GRADE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,17.00,20.99),(2,21.00,24.99),(3,25.00,29.99),(4,30.00,34.99),(5,35.00,39.99),(6,40.00,44.99),(7,45.00,49.99);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemsuser`
--

DROP TABLE IF EXISTS `systemsuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemsuser` (
  `USERID` varchar(10) NOT NULL,
  `PASSWORD` varchar(10) DEFAULT NULL,
  `EMPLOYEENO` int(4) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `AUTHORITY` int(1) DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  KEY `EMPLOYEENO` (`EMPLOYEENO`),
  CONSTRAINT `systemsuser_ibfk_1` FOREIGN KEY (`EMPLOYEENO`) REFERENCES `employee` (`EMPLOYEENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemsuser`
--

LOCK TABLES `systemsuser` WRITE;
/*!40000 ALTER TABLE `systemsuser` DISABLE KEYS */;
INSERT INTO `systemsuser` VALUES ('admin','admin',8010,'honda@kadai.co.jp',1),('jinji','jinji',8009,'iwasaki@kadai.co.jp',2),('ippan','ippan',8011,'tanaka@kadai.co.jp',3);
/*!40000 ALTER TABLE `systemsuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-02 16:11:42

SELECT * FROM department;
SELECT * FROM employee;
SELECT * FROM grade;
SELECT * FROM systemsuser;
