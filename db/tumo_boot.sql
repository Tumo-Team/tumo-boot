/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 127.0.0.1:3306
 Source Schema         : tumo_boot

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 18/05/2021 21:58:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('client', NULL, '$2a$10$22emI3a6/w3a4ZZIa0.pY.dvLsyx4GH.he37wULtW8nJ.TeiGUpC6', 'app', 'password,refresh_token', 'http://tycoding.cn', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL COMMENT '部门ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1362597682681577273, 1362597682681577473, '测试部门1.1', '测试', '2021-05-15 07:51:32');
INSERT INTO `sys_dept` VALUES (1362597682681577473, 0, '测试部门', '测试', '2021-05-14 07:51:37');
INSERT INTO `sys_dept` VALUES (1394652452952887298, 0, '1', '1111', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `type` int(10) DEFAULT NULL COMMENT '日志类型',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(20) DEFAULT NULL COMMENT '操作描述',
  `url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `time` bigint(20) DEFAULT NULL COMMENT '耗时(毫秒)',
  `method` varchar(100) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES (1362941987702976514, 2, 'anonymousUser', 'Redis连接异常', '/tumo-boot/auth/captcha', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36', '2021-02-20 09:47:46');
INSERT INTO `sys_log` VALUES (1362941988407619585, 2, 'anonymousUser', 'Redis连接异常', '/tumo-boot/auth/captcha', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36', '2021-02-20 09:47:47');
INSERT INTO `sys_log` VALUES (1362942873732935682, 2, 'anonymousUser', 'Redis连接异常', '/tumo-boot/auth/captcha', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36', '2021-02-20 09:51:17');
INSERT INTO `sys_log` VALUES (1362944429131948033, 2, NULL, '请求未授权', '/tumo-boot/auth/captch', NULL, NULL, '', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36', '2021-02-20 09:57:28');
INSERT INTO `sys_log` VALUES (1362948179904962561, 2, 'admin', '服务器异常', '/tumo-boot/upms/user/list', NULL, NULL, 'page=%5B1%5D&limit=%5B5%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36', '2021-02-20 10:12:23');
INSERT INTO `sys_log` VALUES (1363025888630812673, 2, 'anonymousUser', '服务器异常', '/error', NULL, NULL, 'username=%5Badmin%5D&password=%5Btycoding%5D&grant_type=%5Bpassword%5D&captcha=%5B6ojm%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36', '2021-02-20 15:21:10');
INSERT INTO `sys_log` VALUES (1373102800139104257, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36', '2021-03-20 10:43:12');
INSERT INTO `sys_log` VALUES (1373798252733992961, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36', '2021-03-22 08:46:42');
INSERT INTO `sys_log` VALUES (1373868267697016833, 1, 'tumo-boot', '删除菜单', '/tumo-boot/upms/menu/1362663179380875265', 30, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36', '2021-03-22 13:24:55');
INSERT INTO `sys_log` VALUES (1373868267709599746, 1, 'tumo-boot', '删除菜单', '/tumo-boot/upms/menu/1362663179380875265', 38, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36', '2021-03-22 13:24:55');
INSERT INTO `sys_log` VALUES (1379708863757983745, 2, NULL, '请求未授权', '/tumo-boot/tumo-boot/auth/oauth/token', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-07 16:13:21');
INSERT INTO `sys_log` VALUES (1379710262986186754, 2, NULL, '请求未授权', '/tumo-boot/auth/token/captcha', NULL, NULL, '_t=%5B1617783534972%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-07 16:18:55');
INSERT INTO `sys_log` VALUES (1379710471816388609, 2, 'anonymousUser', 'Redis连接异常', '/tumo-boot/auth/captcha', NULL, NULL, '_t=%5B1617783581573%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-07 16:19:45');
INSERT INTO `sys_log` VALUES (1380038949422075905, 2, NULL, '请求未授权', '/tumo-boot/login', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-08 14:05:00');
INSERT INTO `sys_log` VALUES (1380067074390462465, 2, NULL, '请求未授权', '/tumo-boot/login', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-08 15:56:45');
INSERT INTO `sys_log` VALUES (1380067098218303489, 2, NULL, '请求未授权', '/tumo-boot/login', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-08 15:56:51');
INSERT INTO `sys_log` VALUES (1380067288799088642, 2, NULL, '请求未授权', '/tumo-boot/login', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-08 15:57:37');
INSERT INTO `sys_log` VALUES (1380067361238913025, 2, NULL, '请求未授权', '/tumo-boot/login', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-08 15:57:54');
INSERT INTO `sys_log` VALUES (1380343409486688258, 2, 'anonymousUser', 'Redis连接异常', '/tumo-boot/auth/captcha', NULL, NULL, '_t=%5B1617934486186%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-09 10:14:49');
INSERT INTO `sys_log` VALUES (1380396132919767042, 2, 'anonymousUser', 'Redis连接异常', '/tumo-boot/auth/captcha', NULL, NULL, '_t=%5B1617947059156%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-09 13:44:19');
INSERT INTO `sys_log` VALUES (1380425595707498498, 2, 'anonymousUser', '服务器异常', '/tumo-boot/auth/mail', NULL, NULL, '', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-09 15:41:23');
INSERT INTO `sys_log` VALUES (1380425807977029633, 2, 'anonymousUser', '服务器异常', '/tumo-boot/auth/mail', NULL, NULL, 'receiver=%5Btycoding%40sina.com%5D', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-09 15:42:14');
INSERT INTO `sys_log` VALUES (1381808801990184962, 2, NULL, '请求未授权', '/error', NULL, NULL, 'grant_type=%5Bpassword%5D&username=%5Btumo-boot%5D&password=%5Btycoding%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-13 11:17:46');
INSERT INTO `sys_log` VALUES (1381808948216205313, 2, NULL, '请求未授权', '/tumo-boot/upms/user/filter/list', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36', '2021-04-13 11:18:21');
INSERT INTO `sys_log` VALUES (1386603187527520258, 2, NULL, '请求未授权', '/auth/oauth/token', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36', '2021-04-26 16:48:56');
INSERT INTO `sys_log` VALUES (1386864018307805185, 2, NULL, '请求未授权', '/tumo-boot/getUserInfoById', NULL, NULL, '_t=%5B1619489123188%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36', '2021-04-27 10:05:23');
INSERT INTO `sys_log` VALUES (1386864506403155970, 2, NULL, '请求未授权', '/tumo-boot/getUserInfoById', NULL, NULL, '_t=%5B1619489239636%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36', '2021-04-27 10:07:20');
INSERT INTO `sys_log` VALUES (1386866427373416450, 2, NULL, '请求未授权', '/tumo-boot/getUserInfoById', NULL, NULL, '_t=%5B1619489697662%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36', '2021-04-27 10:14:58');
INSERT INTO `sys_log` VALUES (1386871789895938050, 2, NULL, '请求未授权', '/tumo-boot/undefined', NULL, NULL, '_t=%5B1619490976177%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36', '2021-04-27 10:36:16');
INSERT INTO `sys_log` VALUES (1386872015763402754, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '_t=%5B1619491030039%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36', '2021-04-27 10:37:10');
INSERT INTO `sys_log` VALUES (1392051066228551681, 2, NULL, '请求未授权', '/tumo-boot/tumo-boot/auth/oauth/token', NULL, NULL, 'username=%5B%7B%22username%22%3A%22admin%22%2C%22password%22%3A%22123456%22%7D%5D&grant_type=%5Bpassword%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-11 17:36:51');
INSERT INTO `sys_log` VALUES (1392051903457767425, 2, NULL, '请求未授权', '/tumo-boot/tumo-boot/auth/oauth/token', NULL, NULL, 'username=%5Badmin%5D&password=%5B123456%5D&grant_type=%5Bpassword%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-11 17:40:11');
INSERT INTO `sys_log` VALUES (1392110691055468546, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-11 21:33:48');
INSERT INTO `sys_log` VALUES (1392294597549510657, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:34');
INSERT INTO `sys_log` VALUES (1392294598044438529, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:34');
INSERT INTO `sys_log` VALUES (1392294598329651201, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:34');
INSERT INTO `sys_log` VALUES (1392294598723915778, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294599067848705, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294599428558849, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294599860572161, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294600225476610, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294600569409538, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294601139834881, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294601529905153, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294602108719106, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294602498789378, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:35');
INSERT INTO `sys_log` VALUES (1392294602997911554, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:36');
INSERT INTO `sys_log` VALUES (1392294603673194498, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:36');
INSERT INTO `sys_log` VALUES (1392294604243619841, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:36');
INSERT INTO `sys_log` VALUES (1392294604767907842, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:36');
INSERT INTO `sys_log` VALUES (1392294605166366722, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:36');
INSERT INTO `sys_log` VALUES (1392294606013616129, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:36');
INSERT INTO `sys_log` VALUES (1392294606969917441, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:37');
INSERT INTO `sys_log` VALUES (1392294607452262402, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:37');
INSERT INTO `sys_log` VALUES (1392294608211431426, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:37');
INSERT INTO `sys_log` VALUES (1392294608794439682, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:37');
INSERT INTO `sys_log` VALUES (1392294609335504898, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:37');
INSERT INTO `sys_log` VALUES (1392294610124034050, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:37');
INSERT INTO `sys_log` VALUES (1392294610618961922, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:37');
INSERT INTO `sys_log` VALUES (1392294611080335361, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:38');
INSERT INTO `sys_log` VALUES (1392294611768201217, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:38');
INSERT INTO `sys_log` VALUES (1392294612468649986, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:38');
INSERT INTO `sys_log` VALUES (1392294613110378497, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:38');
INSERT INTO `sys_log` VALUES (1392294613701775361, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:38');
INSERT INTO `sys_log` VALUES (1392294614393835521, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:38');
INSERT INTO `sys_log` VALUES (1392294614947483649, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:38');
INSERT INTO `sys_log` VALUES (1392294615480160257, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:39');
INSERT INTO `sys_log` VALUES (1392294615790538753, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:39');
INSERT INTO `sys_log` VALUES (1392294616851697666, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:39');
INSERT INTO `sys_log` VALUES (1392294617619255297, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:39');
INSERT INTO `sys_log` VALUES (1392294618583945217, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:39');
INSERT INTO `sys_log` VALUES (1392294619234062337, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:39');
INSERT INTO `sys_log` VALUES (1392294620668514305, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:40');
INSERT INTO `sys_log` VALUES (1392294622824386562, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:40');
INSERT INTO `sys_log` VALUES (1392294623554195458, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:40');
INSERT INTO `sys_log` VALUES (1392294624246255617, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:41');
INSERT INTO `sys_log` VALUES (1392294625206751233, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:41');
INSERT INTO `sys_log` VALUES (1392294626033029121, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:41');
INSERT INTO `sys_log` VALUES (1392294626460848130, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:41');
INSERT INTO `sys_log` VALUES (1392294627140325378, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:41');
INSERT INTO `sys_log` VALUES (1392294628063072257, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:42');
INSERT INTO `sys_log` VALUES (1392294628897738753, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:42');
INSERT INTO `sys_log` VALUES (1392294629963091970, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:42');
INSERT INTO `sys_log` VALUES (1392294630931976194, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:42');
INSERT INTO `sys_log` VALUES (1392294631741476865, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:42');
INSERT INTO `sys_log` VALUES (1392294633289175042, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:43');
INSERT INTO `sys_log` VALUES (1392294634396471298, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:43');
INSERT INTO `sys_log` VALUES (1392294635235332098, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:43');
INSERT INTO `sys_log` VALUES (1392294635734454274, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:43');
INSERT INTO `sys_log` VALUES (1392294637114380290, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:44');
INSERT INTO `sys_log` VALUES (1392294637693194241, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:44');
INSERT INTO `sys_log` VALUES (1392294638079070210, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:44');
INSERT INTO `sys_log` VALUES (1392294639261863937, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:44');
INSERT INTO `sys_log` VALUES (1392294640390131713, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:45');
INSERT INTO `sys_log` VALUES (1392294640771813378, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:45');
INSERT INTO `sys_log` VALUES (1392294641644228610, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:45');
INSERT INTO `sys_log` VALUES (1392294642223042561, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:45');
INSERT INTO `sys_log` VALUES (1392294642692804609, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:45');
INSERT INTO `sys_log` VALUES (1392294691506114562, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294691850047489, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294692118482946, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294692303032322, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294692579856385, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294692827320322, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294693058007042, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294693368385537, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294693674569730, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294694085611522, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294694375018497, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294694714757122, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:57');
INSERT INTO `sys_log` VALUES (1392294694983192578, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294695318736898, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294695578783746, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294695876579329, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294696266649602, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294696786743297, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294697126481922, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294697432666113, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294697722073090, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294698045034497, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294698393161730, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294698804203522, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:58');
INSERT INTO `sys_log` VALUES (1392294699185885185, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294699617898498, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294700003774465, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294700486119426, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294700855218177, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294701241094146, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294701543084034, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294701903794178, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294702230949890, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294702562299906, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294702906232833, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:44:59');
INSERT INTO `sys_log` VALUES (1392294703292108802, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:45:00');
INSERT INTO `sys_log` VALUES (1392294703594098689, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:45:00');
INSERT INTO `sys_log` VALUES (1392294704051277826, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:45:00');
INSERT INTO `sys_log` VALUES (1392294704441348097, 2, NULL, '请求未授权', '/tumo-boot/upms/user/info', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:45:00');
INSERT INTO `sys_log` VALUES (1392294704768503810, 2, NULL, '请求未授权', '/tumo-boot/auth/logout', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 09:45:00');
INSERT INTO `sys_log` VALUES (1392402433130516482, 2, NULL, '请求未授权', '/auth/captcha', NULL, NULL, '_t=%5B1620809584157%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 16:53:04');
INSERT INTO `sys_log` VALUES (1392478398837850113, 2, NULL, '请求未授权', '/tumo-boot/getUserInfoById', NULL, NULL, '_t=%5B1620827695720%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 21:54:56');
INSERT INTO `sys_log` VALUES (1392481059326820353, 2, NULL, '请求未授权', '/tumo-boot/getUserInfoById', NULL, NULL, '_t=%5B1620828330236%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 22:05:30');
INSERT INTO `sys_log` VALUES (1392481537192263682, 2, NULL, '请求未授权', '/tumo-boot/getUserInfoById', NULL, NULL, '_t=%5B1620828444168%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-12 22:07:24');
INSERT INTO `sys_log` VALUES (1392771854032207874, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/list', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-13 17:21:01');
INSERT INTO `sys_log` VALUES (1393334584709713922, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1621031825694%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-15 06:37:06');
INSERT INTO `sys_log` VALUES (1393356761425190913, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/list', NULL, NULL, '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-15 08:05:14');
INSERT INTO `sys_log` VALUES (1393358504825397250, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/list', NULL, NULL, 'page=%5B1%5D&limit=%5B10%5D&_t=%5B1621037529299%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-15 08:12:10');
INSERT INTO `sys_log` VALUES (1393360338998730754, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/list', NULL, NULL, 'page=%5B1%5D&limit=%5B10%5D&_t=%5B1621037966670%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-15 08:19:27');
INSERT INTO `sys_log` VALUES (1393360929699340290, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/list', NULL, NULL, 'page=%5B1%5D&limit=%5B10%5D&_t=%5B1621038107590%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-15 08:21:48');
INSERT INTO `sys_log` VALUES (1393516884091092993, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1621075287634%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-15 18:41:30');
INSERT INTO `sys_log` VALUES (1393535327049760769, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/page', NULL, NULL, '_t=%5B1621079686614%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-15 19:54:47');
INSERT INTO `sys_log` VALUES (1393903946241835009, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1621167572548%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:19:33');
INSERT INTO `sys_log` VALUES (1393912104750317569, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 12, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:51:58');
INSERT INTO `sys_log` VALUES (1393912104800649217, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 25, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:51:58');
INSERT INTO `sys_log` VALUES (1393912573115662337, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 8, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:53:50');
INSERT INTO `sys_log` VALUES (1393912573182771202, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 24, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:53:50');
INSERT INTO `sys_log` VALUES (1393912617235546114, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 16, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:54:00');
INSERT INTO `sys_log` VALUES (1393912617277489153, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 27, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:54:00');
INSERT INTO `sys_log` VALUES (1393912800325304322, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 4, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:54:44');
INSERT INTO `sys_log` VALUES (1393912800392413185, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 20, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:54:44');
INSERT INTO `sys_log` VALUES (1393913044559626242, 1, 'tumo-boot', '新增用户', '/tumo-boot/upms/user', 123, 'cn.tycoding.boot.modules.upms.controller.SysUserController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:55:42');
INSERT INTO `sys_log` VALUES (1393913044593180674, 1, 'tumo-boot', '新增用户', '/tumo-boot/upms/user', 131, 'cn.tycoding.boot.modules.upms.controller.SysUserController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-16 20:55:42');
INSERT INTO `sys_log` VALUES (1394099599247065089, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1621214220077%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 09:17:00');
INSERT INTO `sys_log` VALUES (1394101266344493057, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 37, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 09:23:38');
INSERT INTO `sys_log` VALUES (1394101266403213314, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 57, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 09:23:38');
INSERT INTO `sys_log` VALUES (1394101762226413569, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 26, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 09:25:36');
INSERT INTO `sys_log` VALUES (1394101762457100290, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 92, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 09:25:36');
INSERT INTO `sys_log` VALUES (1394105425078571010, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 32, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 09:40:09');
INSERT INTO `sys_log` VALUES (1394105425179234306, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 83, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 09:40:09');
INSERT INTO `sys_log` VALUES (1394112290726400001, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 68, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 10:07:26');
INSERT INTO `sys_log` VALUES (1394112290793508865, 1, 'tumo-boot', '修改用户', '/tumo-boot/upms/user', 101, 'cn.tycoding.boot.modules.upms.controller.SysUserController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 10:07:26');
INSERT INTO `sys_log` VALUES (1394121978020904961, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/page', NULL, NULL, 'page=%5B1%5D&limit=%5B10%5D&deptId=%5B1362597682681577473%5D&_t=%5B1621219555600%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 10:45:56');
INSERT INTO `sys_log` VALUES (1394122796514185217, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/user/page', NULL, NULL, 'page=%5B1%5D&limit=%5B10%5D&deptId=%5B1362597682681577473%5D&_t=%5B1621219750709%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 10:49:11');
INSERT INTO `sys_log` VALUES (1394164980194054146, 1, 'tumo-boot', '删除用户', '/tumo-boot/upms/user/1393913044513488897', 50, 'cn.tycoding.boot.modules.upms.controller.SysUserController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 13:36:48');
INSERT INTO `sys_log` VALUES (1394164980244385794, 1, 'tumo-boot', '删除用户', '/tumo-boot/upms/user/1393913044513488897', 93, 'cn.tycoding.boot.modules.upms.controller.SysUserController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 13:36:48');
INSERT INTO `sys_log` VALUES (1394165401297981441, 1, 'tumo-boot', '新增用户', '/tumo-boot/upms/user', 201, 'cn.tycoding.boot.modules.upms.controller.SysUserController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 13:38:29');
INSERT INTO `sys_log` VALUES (1394165401314758657, 1, 'tumo-boot', '新增用户', '/tumo-boot/upms/user', 204, 'cn.tycoding.boot.modules.upms.controller.SysUserController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 13:38:29');
INSERT INTO `sys_log` VALUES (1394165470097149953, 1, 'tumo-boot', '删除用户', '/tumo-boot/upms/user/1394165401197318146', 17, 'cn.tycoding.boot.modules.upms.controller.SysUserController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 13:38:45');
INSERT INTO `sys_log` VALUES (1394165470122315777, 1, 'tumo-boot', '删除用户', '/tumo-boot/upms/user/1394165401197318146', 26, 'cn.tycoding.boot.modules.upms.controller.SysUserController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 13:38:45');
INSERT INTO `sys_log` VALUES (1394212302206640129, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/menu/tree', NULL, NULL, '_t=%5B1621241090497%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 16:44:51');
INSERT INTO `sys_log` VALUES (1394215680043245570, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/menu/tree', NULL, NULL, '_t=%5B1621241895662%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 16:58:16');
INSERT INTO `sys_log` VALUES (1394286043569635330, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1621258671885%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 21:37:52');
INSERT INTO `sys_log` VALUES (1394298262565122049, 1, 'tumo-boot', '新增菜单', '/tumo-boot/upms/menu', 38, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:26:25');
INSERT INTO `sys_log` VALUES (1394298262594482177, 1, 'tumo-boot', '新增菜单', '/tumo-boot/upms/menu', 83, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:26:25');
INSERT INTO `sys_log` VALUES (1394300882637778946, 1, 'tumo-boot', '新增菜单', '/tumo-boot/upms/menu', 18, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:36:50');
INSERT INTO `sys_log` VALUES (1394300882671333377, 1, 'tumo-boot', '新增菜单', '/tumo-boot/upms/menu', 26, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:36:50');
INSERT INTO `sys_log` VALUES (1394300925725863938, 1, 'tumo-boot', '新增菜单', '/tumo-boot/upms/menu', 10, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:37:00');
INSERT INTO `sys_log` VALUES (1394300925772001282, 1, 'tumo-boot', '新增菜单', '/tumo-boot/upms/menu', 18, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:37:00');
INSERT INTO `sys_log` VALUES (1394301669023641602, 1, 'tumo-boot', '修改菜单', '/tumo-boot/upms/menu', 26, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:39:57');
INSERT INTO `sys_log` VALUES (1394301669053001729, 1, 'tumo-boot', '修改菜单', '/tumo-boot/upms/menu', 33, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:39:57');
INSERT INTO `sys_log` VALUES (1394301759020822529, 1, 'tumo-boot', '删除菜单', '/tumo-boot/upms/menu/1394298262342823937', 77, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:40:19');
INSERT INTO `sys_log` VALUES (1394301759037599745, 1, 'tumo-boot', '删除菜单', '/tumo-boot/upms/menu/1394298262342823937', 83, 'cn.tycoding.boot.modules.upms.controller.SysMenuController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-17 22:40:19');
INSERT INTO `sys_log` VALUES (1394593681624891394, 1, 'tumo-boot', '新增角色', '/tumo-boot/upms/role', 43, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 18:00:19');
INSERT INTO `sys_log` VALUES (1394593681650057218, 1, 'tumo-boot', '新增角色', '/tumo-boot/upms/role', 71, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 18:00:19');
INSERT INTO `sys_log` VALUES (1394593682216288258, 2, 'tumo-boot', '服务器异常', '/tumo-boot/upms/role/tree', NULL, NULL, 'page=%5B1%5D&limit=%5B10%5D&_t=%5B1621332018671%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 18:00:19');
INSERT INTO `sys_log` VALUES (1394596026257596417, 1, 'tumo-boot', '新增角色', '/tumo-boot/upms/role', 47, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 18:09:38');
INSERT INTO `sys_log` VALUES (1394596026282762242, 1, 'tumo-boot', '新增角色', '/tumo-boot/upms/role', 68, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 18:09:38');
INSERT INTO `sys_log` VALUES (1394625069812113410, 1, 'tumo-boot', '修改角色', '/tumo-boot/upms/role', 15, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 20:05:02');
INSERT INTO `sys_log` VALUES (1394625069862445057, 1, 'tumo-boot', '修改角色', '/tumo-boot/upms/role', 28, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 20:05:02');
INSERT INTO `sys_log` VALUES (1394625097351913473, 1, 'tumo-boot', '删除角色', '/tumo-boot/upms/role/1394596026001743873', 30, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 20:05:09');
INSERT INTO `sys_log` VALUES (1394625097368690689, 1, 'tumo-boot', '删除角色', '/tumo-boot/upms/role/1394596026001743873', 34, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.delete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 20:05:09');
INSERT INTO `sys_log` VALUES (1394635789398323201, 1, 'tumo-boot', '新增角色', '/tumo-boot/upms/role', 66, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 20:47:38');
INSERT INTO `sys_log` VALUES (1394635789511569409, 1, 'tumo-boot', '新增角色', '/tumo-boot/upms/role', 116, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 20:47:38');
INSERT INTO `sys_log` VALUES (1394652453091299329, 1, 'tumo-boot', '新增部门', '/tumo-boot/upms/dept', 27, 'cn.tycoding.boot.modules.upms.controller.SysDeptController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 21:53:51');
INSERT INTO `sys_log` VALUES (1394652453116465154, 1, 'tumo-boot', '新增部门', '/tumo-boot/upms/dept', 57, 'cn.tycoding.boot.modules.upms.controller.SysDeptController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36', '2021-05-18 21:53:51');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` varchar(20) DEFAULT NULL COMMENT '菜单类型',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `is_ext` tinyint(1) DEFAULT NULL COMMENT '是否外链',
  `is_keepalive` tinyint(1) DEFAULT NULL COMMENT '是否缓存',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1362304631325192191, '权限模块', 0, '/upms', NULL, 'menu', 1, 'ant-design:setting-outlined', NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1362304631325192192, '用户管理', 1362304631325192191, 'user', 'user:list', 'menu', 11, 'carbon:user-multiple', '/modules/upms/user/index', 0, 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1362304631325192193, '菜单管理', 1362304631325192191, 'menu', 'menu:list', 'menu', 22, 'bi:menu-button-wide', '/modules/upms/menu/index', 0, 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1362314451879096321, '角色管理', 1362304631325192191, 'role', 'role:list', 'menu', 33, 'ant-design:solution-outlined', '/modules/upms/role/index', 0, 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1362316746228355073, '部门管理', 1362304631325192191, 'dept', 'dept:list', 'menu', 44, 'ant-design:audit-outlined', '/modules/upms/dept/index', 0, 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1362601021230026753, 'API文档', 0, 'https://vvbin.cn/doc-next/', 'doc:list', 'menu', 55, 'carbon:document-attachment', NULL, 0, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级节点',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `alias` varchar(20) DEFAULT NULL COMMENT '角色别名',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1362304631325192103, 0, '超级管理员管理员', 'administrator', 1, '超级管理员管理员，不受权限控制');
INSERT INTO `sys_role` VALUES (1362597571041787906, 0, '用户管理员', 'user-admin', 1, '仅拥有用户管理权限');
INSERT INTO `sys_role` VALUES (1362597571041787916, 1362597571041787906, '用户管理员1', 'user-admin-1', 1, '仅拥有用户管理权限');
INSERT INTO `sys_role` VALUES (1394593681369038850, 0, '1111', '1', 1, '1');
INSERT INTO `sys_role` VALUES (1394635789033418753, 1394593681369038850, '1xxx', '1zzzz', 1, 'zzz');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1394593681369038850, 1362601021230026753);
INSERT INTO `sys_role_menu` VALUES (1394635789033418753, 1362601021230026753);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1362304631325102103, 'tumo-boot', '$2a$10$TlSIkPzm5QqkSMhtP0nFQ.fx864TTT6meypBChmMCcGrkq.5RLh0K', '超级管理员', '男', '19809587839', 'tycoding@sina.com', 3, NULL, 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (1362598312234024962, 'tycoding', '$2a$10$TlSIkPzm5QqkSMhtP0nFQ.fx864TTT6meypBChmMCcGrkq.5RLh0K', '涂陌', '男', '19823879128', 'tycoding@sina.com', 1362597682681577473, NULL, 1, '2021-02-19 11:02:08');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1362304631325102103, 1362304631325192103);
INSERT INTO `sys_user_role` VALUES (1362598312234024962, 1362597571041787906);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
