layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#gameList',
        url : '/game',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "gameListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: '', title: '序号', width:80, sort: true, align:"center",templet:function (d) {
                    return (d.LAY_INDEX);
                }},
            {field:'id',width:0},
            {field: 'username', title: '用户名', minWidth:100, align:"center"},
            {field: 'name', title: '姓名', minWidth:100, align:'center'},
            {field: 'userSex', title: '用户性别', align:'center',templet:function (d) {
                    return d.sex == "1"?"男": "女";
                }},
            {field: 'userStatus', title: '用户状态',  align:'center',templet:function(d){
                return d.status == "0" ? "正常使用" : "限制使用";
            }},
            {field: 'role', title: '用户类型', align:'center',templet:function(d) {
                return d.role.description;
            }},
            {field: 'userGrade', title: 'VIP等级', align:'center',templet:function(d){
                return d.userGrade.gradeName;
            }},
            {field: 'gmtCreate', title: '创建时间', align:'center',minWidth:150,templet:function(d) {
                return formatDate(new Date(d.gmtCreate));
            }},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]],done: function () {
            $("[data-field='id']").css('display','none');
        }
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("newsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addGame(edit){
        var roleId;
        var vipLevelId;
        if (edit) {
            vipLevelId = edit.userGrade.id;
            roleId = edit.role.id;
        }
        var index = layui.layer.open({
            title : "添加游戏",
            type : 2,
            content : "/page/game/gameAdd?roleId="+roleId+"&vipLevelId="+vipLevelId,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".id").val(edit.id);
                    body.find(".username").val(edit.username);  //登录名
                    body.find(".name").val(edit.name);  //名字
                    body.find(".sex input[value="+edit.sex+"]").prop("checked","checked");  //性别
                    body.find(".status option[value="+(edit.status?1:0)+"]").prop("selected",true);    //用户状态
                    // body.find(".role option[value=2]").prop("selected",true);  //用户类型
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addGame_btn").click(function(){
        $(function () {
            addGame();
        })
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('gameListTable'),
            data = checkStatus.data,
            userIds = [];
        if(data.length > 0) {
            for (var i in data) {
                userIds[i] = data[i].id;
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/user',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(userIds),
                    success: function (res) {
                        top.layer.msg(res.msg)
                        if (res.code == 0) {
                            tableIns.reload();
                        }
                        layer.close(index);
                    }
                })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if (layEvent === 'del') {  //删除
            layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/user/' + data.id,
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (res) {
                        top.layer.msg(res.msg)
                        if (res.code == 0) {
                            tableIns.reload();
                        }
                        layer.close(index);
                    }
                })
            });
        }
    });

})

//格式化时间
function filterTime(val) {
    if (val < 10) {
        return "0" + val;
    } else {
        return val;
    }
}

//日期格式化
function formatDate(time) {
    var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1) + '-' + filterTime(time.getDate()) + ' ' + filterTime(time.getHours()) + ':' + filterTime(time.getMinutes()) + ':' + filterTime(time.getSeconds());
    return submitTime;
}
