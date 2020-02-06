layui.use(['form', 'layer'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addGameDownload)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/gameDownload',
            type: 'POST',
            data : $(".addGameDownload").serialize(),
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

    //从url中获取deptId参数
    var deptId = UrlParm.parm("deptId");
    //动态加载游戏下拉框
    $.ajax({
        url: '/games',
        type: 'GET',
        dataType: 'json',
        success: function (res) {
            var data = res.data;
            $(".gameId").empty();
            for (var i = 0; i < data.length; i++) {
                $(".gameId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                if (deptId == data[i].id) {
                    $(".deptId option[value=" + deptId + "]").prop("selected", true);    //用户状态
                }
            }
            form.render();
        }
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