create table bbs_left_nav
(
	id int auto_increment,
	title varchar(50) null comment '菜单显示名',
	icon varchar(50) null comment '显示图标',
	href varchar(255) null comment '链接地址',
	spread boolean default false null,
	menu_level int null comment '菜单等级',
	is_parent boolean default false null comment '是否为父级菜单',
	parent_id int null comment '父级菜单ID',
	constraint bbs_left_nav_pk
		primary key (id)
)
comment '左侧菜单';