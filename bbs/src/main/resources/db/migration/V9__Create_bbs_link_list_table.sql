create table bbs_link_list
(
	link_id int auto_increment,
	logo varchar(150) null comment 'logo地址',
	website_name varchar(100) null comment '网站名称',
	website_url varchar(150) null comment '网站链接',
	master_email varchar(50) null comment '站长邮箱',
	add_time bigint null comment '添加时间',
	modify_time bigint null comment '修改时间',
	show_address varchar(10) null comment '是否主页显示',
	constraint bbs_link_list_pk
		primary key (link_id)
)
comment '友情链接';