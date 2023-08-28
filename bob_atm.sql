/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : bob_atm

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 28/08/2023 09:57:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for atm
-- ----------------------------
DROP TABLE IF EXISTS `atm`;
CREATE TABLE `atm` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `bankcardId` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of atm
-- ----------------------------
BEGIN;
INSERT INTO `atm` (`id`, `status`, `bankcardId`) VALUES (1, 'true', 1001);
COMMIT;

-- ----------------------------
-- Table structure for bankcard
-- ----------------------------
DROP TABLE IF EXISTS `bankcard`;
CREATE TABLE `bankcard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `owner` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of bankcard
-- ----------------------------
BEGIN;
INSERT INTO `bankcard` (`id`, `password`, `owner`, `balance`) VALUES (1001, '123456', '1', 100.10);
INSERT INTO `bankcard` (`id`, `password`, `owner`, `balance`) VALUES (1002, '12345678', '1', 210.20);
INSERT INTO `bankcard` (`id`, `password`, `owner`, `balance`) VALUES (1003, '666666', '2', 0.00);
COMMIT;

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `idCard` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `phoneNumber` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `card_list` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of consumer
-- ----------------------------
BEGIN;
INSERT INTO `consumer` (`id`, `name`, `idCard`, `phoneNumber`, `balance`, `card_list`) VALUES (1, 'xiaowang', '111111111111111111', '17865555555', 0.00, '[1001,1002]');
INSERT INTO `consumer` (`id`, `name`, `idCard`, `phoneNumber`, `balance`, `card_list`) VALUES (2, 'xiaozhang', '222222222222222222', '15510111111', 0.00, '[1003]');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
