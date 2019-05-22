/*
Navicat MySQL Data Transfer

Source Server         : 47.94.159.51
Source Server Version : 80013
Source Host           : 47.94.159.51:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-05-22 11:49:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `is_secret_required` tinyint(4) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `is_scoped` tinyint(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `registered_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity_seconds` int(11) DEFAULT NULL,
  `refresh_token_validity_seconds` int(11) DEFAULT NULL,
  `is_auto_approve` tinyint(4) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', null, null, '$2a$10$Z6RR.gzgXevJ/v7vbsEOPuBe7ub3Jx6y3xy0I8FCOOAK1r1Lxw0LW', null, 'server', 'refresh_token,password', null, null, '3000', '6000', null, null);
INSERT INTO `oauth_client_details` VALUES ('client_gateway', null, null, '$2a$10$LXEYNYOMy92iT5Z4NY1U.uE0g8/unc3CZQp0IA1Xnpuqig7z9X6Vq', null, 'server', 'refresh_token,password', null, null, '3000', '6000', null, null);

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` text,
  `method` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '/dept/**', null, 'framework-resource');
INSERT INTO `sys_resources` VALUES ('2', '/**', null, 'framework-resource-2');
INSERT INTO `sys_resources` VALUES ('6', '/**', null, 'framework-resource');
INSERT INTO `sys_resources` VALUES ('7', '/actuator/**', null, 'framework-config');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resources_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('1', '1', '1');
INSERT INTO `sys_role_resources` VALUES ('2', '2', '2');
INSERT INTO `sys_role_resources` VALUES ('3', '1', '2');
INSERT INTO `sys_role_resources` VALUES ('4', '2', '1');
INSERT INTO `sys_role_resources` VALUES ('5', '2', '6');
INSERT INTO `sys_role_resources` VALUES ('6', '2', '7');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$1PmEwNeWQSJYWTd9V2OXH.XiAC/vte0yhmvFDw24eWSqjYq9qeCre');
INSERT INTO `sys_user` VALUES ('2', 'user1', '$2a$10$UeLrrUADflEH0kSCV1fvv.P2W11g9pNJqkT6FbAiRKj9de0BQl7cm');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1', '2');

-- ----------------------------
-- Table structure for sys_zuul_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_zuul_route`;
CREATE TABLE `sys_zuul_route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `strip_prefix` varchar(255) DEFAULT NULL,
  `retryable` varchar(255) DEFAULT NULL,
  `enabled` varchar(255) DEFAULT NULL,
  `sensitiveHeaders_list` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_zuul_route
-- ----------------------------
INSERT INTO `sys_zuul_route` VALUES ('1', '/testRoute/**', 'framework-resource', null, '1', null, null, null, '2018-11-26 15:17:56', '2018-11-26 15:17:56', null);
INSERT INTO `sys_zuul_route` VALUES ('3', '/auth/**', 'framework-auth', null, '1', null, null, ',   ', '2018-11-26 15:17:49', '2018-11-26 15:17:49', null);
INSERT INTO `sys_zuul_route` VALUES ('4', '/resource/**', 'framework-resource', null, '1', null, null, null, '2018-11-26 15:02:36', '2018-11-26 15:02:36', null);
INSERT INTO `sys_zuul_route` VALUES ('5', '/resource2/**', 'framework-resource-2', null, '1', null, null, null, '2018-11-26 14:57:49', '2018-11-26 14:57:49', null);
INSERT INTO `sys_zuul_route` VALUES ('6', '/test/abcd/dd/**', 'framework-resource-2', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_zuul_route` VALUES ('7', '/configServer/**', 'framework-config', null, '1', null, null, ',  ', '2018-11-29 10:24:52', '2018-11-29 10:24:52', null);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'resource2-张三');

-- ----------------------------
-- Table structure for test2
-- ----------------------------
DROP TABLE IF EXISTS `test2`;
CREATE TABLE `test2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of test2
-- ----------------------------
INSERT INTO `test2` VALUES ('1', 'resource2-王五');
INSERT INTO `test2` VALUES ('2', 'resource2-王五');
INSERT INTO `test2` VALUES ('3', 'resource2-王五');
INSERT INTO `test2` VALUES ('4', 'resource2-王五');
INSERT INTO `test2` VALUES ('5', 'resource2-王五');
INSERT INTO `test2` VALUES ('6', 'resource2-王五');
INSERT INTO `test2` VALUES ('7', 'resource2-王五');
INSERT INTO `test2` VALUES ('8', 'resource2-王五');
INSERT INTO `test2` VALUES ('9', 'resource2-王五');
INSERT INTO `test2` VALUES ('10', 'resource2-王五');
INSERT INTO `test2` VALUES ('11', 'resource2-王五');
INSERT INTO `test2` VALUES ('12', 'resource2-王五');
INSERT INTO `test2` VALUES ('13', 'resource2-王五');
INSERT INTO `test2` VALUES ('14', 'resource2-王五');
INSERT INTO `test2` VALUES ('15', 'resource2-王五');
INSERT INTO `test2` VALUES ('16', 'resource2-王五');
SET FOREIGN_KEY_CHECKS=1;
