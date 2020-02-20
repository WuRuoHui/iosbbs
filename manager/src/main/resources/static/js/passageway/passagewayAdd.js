layui.use(['form', 'layer'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

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
                    layer.close(index);
                    layer.msg(res.msg);
                    if (res.code == 0) {
                        layer.closeAll("iframe");
                        //刷新父页面
                        $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();
                    }
                }, 500);
            }
        })
        return false;
    })

    //格式化时间
    function filterTime(val) {
        if (val < 10) {
            return "0" + val;
        } else {
            return val;
        }
    }

})