layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#menuList',
        url : '/menu/leftNavWithoutKey',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "menuListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: '', title: '序号', width:80, sort: true, align:"center",templet:function (d) {
                    return (d.LAY_INDEX);
                }},
            {field:'id',width:0},
            {field: 'title', title: '标题', minWidth:100, align:"center"},
            {field: 'icon', title: '字体图标', minWidth:100, align:'center'},
            {field: 'href', title: '链接地址', align:'center'},
            {field: 'menuLevel', title: '菜单等级',  align:'center'},
            {field: 'isParent', title: '是否父级菜单', align:'center',templet:function (d) {
                return d.isParent?'是':'否';
                }},
            {field: 'parent', title: '父级菜单名称', align:'center',templet:function (d) {
                return d.parent.name;
                }},
            {title: '操作', minWidth:175, templet:'#menuListBar',fixed:"right",align:"center"}
        ]],done: function () {
            $("[data-field='id']").css('display','none');
        }
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("menuListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    search: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加菜单
    function addMenu(edit){
        var selectId;
        if (edit) selectId = edit.parent.id
        var index = layui.layer.open({
            title : "添加菜单",
            type : 2,
            content : "menuAdd?selectId="+ selectId,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".id").val(edit.id);
                    body.find(".title").val(edit.title);  //菜单名
                    body.find(".icon").val(edit.icon);  //字体图标
                    body.find(".href").val(edit.href);  //url
                    body.find(".menuLevel option[value="+edit.menuLevel+"]").prop("selected",true);  //菜单等级
                    body.find(".isParent input[value="+(edit.isParent?1:0)+"]").prop("checked","checked");  //是否父菜单
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
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
    $(".addMenu_btn").click(function(){
        $(function () {
            addMenu();
        })
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('menuListTable'),
            data = checkStatus.data,
            menuIds = [];
        if(data.length > 0) {
            for (var i in data) {
                menuIds.push(data[i].id);
            }
            layer.confirm('确定删除选中的菜单项？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/menus',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(menuIds),
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
    table.on('tool(menuList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addMenu(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此菜单项？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此菜单项？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                $.ajax({
                    url: '/menu/leftNav/status/' + data.id,
                    type: 'PUT',
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (res) {
                        layer.close(index);
                        top.layer.msg(res.msg)
                        if (res.code == 0) {
                            _this.text(btnText);
                        }
                    }
                })
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此菜单项？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
                    url: '/menu/' + data.id,
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
