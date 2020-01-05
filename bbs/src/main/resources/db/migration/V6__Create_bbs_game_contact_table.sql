create table bbs_game_contact
(
    id int null comment '联系方式ID',
    game_id int null comment '游戏ID',
    dept_id int null comment '部门ID',
    QQ varchar(50) null comment 'QQ',
    phone varchar(50) null comment '电话',
    description varchar(255) null comment '描述'
)
    comment '游戏联系方式表';