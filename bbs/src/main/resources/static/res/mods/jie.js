/**

 @Name: 求解板块

 */

layui.define('fly', function (exports) {

    var $ = layui.jquery;
    var layer = layui.layer;
    var util = layui.util;
    var laytpl = layui.laytpl;
    var form = layui.form;
    var fly = layui.fly;

    var gather = {}, dom = {
        jieda: $('#jieda')
        , content: $('#L_content')
        , jiedaCount: $('#jiedaCount')
    };

    var projectId = UrlParm.parm("callback");

    //动态加载游戏主包
    $.ajax({
        url: 'http://127.0.0.1:8081/mainGames',
        type: 'GET',
        dataType: 'JSON',
        success: function (res) {
            var data = res.data;
            $(".projectId").empty();
            for (var i = 0; i < data.length; i++) {
                $(".projectId").append("<option></option>")
                $(".projectId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                if (projectId == data[i].id) {
                    $(".projectId option[value=" + projectId + "]").prop("selected", true);    //用户状态
                }
            }
            form.render();
        }
    })

    //监听专栏选择
    form.on('select(column)', function (obj) {
        var value = obj.value
            // ,elemQuiz = $('#LAY_quiz')
            , tips = {
                tips: 1
                , maxWidth: 250
                , time: 10000
            };
        // elemQuiz.addClass('layui-hide');
        if (value === '0') {
            layer.tips('下面的信息将便于您获得更好的答案', obj.othis, tips);
            elemQuiz.removeClass('layui-hide');
        } else if (value === '99') {
            layer.tips('系统会对【分享】类型的帖子予以飞吻奖励，但我们需要审核，通过后方可展示', obj.othis, tips);
        }
    });

    //提交回答
    fly.form['/jie/reply/'] = function (data, required) {
        var tpl = '<li>\
      <div class="detail-about detail-about-reply">\
        <a class="fly-avatar" href="/u/{{ layui.cache.user.uid }}" target="_blank">\
          <img src="{{= d.user.avatar}}" alt="{{= d.user.username}}">\
        </a>\
        <div class="fly-detail-user">\
          <a href="/u/{{ layui.cache.user.uid }}" target="_blank" class="fly-link">\
            <cite>{{d.user.username}}</cite>\
          </a>\
        </div>\
        <div class="detail-hits">\
          <span>刚刚</span>\
        </div>\
      </div>\
      <div class="detail-body jieda-body photos">\
        {{ d.content}}\
      </div>\
    </li>'
        data.content = fly.content(data.content);
        laytpl(tpl).render($.extend(data, {
            user: layui.cache.user
        }), function (html) {
            required[0].value = '';
            dom.jieda.find('.fly-none').remove();
            dom.jieda.append(html);

            var count = dom.jiedaCount.text() | 0;
            dom.jiedaCount.html(++count);
        });
    };

    //提交求解
    form.on("submit(addJie)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/jie',
            type: 'POST',
            data: $(".addJie").serialize(),
            dataType: 'json',
            success: function (res) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg(res.msg);
                    setTimeout(function () {
                        if (res.code == '0') {
                            //刷新父页面
                          window.location.href='/user/index'
                        }
                    }, 2000)
                }, 2000);
            }
        })
        return false;
    })

    //更新求解
    form.on("submit(updateJie)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.ajax({
            url: '/jie',
            type: 'PUT',
            data: $(".updateJie").serialize(),
            dataType: 'json',
            success: function (res) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg(res.msg);
                    setTimeout(function () {
                        if (res.code == '0') {
                            //刷新父页面
                            window.location.href='/jie/'+$('.id').val();
                        }
                    }, 2000)
                }, 2000);
            }
        })
        return false;
    })

    //ajax请求时都带上csrf信息
    $(function () {
        var token = $("meta[name='_csrf']").attr("content")
        var header = $("meta[name='_csrf_header']").attr("content")
        $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header,token)
        })
    })

    // var token = $("meta[name='_csrf']").attr("content")
    // var header = $("meta[name='_csrf_header']").attr("content")

    // console.log(window.location.pathname)

    //求解管理
    gather.jieAdmin = {
        //删求解
        del: function (div) {
            layer.confirm('确认删除该求解么？', function (index) {
                layer.close(index);
                $.ajax({
                    url: '/jie/'+div.data('id'),
                    type: 'DELETE',
                    // headers:headers,
                    dataType: 'json',
                    /*beforeSend: function(request) {
                        request.setRequestHeader(header,token);
                    },*/
                    success: function (res) {
                        layer.msg(res.msg);
                        setTimeout(function () {
                            setTimeout(function () {
                                if (res.code == '0') {
                                    //刷新父页面
                                    location.href='/'
                                }
                            }, 2000)
                        }, 2000);
                    }
                })
            });
        }

        //设置置顶、状态
        , set: function (div) {
            var othis = $(this);
            var field = othis.attr('field');
            var rank = othis.attr('rank');
            var confirmMsg;
            var url;
            if ( field === 'stick') {
                rank === '0' ? confirmMsg ='取消置顶' : confirmMsg = '置顶';
                url = 'stick';
            } else if (field === 'boutique') {
                rank === '0' ? confirmMsg ='取消加精' : confirmMsg = '加精';
                url = 'boutique';
            }
            layer.confirm('确认'+confirmMsg+'该求解么？', function (index) {
                layer.close(index);
                $.ajax({
                    url: '/jie/'+field+'/'+div.data('id'),
                    type: 'PUT',
                    data: {
                        rank : othis.attr('rank')
                    },
                    dataType: 'json',
                    success: function (res) {
                        layer.msg(res.msg);
                        setTimeout(function () {
                            setTimeout(function () {
                                if (res.code == '0') {
                                    //刷新父页面
                                    location.reload();
                                    // window.location.href='/jie/'+div.data('id');
                                    // location.href='/'
                                }
                            }, 1000)
                        }, 1000);
                    }
                })
            });
        }

        //收藏
        , collect: function (div) {
            var othis = $(this), type = othis.data('type');
            fly.json('/collection/' + type + '/', {
                cid: div.data('id')
            }, function (res) {
                if (type === 'add') {
                    othis.data('type', 'remove').html('取消收藏').addClass('layui-btn-danger');
                } else if (type === 'remove') {
                    othis.data('type', 'add').html('收藏').removeClass('layui-btn-danger');
                }
            });
        }
    };

    $('body').on('click', '.jie-admin', function () {
        var othis = $(this), type = othis.attr('type');
        gather.jieAdmin[type] && gather.jieAdmin[type].call(this, othis.parent());
    });

    //异步渲染
    var asyncRender = function () {
        var div = $('.fly-admin-box'), jieAdmin = $('#LAY_jieAdmin');
        //查询帖子是否收藏
        if (jieAdmin[0] && layui.cache.user.uid != -1) {
            fly.json('/collection/find/', {
                cid: div.data('id')
            }, function (res) {
                jieAdmin.append('<span class="layui-btn layui-btn-xs jie-admin ' + (res.data.collection ? 'layui-btn-danger' : '') + '" type="collect" data-type="' + (res.data.collection ? 'remove' : 'add') + '">' + (res.data.collection ? '取消收藏' : '收藏') + '</span>');
            });
        }
    }();

    //解答操作
    gather.jiedaActive = {
        zan: function (li) { //赞
            var othis = $(this), ok = othis.hasClass('zanok');
            fly.json('/api/jieda-zan/', {
                ok: ok
                , id: li.data('id')
            }, function (res) {
                if (res.status === 0) {
                    var zans = othis.find('em').html() | 0;
                    othis[ok ? 'removeClass' : 'addClass']('zanok');
                    othis.find('em').html(ok ? (--zans) : (++zans));
                } else {
                    layer.msg(res.msg);
                }
            });
        }
        , reply: function (li) { //回复
            var val = dom.content.val();
            var aite = '@' + li.find('.fly-detail-user cite').text().replace(/\s/g, '');
            dom.content.focus()
            if (val.indexOf(aite) !== -1) return;
            dom.content.val(aite + ' ' + val);
        }
        , accept: function (li) { //采纳
            var othis = $(this);
            layer.confirm('是否采纳该回答为最佳答案？', function (index) {
                layer.close(index);
                fly.json('/api/jieda-accept/', {
                    id: li.data('id')
                }, function (res) {
                    if (res.status === 0) {
                        $('.jieda-accept').remove();
                        li.addClass('jieda-daan');
                        li.find('.detail-about').append('<i class="iconfont icon-caina" title="最佳答案"></i>');
                    } else {
                        layer.msg(res.msg);
                    }
                });
            });
        }
        , edit: function (li) { //编辑
            fly.json('/jie/getDa/', {
                id: li.data('id')
            }, function (res) {
                var data = res.rows;
                layer.prompt({
                    formType: 2
                    , value: data.content
                    , maxlength: 100000
                    , title: '编辑回帖'
                    , area: ['728px', '300px']
                    , success: function (layero) {
                        fly.layEditor({
                            elem: layero.find('textarea')
                        });
                    }
                }, function (value, index) {
                    fly.json('/jie/updateDa/', {
                        id: li.data('id')
                        , content: value
                    }, function (res) {
                        layer.close(index);
                        li.find('.detail-body').html(fly.content(value));
                    });
                });
            });
        }
        , del: function (li) { //删除
            layer.confirm('确认删除该回答么？', function (index) {
                layer.close(index);
                fly.json('/api/jieda-delete/', {
                    id: li.data('id')
                }, function (res) {
                    if (res.status === 0) {
                        var count = dom.jiedaCount.text() | 0;
                        dom.jiedaCount.html(--count);
                        li.remove();
                        //如果删除了最佳答案
                        if (li.hasClass('jieda-daan')) {
                            $('.jie-status').removeClass('jie-status-ok').text('求解中');
                        }
                    } else {
                        layer.msg(res.msg);
                    }
                });
            });
        }
    };

    $('.jieda-reply span').on('click', function () {
        var othis = $(this), type = othis.attr('type');
        gather.jiedaActive[type].call(this, othis.parents('li'));
    });


    //定位分页
    /*if (/\/page\//.test(location.href) && !location.hash) {
        var replyTop = $('#flyReply').offset().top - 80;
        $('html,body').scrollTop(replyTop);
    }*/

    exports('jie', null);
});