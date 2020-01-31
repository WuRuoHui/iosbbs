layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#deptList',
        url : '/depts',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "deptListTable",
        cols : [[
            {type: "checkbox", fixed:"left"},
            {field: '', title: '序号', sort: true, align:"center",templet:function (d) {
                    return (d.LAY_INDEX);
                }},
            {field:'id',width:0},
            {field: 'name', title: '部门名称',  align:'center'},
            {field: 'nickname', title: '简称', align:'center'},
            {title: '操作',  templet:'#deptListBar',fixed:"right",align:"center"}
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

    //添加部门
    function addDept(edit){
        var index = layui.layer.open({
            title : "添加部门",
            type : 2,
            content : "/page/dept/deptAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".id").val(edit.id);
                    body.find(".nickname").val(edit.nickname);  //简称
                    body.find(".name").val(edit.name);  //部门名
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回部门信息列表', '.layui-layer-setwin .layui-layer-close', {
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
    $(".addDept_btn").click(function(){
        $(function () {
            addDept();
        })
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('deptListTable'),
            data = checkStatus.data,
            userIds = [];
        if(data.length > 0) {
            for (var i in data) {
                userIds[i] = data[i].id;
            }
            layer.confirm('确定删除选中的部门信息？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/depts',
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
    table.on('tool(deptList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            addDept(data);
        }else if (layEvent === 'del') {  //删除
            layer.confirm('确定删除此部门信息？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/dept/' + data.id,
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
