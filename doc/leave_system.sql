/*
Navicat MySQL Data Transfer

Source Server         : zhangsenlin
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : leave_system

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2019-05-18 15:49:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_id` int(11) DEFAULT NULL,
  `class_name` varchar(30) NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `FK_Relationship_11` (`grade_id`),
  CONSTRAINT `FK_Relationship_11` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11802 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('11301', '113', '13级1班');
INSERT INTO `class` VALUES ('11302', '113', '13级2班');
INSERT INTO `class` VALUES ('11401', '114', '14级1班');
INSERT INTO `class` VALUES ('11402', '114', '14级2班');
INSERT INTO `class` VALUES ('11501', '115', '15级1班');
INSERT INTO `class` VALUES ('11601', '116', '16级1班');
INSERT INTO `class` VALUES ('11701', '117', '17级1班');
INSERT INTO `class` VALUES ('11801', '118', '18级1班');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(20) NOT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('113', '13级');
INSERT INTO `grade` VALUES ('114', '14级');
INSERT INTO `grade` VALUES ('115', '15级');
INSERT INTO `grade` VALUES ('116', '16级');
INSERT INTO `grade` VALUES ('117', '17级');
INSERT INTO `grade` VALUES ('118', '18级');

-- ----------------------------
-- Table structure for leader
-- ----------------------------
DROP TABLE IF EXISTS `leader`;
CREATE TABLE `leader` (
  `leader_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `leader_name` varchar(30) NOT NULL,
  `leader_password` varchar(30) NOT NULL,
  PRIMARY KEY (`leader_id`),
  KEY `FK_Relationship_9` (`role_id`),
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=134012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leader
-- ----------------------------
INSERT INTO `leader` VALUES ('134011', '3', '刘德华', '1234567');

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave` (
  `leave_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `cause` varchar(500) NOT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  `status` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000' COMMENT '0待审核，1代表审核通过，2代表拒绝，3代表假条过期',
  `total_day` int(11) NOT NULL,
  `handler_name` varchar(11) DEFAULT NULL,
  `handler_role` varchar(11) DEFAULT NULL,
  `handler_time` date DEFAULT NULL,
  PRIMARY KEY (`leave_id`),
  KEY `FK_student_leave` (`student_id`),
  CONSTRAINT `FK_student_leave` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES ('1', '11503080102', '生病', '2019-05-08', '2019-05-12', '00000000002', '7', null, null, null);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13453001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('13453000', '123456', '梁朝伟');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL,
  `msg` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', '学生角色');
INSERT INTO `role` VALUES ('2', '班导', '教师角色');
INSERT INTO `role` VALUES ('3', '系领导', '领导角色');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `student_name` varchar(30) NOT NULL,
  `student_password` varchar(30) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `FK_Relationship_5` (`role_id`),
  KEY `FK_class_student` (`class_id`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11503080407 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('11503080102', '1', '11402', '学生101', '123456');
INSERT INTO `student` VALUES ('11503080103', '1', '11501', '学生145', '123456');
INSERT INTO `student` VALUES ('11503080104', '1', '11501', '学生146', '123456');
INSERT INTO `student` VALUES ('11503080105', '1', '11501', '学生147', '123456');
INSERT INTO `student` VALUES ('11503080106', '1', '11301', '学生1', '123456');
INSERT INTO `student` VALUES ('11503080107', '1', '11601', '学生191', '123456');
INSERT INTO `student` VALUES ('11503080108', '1', '11601', '学生192', '123456');
INSERT INTO `student` VALUES ('11503080109', '1', '11301', '学生2', '123456');
INSERT INTO `student` VALUES ('11503080110', '1', '11402', '学生102', '123456');
INSERT INTO `student` VALUES ('11503080111', '1', '11801', '学生271', '123456');
INSERT INTO `student` VALUES ('11503080112', '1', '11302', '学生40', '123456');
INSERT INTO `student` VALUES ('11503080113', '1', '11801', '学生272', '123456');
INSERT INTO `student` VALUES ('11503080114', '1', '11601', '学生193', '123456');
INSERT INTO `student` VALUES ('11503080116', '1', '11501', '学生148', '123456');
INSERT INTO `student` VALUES ('11503080117', '1', '11402', '学生103', '123456');
INSERT INTO `student` VALUES ('11503080118', '1', '11701', '学生229', '123456');
INSERT INTO `student` VALUES ('11503080119', '1', '11701', '学生230', '123456');
INSERT INTO `student` VALUES ('11503080120', '1', '11302', '学生41', '123456');
INSERT INTO `student` VALUES ('11503080121', '1', '11501', '学生149', '123456');
INSERT INTO `student` VALUES ('11503080122', '1', '11501', '学生150', '123456');
INSERT INTO `student` VALUES ('11503080123', '1', '11501', '学生151', '123456');
INSERT INTO `student` VALUES ('11503080124', '1', '11801', '学生273', '123456');
INSERT INTO `student` VALUES ('11503080125', '1', '11501', '学生152', '123456');
INSERT INTO `student` VALUES ('11503080126', '1', '11601', '学生194', '123456');
INSERT INTO `student` VALUES ('11503080127', '1', '11701', '学生231', '123456');
INSERT INTO `student` VALUES ('11503080129', '1', '11701', '学生232', '123456');
INSERT INTO `student` VALUES ('11503080130', '1', '11501', '学生153', '123456');
INSERT INTO `student` VALUES ('11503080131', '1', '11501', '学生154', '123456');
INSERT INTO `student` VALUES ('11503080132', '1', '11701', '学生233', '123456');
INSERT INTO `student` VALUES ('11503080133', '1', '11401', '学生73', '123456');
INSERT INTO `student` VALUES ('11503080134', '1', '11701', '学生234', '123456');
INSERT INTO `student` VALUES ('11503080135', '1', '11601', '学生195', '123456');
INSERT INTO `student` VALUES ('11503080136', '1', '11801', '学生274', '123456');
INSERT INTO `student` VALUES ('11503080137', '1', '11501', '学生155', '123456');
INSERT INTO `student` VALUES ('11503080138', '1', '11601', '学生196', '123456');
INSERT INTO `student` VALUES ('11503080139', '1', '11701', '学生235', '123456');
INSERT INTO `student` VALUES ('11503080140', '1', '11401', '学生74', '123456');
INSERT INTO `student` VALUES ('11503080142', '1', '11401', '学生75', '123456');
INSERT INTO `student` VALUES ('11503080143', '1', '11801', '学生275', '123456');
INSERT INTO `student` VALUES ('11503080144', '1', '11402', '学生104', '123456');
INSERT INTO `student` VALUES ('11503080145', '1', '11801', '学生276', '123456');
INSERT INTO `student` VALUES ('11503080146', '1', '11701', '学生236', '123456');
INSERT INTO `student` VALUES ('11503080147', '1', '11402', '学生105', '123456');
INSERT INTO `student` VALUES ('11503080148', '1', '11501', '学生156', '123456');
INSERT INTO `student` VALUES ('11503080149', '1', '11801', '学生277', '123456');
INSERT INTO `student` VALUES ('11503080150', '1', '11501', '学生157', '123456');
INSERT INTO `student` VALUES ('11503080151', '1', '11701', '学生237', '123456');
INSERT INTO `student` VALUES ('11503080152', '1', '11401', '学生76', '123456');
INSERT INTO `student` VALUES ('11503080153', '1', '11402', '学生106', '123456');
INSERT INTO `student` VALUES ('11503080154', '1', '11301', '学生3', '123456');
INSERT INTO `student` VALUES ('11503080155', '1', '11601', '学生197', '123456');
INSERT INTO `student` VALUES ('11503080157', '1', '11401', '学生77', '123456');
INSERT INTO `student` VALUES ('11503080158', '1', '11701', '学生238', '123456');
INSERT INTO `student` VALUES ('11503080159', '1', '11302', '学生42', '123456');
INSERT INTO `student` VALUES ('11503080160', '1', '11301', '学生4', '123456');
INSERT INTO `student` VALUES ('11503080161', '1', '11401', '学生78', '123456');
INSERT INTO `student` VALUES ('11503080162', '1', '11301', '学生5', '123456');
INSERT INTO `student` VALUES ('11503080163', '1', '11301', '学生6', '123456');
INSERT INTO `student` VALUES ('11503080164', '1', '11401', '学生79', '123456');
INSERT INTO `student` VALUES ('11503080165', '1', '11302', '学生43', '123456');
INSERT INTO `student` VALUES ('11503080166', '1', '11801', '学生278', '123456');
INSERT INTO `student` VALUES ('11503080167', '1', '11302', '学生44', '123456');
INSERT INTO `student` VALUES ('11503080168', '1', '11402', '学生107', '123456');
INSERT INTO `student` VALUES ('11503080169', '1', '11302', '学生45', '123456');
INSERT INTO `student` VALUES ('11503080170', '1', '11501', '学生158', '123456');
INSERT INTO `student` VALUES ('11503080171', '1', '11601', '学生198', '123456');
INSERT INTO `student` VALUES ('11503080172', '1', '11402', '学生108', '123456');
INSERT INTO `student` VALUES ('11503080173', '1', '11302', '学生46', '123456');
INSERT INTO `student` VALUES ('11503080174', '1', '11401', '学生80', '123456');
INSERT INTO `student` VALUES ('11503080175', '1', '11501', '学生159', '123456');
INSERT INTO `student` VALUES ('11503080176', '1', '11801', '学生279', '123456');
INSERT INTO `student` VALUES ('11503080177', '1', '11401', '学生81', '123456');
INSERT INTO `student` VALUES ('11503080178', '1', '11501', '学生160', '123456');
INSERT INTO `student` VALUES ('11503080179', '1', '11301', '学生7', '123456');
INSERT INTO `student` VALUES ('11503080180', '1', '11501', '学生161', '123456');
INSERT INTO `student` VALUES ('11503080181', '1', '11401', '学生82', '123456');
INSERT INTO `student` VALUES ('11503080182', '1', '11701', '学生239', '123456');
INSERT INTO `student` VALUES ('11503080183', '1', '11601', '学生199', '123456');
INSERT INTO `student` VALUES ('11503080184', '1', '11601', '学生200', '123456');
INSERT INTO `student` VALUES ('11503080185', '1', '11801', '学生280', '123456');
INSERT INTO `student` VALUES ('11503080186', '1', '11601', '学生201', '123456');
INSERT INTO `student` VALUES ('11503080187', '1', '11302', '学生47', '123456');
INSERT INTO `student` VALUES ('11503080188', '1', '11401', '学生83', '123456');
INSERT INTO `student` VALUES ('11503080189', '1', '11402', '学生109', '123456');
INSERT INTO `student` VALUES ('11503080190', '1', '11302', '学生48', '123456');
INSERT INTO `student` VALUES ('11503080191', '1', '11302', '学生49', '123456');
INSERT INTO `student` VALUES ('11503080192', '1', '11401', '学生84', '123456');
INSERT INTO `student` VALUES ('11503080193', '1', '11302', '学生50', '123456');
INSERT INTO `student` VALUES ('11503080194', '1', '11801', '学生281', '123456');
INSERT INTO `student` VALUES ('11503080195', '1', '11302', '学生51', '123456');
INSERT INTO `student` VALUES ('11503080196', '1', '11302', '学生52', '123456');
INSERT INTO `student` VALUES ('11503080197', '1', '11601', '学生202', '123456');
INSERT INTO `student` VALUES ('11503080198', '1', '11601', '学生203', '123456');
INSERT INTO `student` VALUES ('11503080199', '1', '11501', '学生162', '123456');
INSERT INTO `student` VALUES ('11503080200', '1', '11301', '学生8', '123456');
INSERT INTO `student` VALUES ('11503080201', '1', '11601', '学生204', '123456');
INSERT INTO `student` VALUES ('11503080202', '1', '11302', '学生53', '123456');
INSERT INTO `student` VALUES ('11503080203', '1', '11701', '学生240', '123456');
INSERT INTO `student` VALUES ('11503080204', '1', '11601', '学生205', '123456');
INSERT INTO `student` VALUES ('11503080205', '1', '11801', '学生282', '123456');
INSERT INTO `student` VALUES ('11503080206', '1', '11601', '学生206', '123456');
INSERT INTO `student` VALUES ('11503080207', '1', '11401', '学生85', '123456');
INSERT INTO `student` VALUES ('11503080208', '1', '11302', '学生54', '123456');
INSERT INTO `student` VALUES ('11503080209', '1', '11402', '学生110', '123456');
INSERT INTO `student` VALUES ('11503080210', '1', '11301', '学生9', '123456');
INSERT INTO `student` VALUES ('11503080211', '1', '11402', '学生111', '123456');
INSERT INTO `student` VALUES ('11503080212', '1', '11801', '学生283', '123456');
INSERT INTO `student` VALUES ('11503080213', '1', '11701', '学生241', '123456');
INSERT INTO `student` VALUES ('11503080214', '1', '11301', '学生10', '123456');
INSERT INTO `student` VALUES ('11503080215', '1', '11402', '学生112', '123456');
INSERT INTO `student` VALUES ('11503080216', '1', '11401', '学生86', '123456');
INSERT INTO `student` VALUES ('11503080217', '1', '11302', '学生55', '123456');
INSERT INTO `student` VALUES ('11503080218', '1', '11501', '学生163', '123456');
INSERT INTO `student` VALUES ('11503080219', '1', '11701', '学生242', '123456');
INSERT INTO `student` VALUES ('11503080220', '1', '11501', '学生164', '123456');
INSERT INTO `student` VALUES ('11503080221', '1', '11301', '学生11', '123456');
INSERT INTO `student` VALUES ('11503080222', '1', '11302', '学生56', '123456');
INSERT INTO `student` VALUES ('11503080223', '1', '11301', '学生12', '123456');
INSERT INTO `student` VALUES ('11503080224', '1', '11301', '学生13', '123456');
INSERT INTO `student` VALUES ('11503080225', '1', '11402', '学生113', '123456');
INSERT INTO `student` VALUES ('11503080226', '1', '11401', '学生87', '123456');
INSERT INTO `student` VALUES ('11503080227', '1', '11801', '学生284', '123456');
INSERT INTO `student` VALUES ('11503080228', '1', '11601', '学生207', '123456');
INSERT INTO `student` VALUES ('11503080229', '1', '11301', '学生14', '123456');
INSERT INTO `student` VALUES ('11503080230', '1', '11601', '学生208', '123456');
INSERT INTO `student` VALUES ('11503080231', '1', '11801', '学生285', '123456');
INSERT INTO `student` VALUES ('11503080232', '1', '11701', '学生243', '123456');
INSERT INTO `student` VALUES ('11503080233', '1', '11501', '学生165', '123456');
INSERT INTO `student` VALUES ('11503080234', '1', '11601', '学生209', '123456');
INSERT INTO `student` VALUES ('11503080235', '1', '11302', '学生57', '123456');
INSERT INTO `student` VALUES ('11503080236', '1', '11501', '学生166', '123456');
INSERT INTO `student` VALUES ('11503080237', '1', '11701', '学生244', '123456');
INSERT INTO `student` VALUES ('11503080238', '1', '11601', '学生210', '123456');
INSERT INTO `student` VALUES ('11503080239', '1', '11801', '学生286', '123456');
INSERT INTO `student` VALUES ('11503080240', '1', '11402', '学生114', '123456');
INSERT INTO `student` VALUES ('11503080241', '1', '11701', '学生245', '123456');
INSERT INTO `student` VALUES ('11503080242', '1', '11401', '学生88', '123456');
INSERT INTO `student` VALUES ('11503080243', '1', '11601', '学生211', '123456');
INSERT INTO `student` VALUES ('11503080244', '1', '11701', '学生246', '123456');
INSERT INTO `student` VALUES ('11503080245', '1', '11401', '学生89', '123456');
INSERT INTO `student` VALUES ('11503080246', '1', '11501', '学生167', '123456');
INSERT INTO `student` VALUES ('11503080247', '1', '11402', '学生115', '123456');
INSERT INTO `student` VALUES ('11503080248', '1', '11601', '学生212', '123456');
INSERT INTO `student` VALUES ('11503080249', '1', '11701', '学生247', '123456');
INSERT INTO `student` VALUES ('11503080250', '1', '11701', '学生248', '123456');
INSERT INTO `student` VALUES ('11503080251', '1', '11402', '学生116', '123456');
INSERT INTO `student` VALUES ('11503080252', '1', '11701', '学生249', '123456');
INSERT INTO `student` VALUES ('11503080253', '1', '11301', '学生15', '123456');
INSERT INTO `student` VALUES ('11503080254', '1', '11501', '学生168', '123456');
INSERT INTO `student` VALUES ('11503080255', '1', '11402', '学生117', '123456');
INSERT INTO `student` VALUES ('11503080256', '1', '11701', '学生250', '123456');
INSERT INTO `student` VALUES ('11503080257', '1', '11701', '学生251', '123456');
INSERT INTO `student` VALUES ('11503080258', '1', '11501', '学生169', '123456');
INSERT INTO `student` VALUES ('11503080259', '1', '11402', '学生118', '123456');
INSERT INTO `student` VALUES ('11503080260', '1', '11301', '学生16', '123456');
INSERT INTO `student` VALUES ('11503080261', '1', '11801', '学生287', '123456');
INSERT INTO `student` VALUES ('11503080262', '1', '11302', '学生58', '123456');
INSERT INTO `student` VALUES ('11503080263', '1', '11701', '学生252', '123456');
INSERT INTO `student` VALUES ('11503080264', '1', '11301', '学生17', '123456');
INSERT INTO `student` VALUES ('11503080265', '1', '11301', '学生18', '123456');
INSERT INTO `student` VALUES ('11503080266', '1', '11302', '学生59', '123456');
INSERT INTO `student` VALUES ('11503080267', '1', '11402', '学生119', '123456');
INSERT INTO `student` VALUES ('11503080268', '1', '11301', '学生19', '123456');
INSERT INTO `student` VALUES ('11503080269', '1', '11302', '学生60', '123456');
INSERT INTO `student` VALUES ('11503080270', '1', '11501', '学生170', '123456');
INSERT INTO `student` VALUES ('11503080271', '1', '11601', '学生213', '123456');
INSERT INTO `student` VALUES ('11503080272', '1', '11501', '学生171', '123456');
INSERT INTO `student` VALUES ('11503080273', '1', '11402', '学生120', '123456');
INSERT INTO `student` VALUES ('11503080274', '1', '11601', '学生214', '123456');
INSERT INTO `student` VALUES ('11503080275', '1', '11402', '学生121', '123456');
INSERT INTO `student` VALUES ('11503080276', '1', '11301', '学生20', '123456');
INSERT INTO `student` VALUES ('11503080277', '1', '11402', '学生122', '123456');
INSERT INTO `student` VALUES ('11503080278', '1', '11302', '学生61', '123456');
INSERT INTO `student` VALUES ('11503080279', '1', '11301', '学生21', '123456');
INSERT INTO `student` VALUES ('11503080280', '1', '11402', '学生123', '123456');
INSERT INTO `student` VALUES ('11503080281', '1', '11401', '学生90', '123456');
INSERT INTO `student` VALUES ('11503080282', '1', '11302', '学生62', '123456');
INSERT INTO `student` VALUES ('11503080283', '1', '11302', '学生63', '123456');
INSERT INTO `student` VALUES ('11503080284', '1', '11701', '学生253', '123456');
INSERT INTO `student` VALUES ('11503080285', '1', '11601', '学生215', '123456');
INSERT INTO `student` VALUES ('11503080286', '1', '11402', '学生124', '123456');
INSERT INTO `student` VALUES ('11503080287', '1', '11301', '学生22', '123456');
INSERT INTO `student` VALUES ('11503080288', '1', '11701', '学生254', '123456');
INSERT INTO `student` VALUES ('11503080289', '1', '11402', '学生125', '123456');
INSERT INTO `student` VALUES ('11503080290', '1', '11401', '学生91', '123456');
INSERT INTO `student` VALUES ('11503080291', '1', '11402', '学生126', '123456');
INSERT INTO `student` VALUES ('11503080292', '1', '11401', '学生92', '123456');
INSERT INTO `student` VALUES ('11503080293', '1', '11501', '学生172', '123456');
INSERT INTO `student` VALUES ('11503080294', '1', '11301', '学生23', '123456');
INSERT INTO `student` VALUES ('11503080295', '1', '11301', '学生24', '123456');
INSERT INTO `student` VALUES ('11503080296', '1', '11301', '学生25', '123456');
INSERT INTO `student` VALUES ('11503080297', '1', '11501', '学生173', '123456');
INSERT INTO `student` VALUES ('11503080298', '1', '11601', '学生216', '123456');
INSERT INTO `student` VALUES ('11503080299', '1', '11301', '学生26', '123456');
INSERT INTO `student` VALUES ('11503080300', '1', '11601', '学生217', '123456');
INSERT INTO `student` VALUES ('11503080301', '1', '11501', '学生174', '123456');
INSERT INTO `student` VALUES ('11503080302', '1', '11402', '学生127', '123456');
INSERT INTO `student` VALUES ('11503080303', '1', '11402', '学生128', '123456');
INSERT INTO `student` VALUES ('11503080304', '1', '11402', '学生129', '123456');
INSERT INTO `student` VALUES ('11503080305', '1', '11501', '学生175', '123456');
INSERT INTO `student` VALUES ('11503080306', '1', '11501', '学生176', '123456');
INSERT INTO `student` VALUES ('11503080307', '1', '11401', '学生93', '123456');
INSERT INTO `student` VALUES ('11503080308', '1', '11301', '学生27', '123456');
INSERT INTO `student` VALUES ('11503080309', '1', '11801', '学生288', '123456');
INSERT INTO `student` VALUES ('11503080310', '1', '11302', '学生64', '123456');
INSERT INTO `student` VALUES ('11503080311', '1', '11301', '学生28', '123456');
INSERT INTO `student` VALUES ('11503080312', '1', '11701', '学生255', '123456');
INSERT INTO `student` VALUES ('11503080313', '1', '11402', '学生130', '123456');
INSERT INTO `student` VALUES ('11503080314', '1', '11801', '学生289', '123456');
INSERT INTO `student` VALUES ('11503080315', '1', '11402', '学生131', '123456');
INSERT INTO `student` VALUES ('11503080316', '1', '11402', '学生132', '123456');
INSERT INTO `student` VALUES ('11503080317', '1', '11301', '学生29', '123456');
INSERT INTO `student` VALUES ('11503080318', '1', '11701', '学生256', '123456');
INSERT INTO `student` VALUES ('11503080319', '1', '11501', '学生177', '123456');
INSERT INTO `student` VALUES ('11503080320', '1', '11701', '学生257', '123456');
INSERT INTO `student` VALUES ('11503080321', '1', '11701', '学生258', '123456');
INSERT INTO `student` VALUES ('11503080322', '1', '11301', '学生30', '123456');
INSERT INTO `student` VALUES ('11503080323', '1', '11301', '学生31', '123456');
INSERT INTO `student` VALUES ('11503080324', '1', '11501', '学生178', '123456');
INSERT INTO `student` VALUES ('11503080325', '1', '11601', '学生218', '123456');
INSERT INTO `student` VALUES ('11503080326', '1', '11402', '学生133', '123456');
INSERT INTO `student` VALUES ('11503080327', '1', '11801', '学生290', '123456');
INSERT INTO `student` VALUES ('11503080328', '1', '11701', '学生259', '123456');
INSERT INTO `student` VALUES ('11503080329', '1', '11301', '学生32', '123456');
INSERT INTO `student` VALUES ('11503080330', '1', '11801', '学生291', '123456');
INSERT INTO `student` VALUES ('11503080331', '1', '11501', '学生179', '123456');
INSERT INTO `student` VALUES ('11503080332', '1', '11402', '学生134', '123456');
INSERT INTO `student` VALUES ('11503080333', '1', '11701', '学生260', '123456');
INSERT INTO `student` VALUES ('11503080334', '1', '11701', '学生261', '123456');
INSERT INTO `student` VALUES ('11503080335', '1', '11301', '学生33', '123456');
INSERT INTO `student` VALUES ('11503080336', '1', '11302', '学生65', '123456');
INSERT INTO `student` VALUES ('11503080337', '1', '11402', '学生135', '123456');
INSERT INTO `student` VALUES ('11503080338', '1', '11501', '学生180', '123456');
INSERT INTO `student` VALUES ('11503080339', '1', '11401', '学生94', '123456');
INSERT INTO `student` VALUES ('11503080340', '1', '11402', '学生136', '123456');
INSERT INTO `student` VALUES ('11503080341', '1', '11402', '学生137', '123456');
INSERT INTO `student` VALUES ('11503080342', '1', '11801', '学生292', '123456');
INSERT INTO `student` VALUES ('11503080343', '1', '11402', '学生138', '123456');
INSERT INTO `student` VALUES ('11503080344', '1', '11701', '学生262', '123456');
INSERT INTO `student` VALUES ('11503080345', '1', '11601', '学生219', '123456');
INSERT INTO `student` VALUES ('11503080346', '1', '11501', '学生181', '123456');
INSERT INTO `student` VALUES ('11503080347', '1', '11401', '学生95', '123456');
INSERT INTO `student` VALUES ('11503080348', '1', '11302', '学生66', '123456');
INSERT INTO `student` VALUES ('11503080349', '1', '11301', '学生34', '123456');
INSERT INTO `student` VALUES ('11503080350', '1', '11401', '学生96', '123456');
INSERT INTO `student` VALUES ('11503080351', '1', '11801', '学生293', '123456');
INSERT INTO `student` VALUES ('11503080352', '1', '11501', '学生182', '123456');
INSERT INTO `student` VALUES ('11503080353', '1', '11601', '学生220', '123456');
INSERT INTO `student` VALUES ('11503080354', '1', '11501', '学生183', '123456');
INSERT INTO `student` VALUES ('11503080355', '1', '11402', '学生139', '123456');
INSERT INTO `student` VALUES ('11503080356', '1', '11301', '学生35', '123456');
INSERT INTO `student` VALUES ('11503080357', '1', '11301', '学生36', '123456');
INSERT INTO `student` VALUES ('11503080358', '1', '11701', '学生263', '123456');
INSERT INTO `student` VALUES ('11503080359', '1', '11302', '学生67', '123456');
INSERT INTO `student` VALUES ('11503080360', '1', '11601', '学生221', '123456');
INSERT INTO `student` VALUES ('11503080361', '1', '11701', '学生264', '123456');
INSERT INTO `student` VALUES ('11503080362', '1', '11301', '学生37', '123456');
INSERT INTO `student` VALUES ('11503080363', '1', '11401', '学生97', '123456');
INSERT INTO `student` VALUES ('11503080364', '1', '11401', '学生98', '123456');
INSERT INTO `student` VALUES ('11503080365', '1', '11302', '学生68', '123456');
INSERT INTO `student` VALUES ('11503080366', '1', '11302', '学生69', '123456');
INSERT INTO `student` VALUES ('11503080367', '1', '11501', '学生184', '123456');
INSERT INTO `student` VALUES ('11503080368', '1', '11301', '学生38', '123456');
INSERT INTO `student` VALUES ('11503080369', '1', '11601', '学生222', '123456');
INSERT INTO `student` VALUES ('11503080370', '1', '11402', '学生140', '123456');
INSERT INTO `student` VALUES ('11503080371', '1', '11701', '学生265', '123456');
INSERT INTO `student` VALUES ('11503080372', '1', '11501', '学生185', '123456');
INSERT INTO `student` VALUES ('11503080373', '1', '11601', '学生223', '123456');
INSERT INTO `student` VALUES ('11503080374', '1', '11801', '学生294', '123456');
INSERT INTO `student` VALUES ('11503080375', '1', '11501', '学生186', '123456');
INSERT INTO `student` VALUES ('11503080376', '1', '11302', '学生70', '123456');
INSERT INTO `student` VALUES ('11503080377', '1', '11801', '学生295', '123456');
INSERT INTO `student` VALUES ('11503080378', '1', '11701', '学生266', '123456');
INSERT INTO `student` VALUES ('11503080379', '1', '11601', '学生224', '123456');
INSERT INTO `student` VALUES ('11503080380', '1', '11501', '学生187', '123456');
INSERT INTO `student` VALUES ('11503080381', '1', '11402', '学生141', '123456');
INSERT INTO `student` VALUES ('11503080382', '1', '11301', '学生39', '123456');
INSERT INTO `student` VALUES ('11503080383', '1', '11302', '学生71', '123456');
INSERT INTO `student` VALUES ('11503080384', '1', '11601', '学生225', '123456');
INSERT INTO `student` VALUES ('11503080385', '1', '11302', '学生72', '123456');
INSERT INTO `student` VALUES ('11503080386', '1', '11601', '学生226', '123456');
INSERT INTO `student` VALUES ('11503080387', '1', '11801', '学生296', '123456');
INSERT INTO `student` VALUES ('11503080388', '1', '11801', '学生297', '123456');
INSERT INTO `student` VALUES ('11503080389', '1', '11701', '学生267', '123456');
INSERT INTO `student` VALUES ('11503080390', '1', '11701', '学生268', '123456');
INSERT INTO `student` VALUES ('11503080391', '1', '11401', '学生99', '123456');
INSERT INTO `student` VALUES ('11503080392', '1', '11601', '学生227', '123456');
INSERT INTO `student` VALUES ('11503080393', '1', '11701', '学生269', '123456');
INSERT INTO `student` VALUES ('11503080394', '1', '11401', '学生100', '123456');
INSERT INTO `student` VALUES ('11503080395', '1', '11402', '学生142', '123456');
INSERT INTO `student` VALUES ('11503080396', '1', '11402', '学生143', '123456');
INSERT INTO `student` VALUES ('11503080397', '1', '11801', '学生298', '123456');
INSERT INTO `student` VALUES ('11503080398', '1', '11501', '学生188', '123456');
INSERT INTO `student` VALUES ('11503080399', '1', '11701', '学生270', '123456');
INSERT INTO `student` VALUES ('11503080400', '1', '11501', '学生189', '123456');
INSERT INTO `student` VALUES ('11503080401', '1', '11402', '学生144', '123456');
INSERT INTO `student` VALUES ('11503080402', '1', '11801', '学生299', '123456');
INSERT INTO `student` VALUES ('11503080403', '1', '11801', '学生300', '123456');
INSERT INTO `student` VALUES ('11503080404', '1', '11601', '学生228', '123456');
INSERT INTO `student` VALUES ('11503080405', '1', '11501', '学生190', '123456');
INSERT INTO `student` VALUES ('11503080406', '1', '11302', '张学友他弟弟', '12345');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `teacher_name` varchar(30) NOT NULL,
  `teacher_password` varchar(30) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  KEY `FK_Relationship_7` (`role_id`),
  KEY `FK_tea_clas` (`class_id`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1304419 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1304411', '11301', '2', '张学友', '1234567');
INSERT INTO `teacher` VALUES ('1304412', '11302', '2', '教师1', '123456');
INSERT INTO `teacher` VALUES ('1304413', '11401', '2', '教师2', '123456');
INSERT INTO `teacher` VALUES ('1304414', '11402', '2', '教师3', '123456');
INSERT INTO `teacher` VALUES ('1304415', '11501', '2', '教师4', '123456');
INSERT INTO `teacher` VALUES ('1304416', '11601', '2', '教师5', '123456');
INSERT INTO `teacher` VALUES ('1304417', '11701', '2', '教师6', '123456');
INSERT INTO `teacher` VALUES ('1304418', '11801', '2', '教师7', '123456');

-- ----------------------------
-- Table structure for t_set
-- ----------------------------
DROP TABLE IF EXISTS `t_set`;
CREATE TABLE `t_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '1代表是公告，2代表是请假最大天数，3代表老师能处理的天数，4代表系领导能处理的最大天数',
  `content` varchar(400) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_set
-- ----------------------------
INSERT INTO `t_set` VALUES ('1', '1', '测试大撒大撒大大撒大撒大撒大撒大撒大大撒旦', '2019-05-17 16:48:24');
INSERT INTO `t_set` VALUES ('2', '2', '7', '2019-05-17 17:55:54');
INSERT INTO `t_set` VALUES ('3', '3', '3', '2019-05-17 17:55:52');
INSERT INTO `t_set` VALUES ('7', '1', '213213123123', '2019-05-17 16:53:36');
INSERT INTO `t_set` VALUES ('8', '1', '先帝开创的大业未完成一半却中途去世了。现在天下分为三国，指蜀汉国力薄弱，处境艰难。这确实是国家危急存亡的时期啊。不过宫廷里侍从护卫的官员不懈怠，战场上忠诚有志的将士们奋不顾身，大概是他们追念先帝对他们的特别的知遇之恩（作战的原因），想要报答在陛下您身上。（陛下）你实在应该扩大圣明的听闻，来发扬光大先帝遗留下来的美德，振奋有远大志向的人的志气，不应当随便看轻自己，说不恰当的话，以致于堵塞人们忠心地进行规劝的言路。', '2019-05-17 17:59:05');
