/*
MySQL Data Transfer
Source Host: localhost
Source Database: badmin
Target Host: localhost
Target Database: badmin
Date: 2017/7/3 9:59:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(100) NOT NULL COMMENT '用户id',
  `loginTime` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `loginIp` varchar(50) DEFAULT '' COMMENT 'ip地址',
  `ipInfoCountry` varchar(50) DEFAULT '' COMMENT 'ip地址所在国家',
  `ipInfoRegion` varchar(30) DEFAULT '' COMMENT 'ip地址所在省份',
  `ipInfoCity` varchar(30) DEFAULT '' COMMENT 'ip所属城市',
  `ipInfoIsp` varchar(20) DEFAULT '' COMMENT 'ip地址所属运营商',
  `loginType` tinyint(4) DEFAULT NULL COMMENT '登录来源方式，1：web,2:android',
  `loginDesc` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `sys_login_log_ibfk_1` (`userId`),
  KEY `login_time` (`loginTime`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8 COMMENT='系统登录日志';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pId` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `sort` int(11) NOT NULL COMMENT '排序',
  `isMenu` bit(1) NOT NULL COMMENT '是否菜单',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标志',
  PRIMARY KEY (`id`),
  KEY `p_isEnable` (`isEnable`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for sys_re_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_role_permission`;
CREATE TABLE `sys_re_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `permissionId` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_PERM` (`permissionId`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_PERM` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=485 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Table structure for sys_re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_re_user_role`;
CREATE TABLE `sys_re_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID ',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_USER` (`userId`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_USER` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `code` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL COMMENT '排序',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`),
  KEY `r_isEnable` (`isEnable`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_settings
-- ----------------------------
DROP TABLE IF EXISTS `sys_settings`;
CREATE TABLE `sys_settings` (
  `k` varchar(50) NOT NULL DEFAULT '',
  `v` text NOT NULL,
  PRIMARY KEY (`k`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码(加密)',
  `lastLoginIp` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` int(11) NOT NULL COMMENT '登录总次数',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`),
  KEY `u_isEnable` (`isEnable`),
  KEY `u_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('1', '2', '2017-06-27 17:42:40', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('2', 'admin', '2017-06-27 18:02:41', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('3', 'admin', '2017-06-27 18:04:08', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('16', 'admin', '2017-06-28 12:15:28', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('17', 'admin', '2017-06-28 12:23:37', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('18', 'admin', '2017-06-28 12:25:13', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('19', 'admin', '2017-06-28 12:29:07', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('20', 'admin', '2017-06-28 12:29:59', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('21', 'admin', '2017-06-28 12:32:19', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('22', 'admin', '2017-06-28 12:34:47', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('23', 'admin', '2017-06-28 13:32:29', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('24', 'admin', '2017-06-28 13:34:14', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('25', 'admin', '2017-06-28 13:39:37', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('26', 'admin', '2017-06-28 13:40:16', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('27', 'admin', '2017-06-28 13:43:00', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('28', 'admin', '2017-06-28 13:58:53', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('29', 'admin', '2017-06-28 14:12:18', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('30', 'admin', '2017-06-28 14:13:46', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('31', 'admin', '2017-06-28 14:21:59', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('32', 'admin', '2017-06-28 14:33:55', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('33', 'test', '2017-06-28 14:42:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('34', 'test', '2017-06-28 14:42:57', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('35', 'admin', '2017-06-28 14:54:57', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('36', 'admin', '2017-06-28 14:56:44', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('37', 'admin', '2017-06-28 14:57:15', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('38', 'admin', '2017-06-28 14:59:26', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('39', 'admin', '2017-06-28 15:01:35', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('40', 'admin', '2017-06-28 15:06:39', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('41', 'test', '2017-06-28 15:07:38', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('42', 'admin', '2017-06-28 15:07:55', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('43', 'test', '2017-06-28 15:08:25', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('44', 'admin', '2017-06-28 15:08:42', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('45', 'admin', '2017-06-28 15:16:10', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('46', 'test', '2017-06-28 15:16:26', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('47', 'admin', '2017-06-28 15:21:25', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('48', 'test', '2017-06-28 15:21:58', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('49', 'admin', '2017-06-28 15:22:47', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('50', 'test', '2017-06-28 15:23:12', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('51', 'test', '2017-06-28 15:23:25', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('52', 'admin', '2017-06-28 15:23:33', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('53', 'test', '2017-06-28 15:24:07', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('54', 'admin', '2017-06-28 15:32:50', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('55', 'admin', '2017-06-28 15:35:45', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('56', 'test', '2017-06-28 15:36:17', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('57', 'admin', '2017-06-28 15:37:02', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('58', 'test', '2017-06-28 15:37:40', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('59', 'admin', '2017-06-28 15:41:22', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('60', 'test', '2017-06-28 15:41:33', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('61', 'admin', '2017-06-28 15:44:37', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('62', 'admin', '2017-06-28 15:46:15', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('63', 'admin', '2017-06-28 15:57:26', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('64', 'admin', '2017-06-28 15:58:28', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('65', 'admin', '2017-06-28 16:12:30', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('66', 'admin', '2017-06-28 16:17:30', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('67', 'admin', '2017-06-28 16:19:05', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('68', 'admin', '2017-06-28 16:19:31', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('69', 'admin', '2017-06-28 16:20:31', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('70', 'admin', '2017-06-28 16:28:35', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('71', 'admin', '2017-06-28 17:04:03', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('72', 'admin', '2017-06-28 17:34:53', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('73', 'admin', '2017-06-28 17:43:22', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('74', 'admin', '2017-06-28 17:49:22', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('75', 'admin', '2017-06-29 09:01:11', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('76', 'admin', '2017-06-29 09:03:04', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('77', 'admin', '2017-06-29 09:21:22', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('78', 'admin', '2017-06-29 09:51:46', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('79', 'admin', '2017-06-29 09:59:45', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('80', 'admin', '2017-06-29 10:34:57', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('81', 'admin', '2017-06-29 10:37:30', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('82', 'admin', '2017-06-29 11:31:44', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('83', 'admin', '2017-06-29 11:35:18', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('84', 'admin', '2017-06-29 13:33:04', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('85', 'admin', '2017-06-29 13:50:44', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('86', 'admin', '2017-06-29 13:54:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('87', 'admin', '2017-06-29 14:00:02', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('88', 'admin', '2017-06-29 14:01:35', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('89', 'admin', '2017-06-29 14:04:07', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('90', 'admin', '2017-06-29 14:10:50', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('91', 'admin', '2017-06-29 14:31:46', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('92', 'admin', '2017-06-29 14:35:28', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('93', 'admin', '2017-06-29 14:35:28', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('94', 'admin', '2017-06-29 14:36:48', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('95', 'admin', '2017-06-29 14:37:07', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('96', 'admin', '2017-06-29 14:38:30', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('97', 'admin', '2017-06-29 14:52:35', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('98', 'admin', '2017-06-29 14:52:49', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('99', 'admin', '2017-06-29 14:53:50', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('100', 'admin', '2017-06-29 14:53:54', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('101', 'admin', '2017-06-29 14:54:12', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('102', 'admin', '2017-06-29 14:58:19', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('103', 'admin', '2017-06-29 14:59:21', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('104', 'admin', '2017-06-29 14:59:37', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('105', 'admin', '2017-06-29 15:00:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('106', 'admin', '2017-06-29 15:00:54', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('107', 'admin', '2017-06-29 15:01:22', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('108', 'admin', '2017-06-29 15:04:05', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('109', 'admin', '2017-06-29 15:06:32', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('110', 'admin', '2017-06-29 15:07:42', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('111', 'admin', '2017-06-29 15:11:55', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('112', 'admin', '2017-06-29 15:12:13', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('113', 'admin', '2017-06-29 15:12:56', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('114', 'admin', '2017-06-29 15:29:20', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('115', 'admin', '2017-06-29 15:29:34', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('116', 'admin', '2017-06-29 15:35:21', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('117', 'admin', '2017-06-29 15:35:25', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('118', 'admin', '2017-06-29 15:38:11', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('119', 'admin', '2017-06-29 15:38:15', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('120', 'admin', '2017-06-29 15:43:12', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('121', 'admin', '2017-06-29 15:43:15', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('122', 'admin', '2017-06-29 15:43:54', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('123', 'admin', '2017-06-29 15:48:33', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('124', 'admin', '2017-06-29 15:48:41', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('125', 'admin', '2017-06-29 15:52:51', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('126', 'admin', '2017-06-29 15:52:57', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('127', 'admin', '2017-06-29 15:54:11', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('128', 'admin', '2017-06-29 15:58:49', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('129', 'admin', '2017-06-29 15:59:00', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('130', 'admin', '2017-06-29 16:00:31', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('131', 'admin', '2017-06-29 16:16:34', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('132', 'admin', '2017-06-29 16:16:38', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('133', 'admin', '2017-06-29 16:23:38', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('134', 'admin', '2017-06-29 16:23:50', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('135', 'admin', '2017-06-29 16:24:04', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('136', 'admin', '2017-06-29 16:25:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('137', 'admin', '2017-06-29 16:27:49', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('138', 'admin', '2017-06-29 16:35:49', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('139', 'admin', '2017-06-29 16:36:53', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('140', 'admin', '2017-06-29 16:38:13', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('141', 'admin', '2017-06-29 16:40:56', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('142', 'admin', '2017-06-29 16:42:46', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('143', 'admin', '2017-06-29 16:42:47', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('144', 'admin', '2017-06-29 16:43:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('145', 'admin', '2017-06-29 16:47:18', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('146', 'admin', '2017-06-29 16:53:14', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('147', 'admin', '2017-06-29 16:54:03', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('148', 'admin', '2017-06-29 17:02:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('149', 'admin', '2017-06-29 17:07:44', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('150', 'admin', '2017-06-29 17:09:59', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('151', 'admin', '2017-06-29 17:12:35', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('152', 'admin', '2017-06-29 17:22:11', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('153', 'admin', '2017-06-29 17:33:54', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('154', 'admin', '2017-06-29 17:50:27', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('155', 'admin', '2017-06-29 18:17:22', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('156', 'admin', '2017-06-29 18:20:45', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('157', 'admin', '2017-06-29 18:22:31', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('158', 'admin', '2017-06-29 18:25:24', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('159', 'admin', '2017-06-30 08:17:14', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('160', 'admin', '2017-06-30 08:53:52', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('161', 'admin', '2017-06-30 08:58:21', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('162', 'admin', '2017-06-30 09:05:52', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('163', 'admin', '2017-06-30 09:19:32', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('164', 'admin', '2017-06-30 09:33:36', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('165', 'admin', '2017-06-30 09:34:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('166', 'admin', '2017-06-30 09:53:47', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('167', 'admin', '2017-06-30 09:57:35', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('168', 'admin', '2017-06-30 10:24:27', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('169', 'admin', '2017-06-30 10:34:47', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('170', 'admin', '2017-06-30 10:35:04', null, '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('171', 'admin', '2017-06-30 10:35:04', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('172', 'admin', '2017-06-30 10:35:13', null, '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('173', 'admin', '2017-06-30 10:35:13', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('174', 'admin', '2017-06-30 10:40:17', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('175', 'admin', '2017-06-30 10:40:32', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('176', 'admin', '2017-06-30 10:43:48', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('177', 'admin', '2017-06-30 10:43:56', null, '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('178', 'admin', '2017-06-30 10:43:56', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('179', 'admin', '2017-06-30 10:44:26', null, '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('180', 'admin', '2017-06-30 10:44:26', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('181', 'admin', '2017-06-30 10:45:11', null, '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('182', 'admin', '2017-06-30 10:45:11', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('183', 'admin', '2017-06-30 10:45:16', null, '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('184', 'admin', '2017-06-30 10:45:16', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('185', 'admin', '2017-06-30 10:47:40', null, '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('186', 'admin', '2017-06-30 10:47:40', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('187', 'admin', '2017-06-30 10:53:28', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('188', 'admin', '2017-06-30 10:53:34', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('189', 'admin', '2017-06-30 10:53:34', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('190', 'admin', '2017-06-30 10:59:50', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('191', 'admin', '2017-06-30 10:59:50', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('192', 'admin', '2017-06-30 11:00:10', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('193', 'admin', '2017-06-30 11:02:36', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('194', 'admin', '2017-06-30 11:02:45', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('195', 'admin', '2017-06-30 11:03:25', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('196', 'admin', '2017-06-30 11:05:50', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('197', 'admin', '2017-06-30 11:06:08', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('198', 'admin', '2017-06-30 11:07:03', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('199', 'admin', '2017-06-30 11:08:47', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('200', 'admin', '2017-06-30 11:10:45', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('201', 'admin', '2017-06-30 11:13:05', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('202', 'admin', '2017-06-30 11:21:26', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('203', 'admin', '2017-06-30 11:21:38', '192.168.1.122', '', '', '', '', '1', '正常下线');
INSERT INTO `sys_login_log` VALUES ('204', 'admin', '2017-06-30 11:21:55', '192.168.1.122', '', '', '', '', '1', '正常下线');
INSERT INTO `sys_login_log` VALUES ('205', 'admin', '2017-06-30 11:22:03', '192.168.1.122', '', '', '', '', '1', '正常下线');
INSERT INTO `sys_login_log` VALUES ('206', 'admin', '2017-06-30 11:24:32', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('207', 'admin', '2017-06-30 11:24:41', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('208', 'admin', '2017-06-30 11:44:13', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('209', 'admin', '2017-06-30 11:50:57', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('210', 'admin', '2017-06-30 11:53:54', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('211', 'admin', '2017-06-30 11:59:06', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('212', 'admin', '2017-06-30 11:59:25', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('213', 'admin', '2017-06-30 11:59:25', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('214', 'admin', '2017-06-30 12:28:49', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('215', 'admin', '2017-06-30 12:28:49', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('216', 'admin', '2017-06-30 12:29:19', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('217', 'admin', '2017-06-30 12:29:19', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('218', 'admin', '2017-06-30 13:03:18', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('219', 'admin', '2017-06-30 13:03:18', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('220', 'admin', '2017-06-30 13:03:44', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('221', 'admin', '2017-06-30 13:12:54', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('222', 'admin', '2017-06-30 13:13:01', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('223', 'admin', '2017-06-30 13:13:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('224', 'admin', '2017-06-30 13:13:18', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('225', 'admin', '2017-06-30 13:13:18', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('226', 'admin', '2017-06-30 13:15:20', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('227', 'admin', '2017-06-30 13:15:20', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('228', 'admin', '2017-06-30 13:15:29', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('229', 'admin', '2017-06-30 13:15:29', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('230', 'admin', '2017-06-30 13:25:27', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('231', 'admin', '2017-06-30 13:25:40', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('232', 'admin', '2017-06-30 13:25:40', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('233', 'admin', '2017-06-30 13:29:17', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('234', 'admin', '2017-06-30 13:29:23', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('235', 'admin', '2017-06-30 13:29:23', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('236', 'admin', '2017-06-30 13:29:51', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('237', 'admin', '2017-06-30 13:29:52', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('238', 'admin', '2017-06-30 13:33:10', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('239', 'admin', '2017-06-30 13:33:28', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('240', 'admin', '2017-06-30 13:33:28', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('241', 'admin', '2017-06-30 13:38:46', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('242', 'test', '2017-06-30 13:38:58', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('243', 'admin', '2017-06-30 13:47:39', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('244', 'test', '2017-06-30 13:47:53', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('245', 'admin', '2017-06-30 13:50:29', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('246', 'admin', '2017-06-30 13:50:59', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('247', 'admin', '2017-06-30 13:51:00', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('248', 'admin', '2017-06-30 13:51:26', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('249', 'test', '2017-06-30 13:51:36', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('250', 'admin', '2017-06-30 14:01:44', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('251', 'test', '2017-06-30 14:01:54', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('252', 'admin', '2017-06-30 14:03:51', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('253', 'test', '2017-06-30 14:04:01', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('254', 'admin', '2017-06-30 14:37:00', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('255', 'admin', '2017-06-30 14:37:29', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('256', 'admin', '2017-06-30 14:38:24', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('257', 'admin', '2017-06-30 14:38:24', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('258', 'admin', '2017-06-30 14:39:05', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('259', 'admin', '2017-06-30 14:39:18', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('260', 'admin', '2017-06-30 14:42:33', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('261', 'admin', '2017-06-30 14:42:58', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('262', 'admin', '2017-06-30 14:43:48', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('263', 'admin', '2017-06-30 14:43:48', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('264', 'admin', '2017-06-30 14:44:26', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('265', 'admin', '2017-06-30 14:44:39', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('266', 'admin', '2017-06-30 14:47:06', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('267', 'admin', '2017-06-30 17:35:47', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('268', 'test', '2017-06-30 17:36:38', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('269', 'admin', '2017-06-30 17:37:24', '192.168.1.123', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('270', 'admin', '2017-06-30 17:39:02', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('271', 'admin', '2017-06-30 17:43:22', '192.168.1.122', '', '', '', '', '1', '账号异地登录，被迫强制下线');
INSERT INTO `sys_login_log` VALUES ('272', 'admin', '2017-06-30 17:43:22', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_login_log` VALUES ('273', 'admin', '2017-06-30 17:52:18', '192.168.1.122', '', '', '', '', '1', '登录成功');
INSERT INTO `sys_permission` VALUES ('3', '46', '用户管理', 'admin/user', '1', '', '', 'fa-user', '');
INSERT INTO `sys_permission` VALUES ('4', '46', '角色管理', 'admin/role', '2', '', '', 'fa-user-secret', '');
INSERT INTO `sys_permission` VALUES ('5', '46', '权限管理', 'admin/permission', '3', '', '', 'fa-key', '');
INSERT INTO `sys_permission` VALUES ('10', '3', '用户添加', 'admin/user/edit', '2', '', '', 'fa-plus-circle blue', 'sys:user:edit');
INSERT INTO `sys_permission` VALUES ('11', '3', '用户禁用', 'admin/user/enable', '4', '', '', 'fa-lock orange', 'sys:user:enable');
INSERT INTO `sys_permission` VALUES ('12', '3', '用户启用', 'admin/user/enable', '3', '', '', 'fa-unlock green', 'sys:user:enable');
INSERT INTO `sys_permission` VALUES ('13', '3', '用户删除', 'admin/user/delete', '1', '', '', 'fa-trash-o red', 'sys:user:delete');
INSERT INTO `sys_permission` VALUES ('14', '3', '重置密码', 'admin/user/resetPassword', '5', '', '', 'fa-key grey', 'sys:user:resetPassword');
INSERT INTO `sys_permission` VALUES ('15', '3', '分配角色', 'admin/user/allocate', '6', '', '', 'fa-cog grey', 'sys:user:allocate');
INSERT INTO `sys_permission` VALUES ('16', '4', '角色新增', 'admin/role/edit', '5', '', '', 'fa-plus-circle blue', 'sys:role:edit');
INSERT INTO `sys_permission` VALUES ('17', '4', '角色禁用', 'admin/role/enable', '4', '', '', 'fa-lock orange', 'sys:role:enable');
INSERT INTO `sys_permission` VALUES ('18', '4', '角色启用', 'admin/role/enable', '3', '', '', 'fa-unlock green', 'sys:role:enable');
INSERT INTO `sys_permission` VALUES ('19', '4', '角色删除', 'admin/role/delete', '2', '', '', 'fa-trash-o red', 'sys:role:delete');
INSERT INTO `sys_permission` VALUES ('20', '4', '角色授权', 'admin/role/allocate', '1', '', '', 'fa-cog grey', 'sys:role:allocate');
INSERT INTO `sys_permission` VALUES ('24', '4', '角色列表', 'admin/role', '6', '', '', '', 'sys:role:list');
INSERT INTO `sys_permission` VALUES ('25', '5', '权限列表', 'admin/permission/nodes', '1', '', '', '', 'sys:permission:list');
INSERT INTO `sys_permission` VALUES ('27', '3', '用户编辑', 'admin/user/save', '1', '', '', '', 'sys:user:edit');
INSERT INTO `sys_permission` VALUES ('28', '4', '角色编辑', 'admin/role/save', '1', '', '', '', 'sys:role:edit');
INSERT INTO `sys_permission` VALUES ('29', '5', '权限保存', 'admin/permission/save', '1', '', '', '', 'sys:permission:edit');
INSERT INTO `sys_permission` VALUES ('30', '5', '权限删除', 'admin/permission/delete', '1', '', '', '', 'sys:permission:delete');
INSERT INTO `sys_permission` VALUES ('44', '5', '权限添加', 'admin/permission/save', '4', '', '', '', 'sys:permission:edit');
INSERT INTO `sys_permission` VALUES ('45', null, '系统管理', 'sys', '1', '', '', 'fa-cogs', '');
INSERT INTO `sys_permission` VALUES ('46', null, '用户中心', 'user', '2', '', '', 'fa-user-circle', '');
INSERT INTO `sys_permission` VALUES ('48', '45', '登录日志', 'admin/loginlog', '1', '', '', 'fa-history', 'sys:authlog');
INSERT INTO `sys_permission` VALUES ('49', '3', '用户列表', 'sys/user', '7', '', '', '', 'sys:user:list');
INSERT INTO `sys_permission` VALUES ('50', '48', '日志清理', 'admin/loginlog/delete', '1', '', '', '', 'sys:loginlog:delete');
INSERT INTO `sys_permission` VALUES ('51', '48', '日志列表', 'admin/loginlog', '2', '', '', '', 'sys:loginlog:list');
INSERT INTO `sys_permission` VALUES ('52', '46', '在线用户', 'admin/online', '4', '', '', 'fa-crop', '');
INSERT INTO `sys_permission` VALUES ('53', '52', '在线用户列表', 'admin/online/page', '1', '', '', '', 'sys:online:list');
INSERT INTO `sys_permission` VALUES ('54', '52', '用户下线', 'admin/online/kickout', '2', '', '', '', 'sys:online:kill');
INSERT INTO `sys_permission` VALUES ('55', '52', '在线用户刷新', 'admin/online', '3', '', '', '', 'sys:online:refresh');
INSERT INTO `sys_permission` VALUES ('56', '45', '系统设置', 'admin/settings', '2', '', '', 'fa-gear', '');
INSERT INTO `sys_re_role_permission` VALUES ('381', '4', '45');
INSERT INTO `sys_re_role_permission` VALUES ('382', '4', '48');
INSERT INTO `sys_re_role_permission` VALUES ('383', '4', '50');
INSERT INTO `sys_re_role_permission` VALUES ('384', '4', '51');
INSERT INTO `sys_re_role_permission` VALUES ('385', '4', '46');
INSERT INTO `sys_re_role_permission` VALUES ('386', '4', '3');
INSERT INTO `sys_re_role_permission` VALUES ('387', '4', '49');
INSERT INTO `sys_re_role_permission` VALUES ('388', '4', '4');
INSERT INTO `sys_re_role_permission` VALUES ('389', '4', '24');
INSERT INTO `sys_re_role_permission` VALUES ('390', '4', '5');
INSERT INTO `sys_re_role_permission` VALUES ('391', '4', '25');
INSERT INTO `sys_re_role_permission` VALUES ('453', '1', '45');
INSERT INTO `sys_re_role_permission` VALUES ('454', '1', '48');
INSERT INTO `sys_re_role_permission` VALUES ('455', '1', '50');
INSERT INTO `sys_re_role_permission` VALUES ('456', '1', '51');
INSERT INTO `sys_re_role_permission` VALUES ('457', '1', '56');
INSERT INTO `sys_re_role_permission` VALUES ('458', '1', '46');
INSERT INTO `sys_re_role_permission` VALUES ('459', '1', '3');
INSERT INTO `sys_re_role_permission` VALUES ('460', '1', '13');
INSERT INTO `sys_re_role_permission` VALUES ('461', '1', '27');
INSERT INTO `sys_re_role_permission` VALUES ('462', '1', '10');
INSERT INTO `sys_re_role_permission` VALUES ('463', '1', '12');
INSERT INTO `sys_re_role_permission` VALUES ('464', '1', '11');
INSERT INTO `sys_re_role_permission` VALUES ('465', '1', '14');
INSERT INTO `sys_re_role_permission` VALUES ('466', '1', '15');
INSERT INTO `sys_re_role_permission` VALUES ('467', '1', '49');
INSERT INTO `sys_re_role_permission` VALUES ('468', '1', '4');
INSERT INTO `sys_re_role_permission` VALUES ('469', '1', '20');
INSERT INTO `sys_re_role_permission` VALUES ('470', '1', '28');
INSERT INTO `sys_re_role_permission` VALUES ('471', '1', '19');
INSERT INTO `sys_re_role_permission` VALUES ('472', '1', '18');
INSERT INTO `sys_re_role_permission` VALUES ('473', '1', '17');
INSERT INTO `sys_re_role_permission` VALUES ('474', '1', '16');
INSERT INTO `sys_re_role_permission` VALUES ('475', '1', '24');
INSERT INTO `sys_re_role_permission` VALUES ('476', '1', '5');
INSERT INTO `sys_re_role_permission` VALUES ('477', '1', '25');
INSERT INTO `sys_re_role_permission` VALUES ('478', '1', '29');
INSERT INTO `sys_re_role_permission` VALUES ('479', '1', '30');
INSERT INTO `sys_re_role_permission` VALUES ('480', '1', '44');
INSERT INTO `sys_re_role_permission` VALUES ('481', '1', '52');
INSERT INTO `sys_re_role_permission` VALUES ('482', '1', '53');
INSERT INTO `sys_re_role_permission` VALUES ('483', '1', '54');
INSERT INTO `sys_re_role_permission` VALUES ('484', '1', '55');
INSERT INTO `sys_re_user_role` VALUES ('14', '2', '1');
INSERT INTO `sys_re_user_role` VALUES ('15', '3', '4');
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '999', '单点登录管理员角色', '');
INSERT INTO `sys_role` VALUES ('4', '测试', 'test', '1', 'demo测试角色', '');
INSERT INTO `sys_settings` VALUES ('site_name', '百灵权限管理系统 ');
INSERT INTO `sys_settings` VALUES ('meta_description', '百灵权限管理系统，是一款构建在用户、角色、权限、日志、在线用户、用户下线之基础上等开源权限管理项目');
INSERT INTO `sys_settings` VALUES ('site_icp', '京ICP证030173号');
INSERT INTO `sys_settings` VALUES ('mailport', '25');
INSERT INTO `sys_settings` VALUES ('mailauth_username', 'test@163.com');
INSERT INTO `sys_settings` VALUES ('mailauth_password', '123456');
INSERT INTO `sys_settings` VALUES ('mult_login', '0');
INSERT INTO `sys_settings` VALUES ('kill_login', '1');
INSERT INTO `sys_settings` VALUES ('error_times_login', '2');
INSERT INTO `sys_settings` VALUES ('site_copyright', '蝴蝶飞飞');
INSERT INTO `sys_settings` VALUES ('site_keywords', '百灵，权限，用户， 角色');
INSERT INTO `sys_user` VALUES ('2', 'admin', '6b1a2c3a1615cf2ff8a581218bab703a', '192.168.1.122', '2017-06-12 15:58:10', '2', '2015-06-02 11:31:56', '');
INSERT INTO `sys_user` VALUES ('3', 'test', '6b1a2c3a1615cf2ff8a581218bab703a', null, null, '0', '2017-06-12 17:22:44', '');
