/*
Navicat MySQL Data Transfer

Source Server         : PENGYIXIN
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : db_seckill

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-07-06 14:28:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(30) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `imgPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', 'iphone8', '6999', '10000', 'img/iphone.jpg');
INSERT INTO `t_goods` VALUES ('2', '华为P30', '4000', '100000', 'img/rongyao.jpg');
INSERT INTO `t_goods` VALUES ('3', '小米', '600', '1000', 'img/xiaomi.jpg');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) DEFAULT NULL,
  `totalPrice` decimal(10,0) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '1', '200', '2019-07-05 16:43:17', '0', '2');
INSERT INTO `t_order` VALUES ('2', '1', '200', '2019-07-05 16:44:44', '0', '2');
INSERT INTO `t_order` VALUES ('3', '1', '200', '2019-07-05 19:38:39', '0', '2');
INSERT INTO `t_order` VALUES ('4', '1', '200', '2019-07-05 19:38:43', '0', '2');
INSERT INTO `t_order` VALUES ('5', '1', '200', '2019-07-05 19:41:25', '0', '2');
INSERT INTO `t_order` VALUES ('6', '1', '200', '2019-07-05 19:42:10', '0', '2');

-- ----------------------------
-- Table structure for `t_seckillgoods`
-- ----------------------------
DROP TABLE IF EXISTS `t_seckillgoods`;
CREATE TABLE `t_seckillgoods` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) DEFAULT NULL,
  `seckillPrice` decimal(10,0) DEFAULT NULL,
  `seckillStock` int(11) DEFAULT NULL,
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `staticUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seckillgoods
-- ----------------------------
INSERT INTO `t_seckillgoods` VALUES ('1', '1', '200', '94', '2019-07-04 17:05:06', '2019-07-06 18:30:11', 'seckill_detail1.html');
INSERT INTO `t_seckillgoods` VALUES ('2', '2', '100', '95', '2019-07-03 14:30:35', '2019-07-05 14:30:40', 'seckill_detail2.html');
INSERT INTO `t_seckillgoods` VALUES ('3', '3', '150', '100', '2019-07-04 14:30:57', '2019-07-05 14:31:01', 'seckill_detail3.html');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '12345612345', '123456', 'zhengzhou');
INSERT INTO `t_user` VALUES ('2', '15713991187', '111111', 'zhengzhou');
