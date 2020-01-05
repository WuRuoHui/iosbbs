create table bbs_user
(
    id           int auto_increment comment '用户ID'
        primary key,
    username     varchar(50)          null comment '登录用户名',
    password     varchar(100)         null comment '登录密码（加密后）',
    gmt_create   bigint               null comment '创建时间',
    gmt_modified bigint               null comment '修改时间',
    avatar_url   varchar(100)         null comment '头像',
    vip_level    VARCHAR(50)          null comment 'vip等级',
    status       tinyint(1) default 1 null comment '状态',
    name         varchar(50)          null comment '姓名'
);