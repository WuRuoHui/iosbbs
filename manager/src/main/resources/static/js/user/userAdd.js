layui.use(['form', 'layer'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/user',
            type: 'POST',
            data : $(".addUser").serialize(),
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

    var vipLevelId = UrlParm.parm("vipLevelId");

    //动态添加VIP等级下拉框
    $.ajax({
        url: '/user/userGrade',
        type: 'GET',
        dataType: 'json',
        success: function (res) {
            var data = res.data;
            $(".vipLevel").empty();
            for (var i = 0; i < data.length; i++) {
                $(".vipLevel").append("<option value='" + data[i].id + "'>" + data[i].gradeName + "</option>");
                if (vipLevelId == data[i].id) {
                    $(".vipLevel option[value=" + vipLevelId + "]").prop("selected", true);    //用户状态
                }
            }
            form.render();
        }
    })

    var roleId = UrlParm.parm("roleId")
    //动态添加用户角色下拉框
    $(function () {
        $.ajax({
            url: '/role',
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                var data = res.data;
                $(".role").empty();
                for (var i = 0; i < data.length; i++) {
                    $(".role").append("<option value='" + data[i].id + "'>" + data[i].description + "</option>");
                    if (roleId == data[i].id) {
                        $(".role option[value=" + roleId + "]").prop("selected", true);    //用户状态
                    }
                }
                form.render();
            }
        })
    })

    //格式化时间
    function filterTime(val) {
        if (val < 10) {
            return "0" + val;
        } else {
            return val;
        }
    }


    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1) + '-' + filterTime(time.getDate()) + ' ' + filterTime(time.getHours()) + ':' + filterTime(time.getMinutes()) + ':' + filterTime(time.getSeconds());

})