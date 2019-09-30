/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2019-09-30 15:19:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment,
  `userName` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `age` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '1', '1', '1');
INSERT INTO `tb_user` VALUES ('2', '2', '2', '2');
INSERT INTO `tb_user` VALUES ('3', '3', '3', '3');
INSERT INTO `tb_user` VALUES ('4', '4', '4', '4');
INSERT INTO `tb_user` VALUES ('5', '5', '5', '5');
INSERT INTO `tb_user` VALUES ('6', '6', '6', '6');
INSERT INTO `tb_user` VALUES ('7', '7', '7', '7');
INSERT INTO `tb_user` VALUES ('8', '8', '8', '8');
INSERT INTO `tb_user` VALUES ('9', '9', '9', '9');
INSERT INTO `tb_user` VALUES ('10', '10', '10', '10');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', '1234556');
INSERT INTO `user` VALUES ('2', 'zhangsan', '1234556');
INSERT INTO `user` VALUES ('3', 'zhangsan', '1234556');
INSERT INTO `user` VALUES ('4', 'zhangsan', '1234556');
INSERT INTO `user` VALUES ('5', 'zhangsan', '1234556');
INSERT INTO `user` VALUES ('6', 'zhangsan', '1234556');
INSERT INTO `user` VALUES ('7', 'zhangsan', '1234556');
INSERT INTO `user` VALUES ('8', 'zhangsan', '1234556');
