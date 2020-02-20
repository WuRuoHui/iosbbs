layui.use(['form', 'layer', 'laydate', 'table', 'upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;

    //温馨通道列表
    var tableIns = table.render({
        elem: '#passagewayList',
        url : '/passageways',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "passagewayListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: '', title: '序号', width:80, sort: true, align:"center",templet:function (d) {
                    return (d.LAY_INDEX);
                }},
            {field:'id',width:0},
            {field: 'logo', title: 'LOGO', width: 180, align: "center", templet: function (d) {
                    return '<a href="' + d.url + '" target="_blank"><img src="' + d.logo + '" height="26" /></a>';
                }},
            {field: 'name', title: '温馨通道名', minWidth:100, align:"center"},
            {field: 'url', title: '温馨通道地址', width: 300, templet: function (d) {
                    return '<a class="layui-blue" href="' + d.url + '" target="_blank">' + d.url + '</a>';
                }},
            {field: 'showAddress', title: '展示位置', align: 'center', templet: function (d) {
                    return d.showAddress == "checked" ? "首页" : "子页";
                }},
            {field: 'gmtCreate', title: '添加时间', align: 'center', minWidth: 110, templet: function (d) {
                    return formatDate(new Date(d.gmtCreate));
            }},
            {title: '操作', width: 130, fixed: "right", align: "center", templet: function () {
                    return '<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
            }}
        ]],done: function () {
            $("[data-field='id']").css('display','none');
        }
    });

    //搜索输入框回车时间，触发搜索按钮点击事件
    $(".searchVal").on('keydown',function (event) {
        if (event.which == 13) {
            $(".search_btn").click();
            return false;
        }
    });
    //搜索按钮单击事件
    $(".search_btn").on("click", function () {
        $.ajax({
            url: '/systemSetting/linkList',
            type: 'GET',
            dataType: 'json',
            data: {
                search:$(".searchVal").val()
            },
            success: function (res) {
                table.reload("linkListTab", {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    },
                    where: {
                        search: $(".searchVal").val()  //搜索的关键字
                    }
                })
            }
        })
       /* if ($(".searchVal").val() != '') {


        } else {
            layer.msg("请输入搜索的内容");
        }*/
    });

    //添加温馨通道
    function addPassageway(edit){
        var gameId;
        var title = "添加";
        var content = "passagewayAdd";
        if (edit) {
            content = "passagewayUpdate";
            title = "更新";
            gameId = edit.game === null?null:edit.game.id;
        }
        var index = layui.layer.open({
            title : title + "温馨通道",
            type : 2,
            area: ["300px", "385px"],
            content : "/page/systemSetting/"+content,
            success : function(layero, index){
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if(edit){
                    body.find(".id").val(edit.id);
                    body.find(".qq").val(edit.qq);  //游戏名
                    body.find(".phone").val(edit.phone);  //游戏名
                    body.find(".description").val(edit.description);  //游戏名
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回温馨通道列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    }

    $(".addPassageway_btn").click(function () {
        addPassageway();
    })
    /*$(".addPassageway_btn").click(function(){
        $(function () {
            addPassageway();
        })
    })*/

    form.on("submit(addPassageway)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/systemSetting/passageway',
            type: 'POST',
            data :{
                logo: $(".linkLogo").attr("src"),  //logo
                name: $(".name").val(),  //网站名称
                url: $(".url").val(),    //网址
                showAddress: $('.showAddress + .layui-form-switch em').text() == "首页" ? "checked" : "",    //展示位置
            },
            dataType: 'json',
            success: function (res) {
                setTimeout(function () {
                    if (res.code == 0) {
                        top.layer.close(index);
                        top.layer.msg("友链添加成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();
                    } else {
                        top.layer.close(index);
                        top.layer.msg(res.msg)
                    }
                }, 500);
            }
        })
        return false;
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('linkListTab'),
            data = checkStatus.data,
            linkIds = [];
        if (data.length > 0) {
            for (var i in data) {
                linkIds[i] = data[i].linkId;
            }
            layer.confirm('确定删除选中的友链？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/systemSetting/linkList',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(linkIds),
                    success: function (res) {
                        top.layer.msg(res.msg)
                        if (res.code == 0) {
                            tableIns.reload();
                        }
                        layer.close(index);
                    }
                })
            })
        } else {
            layer.msg("请选择需要删除的友链");
        }
    })

    //列表操作
    table.on('tool(linkList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addLink(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此友链？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/systemSetting/linkList/' + data.linkId,
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

    //上传logo
    upload.render({
        elem: '.linkLogo',
        url: '../../json/linkLogo.json',
        method: "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        done: function (res, index, upload) {
            var num = parseInt(4 * Math.random());  //生成0-4的随机数，随机显示一个头像信息
            $('.linkLogoImg').attr('src', res.data[num].src);
            $('.linkLogo').css("background", "#fff");
        }
    });

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

})