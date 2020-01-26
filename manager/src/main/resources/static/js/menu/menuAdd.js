layui.use(['form', 'layer'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addMenu)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/menu',
            type: 'POST',
            data: $(".addMenu").serialize(),
            dataType: 'json',
            success: function (res) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg(res.msg);
                    if (res.code == '0') {
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    }
                }, 2000);
            }
        })
        return false;
    })

    var selectId = UrlParm.parm("selectId");

    function appendTopMenu() {
        //动态添加顶部菜单
        $.ajax({
            url: '/menu/topMenuObject',
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                var data = res.data;
                $(".parent").empty();
                for (var i = 0; i < data.length; i++) {
                    $(".parent").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                    if (selectId == data[i].id) {
                        $(".parent option[value=" + selectId + "]").prop("selected", true);    //用户状态
                    }
                }
                form.render();
            }
        })
    }

    $(function () {
        appendTopMenu();
    })

    /*form.on('radio(isParent)', function (data) {
        if (data.value==0) alert(1)
        console.log(data.value); //被点击的radio的value值
    })*/
})