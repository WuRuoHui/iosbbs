layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#gameContactList',
        url : '/gameContacts',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "gameContactListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: '', title: '序号', width:80, sort: true, align:"center",templet:function (d) {
                    return (d.LAY_INDEX);
                }},
            {field:'id',width:0},
            {field: 'game', title: '游戏名', minWidth:100, align:"center",templet:function (d) {
                    return d.game.name;
            }},
            {field: 'dept', title: '部门', minWidth:100, align:"center",templet:function (d) {
                    return d.game.dept.name;
            }},
            {field: 'qq', title: 'QQ', align:'center'},
            {field: 'phone', title: '电话', align:'center'},
            {field: 'description', title: '描述', align:'center'},
            {title: '操作', minWidth:150, toolbar:'#gameContactListBar',fixed:"right",align:"center"}
        ]],done: function () {
            $("[data-field='id']").css('display','none');
        }
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("gameContactListTable",{
                url : '/gameContacts',
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    nameSearch: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addContactGame(edit){
        var gameId;
        var title = "添加";
        var content = "gameContactAdd";
        if (edit) {
            content = "gameContactUpdate";
            title = "更新";
            gameId = edit.game === null?null:edit.game.id;
        }
        var index = layui.layer.open({
            title : title + "游戏联系方式",
            type : 2,
            content : "/page/gameContact/"+content+"?gameId="+gameId,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".id").val(edit.id);
                    body.find(".qq").val(edit.qq);  //游戏名
                    body.find(".phone").val(edit.phone);  //游戏名
                    body.find(".description").val(edit.description);  //游戏名
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
    $(".addGameContact_btn").click(function(){
        $(function () {
            addContactGame();
        })
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('gameContactListTable'),
            data = checkStatus.data,
            gameContactIds = [];
        if(data.length > 0) {
            for (var i in data) {
                gameContactIds[i] = data[i].id;
            }
            layer.confirm('确定删除选中的游戏？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/gameContacts',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(gameContactIds),
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
            layer.msg("请选择需要删除的游戏信息");
        }
    })

    //列表操作
    table.on('tool(gameContactList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addContactGame(data);
        }else if (layEvent === 'del') {  //删除
            layer.confirm('确定删除此游戏联系方式？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/gameContact/' + data.id,
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
