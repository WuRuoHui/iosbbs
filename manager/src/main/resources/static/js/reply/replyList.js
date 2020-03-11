layui.use(['form', 'layer', 'laydate', 'table', 'upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;

    //友链列表
    var tableIns = table.render({
        elem: '#replyList',
        url: '/replies',
        page: true,
        cellMinWidth: 95,
        height: "full-104",
        limit: 20,
        limits: [10, 15, 20, 25],
        id: "replyListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'id', width: 0, type: 'space', style: 'display:none'},
            {field: '', title: '序号', width:80, sort: true, align:"center",templet:function (d) {
                    return (d.LAY_INDEX);
                }},
            {field: 'content', title: '内容', width: 180, align: "center"},
            {field: 'parentId', title: '回复',templet:function (d) {
                    return d.parentId.title;
                }},
            {field: 'creator', title: '创建者',templet:function (d) {
                    return d.creator.name;
            }},
            {field: 'gmtCreate', title: '添加时间', align: 'center', minWidth: 110, templet: function (d) {
                    return formatDate(new Date(d.gmtCreate));
            }},
            {field: 'likeCount', title: '点赞数', align: 'center', minWidth: 110},
            {field: 'isAccept', title: '是否已结', align: 'center', minWidth: 110},
            {title: '操作', width: 130, fixed: "right", align: "center", templet: function () {
                    return '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
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
            url: '/page/replyList',
            type: 'GET',
            dataType: 'json',
            data: {
                search:$(".searchVal").val()
            },
            success: function (res) {
                table.reload("replyListTable", {
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

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('replyListTable'),
            data = checkStatus.data,
            ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids[i] = data[i].id;
            }
            layer.confirm('确定删除选中的温馨通道？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/systemSetting/passageways',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(ids),
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
            layer.msg("请选择需要删除的温馨通道");
        }
    })

    //列表操作
    table.on('tool(replyList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'del') { //删除
            layer.confirm('确定删除此回复？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/reply/' + data.id,
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