layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //新闻列表
    var tableIns = table.render({
        elem: '#newsList',
        url : '/news',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits : [10,15,20,25],
        id : "newsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'title', title: '文章标题', width:250},
            {field: 'newsAuthor', title: '发布者', align:'center',templet:function(d) {
                return d.creator.name;
            }},
            {field: 'columnId', title: '专栏', align:'center', templet:function(d){
                var column = d.columnId;
                if (column == '0') {
                    return '提问';
                } else if (column == '99'){
                    return '分享';
                } else if (column == '100') {
                    return '讨论';
                } else if (column == '101') {
                    return '建议';
                } else if (column == '168') {
                    return '公告';
                } else if (column == '169') {
                    return '动态';
                }
            }},
            {field: 'project', title: '产品', align:'center', minWidth:110, templet:function(d){
                if (d.project != null)
                    return d.project.name;
            }},
            {field: 'isClose', title: '是否已结', align:'center', templet:function(d){
                newsClose = d.isClose ? 'checked' :'';
                return '<input type="checkbox" data-id="'+d.id+'" name="newsClose" lay-filter="newsClose" lay-skin="switch" lay-text="是|否" '+newsClose+'>'
            }},
            {field: 'isBoutique', title: '是否加精', align:'center', templet:function(d){
                newsBoutique = d.isBoutique ? 'checked' :'';
                return '<input type="checkbox" data-id="'+d.id+'" name="newsBoutique" lay-filter="newsBoutique" lay-skin="switch" lay-text="是|否" '+newsBoutique+'>'
            }},
            {field: 'isSticky', title: '是否置顶', align:'center', templet:function(d){
                newsTop = d.isSticky ? 'checked' :'';
                return '<input type="checkbox" data-id="'+d.id+'" name="newsTop" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" '+newsTop+'>'
            }},
            {field: 'gmtCreate', title: '发布时间', align:'center', minWidth:110, templet:function(d){
                return formatDate(new Date(d.gmtCreate));
            }},
            {field: 'viewCount', title: '查看数', align:'center'},
            {field: 'likeCount', title: '点赞数', align:'center'},
            {field: 'commentCount', title: '留言数', align:'center'},
            {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //是否置顶
    form.on('switch(newsTop)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/news/sticky/'+$(data.elem).data('id'),
            type: 'PATCH',
            dataType: 'json',
            success: function (res) {
                updateNewsRenderCheckbox(res,index,data);
            }
        })
        return false;
    })

    //是否加精
    form.on('switch(newsBoutique)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/news/boutique/'+$(data.elem).data('id'),
            type: 'PATCH',
            dataType: 'json',
            success: function (res) {
                updateNewsRenderCheckbox(res,index,data);
            }
        })
        return false;
    })

    //是否已结
    form.on('switch(newsClose)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/news/closed/'+$(data.elem).data('id'),
            type: 'PATCH',
            dataType: 'json',
            success: function (res) {
                updateNewsRenderCheckbox(res,index,data);
            }
        })
        return false;
    })

    function updateNewsRenderCheckbox(res,index,data) {
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg(res.msg);
            if (res.code == '0') {
            } else {
                $(data.elem).prop('checked',!$(data.elem).prop('checked'))
                form.render('checkbox');
            }
        }, 1000);
    }

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("newsListTable",{
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

    //添加文章
    function addNews(edit){
        var index = layui.layer.open({
            title : "添加文章",
            type : 2,
            content : "newsAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".newsName").val(edit.newsName);
                    body.find(".abstract").val(edit.abstract);
                    body.find(".thumbImg").attr("src",edit.newsImg);
                    body.find("#news_content").val(edit.content);
                    body.find(".newsStatus select").val(edit.newsStatus);
                    body.find(".openness input[name='openness'][title='"+edit.newsLook+"']").prop("checked","checked");
                    body.find(".newsTop input[name='newsTop']").prop("checked",edit.newsTop);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    $(".addNews_btn").click(function(){
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].id);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/news',
                    type: 'DELETE',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: JSON.stringify(newsId),
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
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            window.open('http://localhost:8080/jie/'+data.id,'_blank')
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此文章？',{icon:3, title:'提示信息'},function(index){
                $.ajax({
                    url: '/news/' + data.id,
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
        } else if(layEvent === 'look'){ //预览
            window.open('http://localhost:8080/jie/'+data.id,'_blank')
            // layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
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