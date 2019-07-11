
create table `answer` (
    id integer(64) NOT NULL ,
    answer_text varchar(255) ,
    answer_images varchar(255) ,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;


CREATE TABLE `problem` (
    id integer(64) NOT NULL  ,
    problem_text varchar(255)  DEFAULT NULL ,
    parent_id integer(64) ,
    answer_id integer(64) ,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;



-- 包括选项，图片，语音等
create table `ext_data`(
	id integer(64) NOT NULL,
	problem_id integer(64), -- 所属问题
    -- 比如选项信息的key可以是 choice_A,choice_B,choice_C 等
    `key` varchar(255) ,
    `value` varchar(225) ,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;


CREATE TABLE `tag` (
    id integer(64) NOT NULL ,
    value varchar(255) NOT NULL ,
	parent_id integer(64) ,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;


CREATE TABLE `problem_tag` (
    problem_id integer(64) NOT NULL,
    tag_id integer(64) NOT NULL,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
	primary key (`problem_id`,`tag_id`)
) ;


create table `status`(
	problem_id integer(64),
    verify_status int(3), -- 审核状态
	is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
	primary key (`problem_id`)
) ;

CREATE TABLE `paper` (
    id integer(64) NOT NULL ,
    title varchar(255) NOT NULL, -- 试卷标题

    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;


CREATE TABLE `paper_item` (
    problem_id integer(64) NOT NULL,
    paper_id integer(64) NOT NULL,
    serial int(8) not null,

    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
	primary key (`problem_id`,`paper_id`)

) ;


CREATE TABLE `paper_tag` (
    paper_id integer(64) NOT NULL,
    tag_id integer(64) NOT NULL,

    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
	primary key (`paper_id`,`tag_id`)
) ;
create table `role`(
	id integer(64) NOT NULL ,
    rolename varchar(255) ,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;
create table `user` (
	id bigint(20) NOT NULL ,
    username varchar(255),
    password_salt_md5 varchar(32) ,
    salt varchar(32) ,
    image varchar(255) ,
    role_id integer(64) ,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;


CREATE TABLE `permission` (
    id integer(64) NOT NULL ,
    name varchar(255),
    url VARCHAR(255) ,
    method VARCHAR(255) ,
    parent_permission  integer(64)  ,
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`id`)
) ;



create table `role_permission` (
	role_id integer(64),
    permission_id integer(64),
    is_del bit(1) DEFAULT NULL ,
    create_at datetime DEFAULT NULL  ,
    create_by bigint(20) DEFAULT NULL ,
    update_at datetime DEFAULT NULL  ,
    update_by bigint(20) DEFAULT NULL ,
    primary key (`role_id`,`permission_id`)
) ;