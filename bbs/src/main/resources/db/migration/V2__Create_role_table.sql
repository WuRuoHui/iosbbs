create table bbs_role
(
    id          int auto_increment comment '角色ID'
        primary key,
    name        varchar(50) null comment '角色名',
    description varchar(50) null comment '角色描述'
);