layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
        $.ajax({
            type: "POST",
            url: "/login",
            data: $('#loginForm').serialize(),
            dataType: "json",
            success: function (loginCallback) {
                if (loginCallback.code == '200') {
                    layer.msg("登录成功", {
                        icon: 6,
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {    //弹框后的操作
                        window.location.href = "/";
                    });
                } else {
                    layer.msg("用户名或密码错误，请重新输入", {
                        icon: 2,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }
            },
            error: function (jqXHR) {
                layer.alert("发生错误：" + jqXHR.status, {
                    title: 'Error'
                });
            }
        });
        return false;
    });



    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
