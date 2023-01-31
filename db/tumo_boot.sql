/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : tumo_boot

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 31/01/2023 15:04:38
*/

DROP DATABASE IF EXISTS `tumo_boot`;
CREATE DATABASE `tumo_boot` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `tumo_boot`;

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
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client', NULL, '$2a$10$22emI3a6/w3a4ZZIa0.pY.dvLsyx4GH.he37wULtW8nJ.TeiGUpC6', 'app', 'password,refresh_token', 'http://tycoding.cn', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for oss_file
-- ----------------------------
DROP TABLE IF EXISTS `oss_file`;
CREATE TABLE `oss_file` (
  `id` bigint NOT NULL COMMENT '主键',
  `origin_name` varchar(255) DEFAULT NULL COMMENT '原始文件名称',
  `target_name` varchar(255) DEFAULT NULL COMMENT '文件存储名称',
  `bucket` varchar(255) DEFAULT NULL COMMENT '桶路径',
  `url` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小',
  `des` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源文件表';

-- ----------------------------
-- Records of oss_file
-- ----------------------------
BEGIN;
INSERT INTO `oss_file` (`id`, `origin_name`, `target_name`, `bucket`, `url`, `type`, `size`, `des`, `create_time`) VALUES (1620311420509892610, 'default.png', '1620311412674932737.png', '/20230131', 'http://127.0.0.1:80/upload/20230131/1620311412674932737.png', 'png', 12259, 'default.png', '2023-01-31 14:41:59');
INSERT INTO `oss_file` (`id`, `origin_name`, `target_name`, `bucket`, `url`, `type`, `size`, `des`, `create_time`) VALUES (1620313254561591297, 'default.png', '1620313249234825217.png', '/20230131', 'http://127.0.0.1:80/upload/20230131/1620313249234825217.png', 'png', 12259, 'default.png', '2023-01-31 14:49:17');
INSERT INTO `oss_file` (`id`, `origin_name`, `target_name`, `bucket`, `url`, `type`, `size`, `des`, `create_time`) VALUES (1620313436879597570, '监控接口.xlsx', '1620313432874037249.xlsx', '/20230131', 'http://127.0.0.1:80/upload/20230131/1620313432874037249.xlsx', 'xlsx', 91695, '监控接口.xlsx', '2023-01-31 14:50:01');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL COMMENT '部门ID',
  `parent_id` bigint NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `order_no` int DEFAULT NULL COMMENT '排序',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1, 1362597682681577273, '测试2', 2, '测试');
INSERT INTO `sys_dept` (`id`, `parent_id`, `name`, `order_no`, `des`) VALUES (1362597682681577273, 0, '测试部门', 1, '测试');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL COMMENT '主键',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort` int DEFAULT NULL COMMENT '排序',
  `is_system` tinyint(1) DEFAULT NULL COMMENT '是否系统内置',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `type`, `name`, `sort`, `is_system`, `des`) VALUES (1423646158364020738, 'log_type', '系统日志', 1, 1, '日志类型字典，不可删除');
INSERT INTO `sys_dict` (`id`, `type`, `name`, `sort`, `is_system`, `des`) VALUES (1620314519723061249, '1', '1', 1, 0, '11');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint NOT NULL COMMENT '主键',
  `dict_id` bigint NOT NULL COMMENT '字典表主键',
  `value` varchar(255) DEFAULT NULL COMMENT '字典项值',
  `label` varchar(255) DEFAULT NULL COMMENT '字典项名称',
  `type` varchar(255) DEFAULT NULL COMMENT '字典项类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `is_system` tinyint(1) DEFAULT NULL COMMENT '是否系统内置',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典项表';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `sort`, `is_system`, `des`) VALUES (1423905893285801986, 1, '1', '1', '1', 1, NULL, '111');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `sort`, `is_system`, `des`) VALUES (1423906599338160129, 1423646158364020738, '1', '正常', 'log_type', 1, 1, '请求正常的日志');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `sort`, `is_system`, `des`) VALUES (1423910288618598401, 1423646158364020738, '2', '异常', 'log_type', 2, 1, '请求异常的日志');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `sort`, `is_system`, `des`) VALUES (1620314561074704385, 1620314519723061249, '1', '1', NULL, 1, 0, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL COMMENT '编号',
  `type` int DEFAULT NULL COMMENT '日志类型，1正常 2异常 ',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(20) DEFAULT NULL COMMENT '操作描述',
  `url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `time` bigint DEFAULT NULL COMMENT '耗时(毫秒)',
  `method` varchar(100) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='日志表';

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
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父级ID',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` varchar(20) DEFAULT NULL COMMENT '菜单类型',
  `order_no` int DEFAULT NULL COMMENT '排序',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `is_ext` tinyint(1) DEFAULT NULL COMMENT '是否外链',
  `is_keepalive` tinyint(1) DEFAULT NULL COMMENT '是否缓存',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402556566351122433, '权限管理', 0, '/upms', '', 'menu', 100, 'ant-design:setting-outlined', 'Layout', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402557067260071938, '用户管理', 1402556566351122433, 'user', '', 'menu', 101, 'ant-design:user-switch-outlined', '/modules/upms/user/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402557067260071948, '用户查看', 1402557067260071938, NULL, 'upms:user:view', 'button', 102, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402562175471669250, '用户新增', 1402557067260071938, NULL, 'upms:user:add', 'button', 103, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402563862928248833, '用户修改', 1402557067260071938, NULL, 'upms:user:update', 'button', 104, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402564440660070202, '重置密码', 1402557067260071938, NULL, 'upms:user:reset', 'button', 106, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402564440660070402, '用户删除', 1402557067260071938, NULL, 'upms:user:delete', 'button', 105, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402891854378217473, '角色管理', 1402556566351122433, 'role', '', 'menu', 110, 'ant-design:user-switch-outlined', '/modules/upms/role/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402891854378217483, '角色查看', 1402891854378217473, NULL, 'upms:role:view', 'button', 111, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402895826841288705, '角色新增', 1402891854378217473, NULL, 'upms:role:add', 'button', 112, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402895916096077825, '角色修改', 1402891854378217473, NULL, 'upms:role:update', 'button', 113, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896004537171970, '角色删除', 1402891854378217473, NULL, 'upms:role:delete', 'button', 114, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896397505708033, '部门管理', 1402556566351122433, 'dept', '', 'menu', 120, 'ant-design:audit-outlined', '/modules/upms/dept/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896397505708133, '部门查看', 1402896397505708033, NULL, 'upms:dept:view', 'button', 121, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896819314278401, '部门新增', 1402896397505708033, NULL, 'upms:dept:add', 'button', 122, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402896925455335425, '部门修改', 1402896397505708033, NULL, 'upms:dept:update', 'button', 123, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897035325128705, '部门删除', 1402896397505708033, NULL, 'upms:dept:delete', 'button', 124, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897356944359426, '菜单管理', 1402556566351122433, 'menu', '', 'menu', 130, 'ant-design:unordered-list-outlined', '/modules/upms/menu/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897356944359526, '菜单查看', 1402897356944359426, NULL, 'upms:menu:view', 'button', 131, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897461059567617, '菜单新增', 1402897356944359426, NULL, 'upms:menu:add', 'button', 132, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897607436582914, '菜单修改', 1402897356944359426, NULL, 'upms:menu:update', 'button', 133, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402897754488881153, '菜单删除', 1402897356944359426, NULL, 'upms:menu:delete', 'button', 134, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402900711645126657, '系统管理', NULL, '/system', '', 'menu', 200, 'ant-design:fork-outlined', 'Layout', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402901682110603265, '系统日志', 1402900711645126657, 'log', '', 'menu', 210, 'ant-design:thunderbolt-filled', '/modules/system/log/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402901682110603365, '日志查看', 1402901682110603265, NULL, 'system:log:view', 'button', 211, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902022428041217, '日志删除', 1402901682110603265, NULL, 'system:log:delete', 'button', 212, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902525295730689, '文件管理', 1402900711645126657, 'file', '', 'menu', 220, 'ant-design:folder-open-twotone', '/modules/system/oss/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902525295730789, '文件查看', 1402902525295730689, NULL, 'system:oss:view', 'button', 221, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902709501173762, '文件新增', 1402902525295730689, NULL, 'system:oss:add', 'button', 222, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402902826891354113, '文件修改', 1402902525295730689, NULL, 'system:oss:update', 'button', 223, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1402903214973526017, '文件删除', 1402902525295730689, NULL, 'system:oss:delete', 'button', 224, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542523561086978, '令牌管理', 1402900711645126657, 'token', '', 'menu', 201, 'ant-design:property-safety-outlined', '/modules/system/token/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542523561087978, '令牌查看', 1403542523561086978, NULL, 'system:token:view', 'button', 202, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542523561187978, '令牌详情', 1403542523561086978, NULL, 'system:token:info', 'button', 203, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403542705811984386, '令牌删除', 1403542523561086978, NULL, 'system:token:delete', 'button', 204, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1403549005811984386, '项目文档', NULL, '/doc', 'doc:view', 'menu', 300, 'ant-design:rocket-outlined', '', 0, 1, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1406058379956326402, '开发文档', 1403549005811984386, 'http://docs.boot.tycoding.cn/', 'doc:dev:view', 'menu', 302, 'ant-design:star-outlined', NULL, 0, 1, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1406058753513623553, '开源地址', 1403549005811984386, 'https://github.com/Tumo-Team/tumo-boot', 'doc:git:view', 'menu', 303, 'ant-design:github-filled', NULL, 0, 1, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423636580482088961, '字典管理', 1402900711645126657, 'dict', NULL, 'menu', 230, 'ant-design:exception-outlined', '/modules/system/dict/index', 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423638875592654849, '字典新增', 1423636580482088961, NULL, 'system:dict:add', 'button', 231, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639000616468481, '字典修改', 1423636580482088961, NULL, 'system:dict:update', 'button', 232, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639102978457602, '字典删除', 1423636580482088961, NULL, 'system:dict:delete', 'button', 233, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639486551752706, '字典项新增', 1423636580482088961, NULL, 'system:dict:item:add', 'button', 235, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423639588704026626, '字典项修改', 1423636580482088961, NULL, 'system:dict:item:update', 'button', 236, NULL, NULL, 0, 0, 1, 1);
INSERT INTO `sys_menu` (`id`, `name`, `parent_id`, `path`, `perms`, `type`, `order_no`, `icon`, `component`, `is_disabled`, `is_ext`, `is_keepalive`, `is_show`) VALUES (1423640622671265793, '字典项删除', 1423636580482088961, NULL, 'system:dict:item:delete', 'button', 237, NULL, NULL, 0, 0, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `parent_id` bigint DEFAULT NULL COMMENT '上级节点',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `alias` varchar(20) DEFAULT NULL COMMENT '角色别名',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `parent_id`, `name`, `alias`, `status`, `des`) VALUES (1362304631325192103, 0, '超级管理员', 'administrator', 1, '超级管理员管理员，不受权限控制');
INSERT INTO `sys_role` (`id`, `parent_id`, `name`, `alias`, `status`, `des`) VALUES (1404805390442635266, 0, '演示环境角色', 'demo_env', 1, '演示环境使用角色，没有页面操作权限');
INSERT INTO `sys_role` (`id`, `parent_id`, `name`, `alias`, `status`, `des`) VALUES (1620258484253642753, 1620258568231997441, '111', '1111', 1, '1111');
INSERT INTO `sys_role` (`id`, `parent_id`, `name`, `alias`, `status`, `des`) VALUES (1620258568231997441, 0, '测试角色', 'test', 1, '这是测试的');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402556566351122433);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402557067260071938);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402557067260071948);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402562175471669250);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402563862928248833);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402564440660070202);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402564440660070402);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402891854378217473);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402891854378217483);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402895826841288705);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402895916096077825);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402896004537171970);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402896397505708033);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402896397505708133);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402896819314278401);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402896925455335425);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402897035325128705);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402897356944359426);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402897356944359526);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402897461059567617);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402897607436582914);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402897754488881153);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402900711645126657);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402901682110603265);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402901682110603365);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402902022428041217);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402902525295730689);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402902525295730789);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402902709501173762);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402902826891354113);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1402903214973526017);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1403542523561086978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1403542523561087978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1403542523561187978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1403542705811984386);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1403549005811984386);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1406058379956326402);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1406058753513623553);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1423636580482088961);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1423638875592654849);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1423639000616468481);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1423639102978457602);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1423639486551752706);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1423639588704026626);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1404805390442635266, 1423640622671265793);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402900711645126657);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402901682110603265);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402901682110603365);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902022428041217);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902525295730689);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902525295730789);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902709501173762);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402902826891354113);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1402903214973526017);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542523561086978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542523561087978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542523561187978);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1403542705811984386);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423636580482088961);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423638875592654849);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639000616468481);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639102978457602);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639486551752706);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423639588704026626);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1620258568231997441, 1423640622671265793);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1362304631325102103, 'administrator', '$2a$10$ax1tsaE7fqB03iMQ/zeV8OAQ4bcTu5ik92XUfzUsP2XVrEXsFO/pS', '超级管理员', '女', '19809587831', 'tycoding@sina.com', 1362597682681577473, 'http://tycoding.cn/imgs/tycoding.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1362598312234024962, 'tycoding', '$2a$10$KBNb3GXoL4KKy55reaxnq.y0SgPWy2C6GT5yDqUuCSzCpqVBBGORK', '涂陌', '男', '19823879128', 'tycoding@sina.com', 1362597682681577473, '/upload/default.png', 0, '2021-02-19 11:02:08');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `sex`, `phone`, `email`, `dept_id`, `avatar`, `status`, `create_time`) VALUES (1404807635385069569, 'demo', '$2a$10$/07tXYxlTY/iJfVZOU.8AeeZiQLX3MIQWUGwV9/N3wH6nMUbYFpl2', '演示环境账号', '男', '18929809812', 'ty@qq.com', 1362597682681577273, 'http://tycoding.cn/imgs/tycoding.png', 1, '2021-06-15 22:26:55');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1362304631325102103, 1362304631325192103);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1362598312234024962, 1404805390442635266);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1404807635385069569, 1404805390442635266);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
