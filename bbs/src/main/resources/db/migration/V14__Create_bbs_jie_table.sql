create table bbs_jie
(
	id int auto_increment comment '求解主键ID',
	title varchar(20) null comment '求解标题',
	column_id int null comment '专栏ID',
	project_id int null comment '产品ID',
	content text null comment '内容',
	gmt_create bigint null comment '创建时间',
	gmt_modify bigint null comment '修改时间',
	creator int null comment '创建者ID',
	view_count int null comment '查看数',
	like_count int null comment '点赞数',
	comment_count int null comment '回复数',
	is_sticky boolean null comment '是否置顶',
	is_boutique boolean null comment '是否精贴',
	is_closed boolean null comment '是否已结',
	constraint bbs_jie_pk
		primary key (id)
)
comment '求解表';