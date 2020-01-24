layui.define(['layer', 'laytpl', 'form', 'element', 'upload', 'util'], function (exports) {
    var $ = layui.jquery;
    $(".fly-column li").click(function () {
        $('.layui-this').removeClass('layui-this');
        $(this).addClass('layui-this');
    })
})