create table bbs_game_download
(
	id int auto_increment comment '主键ID',
	game_id int null comment '游戏ID',
	url varchar(255) null comment '下载地址',
	mix int null comment 'iOS专服（0）、混服（1）、安卓专服（2）',
	constraint bbs_game_download_pk
		primary key (id)
)
comment '游戏下载方式';