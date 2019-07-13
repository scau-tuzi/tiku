DROP DATABASE if exists `tiku`;
CREATE SCHEMA `tiku` DEFAULT CHARACTER SET utf8mb4 ;
use `tiku`	;
SET NAMES utf8mb4;

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `answer_text` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '答案文本',
  `answer_images` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '答案图片',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '答案' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `ext_data`;
CREATE TABLE `ext_data`  (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `problem_id` int(64) NULL DEFAULT NULL,
  `keyname` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '数据项的key',
  `value` varchar(225) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '数据项的值',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '问题的额外信息' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `title` varchar(255) CHARACTER SET utf8mb4  NOT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '试卷' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '试卷项，每一项对应一个问题' ROW_FORMAT = Dynamic;




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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '试卷所属的标签' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '请求接口',
  `method` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '请求方法',
  `parent_permission` int(64) NULL DEFAULT NULL COMMENT '父权限',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '权限列表' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `problem_text` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '问题主体文本',
  `parent_id` int(64) NULL DEFAULT NULL COMMENT '父问题',
  `answer_id` int(64) NULL DEFAULT NULL COMMENT '对应的答案',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '问题' ROW_FORMAT = Dynamic;
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '问题和标签的关系（多对多)' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `rolename` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL,
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '角色信息' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '角色权限表' ROW_FORMAT = Dynamic;
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '问题状态表' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `value` varchar(255) CHARACTER SET utf8mb4  NOT NULL COMMENT '标签名称',
  `parent_id` int(64) NULL DEFAULT NULL COMMENT '父标签',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '标签' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `username` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL,
  `password_salt_md5` varchar(32) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '密码加盐后的md5',
  `salt` varchar(32) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '盐值',
  `image` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '头像url',
  `role_id` int(64) NULL DEFAULT NULL COMMENT '所属角色',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- 一个用户某道题目的状态
CREATE TABLE `user_problem_status`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',

  `problem_id` int(64) NOT NULL, COMMENT '问题id',
  `user_uuid` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '用户uuid',
  `status` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '状态字符串',

  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户题目状态表' ROW_FORMAT = Dynamic;

-- 题库用户 user表是后台用户
CREATE TABLE `tiku_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `username` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL,
  `password_salt_md5` varchar(32) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '密码加盐后的md5',
  `salt` varchar(32) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '盐值',
  `image` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '头像url',
  `user_uuid` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '用户uuid',
  `role_id` int(64) NULL DEFAULT NULL COMMENT '所属角色',
  `is_del` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '题库用户' ROW_FORMAT = Dynamic;





















