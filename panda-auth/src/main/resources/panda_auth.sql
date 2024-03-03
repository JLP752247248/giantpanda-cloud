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

 Date: 12/08/2022 17:00:47
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
  `permission` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限内容，这里可以填写具体资源uri',
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
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `create_time` datetime not null default '1949-10-01' COMMENT '创建时间',
  `update_time` datetime not null default '1949-10-01' COMMENT '更新时间',
  `status` smallint(255) NOT NULL DEFAULT 0 COMMENT '状态 0停用 1启用',
  `parent_id` bigint(10) NOT NULL DEFAULT -1 COMMENT '父角色id',
  `desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'anonymous', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, -1, '匿名角色', NULL);
INSERT INTO `sys_role` VALUES (2, 'superadmin', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, -1, '顶级管理员', NULL);
INSERT INTO `sys_role` VALUES (3, 'admin', '2022-08-12 14:49:16', '2022-08-12 14:49:24', 1, 2, '管理员', NULL);

-- ----------------------------
-- Table structure for sys_sequence
-- ----------------------------
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence`  (
  `seq_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序列键',
  `seq_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序列名',
  `seq_value` bigint(11) NOT NULL DEFAULT 0 COMMENT '序列值',
  PRIMARY KEY (`seq_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_sequence
-- ----------------------------
INSERT INTO `sys_sequence` VALUES ('user_key', '用户表主键', 100);
INSERT INTO `sys_sequence` VALUES ('xxx', 'dfasdf', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，密文',
  `phone` bigint(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `birth_date` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT 1 COMMENT '状态：1 有效  2 注销 3 禁用',
  `create_time` datetime NOT NULL DEFAULT '1949-10-01',
  `update_time` datetime NOT NULL DEFAULT '1949-10-01',
  `login_dt` datetime DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_uid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (7, 'xx', '343fdagdfsgfd', NULL, NULL, NULL, NULL, '2022-08-10 02:11:54', '2022-08-10 02:11:54', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (100, 'testuser3', '1234', 13951554654, NULL, NULL, NULL, '2022-08-11 16:41:39', '2022-08-11 16:41:39', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2000004, 'testuser1', '1234', 13951554654, NULL, NULL, NULL, '2022-08-10 14:17:18', '2022-08-10 14:17:18', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2000005, 'jianglp', '1234', 13951554654, NULL, NULL, NULL, '2022-08-10 14:18:01', '2022-08-10 14:18:01', NULL, NULL, NULL);

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


SET FOREIGN_KEY_CHECKS = 1;
