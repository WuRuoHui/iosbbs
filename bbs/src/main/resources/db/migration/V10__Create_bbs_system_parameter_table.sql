create table bbs_system_parameter
(
	id int not null comment '主键ID',
	cms_name varchar(20) null comment '网站名称',
	version varchar(20) null comment '当前版本',
	author varchar(10) null comment '作者',
	home_page varchar(20) null comment '网站首页',
	server varchar(50) null comment '服务器环境',
	data_base varchar(20) null comment '数据库版本',
	max_upload varchar(10) null comment '最大上传限制',
	user_rights varchar(20) null comment '用户权限',
	keywords varchar(50) null comment '默认关键字',
	powerby varchar(50) null comment '版权信息',
	description varchar(100) null comment '描述',
	record varchar(50) null comment '备案号',
	constraint bbs_system_parameter_pk
		primary key (id)
)
comment '系统参数';