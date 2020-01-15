create table bbs_user_grade
(
	id int auto_increment comment '主键ID',
	grade_icon varchar(50) null comment '等级图标',
	grade_name varchar(50) null comment '等级名称',
	grade_point int null comment '默认积分',
	grade_gold int null comment '默认金币',
	grade_value int null comment '等级值',
	constraint bbs_user_grade_pk
		primary key (id)
)
comment 'VIP等级表';