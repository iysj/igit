/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : esmybatis

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2015-01-11 21:31:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for site_main
-- ----------------------------
DROP TABLE IF EXISTS `site_main`;
CREATE TABLE `site_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '网站简称',
  `domain` varchar(50) DEFAULT NULL COMMENT '域名 不带http://',
  `link` varchar(200) DEFAULT NULL COMMENT '网址连接 带http://',
  `state` int(1) DEFAULT NULL COMMENT '状态 0禁用 1可用 2审核中',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `pic` varchar(200) DEFAULT NULL COMMENT '图片URL',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除状态 0=未删除 1=删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_main
-- ----------------------------
INSERT INTO `site_main` VALUES ('1', '华生论坛', 'www.mn606.com', 'http://www.mn606.com', '0', '2012-12-23 20:13:24', '2013-01-09 22:37:32', '0', '', '0');
INSERT INTO `site_main` VALUES ('2', '美女之家', 'www.mn606.com', 'http://www.mn606.com', '0', '2012-12-23 20:16:53', '2013-01-11 22:14:09', '0', '', '0');
INSERT INTO `site_main` VALUES ('3', '532525', '532532', 'http://532532.com', '0', '2012-12-23 20:32:15', '2012-12-23 20:32:15', '0', '', '1');
INSERT INTO `site_main` VALUES ('4', '百度', 'www.mn606.com', 'http://www.mn606.com', '0', '2013-01-08 16:29:14', '2013-01-08 16:29:14', '0', '', '1');
INSERT INTO `site_main` VALUES ('5', '11111', '1111', 'http://www.mn606.com', '0', '2013-01-09 16:55:23', '2013-01-09 16:55:23', '0', '', '1');
INSERT INTO `site_main` VALUES ('6', '1112', '222', 'http://www.sina.com', '0', '2013-01-09 16:55:47', '2013-01-09 16:55:47', '0', '', '1');

-- ----------------------------
-- Table structure for site_type
-- ----------------------------
DROP TABLE IF EXISTS `site_type`;
CREATE TABLE `site_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `code` varchar(10) DEFAULT NULL COMMENT 'code 英文和数字',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `descr` varchar(250) DEFAULT NULL COMMENT '描述',
  `state` int(1) DEFAULT NULL COMMENT '状态 0=可用,1=禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_type
-- ----------------------------
INSERT INTO `site_type` VALUES ('1', '美女论坛', 'mnlt', '0', '美女论坛', '0', '2012-12-23 20:06:45', '2013-01-15 17:45:55');
INSERT INTO `site_type` VALUES ('2', '美女网站', 'mnwz', '0', '11', '0', '2012-12-23 20:30:39', '2013-01-15 17:45:53');

-- ----------------------------
-- Table structure for site_type_rel
-- ----------------------------
DROP TABLE IF EXISTS `site_type_rel`;
CREATE TABLE `site_type_rel` (
  `siteId` int(11) NOT NULL COMMENT '站点id 关联：site_main.id',
  `siteTypeId` int(11) NOT NULL COMMENT '站点分类id 关联：site_type.id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_type_rel
-- ----------------------------
INSERT INTO `site_type_rel` VALUES ('1', '2');
INSERT INTO `site_type_rel` VALUES ('1', '1');
INSERT INTO `site_type_rel` VALUES ('2', '1');
INSERT INTO `site_type_rel` VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '系统url',
  `parentId` int(10) DEFAULT NULL COMMENT ' 父id 关联sys_menu.id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0=未删除，1=已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `actions` varchar(500) DEFAULT '0' COMMENT '注册Action 按钮|分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '', null, '0', '2012-12-23 17:21:58', '2013-01-10 22:30:50', '1', '');
INSERT INTO `sys_menu` VALUES ('2', '菜单管理', '/admin/sysMenu/view.do', '1', '0', '2012-12-23 18:18:32', '2013-01-13 02:29:33', '0', 'dataList.do');
INSERT INTO `sys_menu` VALUES ('3', '站点管理', '', null, '0', '2012-12-23 20:26:35', '2012-12-23 21:16:51', '1', '');
INSERT INTO `sys_menu` VALUES ('4', '站点信息管理', '/siteMain/list.shtml', '3', '0', '2012-12-23 20:26:53', '2013-01-13 01:24:47', '0', 'dataList.do|/siteType/typeListJson.do');
INSERT INTO `sys_menu` VALUES ('5', '站点类型', '/siteType/list.shtml', '3', '0', '2012-12-23 20:28:23', '2013-01-13 01:20:46', '0', 'dataList.do');
INSERT INTO `sys_menu` VALUES ('6', '操作员管理', '/sysUser/list.shtml', '1', '0', '2012-12-23 22:15:33', '2013-01-13 00:57:51', '0', 'dataList.do');
INSERT INTO `sys_menu` VALUES ('7', '角色管理', '/sysRole/role.shtml', '1', '0', '2012-12-24 22:17:51', '2013-01-13 01:15:00', '0', 'dataList.do|/sysMenu/getMenuTree.do');
INSERT INTO `sys_menu` VALUES ('8', '操作员授权', '/sysUser/userRole.shtml', '1', '0', '2013-01-06 11:42:26', '2013-01-14 11:35:04', '0', 'userList.do|/sysRole/loadRoleList.do');
INSERT INTO `sys_menu` VALUES ('9', '测试11', '111', null, '1', '2013-01-15 16:40:48', '2013-01-15 17:22:11', '1', '');
INSERT INTO `sys_menu` VALUES ('10', '测试2', '', null, '1', '2013-01-15 16:42:59', '2013-01-15 17:23:23', '1', '');
INSERT INTO `sys_menu` VALUES ('11', '页面管理', '/webPage/list.shtml', '3', '0', '2013-01-16 14:01:45', '2013-01-16 15:21:15', '0', 'dataList.do');
INSERT INTO `sys_menu` VALUES ('12', '111', '1111', null, '0', '2013-01-16 14:34:50', null, '0', '111');
INSERT INTO `sys_menu` VALUES ('13', 'ttt', 'tttt', null, '0', '2013-01-16 14:50:42', null, '0', 'tttt');
INSERT INTO `sys_menu` VALUES ('14', 'tt1', 't1', '13', '0', '2013-01-16 14:50:54', null, '0', '');
INSERT INTO `sys_menu` VALUES ('15', '11', '11', null, '0', '2013-01-16 14:52:46', null, '0', '');
INSERT INTO `sys_menu` VALUES ('16', 'aaa', '', null, '0', '2013-01-16 15:00:56', null, '0', '');
INSERT INTO `sys_menu` VALUES ('17', 'test', '/test.do', null, '0', '2013-01-16 15:25:35', null, '0', 'test.do');
INSERT INTO `sys_menu` VALUES ('18', 'test', 'ts', null, '0', '2013-01-16 16:20:40', null, '3', '');

-- ----------------------------
-- Table structure for sys_menu_btn
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_btn`;
CREATE TABLE `sys_menu_btn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` int(11) NOT NULL COMMENT ' 菜单id关联 sys_menu.id',
  `btnName` varchar(30) DEFAULT NULL COMMENT '按钮名称',
  `btnType` varchar(30) DEFAULT NULL COMMENT '按钮类型，用于列表页显示的按钮',
  `actionUrls` varchar(250) DEFAULT NULL COMMENT 'url注册，用"," 分隔 。用于权限控制UR',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_btn
-- ----------------------------
INSERT INTO `sys_menu_btn` VALUES ('5', '2', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('6', '2', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('7', '2', '删除', 'remove', 'delete.do');
INSERT INTO `sys_menu_btn` VALUES ('8', '6', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('9', '6', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('10', '6', '修改密码', 'editPwd', 'updatePwd.do');
INSERT INTO `sys_menu_btn` VALUES ('11', '6', '删除', 'remove', 'delete.do');
INSERT INTO `sys_menu_btn` VALUES ('12', '7', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('13', '7', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('14', '7', '删除', 'remove', 'delete.do');
INSERT INTO `sys_menu_btn` VALUES ('15', '8', '授权', 'authRole', '/sysUser/getUser.do|/sysUser/addUserRole.do');
INSERT INTO `sys_menu_btn` VALUES ('16', '5', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('17', '5', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('18', '5', '删除', 'remove', 'delete.do');
INSERT INTO `sys_menu_btn` VALUES ('19', '4', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('20', '4', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('21', '4', '删除', 'remove', 'delete.do');
INSERT INTO `sys_menu_btn` VALUES ('22', '11', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('23', '11', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('24', '11', '删除', 'remove', 'delete.do');
INSERT INTO `sys_menu_btn` VALUES ('25', '12', '11', '111', '111');
INSERT INTO `sys_menu_btn` VALUES ('26', '15', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('27', '15', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('28', '15', '删除', 'remove', 'delete.do');
INSERT INTO `sys_menu_btn` VALUES ('29', '17', 'add', 'add', '');
INSERT INTO `sys_menu_btn` VALUES ('30', '18', '添加', 'add', 'save.do');
INSERT INTO `sys_menu_btn` VALUES ('31', '18', '修改', 'edit', 'getId.do|save.do');
INSERT INTO `sys_menu_btn` VALUES ('32', '18', '删除', 'remove', 'delete.do');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `roleName` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  `state` int(1) DEFAULT NULL COMMENT '状态0=可用 1=禁用',
  `descr` varchar(200) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '2013-01-05 16:07:00', null, '2013-01-14 11:28:29', null, '0', null);
INSERT INTO `sys_role` VALUES ('3', '管理员', '2013-01-06 10:45:06', null, '2013-01-14 11:22:38', null, '0', null);
INSERT INTO `sys_role` VALUES ('18', '站点管理', '2013-01-13 01:21:46', null, '2013-01-16 14:05:39', null, '0', null);
INSERT INTO `sys_role` VALUES ('19', '测试管理员', '2013-01-13 17:10:21', null, '2013-01-16 14:06:08', null, '1', null);
INSERT INTO `sys_role` VALUES ('20', '23423', '2013-01-16 15:56:47', null, '2013-01-16 15:56:47', null, '0', null);

-- ----------------------------
-- Table structure for sys_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_rel`;
CREATE TABLE `sys_role_rel` (
  `roleId` int(11) NOT NULL COMMENT '角色主键 sys_role.id',
  `objId` int(11) NOT NULL COMMENT '关联主键 type=0管理sys_menu.id, type=1关联sys_user.id',
  `relType` int(1) DEFAULT NULL COMMENT '关联类型 0=菜单,1=用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_rel
-- ----------------------------
INSERT INTO `sys_role_rel` VALUES ('3', '8', '0');
INSERT INTO `sys_role_rel` VALUES ('3', '1', '0');
INSERT INTO `sys_role_rel` VALUES ('3', '2', '0');
INSERT INTO `sys_role_rel` VALUES ('3', '6', '0');
INSERT INTO `sys_role_rel` VALUES ('3', '7', '0');
INSERT INTO `sys_role_rel` VALUES ('3', '5', '2');
INSERT INTO `sys_role_rel` VALUES ('3', '8', '2');
INSERT INTO `sys_role_rel` VALUES ('3', '9', '2');
INSERT INTO `sys_role_rel` VALUES ('3', '12', '2');
INSERT INTO `sys_role_rel` VALUES ('3', '13', '2');
INSERT INTO `sys_role_rel` VALUES ('3', '15', '2');
INSERT INTO `sys_role_rel` VALUES ('1', '3', '0');
INSERT INTO `sys_role_rel` VALUES ('1', '4', '0');
INSERT INTO `sys_role_rel` VALUES ('1', '5', '0');
INSERT INTO `sys_role_rel` VALUES ('1', '19', '2');
INSERT INTO `sys_role_rel` VALUES ('1', '20', '2');
INSERT INTO `sys_role_rel` VALUES ('1', '21', '2');
INSERT INTO `sys_role_rel` VALUES ('1', '16', '2');
INSERT INTO `sys_role_rel` VALUES ('1', '17', '2');
INSERT INTO `sys_role_rel` VALUES ('1', '18', '2');
INSERT INTO `sys_role_rel` VALUES ('3', '6', '1');
INSERT INTO `sys_role_rel` VALUES ('1', '6', '1');
INSERT INTO `sys_role_rel` VALUES ('3', '5', '1');
INSERT INTO `sys_role_rel` VALUES ('18', '5', '1');
INSERT INTO `sys_role_rel` VALUES ('18', '11', '0');
INSERT INTO `sys_role_rel` VALUES ('18', '3', '0');
INSERT INTO `sys_role_rel` VALUES ('18', '4', '0');
INSERT INTO `sys_role_rel` VALUES ('18', '5', '0');
INSERT INTO `sys_role_rel` VALUES ('18', '19', '2');
INSERT INTO `sys_role_rel` VALUES ('18', '20', '2');
INSERT INTO `sys_role_rel` VALUES ('18', '16', '2');
INSERT INTO `sys_role_rel` VALUES ('18', '17', '2');
INSERT INTO `sys_role_rel` VALUES ('18', '22', '2');
INSERT INTO `sys_role_rel` VALUES ('18', '23', '2');
INSERT INTO `sys_role_rel` VALUES ('18', '24', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '8', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '1', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '2', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '6', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '7', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '3', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '4', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '5', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '11', '0');
INSERT INTO `sys_role_rel` VALUES ('19', '7', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '8', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '10', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '13', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '15', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '19', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '20', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '16', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '17', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '22', '2');
INSERT INTO `sys_role_rel` VALUES ('19', '23', '2');
INSERT INTO `sys_role_rel` VALUES ('1', '3', '1');
INSERT INTO `sys_role_rel` VALUES ('3', '3', '1');
INSERT INTO `sys_role_rel` VALUES ('18', '3', '1');
INSERT INTO `sys_role_rel` VALUES ('1', '1', '1');
INSERT INTO `sys_role_rel` VALUES ('3', '1', '1');
INSERT INTO `sys_role_rel` VALUES ('18', '1', '1');
INSERT INTO `sys_role_rel` VALUES ('20', '2', '0');
INSERT INTO `sys_role_rel` VALUES ('20', '1', '0');
INSERT INTO `sys_role_rel` VALUES ('20', '6', '0');
INSERT INTO `sys_role_rel` VALUES ('20', '5', '2');
INSERT INTO `sys_role_rel` VALUES ('20', '6', '2');
INSERT INTO `sys_role_rel` VALUES ('20', '7', '2');
INSERT INTO `sys_role_rel` VALUES ('20', '8', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `email` varchar(50) NOT NULL COMMENT '邮箱也是登录帐号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0=可用,1=禁用',
  `loginCount` int(11) DEFAULT NULL COMMENT '登录总次数',
  `loginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0=未删除,1=已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  `superAdmin` int(1) NOT NULL DEFAULT '0' COMMENT '是否超级管理员 0= 不是，1=是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin@qq.com', '123456', '超级大Boss', '0', '125', '2013-01-16 15:20:37', '0', '2012-12-23 23:01:15', '2013-01-16 15:20:37', null, null, '1');
INSERT INTO `sys_user` VALUES ('3', '362217990@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', 'vowo', '0', '1', '2013-01-07 12:53:29', '0', '2012-12-23 23:17:39', '2013-01-13 03:33:41', null, null, '0');
INSERT INTO `sys_user` VALUES ('5', 'wolf@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '大灰狼', '0', '135', '2014-11-02 17:47:10', '0', '2013-01-07 12:30:10', '2014-11-02 17:47:10', null, null, '0');
INSERT INTO `sys_user` VALUES ('6', 'youke@qq.com', null, ' 游客', '0', null, null, '0', '2013-01-13 03:41:32', '2013-01-13 03:41:32', null, null, '0');

-- ----------------------------
-- Table structure for web_page
-- ----------------------------
DROP TABLE IF EXISTS `web_page`;
CREATE TABLE `web_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '页面名称',
  `typeid` varchar(11) DEFAULT NULL COMMENT '页面类型id web_type.id',
  `localPath` varchar(250) DEFAULT NULL COMMENT '页面路径',
  `title` varchar(250) DEFAULT NULL COMMENT '页面标题',
  `keyword` varchar(250) DEFAULT NULL COMMENT '关键字',
  `description` varchar(500) DEFAULT NULL COMMENT '页面描述',
  `deleted` int(1) DEFAULT NULL COMMENT '删除状态 0=未删除，1=已删除',
  `templateContent` text COMMENT '页面模板内容',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间按',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of web_page
-- ----------------------------
