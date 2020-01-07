create table bbs_top_menu
(
	id int auto_increment comment '主页顶部菜单主键ID' primary key ,
	name varchar(50) null comment '主页顶部菜单项显示名',
	parameter_name  varchar(50) null comment '主页显示菜单参数名',
    icon varchar(50) null  comment 'icon'
)
comment '主页顶部菜单';