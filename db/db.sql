/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : tumo_boot

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 12/12/2020 12:38:33
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, 0, '开发部', '项目开发部门', '2020-10-29 09:34:32');
INSERT INTO `sys_dept` VALUES (2, 1, '开发一部', '开发部门的下级部门', '2020-10-26 09:34:37');
INSERT INTO `sys_dept` VALUES (3, 1, '开发二部', '开发部门的下级部门', '2020-06-29 09:34:42');
INSERT INTO `sys_dept` VALUES (4, 0, '测试部', '负责测试验收项目', '2020-08-29 09:34:46');
INSERT INTO `sys_dept` VALUES (5, 4, '测试一部', '测试部门的子部门', '2020-11-29 09:34:49');
INSERT INTO `sys_dept` VALUES (6, 0, '人事部', '负责公司人员招聘', '2020-05-29 09:34:52');
INSERT INTO `sys_dept` VALUES (7, 0, '测试部2', '测试', '2020-10-29 13:08:44');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES (1, 'admin', '查看用户列表', NULL, 20, 'cn.tycoding.system.controller.UserController.queryList()', ' queryPage\"QueryPage(pageCode=1, pageSize=6)\" user\"User(id=null, username=null, password=null, salt=null, deptId=null, deptName=null, createTime=null, modifyTime=null, avatar=null, phone=null, sex=null, description=null, status=null)\"', '127.0.0.1', NULL, '2019-03-13 00:42:34');
INSERT INTO `sys_log` VALUES (7, 'admin', '更新用户', NULL, 83, 'cn.tycoding.system.controller.UserController.update()', ' user\"UserWithRole(roleId=1, roleIds=[1])\"', '127.0.0.1', NULL, '2019-03-13 01:21:48');
INSERT INTO `sys_log` VALUES (10, 'admin', '删除用户', NULL, 65, 'cn.tycoding.system.controller.UserController.delete()', ' ids\"[9]\"', '127.0.0.1', NULL, '2019-03-13 05:00:56');
INSERT INTO `sys_log` VALUES (11, 'admin', '删除用户', NULL, 9, 'cn.tycoding.system.controller.UserController.delete()', ' ids\"[9]\"', '127.0.0.1', NULL, '2019-03-13 05:01:18');
INSERT INTO `sys_log` VALUES (12, 'admin', '删除登录日志', NULL, 39, 'cn.tycoding.monitor.controller.LoginLogController.delete()', ' ids\"[3]\"', '127.0.0.1', NULL, '2019-03-13 05:13:03');
INSERT INTO `sys_log` VALUES (13, 'admin', '删除日志', NULL, 44, 'cn.tycoding.monitor.controller.LogController.delete()', ' ids\"[8]\"', '127.0.0.1', NULL, '2019-03-13 05:15:54');
INSERT INTO `sys_log` VALUES (14, 'admin', '删除日志', NULL, 9, 'cn.tycoding.monitor.controller.LogController.delete()', ' ids\"[9]\"', '127.0.0.1', NULL, '2019-03-13 05:15:58');
INSERT INTO `sys_log` VALUES (15, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 39, 'add', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:29:37');
INSERT INTO `sys_log` VALUES (16, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 37, 'add', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:33:10');
INSERT INTO `sys_log` VALUES (17, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 28, 'cn.tycoding.boot.modules.blog.controller.TagControlleradd()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:38:57');
INSERT INTO `sys_log` VALUES (18, 'admin', '删除文章标签', '/tumo-boot/blog/tag/10', 33, 'cn.tycoding.boot.modules.blog.controller.TagControllerdelete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:46:54');
INSERT INTO `sys_log` VALUES (19, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 27, 'cn.tycoding.boot.modules.blog.controller.TagController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:53:30');
COMMIT;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `device` varchar(255) DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='登录日志表';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_login_log` VALUES (1, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 04:36:13', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (4, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 06:15:56', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (5, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 08:05:34', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (6, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 08:52:32', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (7, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 18:31:09', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (8, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 20:33:47', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (9, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 21:32:03', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (10, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-14 01:03:48', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '菜单路径',
  `perms` text COMMENT '权限标识',
  `type` varchar(20) DEFAULT NULL COMMENT '类型：如button按钮 menu菜单',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(30) DEFAULT NULL COMMENT 'Vue组件',
  `hidden` tinyint(1) DEFAULT NULL COMMENT '是否隐藏',
  `frame` tinyint(1) DEFAULT NULL COMMENT '是否是外链',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '权限模块', 0, '/system', NULL, 'menu', 'safety-certificate', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (2, '用户管理', 1, 'user', 'user:list', 'menu', 'user', '/modules/system/user/index', 0, 0);
INSERT INTO `sys_menu` VALUES (3, '角色管理', 1, 'role', 'role:list', 'menu', 'audit', '/modules/system/role/index', 0, 0);
INSERT INTO `sys_menu` VALUES (4, '部门管理', 1, 'dept', 'dept:list', 'menu', 'apartment', '/modules/system/dept/index', 0, 0);
INSERT INTO `sys_menu` VALUES (5, '菜单管理', 1, 'menu', 'menu:list', 'menu', 'cluster', '/modules/system/menu/index', 0, 0);
INSERT INTO `sys_menu` VALUES (10, '新增用户', 2, NULL, 'user:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (11, '修改用户', 2, NULL, 'user:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (12, '删除用户', 2, NULL, 'user:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (13, '新增角色', 3, NULL, 'role:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (14, '修改角色', 3, NULL, 'role:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (15, '删除角色', 3, NULL, 'role:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (16, '新增部门', 5, NULL, 'dept:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (17, '修改部门', 5, NULL, 'dept:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (18, '删除部门', 5, NULL, 'dept:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (20, '新增菜单', 4, NULL, 'menu:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (21, '删除菜单', 4, NULL, 'menu:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (24, '删除用户', 3, NULL, 'role:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (56, '修改菜单', 4, NULL, 'menu:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (130, '系统模块', 0, '/setting', NULL, 'menu', 'setting', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (131, '日志管理', 130, 'log', NULL, 'menu', 'exception', '/modules/setting/log/index', 0, 0);
INSERT INTO `sys_menu` VALUES (132, 'Api文档', 130, 'http://localhost:8080/doc.html', NULL, 'menu', 'file-search', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (135, 'Test-1', 0, '/test/1', 'TEST-1', 'menu', 'radar-chart', '/modules/test/1', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `alias` varchar(20) DEFAULT NULL COMMENT '角色别名',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', 'ADMIN', '管理员', '2019-01-01 00:00:00', 0);
INSERT INTO `sys_role` VALUES (2, '测试账号', 'TEST', '测试，可查看所有页面，但无操作权限', '2019-01-01 00:00:00', 1);
INSERT INTO `sys_role` VALUES (3, '用户管理员', 'USER_ADMIN', '负责用户的增删改查操作', '2019-01-01 00:00:00', 1);
INSERT INTO `sys_role` VALUES (4, '系统监控员', 'MONITOR_ADMIN', '可查看系统监控信息', '2019-02-14 08:51:48', 0);
INSERT INTO `sys_role` VALUES (5, '天气预报员', 'TEST', '可查看天气预报信息', '2019-02-14 02:54:56', 4);
INSERT INTO `sys_role` VALUES (6, '用户查看', 'TEST', '查看用户，但无操作权限', '2019-02-14 02:59:17', 4);
INSERT INTO `sys_role` VALUES (8, '111', '1', '1', '2020-10-28 13:26:22', 0);
INSERT INTO `sys_role` VALUES (9, '111.1', '111', '1', '2020-10-28 13:26:37', 8);
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
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 56);
INSERT INTO `sys_role_menu` VALUES (1, 130);
INSERT INTO `sys_role_menu` VALUES (1, 131);
INSERT INTO `sys_role_menu` VALUES (1, 132);
INSERT INTO `sys_role_menu` VALUES (1, 135);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 18);
INSERT INTO `sys_role_menu` VALUES (2, 19);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 27);
INSERT INTO `sys_role_menu` VALUES (2, 28);
INSERT INTO `sys_role_menu` VALUES (2, 29);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 34);
INSERT INTO `sys_role_menu` VALUES (2, 35);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (4, 18);
INSERT INTO `sys_role_menu` VALUES (4, 19);
INSERT INTO `sys_role_menu` VALUES (4, 20);
INSERT INTO `sys_role_menu` VALUES (4, 21);
INSERT INTO `sys_role_menu` VALUES (4, 22);
INSERT INTO `sys_role_menu` VALUES (4, 23);
INSERT INTO `sys_role_menu` VALUES (6, 131);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '19809587839', 'tycoding@sina.com', 3, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (2, 'tycoding', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '18798797687', 'tycoding@sina.com', 5, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (3, 'tumo', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '781797907', 'tycoding@sina.com', 6, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (4, 'monitor', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', NULL, '18798797687', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (5, 'synoptic', '123456', '女', '18798797687', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 0, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (6, 'user', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '18798797687', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 0, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (8, 'test2', 'sd', '男', '12', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2020-07-18 07:29:27');
INSERT INTO `sys_user` VALUES (9, '111', '1', '男', '17823787849', 'tycoding@sina.com', 5, NULL, 1, '2020-10-28 13:13:18');
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
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (1, 3);
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (3, 4);
INSERT INTO `sys_user_role` VALUES (4, 4);
INSERT INTO `sys_user_role` VALUES (4, 5);
INSERT INTO `sys_user_role` VALUES (5, 5);
INSERT INTO `sys_user_role` VALUES (5, 6);
INSERT INTO `sys_user_role` VALUES (5, 9);
INSERT INTO `sys_user_role` VALUES (6, 6);
INSERT INTO `sys_user_role` VALUES (8, 4);
INSERT INTO `sys_user_role` VALUES (8, 6);
INSERT INTO `sys_user_role` VALUES (9, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
