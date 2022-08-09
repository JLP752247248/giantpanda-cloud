/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : panda_auth

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 09/08/2022 14:16:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_addr
-- ----------------------------
DROP TABLE IF EXISTS `sys_addr`;
CREATE TABLE `sys_addr`  (
  `user_id` bigint(20) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `country` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `zip_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_addr
-- ----------------------------
INSERT INTO `sys_addr` VALUES (2, 'Via Filzi 2', 'Borgo Teresiano', 'Florence', 'Italy', '50100');
INSERT INTO `sys_addr` VALUES (7, 'Piazza Grande 12', 'Gran canal', 'Venice', 'Italy', '30100');
INSERT INTO `sys_addr` VALUES (8, 'Via Roma 2', 'Borgo Teresiano', 'Trieste', 'Italy', '34100');

-- ----------------------------
-- Table structure for sys_contact
-- ----------------------------
DROP TABLE IF EXISTS `sys_contact`;
CREATE TABLE `sys_contact`  (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `skype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `facebook` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `linkedin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `website` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_contact
-- ----------------------------
INSERT INTO `sys_contact` VALUES (1, 'andrea.test@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_contact` VALUES (2, 'mario.rossi@gmail.com', NULL, NULL, NULL, NULL, NULL, 'test contact note on mario rossi');
INSERT INTO `sys_contact` VALUES (3, 'stefania.verdi@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_contact` VALUES (4, 'veronica.gialli@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_contact` VALUES (5, 'mark.green@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_contact` VALUES (6, 'paul.ludwing@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_contact` VALUES (7, 'jennifer.red@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_contact` VALUES (8, 'karina.yellow@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` int(11) DEFAULT 1,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission`(`permission`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_perm
-- ----------------------------
INSERT INTO `sys_perm` VALUES (1, 'LOGIN', 1, 'User Login');
INSERT INTO `sys_perm` VALUES (2, 'VIEW_PROFILE', 1, 'View user profile');
INSERT INTO `sys_perm` VALUES (3, 'ADMIN_USER_DATA', 1, 'Manage user data');
INSERT INTO `sys_perm` VALUES (4, 'ADMIN_STATISTICS', 0, 'View statistical graphs');

-- ----------------------------
-- Table structure for sys_perm_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm_role`;
CREATE TABLE `sys_perm_role`  (
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`permission_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_perm_role
-- ----------------------------
INSERT INTO `sys_perm_role` VALUES (1, 1);
INSERT INTO `sys_perm_role` VALUES (2, 1);
INSERT INTO `sys_perm_role` VALUES (1, 2);
INSERT INTO `sys_perm_role` VALUES (2, 2);
INSERT INTO `sys_perm_role` VALUES (3, 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role`(`role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, 'ADMINISTRATOR');
INSERT INTO `sys_role` VALUES (1, 'USER');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `surname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT 1,
  `creation_dt` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_dt` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_dt` timestamp(0) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `secured` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'andrea', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Andrea', 'Test', 0, '1977-08-14', 1, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (2, 'mario', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Mario', 'Rossi', 0, NULL, 1, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (3, 'stefania', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Stefania', 'Verdi', 1, NULL, 1, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (4, 'veronica', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Veronica', 'Gialli', 1, NULL, 1, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (5, 'mark', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Mark', 'Green', 0, NULL, 1, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (6, 'paul', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Paul', 'Ludwing', 0, NULL, 0, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (7, 'jennifer', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Jennifer', 'Red', 0, NULL, 1, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (8, 'karina', '1d/NZaEqNgtEomytAPrwm/+QjmbudLg33oeEk77Xh88=', 'Karina', 'Yellow', 1, NULL, 1, '2022-08-03 14:01:18', '2022-08-03 14:01:18', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 1);
INSERT INTO `sys_user_role` VALUES (3, 1);
INSERT INTO `sys_user_role` VALUES (4, 1);
INSERT INTO `sys_user_role` VALUES (5, 1);
INSERT INTO `sys_user_role` VALUES (6, 1);
INSERT INTO `sys_user_role` VALUES (7, 1);
INSERT INTO `sys_user_role` VALUES (8, 1);

-- ----------------------------
-- View structure for enabled_users
-- ----------------------------
DROP VIEW IF EXISTS `enabled_users`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `enabled_users` AS select `users`.`username` AS `username`,`contacts`.`email` AS `email`,`contacts`.`phone` AS `phone`,`users`.`creation_dt` AS `creation_dt`,`users`.`updated_dt` AS `updated_dt`,`users`.`login_dt` AS `login_dt`,`users`.`secured` AS `secured` from (`users` join `contacts` on((`contacts`.`user_id` = `users`.`id`))) where (`users`.`enabled` is true);

SET FOREIGN_KEY_CHECKS = 1;
