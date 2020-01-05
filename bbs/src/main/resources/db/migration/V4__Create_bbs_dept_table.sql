create table bbs_dept
(
    id int auto_increment comment '部门ID',
    name varchar(50) null comment '部门名称',
    nickname varchar(50) null comment '部门名称昵称',
    constraint dept_pk
        primary key (id)
)
    comment '部门表';