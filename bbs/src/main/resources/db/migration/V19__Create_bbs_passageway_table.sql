create table bbs_passageway
(
	id int auto_increment comment '温馨通道主键id',
	logo varchar(150) null comment '温馨通道logo',
	name varchar(100) null comment '温馨通道名',
	url varchar(100) null comment '温馨通道url',
	gmt_create bigint null comment '创建时间',
	gmt_modify bigint null comment '修改时间',
    show_address varchar(10) null comment '是否主页显示',
	constraint bbs_passageway_pk
		primary key (id)
)
comment '温馨通道';