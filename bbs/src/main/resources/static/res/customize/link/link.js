/*
$.ajax({
    url: 'http://127.0.0.1:8081//systemSetting/linkList',
    type: 'GET',
    dataType: 'JSON',
    success: function (res) {
        var data = res.data;
        $(".linkPar").empty();
        for (var i = 0; i < data.length; i++) {
            $(".linkPar").append("<dd><a href='"+data.websiteUrl+"' target='_blank'>"+"data.websiteName"+"</a></dd>");
        }
        // form.render();
    }
})*/
