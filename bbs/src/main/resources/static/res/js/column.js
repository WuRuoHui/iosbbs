layui.define(['laypage'], function (exports) {

    var laypage = layui.laypage;
    if (jCount != 'undefine') {
        var url = window.location.pathname;
        var curr = 1;
        if (url.indexOf('page')) {
            curr = url.substring(url.indexOf('page') + 5);
        }
        //初始化分页
        laypage.render({
            elem: 'paging' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: jCount //数据总数，从服务端得到
            ,curr :curr
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                // console.log(obj.limit); //得到每页显示的条数

                //首次不执行
                if(!first){
                    var winUrl = window.location.href;
                    if (window.location.pathname.indexOf('page') != -1){
                        if (obj.curr == '1') {
                            window.location.href = winUrl.substring(0,winUrl.indexOf('page')-1)
                        } else {
                            window.location.href = winUrl.substring(0,winUrl.indexOf('page')+4)
                        }
                    }else {
                        console.log('page')
                        window.location.href = window.location.pathname+'/page/'+obj.curr
                    }
                    laypage.render({
                        elem: 'paging' //注意，这里的 test1 是 ID，不用加 # 号
                        ,count: jCount //数据总数，从服务端得到
                        ,curr:obj.curr
                    })
                }
            }
        });
    }

})