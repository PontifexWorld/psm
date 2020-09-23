/*
Navicat MySQL Data Transfer

Source Server         : localhost1
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : mds

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-09-23 18:08:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lianjia
-- ----------------------------
DROP TABLE IF EXISTS `lianjia`;
CREATE TABLE `lianjia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房源编号',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位',
  `mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '方式',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型',
  `floor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼层',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of lianjia
-- ----------------------------
INSERT INTO `lianjia` VALUES ('3', 'BJ2371645828445249536', '5200.00', '元/月', '整租', '1室1厅1卫 71㎡', '南 中楼层/11层');
INSERT INTO `lianjia` VALUES ('4', 'BJ2375960143411019776', '2200.00', '元/月', '整租', '1房间1卫 42㎡', '西南 高楼层/26层');
INSERT INTO `lianjia` VALUES ('5', 'BJ2425308423542546432', '6300.00', '元/月', '整租', '2室1厅1卫 53㎡', '南 低楼层/6层');
INSERT INTO `lianjia` VALUES ('6', 'BJ2468770790464823296', '3500.00', '元/月', '整租', '2室1厅1卫 92㎡ 精装修', '南/北 中楼层/14层');
INSERT INTO `lianjia` VALUES ('7', 'BJ2469369922154151936', '4500.00', '元/月', '整租', '2室1厅1卫 58㎡', '南 中楼层/6层');
INSERT INTO `lianjia` VALUES ('8', 'BJ2468821822838145024', '3500.00', '元/月', '整租', '2室1厅1卫 63㎡', '南/北 高楼层/6层');
INSERT INTO `lianjia` VALUES ('9', 'BJ2468640495157649408', '2500.00', '元/月', '整租', '2室1厅1卫 95㎡', '南/北 高楼层/6层');
INSERT INTO `lianjia` VALUES ('10', 'BJ2468701921612144640', '4500.00', '元/月', '整租', '2室1厅1卫 84㎡', '东 低楼层/24层');
INSERT INTO `lianjia` VALUES ('11', 'BJ2468627827269320704', '6000.00', '元/月', '整租', '1室1厅1卫 52㎡ 精装修', '东 高楼层/22层');
INSERT INTO `lianjia` VALUES ('12', 'BJ2467444582678536192', '3500.00', '元/月', '整租', '2室1厅1卫 88㎡ 精装修', '南/北 中楼层/6层');
INSERT INTO `lianjia` VALUES ('13', 'BJ2467206292029317120', '2600.00', '元/月', '整租', '2室1厅1卫 88㎡', '南/北 高楼层/9层');
INSERT INTO `lianjia` VALUES ('14', 'BJ2467329706807001088', '4600.00', '元/月', '整租', '2室1厅1卫 60㎡', '南/北 中楼层/6层');
INSERT INTO `lianjia` VALUES ('15', 'BJ2467407722547781632', '4500.00', '元/月', '整租', '2室1厅1卫 66㎡ 精装修', '南 低楼层/13层');
INSERT INTO `lianjia` VALUES ('16', 'BJ2467877104365076480', '2600.00', '元/月', '整租', '1室1厅1卫 67㎡ 精装修', '南 高楼层/11层');
INSERT INTO `lianjia` VALUES ('17', 'BJ2469314592396804096', '5800.00', '元/月', '整租', '2室1厅1卫 58㎡ 精装修', '东/北 低楼层/16层');
INSERT INTO `lianjia` VALUES ('18', 'BJ2468896102670680064', '4500.00', '元/月', '整租', '1室1厅1卫 44㎡', '南 地下室/7层');
INSERT INTO `lianjia` VALUES ('19', 'BJ2467303931651833856', '4800.00', '元/月', '整租', '2室1厅1卫 54㎡', '南/北 中楼层/6层');
INSERT INTO `lianjia` VALUES ('20', 'BJ2467260845621510144', '4500.00', '元/月', '整租', '2室1厅1卫 68㎡ 精装修', '东/西 中楼层/6层');
INSERT INTO `lianjia` VALUES ('21', 'BJ2467867729827741696', '6000.00', '元/月', '整租', '2室1厅1卫 55㎡', '南/北 高楼层/6层');
INSERT INTO `lianjia` VALUES ('22', 'BJ2467251387298488320', '4600.00', '元/月', '整租', '2室1厅1卫 61㎡', '南/北 中楼层/6层');
INSERT INTO `lianjia` VALUES ('23', 'BJ2467421271592271872', '5300.00', '元/月', '整租', '1室1厅1卫 44㎡', '南 低楼层/6层');
INSERT INTO `lianjia` VALUES ('24', 'BJ2467348686024212480', '4000.00', '元/月', '整租', '2室1厅1卫 51㎡', '南/北 高楼层/6层');
INSERT INTO `lianjia` VALUES ('25', 'BJ2467894912775880704', '4200.00', '元/月', '整租', '2室1厅1卫 60㎡', '南 低楼层/16层');
INSERT INTO `lianjia` VALUES ('26', 'BJ2467431456108969984', '5800.00', '元/月', '整租', '1室1厅1卫 48㎡ 精装修', '南 高楼层/18层');
INSERT INTO `lianjia` VALUES ('27', 'BJ2467136659695599616', '6100.00', '元/月', '整租', '1室1厅1卫 49㎡', '东 中楼层/6层');
INSERT INTO `lianjia` VALUES ('28', 'BJ2467280492924510208', '6000.00', '元/月', '整租', '1室1厅1卫 51㎡ 精装修', '东 高楼层/24层');
INSERT INTO `lianjia` VALUES ('29', 'BJ2467260572572590080', '6000.00', '元/月', '整租', '1室1厅1卫 73㎡ 精装修', '东北 中楼层/11层');
INSERT INTO `lianjia` VALUES ('30', 'BJ2467325785829015552', '5500.00', '元/月', '整租', '1室1厅1卫 41㎡', '南 低楼层/6层');
INSERT INTO `lianjia` VALUES ('31', 'BJ2467221391314001920', '5600.00', '元/月', '整租', '1室1厅1卫 40㎡ 精装修', '南 中楼层/5层');
INSERT INTO `lianjia` VALUES ('32', 'BJ2467990991387770880', '5800.00', '元/月', '整租', '1室1厅1卫 44㎡', '西 低楼层/6层');

-- ----------------------------
-- Table structure for ms_auth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `ms_auth_client_details`;
CREATE TABLE `ms_auth_client_details` (
  `CLIENT_ID` varchar(32) NOT NULL COMMENT '主键,用于唯一标识每一个客户端',
  `RESOURCE_IDS` varchar(256) DEFAULT NULL COMMENT '客户端所能访问的资源ID集合,多个资源时用逗号(,)分隔',
  `CLIENT_SECRET` varchar(256) DEFAULT NULL COMMENT '用于指定客户端(CLIENT)的访问密匙',
  `SCOPE` varchar(256) DEFAULT NULL COMMENT '指定客户端申请的权限范围,可选值包括READ,WRITE,TRUST',
  `AUTHORIZED_GRANT_TYPES` varchar(256) DEFAULT NULL COMMENT '指定客户端支持的GRANT_TYPE,可选值包括AUTHORIZATION_CODE,PASSWORD,REFRESH_TOKEN,IMPLICIT,CLIENT_CREDENTIALS, 若支持多个GRANT_TYPE用逗号(,)分隔',
  `WEB_SERVER_REDIRECT_URI` varchar(256) DEFAULT NULL COMMENT '客户端的重定向URI,可为空, 当GRANT_TYPE为AUTHORIZATION_CODE或IMPLICIT时',
  `AUTHORITIES` varchar(256) DEFAULT NULL COMMENT '指定客户端所拥有的SPRING SECURITY的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: ROLE_UNITY,ROLE_USER',
  `ACCESS_TOKEN_VALIDITY` int(11) DEFAULT NULL COMMENT '设定客户端的ACCESS_TOKEN的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)',
  `REFRESH_TOKEN_VALIDITY` int(11) DEFAULT NULL COMMENT '设定客户端的REFRESH_TOKEN的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天)',
  `ADDITIONAL_INFORMATION` varchar(4096) DEFAULT NULL COMMENT '预留的字段,在OAUTH的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据',
  `AUTOAPPROVE` varchar(256) DEFAULT NULL COMMENT '设置用户是否自动APPROVAL操作, 默认值为 FALSE, 可选值包括 TRUE,FALSE, READ,WRITE',
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ms_auth_client_details
-- ----------------------------
INSERT INTO `ms_auth_client_details` VALUES ('client', null, '$2a$10$Ptj2B7g6wE8NdVJqYRJTNuWMkyr2Z1cjRMgGNCc7lAHFxcEegkv6K', 'WRIGTH,read', 'authorization_code', 'localhost:18000/login', 'WRIGTH_READ,WRIGTH_WRITE', '20000', null, null, 'true');
INSERT INTO `ms_auth_client_details` VALUES ('client1', null, '$2a$10$Ptj2B7g6wE8NdVJqYRJTNuWMkyr2Z1cjRMgGNCc7lAHFxcEegkv6K', 'WRIGTH,read', 'implicit,refresh_token,password,authorization_code', 'localhost:18000/login', 'WRIGTH_READ,WRIGTH_WRITE', '20000', null, null, 'true');
INSERT INTO `ms_auth_client_details` VALUES ('client2', null, '$2a$10$Ptj2B7g6wE8NdVJqYRJTNuWMkyr2Z1cjRMgGNCc7lAHFxcEegkv6K', 'WRIGTH,read', 'implicit,refresh_token,password,authorization_code', 'localhost:18000/login111111', 'WRIGTH_READ,WRIGTH_WRITE', '20000', null, null, 'true');

-- ----------------------------
-- Table structure for ms_auth_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ms_auth_login_log`;
CREATE TABLE `ms_auth_login_log` (
  `ID` varchar(32) DEFAULT NULL COMMENT '唯一ID',
  `LOGIN_USERNAME` varchar(64) DEFAULT NULL COMMENT '登录名',
  `LOGIN_IP` varchar(16) DEFAULT NULL COMMENT '登录IP',
  `FILTER_URL` varchar(255) DEFAULT NULL COMMENT '被拦截地址',
  `LOGIN_STATUS` int(4) DEFAULT NULL COMMENT '登录状态1：成功登录 0：登录失败',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ms_auth_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for ms_auth_org
-- ----------------------------
DROP TABLE IF EXISTS `ms_auth_org`;
CREATE TABLE `ms_auth_org` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '组织机构ID',
  `NAME` varchar(128) NOT NULL DEFAULT '' COMMENT '组织机构名称',
  `ABBR_NAME` varchar(64) NOT NULL DEFAULT '' COMMENT '组织机构简称',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '组织机构类型',
  `PARENT_ID` varchar(32) DEFAULT '' COMMENT '父类组织机构ID',
  `SERIAL_INDEX` double DEFAULT NULL COMMENT '组织机构排序',
  `IS_DELETE` int(11) NOT NULL COMMENT '是否已经删除1：已删除 0：未删除',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of ms_auth_org
-- ----------------------------

-- ----------------------------
-- Table structure for ms_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `ms_auth_user`;
CREATE TABLE `ms_auth_user` (
  `ID` varchar(32) NOT NULL COMMENT '人员ID',
  `USERNAME` varchar(64) NOT NULL COMMENT '账号',
  `DISPLAY_NAME` varchar(64) NOT NULL COMMENT '显示名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `ORG_ID` varchar(128) DEFAULT NULL COMMENT '组织机构ID',
  `EXPIRY_DATE` datetime DEFAULT NULL COMMENT '账号失效时间',
  `PASSWORD_EXPIRY_DATE` datetime DEFAULT NULL COMMENT '密码失效时间',
  `SERIAL_INDEX` double DEFAULT NULL COMMENT '排序',
  `IS_DELETED` int(11) NOT NULL COMMENT '是否已经删除1：已删除 0：未删除',
  `AVAILABLE_FLAG` int(11) DEFAULT NULL COMMENT '是否可用 0：禁用 1：正常 2：未激活',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `PHONE_NO` varchar(16) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `IS_DELETE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of ms_auth_user
-- ----------------------------
INSERT INTO `ms_auth_user` VALUES ('01b38c76008b410a947e143f64a80212', 'user', '普通用户(密码为secret)', '$2a$10$Ptj2B7g6wE8NdVJqYRJTNuWMkyr2Z1cjRMgGNCc7lAHFxcEegkv6K', '', '2020-12-30 16:00:00', '2020-12-30 16:00:00', '3', '0', '1', '2019-11-29 06:51:22', '2020-01-07 09:35:21', '18501184252', null, null);

-- ----------------------------
-- Table structure for ms_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ms_auth_user_role`;
CREATE TABLE `ms_auth_user_role` (
  `ID` varchar(50) NOT NULL COMMENT '唯一ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of ms_auth_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pid` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '/user/common', 'common', null, '0');
INSERT INTO `permission` VALUES ('2', '/user/admin', 'admin', null, '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'USER');
INSERT INTO `role` VALUES ('2', 'ADMIN');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) NOT NULL,
  `permission_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '2', '1');
INSERT INTO `role_permission` VALUES ('3', '2', '2');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1001', '阿斯顿', '12', '阿德飒飒');
INSERT INTO `t_student` VALUES ('1002', '梁世远', '900', '测试数据信息');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `age` varchar(30) DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user', '{bcrypt}$2a$10$Tme77eHtXzcB8ghQUepYguJr7P7ESg0Y7XHMnk60s.kf2A.BWBD9m');
INSERT INTO `user` VALUES ('2', 'admin', '{bcrypt}$2a$10$Tme77eHtXzcB8ghQUepYguJr7P7ESg0Y7XHMnk60s.kf2A.BWBD9m');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=armscii8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '1');
INSERT INTO `user_role` VALUES ('3', '2', '2');
