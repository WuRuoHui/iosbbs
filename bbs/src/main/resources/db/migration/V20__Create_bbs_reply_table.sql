create table bbs_reply
(
	id int auto_increment comment '回复主键id',
	content varchar(255) null comment '回复内容',
	gmt_create bigint null comment '创建时间',
	parent_id int null comment '父id',
	gmt_modify bigint null comment '修改时间',
	type int null comment '回复的类型（文章或者评论)',
	like_count int default 0 comment '点赞数',
	creator int null comment '创建者id',
	is_accept int default 0 comment '是否采纳',
	constraint bbs_reply_pk
		primary key (id)
);
