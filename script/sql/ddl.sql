
-- 初始化仓库
DROP DATABASE if exists `tiku`;
CREATE SCHEMA `tiku` DEFAULT CHARACTER SET utf8mb4 ;
use `tiku`	;
--  不用手动清空，因为上面已经把整个架构删掉一次了 
-- DROP TABLE  if exists `problem`;-

create table `answer` (
    id integer(64) NOT NULL COMMENT '标识',
    answer_text varchar(255) comment '答案文本',
    answer_images varchar(255) comment '答案图片',
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '答案';


CREATE TABLE `problem` (
    id integer(64) NOT NULL  COMMENT '标识', 
    problem_text varchar(255)  DEFAULT NULL comment '问题主体文本',
    parent_id integer(64) comment '父问题',
    answer_id integer(64) comment '对应的答案',
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '问题' ;



-- 包括选项，图片，语音等
create table `ext_data`(
	id integer(64) NOT NULL,
	problem_id integer(64), -- 所属问题
    -- 比如选项信息的key可以是 choice_A,choice_B,choice_C 等
    `key` varchar(255) comment '数据项的key',  
    `value` varchar(225) comment '数据项的值',
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '问题的额外信息';


CREATE TABLE `tag` (
    id integer(64) NOT NULL COMMENT '标识',
    value varchar(255) NOT NULL comment '标签名称',
	parent_id integer(64) comment '父标签',
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '标签';


CREATE TABLE `problem_tag` (
    problem_id integer(64) NOT NULL,
    tag_id integer(64) NOT NULL,
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
	primary key (`problem_id`,`tag_id`)
) comment '问题和标签的关系（多对多)' ;


create table `status`(
	problem_id integer(64),
    verify_status int(3), -- 审核状态
	is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
	primary key (`problem_id`)
) comment '问题状态表';

CREATE TABLE `paper` (
    id integer(64) NOT NULL COMMENT '标识',
    title varchar(255) NOT NULL, -- 试卷标题
    
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '试卷';


CREATE TABLE `paper_item` (
    problem_id integer(64) NOT NULL,
    paper_id integer(64) NOT NULL,
    serial int(8) not null,
    
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
	primary key (`problem_id`,`paper_id`)

) comment '试卷项，每一项对应一个问题';


CREATE TABLE `paper_tag` (
    paper_id integer(64) NOT NULL,
    tag_id integer(64) NOT NULL,
    
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
	primary key (`paper_id`,`tag_id`)
) comment '试卷所属的标签' ;
create table `role`(
	id integer(64) NOT NULL COMMENT '标识',
    rolename varchar(255) ,
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '角色信息';
create table `user` (
	id bigint(20) NOT NULL COMMENT '标识',
    username varchar(255),
    password_salt_md5 varchar(32) comment '密码加盐后的md5',
    salt varchar(32) comment '盐值',
    image varchar(255) comment '头像url',
    role_id integer(64) comment '所属角色',
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '用户表';


CREATE TABLE `permission` (
    id integer(64) NOT NULL COMMENT '标识',
    name varchar(255), 
    url VARCHAR(255) comment '请求接口',
    method VARCHAR(255) comment '请求方法',
    parent_permission  integer(64)  comment '父权限',
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`id`)
) comment '权限列表';



create table `role_permission` (
	role_id integer(64),
    permission_id integer(64),
    is_del bit(1) DEFAULT NULL comment '是否删除',
    create_at datetime DEFAULT NULL  comment '创建时间',
    create_by bigint(20) DEFAULT NULL comment  '创建人',
    update_at datetime DEFAULT NULL  comment '更新时间',
    update_by bigint(20) DEFAULT NULL comment '更新人',
    primary key (`role_id`,`permission_id`)
) comment '角色权限表' ;
