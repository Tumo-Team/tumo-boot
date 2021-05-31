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

 Date: 31/05/2021 17:09:12
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
-- Table structure for oss_file
-- ----------------------------
DROP TABLE IF EXISTS `oss_file`;
CREATE TABLE `oss_file` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `origin_name` varchar(255) DEFAULT NULL COMMENT '原始文件名称',
  `target_name` varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  `bucket` varchar(255) DEFAULT NULL COMMENT '桶路径',
  `url` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `des` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源文件表';

-- ----------------------------
-- Records of oss_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL COMMENT '部门ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1362597682681577273, 1362597682681577473, '测试部门1.1', 1, '测试');
INSERT INTO `sys_dept` VALUES (1362597682681577473, 0, '测试部门', 2, '测试');
INSERT INTO `sys_dept` VALUES (1394652452952887298, 0, '1', 3, '1111');
INSERT INTO `sys_dept` VALUES (1394933284307267585, 1394652452952887298, '1111', 12, '1212');
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
INSERT INTO `sys_log` VALUES (1398092001940934657, 2, 'anonymousUser', 'Redis连接异常', '/tumo-boot/auth/captcha', NULL, NULL, '_t=%5B1622166079600%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-28 09:41:23');
INSERT INTO `sys_log` VALUES (1398095251809308673, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1622166857750%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-28 09:54:18');
INSERT INTO `sys_log` VALUES (1398148294542192641, 1, 'tumo-boot', '修改角色', '/tumo-boot/upms/role', 40, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-28 13:25:04');
INSERT INTO `sys_log` VALUES (1398148294596718593, 1, 'tumo-boot', '修改角色', '/tumo-boot/upms/role', 69, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-28 13:25:04');
INSERT INTO `sys_log` VALUES (1398148328478306305, 1, 'tumo-boot', '修改角色', '/tumo-boot/upms/role', 23, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-28 13:25:13');
INSERT INTO `sys_log` VALUES (1398148328499277826, 1, 'tumo-boot', '修改角色', '/tumo-boot/upms/role', 33, 'cn.tycoding.boot.modules.upms.controller.SysRoleController.update()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-28 13:25:13');
INSERT INTO `sys_log` VALUES (1398878836290183169, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1622353673952%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-30 13:47:57');
INSERT INTO `sys_log` VALUES (1399174316924559362, 2, NULL, '请求未授权', '/tumo-boot/upms/menu/build', NULL, NULL, '_t=%5B1622424127131%5D', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '2021-05-31 09:22:07');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` varchar(20) DEFAULT NULL COMMENT '菜单类型',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
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
INSERT INTO `sys_menu` VALUES (1395758993001025537, '资源模块', 0, '/resource', 'resource:list', 'menu', 22, 'ant-design:send-outlined', '/', 0, 0, 1, 1);
INSERT INTO `sys_menu` VALUES (1395759263542022145, '文件管理', 1395758993001025537, 'oss', 'oss:list', 'menu', 23, 'ant-design:folder-open-twotone', '/modules/resource/oss/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` VALUES (1397909459826774017, '系统日志', 1395758993001025537, 'log', 'log:view', 'menu', 20, 'ant-design:rocket-filled', '/modules/resource/log/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` VALUES (1397921420912353282, '222', 0, '/222', NULL, 'menu', 222, 'ant-design:android-filled', '/222', 0, 0, 1, 1);
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
INSERT INTO `sys_role` VALUES (1394593681369038850, 0, '1111', '1', 0, '1');
INSERT INTO `sys_role` VALUES (1394635789033418753, 1394593681369038850, '1xxx', '1zzzz', 0, 'zzz');
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
INSERT INTO `sys_user` VALUES (1362304631325102103, 'tumo-boot', '$2a$10$TlSIkPzm5QqkSMhtP0nFQ.fx864TTT6meypBChmMCcGrkq.5RLh0K', '超级管理员', '女', '19809587839', 'tycoding@sina.com', 1362597682681577473, '/upload/default.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (1362598312234024962, 'tycoding', '$2a$10$TlSIkPzm5QqkSMhtP0nFQ.fx864TTT6meypBChmMCcGrkq.5RLh0K', '涂陌', '男', '19823879128', 'tycoding@sina.com', 1362597682681577473, '/upload/default.png', 1, '2021-02-19 11:02:08');
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
