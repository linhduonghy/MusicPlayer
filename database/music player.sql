-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: music player
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_mylist`
--

DROP TABLE IF EXISTS `tbl_mylist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_mylist` (
  `IDlist` int(11) NOT NULL AUTO_INCREMENT,
  `usr` varchar(30) NOT NULL,
  `name_of_list` varchar(30) NOT NULL,
  PRIMARY KEY (`IDlist`),
  UNIQUE KEY `IDlist` (`IDlist`),
  KEY `usr_user` (`usr`),
  CONSTRAINT `usr_user` FOREIGN KEY (`usr`) REFERENCES `tbl_user` (`usr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_mylist`
--

LOCK TABLES `tbl_mylist` WRITE;
/*!40000 ALTER TABLE `tbl_mylist` DISABLE KEYS */;
INSERT INTO `tbl_mylist` VALUES (6,'linhdv','linhdv'),(7,'linhdv','list');
/*!40000 ALTER TABLE `tbl_mylist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_mylist_this_song`
--

DROP TABLE IF EXISTS `tbl_mylist_this_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_mylist_this_song` (
  `IDlist` int(11) NOT NULL,
  `IDsong` int(11) NOT NULL,
  KEY `IDlist_mylist` (`IDlist`),
  KEY `IDsong_song` (`IDsong`),
  CONSTRAINT `IDlist_mylist` FOREIGN KEY (`IDlist`) REFERENCES `tbl_mylist` (`idlist`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `IDsong_song` FOREIGN KEY (`IDsong`) REFERENCES `tbl_song` (`idsong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_mylist_this_song`
--

LOCK TABLES `tbl_mylist_this_song` WRITE;
/*!40000 ALTER TABLE `tbl_mylist_this_song` DISABLE KEYS */;
INSERT INTO `tbl_mylist_this_song` VALUES (6,9),(6,10),(6,4),(6,1),(6,12),(6,6),(6,2),(7,50),(7,49),(6,8),(7,8);
/*!40000 ALTER TABLE `tbl_mylist_this_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_sing_this_song`
--

DROP TABLE IF EXISTS `tbl_sing_this_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_sing_this_song` (
  `IDsinger` int(11) NOT NULL,
  `IDsong` int(11) NOT NULL,
  KEY `IDsinger_singer` (`IDsinger`),
  KEY `singer_song` (`IDsong`),
  CONSTRAINT `IDsinger_singer` FOREIGN KEY (`IDsinger`) REFERENCES `tbl_singer` (`idsinger`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `singer_song` FOREIGN KEY (`IDsong`) REFERENCES `tbl_song` (`idsong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sing_this_song`
--

LOCK TABLES `tbl_sing_this_song` WRITE;
/*!40000 ALTER TABLE `tbl_sing_this_song` DISABLE KEYS */;
INSERT INTO `tbl_sing_this_song` VALUES (1,8),(2,2),(3,2),(4,6),(4,7),(5,4),(5,5),(5,1),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(2,23),(2,24),(2,25),(2,26),(2,27),(6,23),(7,26),(8,27),(9,28),(10,28),(9,29),(9,30),(11,30),(9,31),(12,31),(13,32),(13,33),(13,34),(13,35),(13,36),(13,37),(14,33),(15,33),(16,35),(17,35),(16,36),(18,36),(20,37),(21,37),(19,36),(22,38),(23,39),(24,39),(25,40),(2,40),(27,42),(28,43),(29,44),(2,44),(30,44),(26,41),(31,45),(39,45),(32,46),(33,47),(34,48),(35,48),(36,49),(37,49),(38,50),(40,3);
/*!40000 ALTER TABLE `tbl_sing_this_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_singer`
--

DROP TABLE IF EXISTS `tbl_singer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_singer` (
  `IDsinger` int(11) NOT NULL AUTO_INCREMENT,
  `name_of_singer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `country` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Date_of_Birth` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IDsinger`),
  UNIQUE KEY `IDsinger` (`IDsinger`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_singer`
--

LOCK TABLES `tbl_singer` WRITE;
/*!40000 ALTER TABLE `tbl_singer` DISABLE KEYS */;
INSERT INTO `tbl_singer` VALUES (1,'Khánh Phương',NULL,'Việt Nam','4/11/1981'),(2,'Đen Vâu',NULL,'Việt Nam','13/5/1989'),(3,'Phương Anh Đào',NULL,'Việt Nam','30/4/1992'),(4,'Phùng Khánh Linh',NULL,'Việt Nam','7/5/1994'),(5,'Ánh Phương',NULL,'Việt Nam','20/11/1989'),(6,'Hậu Vi',NULL,'Việt Nam',NULL),(7,'Linh Cáo',NULL,'Việt Nam',NULL),(8,'Link Lee',NULL,'Việt Nam','1988'),(9,'Karik',NULL,'Việt Nam','12/4/1989'),(10,'Uyên Pím',NULL,'Việt Nam',NULL),(11,' Windy Quyên',NULL,'Việt Nam',NULL),(12,'Thái Trinh',NULL,'Việt Nam',NULL),(13,'Alan Walker',NULL,'Na Uy','24/8/1997'),(14,'Sabrina Carpenter',NULL,'Mỹ','11/5/1999'),(15,'Farruko ',NULL,NULL,NULL),(16,'K-391',NULL,'Na Uy','2/11/1994'),(17,'Emelie Hollow',NULL,NULL,NULL),(18,'Julie Bergan',NULL,NULL,NULL),(19,'Seungri',NULL,'Hàn Quốc','12/12/1990'),(20,'Au/Ra',NULL,'',''),(21,'Tomine Harket',NULL,NULL,NULL),(22,'Minh Vương M4U',NULL,'Việt Nam','21/12/1984'),(23,'Jack',NULL,'Việt Nam',NULL),(24,'K-ICM',NULL,'Việt Nam',NULL),(25,'Biên',NULL,'Việt Nam',NULL),(26,'Quân A.P',NULL,'Việt Nam',NULL),(27,'Hương Giang',NULL,'Việt Nam',NULL),(28,'Đức Phúc',NULL,'Việt Nam',NULL),(29,'Min',NULL,'Việt Nam',NULL),(30,'Justa Tee',NULL,'Việt Nam',NULL),(31,'Amee',NULL,'Việt Nam',NULL),(32,'Lou Hoàng',NULL,'Việt Nam',NULL),(33,'Thanh Hưng',NULL,'Việt Nam',NULL),(34,'Da Lab',NULL,'Việt Nam',NULL),(35,'Tóc Tiên',NULL,'Việt Nam',NULL),(36,'Đinh Kiên Phong',NULL,'Việt Nam',NULL),(37,'Thế Anh BLA',NULL,'Việt Nam',NULL),(38,'Sobin Hoàng Sơn',NULL,'Việt Nam',NULL),(39,'Viruss',NULL,'Việt Nam',NULL),(40,'Yoo Mirae',NULL,'Hàn Quốc',NULL);
/*!40000 ALTER TABLE `tbl_singer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_song`
--

DROP TABLE IF EXISTS `tbl_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_song` (
  `IDsong` int(11) NOT NULL AUTO_INCREMENT,
  `name_of_song` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`IDsong`),
  UNIQUE KEY `IDsong` (`IDsong`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_song`
--

LOCK TABLES `tbl_song` WRITE;
/*!40000 ALTER TABLE `tbl_song` DISABLE KEYS */;
INSERT INTO `tbl_song` VALUES (1,'Đường Về Nhà','DuongVeNha-AiPhuong-5842491'),(2,'Lối Nhỏ','LoiNho-DenPhuongAnhDao-6129215'),(3,'Always','Always Descendants Of The Sun OST - yoonmirae'),(4,'Tôi Thấy Hoa Vàng Trên Cỏ Xanh','ToiThayHoaVangTrenCoXanh-AiPhuong-4139972'),(5,'Con Đường Mưa','ConDuongMua-AiPhuong-2815469'),(6,'Trên Ô Cửa Sổ Máy Bay','TrenOCuaMayBay-PhungKhanhLinh-5494097'),(7,'Hôm Nay Tôi Buồn','HomNayToiBuon-PhungKhanhLinh-5383740'),(8,'Em Có Yêu Anh Không','EmCoYeuAnhKhong-KhanhPhuong-3833743'),(9,'Hạnh Phúc Là Gì','HanhPhucLaGi-KhanhPhuong-2874948'),(10,'Mong Em Tha Thứ','MongEmThuTha-KhanhPhuong-4696841'),(11,'Chúng Ta Chẳng Giống Nhau','ChungTaChangGiongNhau-KhanhPhuong-5394131'),(12,'Mưa Thủy Tinh','MuaThuyTinh-KhanhPhuong_nn'),(13,'Nhân Thế Không Ai Yêu Em Bằng Anh','NhanTheKhongAiYeuEmBangAnh-KhanhPhuong-2473906'),(14,'Nếu Không Có Anh Ta','NeuKhongCoAnhTa-KhanhPhuong-2445244'),(15,'Chúc Em Bên Người','ChucEmBenNguoi-KhanhPhuong_3bswq'),(16,'Món Quà Sinh Nhật','MonQuaSinhNhat-KhanhPhuong-2473902'),(17,'Xa Muôn Trùng Mây','XaMuonTrungMay-KhanhPhuong-2439478'),(18,'Hình Bóng Của Mây','HinhBongCuaMay-KhanhPhuong_3e9eq'),(19,'Niềm Hi Vọng Sai Lầm','NiemHyVongSaiLam-KhanhPhuong_42fam'),(20,'Một Cuốn Phim Buồn','MotCuonPhimBuon-KhanhPhuong-2473911'),(21,'Phải Cố Quên Đi Người Ta','PhaiCoQuenDiNguoiTa-KhanhPhuong-2473908'),(22,'Có Lẽ Anh Đã Sai','CoLeAnhDaSai-KhanhPhuong_43nyz'),(23,'Mơ','Mo - Den_ Hau Vi'),(24,'Đi Theo Bóng Mặt Trời','DiTheoBongMatTroi-Den-4938048'),(25,'Ngày Khác Lạ','NgayKhacLa-DenDJGiangPhamTripleD-5393909'),(26,'Ta Cứ Đi Cùng Nhau','Ta Cu Di Cung Nhau - Den_ Linh Cao'),(27,'Cô Gái Bàn Bên','Co Gai Ban Ben - Den_ Lynk Lee'),(28,'Thương','Thuong - Karik_ Uyen Pim'),(29,'Từng Là Tất Cả','Tung La Tat Ca - Karik'),(30,'Khóc Một Mình','Khoc Mot Minh - Karik_ Windy Quyen'),(31,'Cạn Cả Nước Mắt','Can Ca Nuoc Mat - Karik_ Thai Trinh'),(32,'Alone','Alone - Alan Walker_ Noonie Bao'),(33,'On My Way','On My Way - Alan Walker_Sabrina Carpente'),(34,'Faded','Faded - Alan Walker'),(35,'Lily ','Lily - Alan Walker_ K-391_ Emelie Hollow'),(36,'Ignite','Ignite - K-391_ Alan Walker_ Julie Berga'),(37,'Darkside ','Darkside - Alan Walker_ Tomine Harket_ A'),(38,'Em ơi lên phố','Em-Oi-Len-Pho-Minh-Vuong-M4U'),(39,'Em Gì Ơi','Em-Gi-Oi-Jack-K-ICM'),(40,'Hai Triêu Năm','hai-trieu-nam-Den-Bien'),(41,'Còn Gì Đau Hơn Chứ Đã Từng','Con-Gi-Dau-Hon-Chu-Da-Tung-Quan-A-P'),(42,'Anh Ta Bỏ Em Rồi','Anh-Ta-Bo-Em-Roi-Huong-Giang'),(43,'Hết Thương Cạn Nhớ','Het-Thuong-Can-Nho-Duc-Phuc'),(44,'Vì Yêu Cứ Đâm Đầu','Vi-Yeu-Cu-Dam-Dau-MIN-Den-JustaTee'),(45,'Trời Giấu Trời Mang Đi','Troi-Giau-Troi-Mang-Di-AMEE-ViruSs'),(46,'Là Bạn Không Thể Yêu','La-Ban-Khong-The-Yeu-Lou-Hoang'),(47,'Thay Tôi Yêu Cô Ấy','Thay-Toi-Yeu-Co-Ay-Thanh-Hung'),(48,'Nước Mắt Em Lau Bằng Tình Yêu Mới','Nuoc-Mat-Em-Lau-Bang-Tinh-Yeu-Moi-Da-LAB-Toc-Tien'),(49,'Lên Xe Đi Em Ơi','Len-Xe-Di-Em-Oi-Dinh-Kien-Phong-The-Anh-BLA'),(50,'Nếu Ngày Ấy','Neu-Ngay-Ay-Soobin-Hoang-Son');
/*!40000 ALTER TABLE `tbl_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_user` (
  `usr` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `uname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isVip` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`usr`),
  UNIQUE KEY `usr` (`usr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (('linhdv','linhdv','Duong Van Linh',0));
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-25 20:54:56
