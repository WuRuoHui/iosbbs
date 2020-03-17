layui.use(['layer'],function() {
    var $ = layui.jquery;
    //ajax请求时都带上csrf信息
    $(function () {
        var token = $("meta[name='_csrf']").attr("content")
        var header = $("meta[name='_csrf_header']").attr("content")
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token)
        })
    })
})