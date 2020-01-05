create table bbs_user_role
(
    user_id int not null comment '用户ID',
    role_id int not null comment '角色ID'
)
    comment '用户-角色关联表';
