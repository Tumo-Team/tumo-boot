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

 Date: 19/02/2021 18:30:35
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
INSERT INTO `sys_dept` VALUES (1362597682681577473, 0, '测试部门', '测试', NULL);
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
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `id` bigint(20) NOT NULL COMMENT '主键',
                            `name` varchar(20) NOT NULL COMMENT '菜单名称',
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
                            `path` varchar(100) DEFAULT NULL COMMENT '菜单路径',
                            `perms` text COMMENT '权限标识',
                            `type` varchar(20) DEFAULT NULL COMMENT '菜单类型',
                            `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
                            `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
                            `hidden` tinyint(1) DEFAULT NULL COMMENT '是否隐藏',
                            `frame` tinyint(1) DEFAULT NULL COMMENT '是否是外链',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1362304631325192191, '权限模块', 0, '/upms', NULL, 'sysMenu', 'safety-certificate', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (1362304631325192192, '菜单管理', 1362304631325192191, 'sysMenu', 'sysMenu:list', 'sysMenu', 'cluster', '/modules/upms/sysMenu/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362311197558341634, '用户管理', 1362304631325192191, 'sysUser', 'sysUser:list', 'sysMenu', 'team', '/modules/upms/sysUser/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362314451879096321, '角色管理', 1362304631325192191, 'sysRole', 'sysRole:list', 'sysMenu', 'solution', '/modules/upms/sysRole/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362316746228355073, '部门管理', 1362304631325192191, 'sysDept', 'sysDept:list', 'sysMenu', 'audit', '/modules/upms/sysDept/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362600462804586498, '系统模块', 0, '/system', 'system', 'sysMenu', 'setting', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (1362601021230026753, 'API文档', 1362600462804586498, 'http://127.0.0.1:8090/doc.html', 'doc:list', 'sysMenu', 'file-search', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (1362604427059400705, '操作日志', 1362600462804586498, 'sysLog/api', 'sysLog:api:list', 'sysMenu', 'api', '/modules/system/sysLog/api/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362604853041303554, '错误日志', 1362600462804586498, 'sysLog/error', 'sysLog:error:list', 'sysMenu', 'bug', '/modules/system/sysLog/error/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362663179380875265, 'Test Page', 0, '/test', 'test', 'sysMenu', 'alert', '/modules/test/index', 0, 0);
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
                            `des` varchar(100) DEFAULT NULL COMMENT '描述',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1362304631325192103, 0, '超级管理员管理员', 'administrator', '超级管理员管理员，不受权限控制');
INSERT INTO `sys_role` VALUES (1362597571041787906, 0, '用户管理员', 'sysUser-admin', '仅拥有用户管理权限');
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
INSERT INTO `sys_user` VALUES (1362304631325102103, 'admin', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '超级管理员', '男', '19809587839', 'tycoding@sina.com', 3, NULL, 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (1362598312234024962, 'tycoding', '123456', '涂陌', '男', '19823879128', 'tycoding@sina.com', 1362597682681577473, NULL, 1, '2021-02-19 11:02:08');
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
