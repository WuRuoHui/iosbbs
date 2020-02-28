layui.define(['laypage'], function (exports) {

    var laypage = layui.laypage;
    if (jList != 'undefine') {
        //初始化分页
        laypage.render({
            elem: 'paging' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: jList.length //数据总数，从服务端得到
        });
    }

})