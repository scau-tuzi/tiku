
-- 初始化仓库
DROP DATABASE if exists `tiku`;
CREATE SCHEMA `tiku` DEFAULT CHARACTER SET utf8mb4 ;
use `tiku`	;
--  不用手动清空，因为上面已经把整个架构删掉一次了 
-- DROP TABLE  if exists `problem`;-

-- 问题
CREATE TABLE `problem` (
  `id` int(11) NOT NULL auto_increment,
  -- 问题的基本信息
  `problem_text` varchar(255), -- 问题主体文本
  `parent_id` int(11) , -- 父问题
   `anwser_id` int(11), -- 对应的答案
  `is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
   primary key (`id`)
) ;

-- 答案
create table `anwser` (
 `id` int(11) not null auto_increment,
  `answer_text` varchar(255), -- 答案文本
  `answer_images` varchar(255), -- 答案图片
  
    `is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
   primary key (`id`)
);

-- 问题的额外信息
-- 包括选项，图片，语音等
create table `ext_data`(
	`problem_id` int(11), -- 所属问题
    -- 数据项的key ，比如选项信息的key可以是 choice_A,choice_B,choice_C 等
    `key` varchar(255),  
    `value` varchar(225), -- 数据项的值
	
    `is_del` int(1),
	`create_time` datetime,
	`create_user` int(11),
	`last_modifiy_time` datetime,
	`last_modifiy_user` int(11),
	`delete_time` datetime
);

-- 标签
CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `value` varchar(255) NOT NULL, -- 标签名称
  
  primary key (`id`),
    `is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime
) ;

-- 问题和标签的关系（多对多)
CREATE TABLE `problem_tag` (
  `problem_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
	
    `is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
	primary key (`problem_id`,`tag_id`)
) ;

-- 问题状态表
create table `status`(
	`problem_id` int(11),
    `verify_status` int(3), -- 审核状态
    
	`is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
	primary key (`problem_id`)
);

-- 试卷
CREATE TABLE `paper` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL, -- 试卷标题
  
    `is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
	primary key (`id`)
) ;

-- 试卷项，每一项对应一个问题
CREATE TABLE `paper_item` (
  `problem_id` int(11) NOT NULL,
  `paper_id` int(11) NOT NULL,
  `serial` int(8) not null,
  
    `is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
	primary key (`problem_id`,`paper_id`)

) ;

-- 试卷所属的标签
CREATE TABLE `paper_tag` (
  `paper_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  
    `is_del` int(1),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
	primary key (`paper_id`,`tag_id`)
) ;

-- 用户表
create table `user` (
	`id` int(11),
    `username` varchar(255),
    `password_salt_md5` varchar(32), -- 密码加盐后的md5
    `salt` varchar(32), -- 盐值
    `image` varchar(255), -- 头像url
    `role_id` int(11), -- 所属角色
    
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
    primary key (`id`)
);

-- 权限列表
CREATE TABLE `permission` (
    `id` INT(11),
    `name` varchar(255), -- 
    `url` VARCHAR(255), -- 请求接口
    `method` VARCHAR(255), -- 请求方法
    `parent_permission` INT(11), -- 父权限
    
    `create_time` DATETIME, 
    `create_user` INT(11),
    `last_modifiy_time` DATETIME,
    `last_modifiy_user` INT(11),
    `delete_time` DATETIME,
    PRIMARY KEY (`id`)
);

-- 角色信息
create table `role`(
	`id` int(11),
    `rolename` varchar(255) ,
    
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
    primary key (`id`)
);

-- 角色权限表
create table `role_permission` (
	`role_id` int(11),
    `permission_id` int(11),
  `create_time` datetime,
  `create_user` int(11),
  `last_modifiy_time` datetime,
  `last_modifiy_user` int(11),
  `delete_time` datetime,
    primary key (`role_id`,`permission_id`)
);

