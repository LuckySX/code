/*
Navicat MySQL Data Transfer

Source Server         : wa-226
Source Server Version : 50617
Source Host           : 172.17.20.226:3306
Source Database       : netsec_sz

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2021-04-20 01:49:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_traffic_areas_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_areas_hour`;
CREATE TABLE `s_traffic_areas_hour` (
`area`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ips`  bigint(11) NOT NULL ,
`webs`  bigint(11) NOT NULL ,
`ip_upbytes`  bigint(11) NOT NULL ,
`ip_downbytes`  bigint(11) NOT NULL ,
`ip_links`  bigint(11) NOT NULL ,
`web_upbytes`  bigint(11) NOT NULL ,
`web_downbytes`  bigint(11) NOT NULL ,
`web_links`  bigint(11) NOT NULL ,
`date`  datetime NOT NULL ,
INDEX `index_date` (`date`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_dips_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_dips_hour`;
CREATE TABLE `s_traffic_dips_hour` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`dip`  bigint(20) NOT NULL ,
`dip_county`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`sumbytes`  bigint(20) NOT NULL ,
`date`  datetime NOT NULL ,
`area`  int(10) NOT NULL DEFAULT 2 ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=7

;

-- ----------------------------
-- Table structure for s_traffic_homepage_areas_day
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_homepage_areas_day`;
CREATE TABLE `s_traffic_homepage_areas_day` (
`area`  int(20) NOT NULL ,
`areaname`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ips`  bigint(20) NOT NULL ,
`webs`  bigint(20) NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`links`  bigint(20) NOT NULL ,
`date`  datetime NOT NULL ,
INDEX `index_date` (`date`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_homepage_ips_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_homepage_ips_hour`;
CREATE TABLE `s_traffic_homepage_ips_hour` (
`area`  int(20) NOT NULL ,
`ip`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`sumbytes`  bigint(20) NOT NULL ,
`date`  datetime NOT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_homepage_map_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_homepage_map_hour`;
CREATE TABLE `s_traffic_homepage_map_hour` (
`area`  int(20) NOT NULL ,
`s_area`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`t_area`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`appname`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`links`  bigint(20) NOT NULL ,
`slongitude`  double NOT NULL ,
`slatitude`  double NOT NULL ,
`tlongitude`  double NOT NULL ,
`tlatitude`  double NOT NULL ,
`date`  datetime NOT NULL ,
INDEX `index_name` (`s_area`, `t_area`) USING BTREE ,
INDEX `index_name2` (`area`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_homepage_operators_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_homepage_operators_hour`;
CREATE TABLE `s_traffic_homepage_operators_hour` (
`area`  int(20) NOT NULL ,
`operator`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`sumbytes`  bigint(20) NOT NULL ,
`date`  datetime NOT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_homepage_protocols_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_homepage_protocols_hour`;
CREATE TABLE `s_traffic_homepage_protocols_hour` (
`area`  int(20) NOT NULL ,
`protocol`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`sumbytes`  bigint(40) NOT NULL ,
`date`  datetime NOT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_ips_day
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_ips_day`;
CREATE TABLE `s_traffic_ips_day` (
`ip`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`filing`  tinyint(1) NULL DEFAULT NULL ,
`upbytes`  bigint(11) NOT NULL ,
`downbytes`  bigint(11) NOT NULL ,
`links`  bigint(11) NOT NULL ,
`date`  datetime NOT NULL ,
INDEX `index_date` (`date`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_ips_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_ips_hour`;
CREATE TABLE `s_traffic_ips_hour` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`area`  int(20) NOT NULL ,
`ip`  bigint(20) NULL DEFAULT NULL ,
`ip_county`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`protocol`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`filing`  tinyint(1) NULL DEFAULT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`sumbytes`  bigint(20) NOT NULL ,
`links`  bigint(20) NOT NULL ,
`date`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=63174

;

-- ----------------------------
-- Table structure for s_traffic_ips_proto_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_ips_proto_hour`;
CREATE TABLE `s_traffic_ips_proto_hour` (
`ip`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`protocol`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(11) NOT NULL ,
`downbytes`  bigint(11) NOT NULL ,
`links`  bigint(11) NOT NULL ,
`date`  datetime NOT NULL ,
`filing`  tinyint(1) NULL DEFAULT NULL ,
INDEX `index_date` (`date`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_map_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_map_hour`;
CREATE TABLE `s_traffic_map_hour` (
`dip`  bigint(20) NOT NULL ,
`dip_used`  int(11) NOT NULL ,
`domain`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`volume`  bigint(20) NOT NULL ,
`sip_cnt`  bigint(20) NOT NULL ,
`segment`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`dip_company_id`  bigint(20) NOT NULL ,
`dip_county`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`dip_company_name`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`app_volume`  bigint(20) NOT NULL ,
`dip_longitude`  double NOT NULL ,
`dip_latitude`  double NOT NULL ,
`date`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_operators_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_operators_hour`;
CREATE TABLE `s_traffic_operators_hour` (
`operator`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`date`  datetime NOT NULL ,
INDEX `index_date_operator` (`date`, `operator`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_operators_proto_day
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_operators_proto_day`;
CREATE TABLE `s_traffic_operators_proto_day` (
`operator`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`protocol`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`bytes`  bigint(11) NOT NULL ,
`date`  datetime NOT NULL ,
INDEX `index_date_operator` (`date`, `operator`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_operators_proto_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_operators_proto_hour`;
CREATE TABLE `s_traffic_operators_proto_hour` (
`operator`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`protocol`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`sumbytes`  bigint(20) NOT NULL ,
`links`  bigint(20) NOT NULL ,
`date`  datetime NOT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_operators_protocols_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_operators_protocols_hour`;
CREATE TABLE `s_traffic_operators_protocols_hour` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`area`  int(20) NOT NULL ,
`operator`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`protocol`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sumbytes`  bigint(20) NULL DEFAULT NULL ,
`date`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=43144

;

-- ----------------------------
-- Table structure for s_traffic_protocols_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_protocols_hour`;
CREATE TABLE `s_traffic_protocols_hour` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`area`  int(20) NOT NULL ,
`protocol_class`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`protocol`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(11) NOT NULL ,
`downbytes`  bigint(11) NOT NULL ,
`links`  bigint(11) NOT NULL ,
`date`  datetime NOT NULL ,
`sumbytes`  bigint(20) NOT NULL ,
PRIMARY KEY (`id`),
INDEX `index_date` (`date`) USING BTREE ,
INDEX `index_protocol_class` (`protocol_class`) USING BTREE ,
INDEX `index_protocol` (`protocol`) USING BTREE ,
INDEX `index_date_protocal` (`date`, `protocol_class`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=495725

;

-- ----------------------------
-- Table structure for s_traffic_protocols_ips_day
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_protocols_ips_day`;
CREATE TABLE `s_traffic_protocols_ips_day` (
`protocol`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ip`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(11) NOT NULL ,
`downbytes`  bigint(11) NOT NULL ,
`links`  bigint(11) NOT NULL ,
`date`  datetime NOT NULL ,
INDEX `index_protocol_date` (`protocol`, `date`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for s_traffic_worldmap_hour
-- ----------------------------
DROP TABLE IF EXISTS `s_traffic_worldmap_hour`;
CREATE TABLE `s_traffic_worldmap_hour` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`area`  int(20) NOT NULL ,
`s_area`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`t_area`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`upbytes`  bigint(20) NOT NULL ,
`downbytes`  bigint(20) NOT NULL ,
`links`  bigint(20) NOT NULL ,
`slongitude`  double NOT NULL ,
`slatitude`  double NOT NULL ,
`tlongitude`  double NOT NULL ,
`tlatitude`  double NOT NULL ,
`date`  datetime NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=386723

;

-- ----------------------------
-- Auto increment value for s_traffic_dips_hour
-- ----------------------------
ALTER TABLE `s_traffic_dips_hour` AUTO_INCREMENT=7;

-- ----------------------------
-- Auto increment value for s_traffic_ips_hour
-- ----------------------------
ALTER TABLE `s_traffic_ips_hour` AUTO_INCREMENT=63174;

-- ----------------------------
-- Auto increment value for s_traffic_operators_protocols_hour
-- ----------------------------
ALTER TABLE `s_traffic_operators_protocols_hour` AUTO_INCREMENT=43144;

-- ----------------------------
-- Auto increment value for s_traffic_protocols_hour
-- ----------------------------
ALTER TABLE `s_traffic_protocols_hour` AUTO_INCREMENT=495725;

-- ----------------------------
-- Auto increment value for s_traffic_worldmap_hour
-- ----------------------------
ALTER TABLE `s_traffic_worldmap_hour` AUTO_INCREMENT=386723;
