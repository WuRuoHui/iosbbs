create table bbs_game
(
    id int auto_increment comment '游戏ID',
    name varchar(50) null comment '游戏名',
    status boolean default 1 null comment '是否上架',
    gmt_create bigint null comment '创建时间',
    gmt_modify bigint null comment '修改时间',
    dept_id int null comment '所属部门ID',
    sort_order int default 1 null comment '排序优先级',
    parent_id int null comment '父包ID',
    is_parent boolean default 1 null comment '是否父包',
    constraint bbs_game_pk
        primary key (id)
)
    comment '游戏表';
