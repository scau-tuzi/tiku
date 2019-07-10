/*
 Navicat MySQL Data Transfer

 Source Server         : zhengfang
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : tiku

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 10/07/2019 11:26:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` int(64) NOT NULL COMMENT '标识',
  `answer_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '答案文本',
  `answer_images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '答案图片',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '答案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES (1000000, '1', NULL, NULL, '2019-07-10 09:58:58', 1, NULL, NULL);
INSERT INTO `answer` VALUES (1000001, '东', NULL, NULL, '2019-07-10 09:59:27', 1, NULL, NULL);
INSERT INTO `answer` VALUES (1000002, '四', NULL, NULL, '2019-07-10 10:00:10', 1, NULL, NULL);
INSERT INTO `answer` VALUES (1000003, 'apple', NULL, NULL, '2019-07-10 10:00:36', 1, NULL, NULL);
INSERT INTO `answer` VALUES (1000004, '撒哈拉大沙漠', NULL, NULL, '2019-07-10 10:42:47', 1, NULL, NULL);
INSERT INTO `answer` VALUES (1000005, 'C', NULL, NULL, '2019-07-10 11:14:22', 1, NULL, NULL);
INSERT INTO `answer` VALUES (1000006, '塔克拉玛干沙漠', NULL, NULL, '2019-07-10 11:14:48', 1, NULL, NULL);

-- ----------------------------
-- Table structure for ext_data
-- ----------------------------
DROP TABLE IF EXISTS `ext_data`;
CREATE TABLE `ext_data`  (
  `id` int(64) NOT NULL,
  `problem_id` int(64) NULL DEFAULT NULL,
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据项的key',
  `value` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据项的值',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '问题的额外信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ext_data
-- ----------------------------
INSERT INTO `ext_data` VALUES (10000051, 1000005, 'A', '5', NULL, '2019-07-10 11:01:12', 2, NULL, NULL);
INSERT INTO `ext_data` VALUES (10000052, 1000005, 'B', '1', NULL, '2019-07-10 11:01:52', 2, NULL, NULL);
INSERT INTO `ext_data` VALUES (10000053, 1000005, 'C', '4', NULL, '2019-07-10 11:02:10', 2, NULL, NULL);
INSERT INTO `ext_data` VALUES (10000054, 1000005, 'D', '11', NULL, '2019-07-10 11:02:38', 2, NULL, NULL);

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `id` int(64) NOT NULL COMMENT '标识',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '试卷' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES (100, 'first grade math test', NULL, '2019-07-10 10:57:28', 4, NULL, NULL);
INSERT INTO `paper` VALUES (101, 'seventh grade geography test', NULL, '2019-07-10 11:15:42', 4, NULL, NULL);

-- ----------------------------
-- Table structure for paper_item
-- ----------------------------
DROP TABLE IF EXISTS `paper_item`;
CREATE TABLE `paper_item`  (
  `problem_id` int(64) NOT NULL,
  `paper_id` int(64) NOT NULL,
  `serial` int(8) NOT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`problem_id`, `paper_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '试卷项，每一项对应一个问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper_item
-- ----------------------------
INSERT INTO `paper_item` VALUES (1000000, 100, 2, NULL, '2019-07-10 10:48:48', 4, NULL, NULL);
INSERT INTO `paper_item` VALUES (1000002, 100, 1, NULL, '2019-07-10 10:49:25', 4, NULL, NULL);
INSERT INTO `paper_item` VALUES (1000004, 101, 1, NULL, '2019-07-10 11:17:11', 4, NULL, NULL);
INSERT INTO `paper_item` VALUES (1000005, 100, 3, NULL, '2019-07-10 11:03:07', 4, NULL, NULL);
INSERT INTO `paper_item` VALUES (1000006, 101, 2, NULL, '2019-07-10 11:17:29', 4, NULL, NULL);

-- ----------------------------
-- Table structure for paper_tag
-- ----------------------------
DROP TABLE IF EXISTS `paper_tag`;
CREATE TABLE `paper_tag`  (
  `paper_id` int(64) NOT NULL,
  `tag_id` int(64) NOT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`paper_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '试卷所属的标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper_tag
-- ----------------------------
INSERT INTO `paper_tag` VALUES (100, 11, NULL, '2019-07-10 10:56:23', 4, NULL, NULL);
INSERT INTO `paper_tag` VALUES (100, 21, NULL, '2019-07-10 10:56:47', 4, NULL, NULL);
INSERT INTO `paper_tag` VALUES (101, 12, NULL, '2019-07-10 11:16:22', 4, NULL, NULL);
INSERT INTO `paper_tag` VALUES (101, 24, NULL, '2019-07-10 11:16:34', 4, NULL, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(64) NOT NULL COMMENT '标识',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求接口',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `parent_permission` int(64) NULL DEFAULT NULL COMMENT '父权限',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (123456, 'query', NULL, 'query', NULL, NULL, '2019-07-10 10:29:16', 2, NULL, NULL);
INSERT INTO `permission` VALUES (123457, 'post', NULL, 'post', NULL, NULL, '2019-07-10 10:31:02', 2, NULL, NULL);
INSERT INTO `permission` VALUES (123458, 'get', NULL, 'get', NULL, NULL, '2019-07-03 10:31:28', 2, NULL, NULL);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` int(64) NOT NULL COMMENT '标识',
  `problem_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题主体文本',
  `parent_id` int(64) NULL DEFAULT NULL COMMENT '父问题',
  `answer_id` int(64) NULL DEFAULT NULL COMMENT '对应的答案',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (1000000, '9-8=？', NULL, 1000000, NULL, '2019-07-10 09:49:56', 1, NULL, NULL);
INSERT INTO `problem` VALUES (1000001, '太阳从{0}边生起', NULL, 1000001, NULL, '2019-07-10 09:52:33', 1, NULL, NULL);
INSERT INTO `problem` VALUES (1000002, '青蛙有{0}条腿', NULL, 1000002, NULL, '2019-07-10 09:54:44', 1, NULL, NULL);
INSERT INTO `problem` VALUES (1000003, '请拼写出苹果的英文', NULL, 1000003, NULL, '2019-07-10 09:55:55', 1, NULL, NULL);
INSERT INTO `problem` VALUES (1000004, '世界上最大的沙漠叫？', NULL, 1000004, NULL, '2019-07-10 10:42:13', 1, NULL, NULL);
INSERT INTO `problem` VALUES (1000005, '下列选项是偶数的是？', NULL, 1000005, NULL, '2019-07-10 10:59:58', 1, NULL, NULL);
INSERT INTO `problem` VALUES (1000006, '中国最大的沙漠叫？', NULL, 1000006, NULL, '2019-07-10 11:12:55', 1, NULL, NULL);

-- ----------------------------
-- Table structure for problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `problem_tag`;
CREATE TABLE `problem_tag`  (
  `problem_id` int(64) NOT NULL,
  `tag_id` int(64) NOT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`problem_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '问题和标签的关系（多对多)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_tag
-- ----------------------------
INSERT INTO `problem_tag` VALUES (1000000, 11, NULL, '2019-07-10 10:02:23', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000000, 21, NULL, '2019-07-10 10:03:16', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000001, 11, NULL, '2019-07-10 10:03:47', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000001, 22, NULL, '2019-07-10 10:04:03', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000002, 11, NULL, '2019-07-10 10:43:47', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000002, 21, NULL, '2019-07-10 10:44:00', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000003, 11, NULL, '2019-07-10 10:44:34', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000003, 23, NULL, '2019-07-10 10:44:15', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000004, 12, NULL, '2019-07-10 10:44:47', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000004, 24, NULL, '2019-07-10 10:45:00', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000005, 11, NULL, '2019-07-10 11:00:20', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000005, 21, NULL, '2019-07-10 11:00:35', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000006, 12, NULL, '2019-07-10 11:13:25', 1, NULL, NULL);
INSERT INTO `problem_tag` VALUES (1000006, 24, NULL, '2019-07-10 11:13:41', 1, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(64) NOT NULL COMMENT '标识',
  `rolename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (100, 'superadministrator', NULL, '2019-07-03 10:32:57', 2, NULL, NULL);
INSERT INTO `role` VALUES (101, 'administrator', NULL, '2019-07-10 10:33:33', 2, NULL, NULL);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` int(64) NOT NULL,
  `permission_id` int(64) NOT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (100, 123456, NULL, '2019-07-10 10:34:03', 2, NULL, NULL);
INSERT INTO `role_permission` VALUES (100, 123457, NULL, '2019-07-03 10:34:19', 2, NULL, NULL);
INSERT INTO `role_permission` VALUES (100, 123458, NULL, '2019-07-10 10:34:33', 3, NULL, NULL);
INSERT INTO `role_permission` VALUES (101, 123456, NULL, '2019-07-10 10:35:51', 3, NULL, NULL);

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status`  (
  `problem_id` int(64) NOT NULL,
  `verify_status` int(3) NULL DEFAULT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`problem_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '问题状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES (1000000, 1, NULL, '2019-07-10 10:18:01', 1, NULL, NULL);
INSERT INTO `status` VALUES (1000001, 1, NULL, '2019-07-10 10:19:28', 1, '2019-07-10 10:19:58', 1);
INSERT INTO `status` VALUES (1000002, 1, NULL, '2019-07-10 10:20:25', 1, NULL, NULL);
INSERT INTO `status` VALUES (1000003, 1, NULL, '2019-07-10 11:09:19', 1, NULL, NULL);
INSERT INTO `status` VALUES (1000004, 0, NULL, '2019-07-10 11:09:44', 1, NULL, NULL);
INSERT INTO `status` VALUES (1000005, 1, NULL, '2019-07-10 11:10:06', 1, NULL, NULL);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(64) NOT NULL COMMENT '标识',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名称',
  `parent_id` int(64) NULL DEFAULT NULL COMMENT '父标签',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, 'grade', NULL, NULL, '2019-07-10 10:09:15', 1, NULL, NULL);
INSERT INTO `tag` VALUES (2, 'project', NULL, NULL, '2019-07-10 10:09:53', 1, NULL, NULL);
INSERT INTO `tag` VALUES (11, 'first grade', 1, NULL, '2019-07-10 10:10:35', 1, NULL, NULL);
INSERT INTO `tag` VALUES (12, 'seventh grade', 1, NULL, '2019-07-10 10:46:52', 1, NULL, NULL);
INSERT INTO `tag` VALUES (21, 'math', 2, NULL, '2019-07-10 10:12:56', 1, NULL, NULL);
INSERT INTO `tag` VALUES (22, 'natural science', 2, NULL, '2019-07-10 10:16:00', 1, NULL, NULL);
INSERT INTO `tag` VALUES (23, 'english', 2, NULL, '2019-07-10 10:46:27', 1, NULL, NULL);
INSERT INTO `tag` VALUES (24, 'geography', 2, NULL, '2019-07-10 10:47:31', 1, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '标识',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password_salt_md5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码加盐后的md5',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐值',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像url',
  `role_id` int(64) NULL DEFAULT NULL COMMENT '所属角色',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (100, 'zhangsan', '1000', '1', NULL, NULL, NULL, '2019-07-10 10:21:53', 1, NULL, NULL);
INSERT INTO `user` VALUES (101, 'lisi', '1001', '2', NULL, NULL, NULL, '2019-07-03 10:22:26', 1, NULL, NULL);
INSERT INTO `user` VALUES (102, 'wangwu', '1002', '3', NULL, NULL, NULL, '2019-07-10 10:22:57', 1, NULL, NULL);
INSERT INTO `user` VALUES (103, 'zhaoliu', '1003', '4', NULL, NULL, NULL, '2019-07-03 10:23:38', 1, NULL, NULL);
INSERT INTO `user` VALUES (200, 'admin', '0000', '1', NULL, NULL, NULL, '2019-07-10 10:24:53', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
