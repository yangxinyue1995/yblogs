/*
Navicat MySQL Data Transfer

Source Server         : blogs
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : blogs

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2019-10-21 18:07:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blogs_body
-- ----------------------------
DROP TABLE IF EXISTS `blogs_body`;
CREATE TABLE `blogs_body` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `img_name` varchar(255) DEFAULT NULL,
  `img_url` mediumtext,
  `tag` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `create_date_time` varchar(255) DEFAULT NULL,
  `weigh` int(11) DEFAULT NULL COMMENT '权重 计量热点次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` mediumtext COMMENT '评论',
  `blogs_id` int(11) DEFAULT NULL COMMENT '文章id',
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `create_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gaoxiao_images
-- ----------------------------
DROP TABLE IF EXISTS `gaoxiao_images`;
CREATE TABLE `gaoxiao_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1779 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `img_bytes` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5380 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `sex` bigint(32) DEFAULT '1' COMMENT '性别',
  `create_date_time` datetime DEFAULT NULL,
  `user_img_url` varchar(255) DEFAULT NULL,
  `user_sign` varchar(255) DEFAULT NULL COMMENT '签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for tb_third_post
-- ----------------------------
DROP TABLE IF EXISTS `tb_third_post`;
CREATE TABLE `tb_third_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `third_type` varchar(255) DEFAULT NULL COMMENT '第三方类型',
  `third_name` varchar(255) DEFAULT NULL COMMENT '来源名称',
  `object_id` varchar(255) DEFAULT NULL COMMENT '来源id',
  `title` varchar(255) DEFAULT NULL COMMENT '文章名称',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `content` longtext COMMENT '文章主体',
  `like_num` int(11) DEFAULT NULL COMMENT '喜欢数',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论数',
  `redirect_url` varchar(255) DEFAULT NULL COMMENT '回调地址',
  `creatime` datetime DEFAULT NULL COMMENT '创建时间',
  `can_analysis` varchar(255) DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL COMMENT '本地创建时间',
  `weigh` int(11) DEFAULT NULL COMMENT '权重值',
  `type` varchar(255) DEFAULT NULL COMMENT '文章类型 转载，原创',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51636 DEFAULT CHARSET=utf8;
