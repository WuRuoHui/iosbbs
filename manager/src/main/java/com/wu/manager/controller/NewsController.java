package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.service.NewsService;
import com.wu.manager.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 文章controller
 * @author: Wu
 * @create: 2020-01-08 08:19
 **/

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private ReplyService replyService;

    @RequestMapping("/page/news/{pageName}")
    public String showNewsPage(@PathVariable(name = "pageName") String pageName) {
        return "page/news/"+pageName;
    }

    @RequestMapping(value = "/news",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult  selectAllNews(Integer limit,Integer page) {
        LayUIResult layUIResult = newsService.selectNewsWithPaging(page,limit);
        return layUIResult;
    }

    @RequestMapping(value = "/news/recent",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectRecentNews() {
        LayUIResult layUIResult = newsService.selectRecentNews();
        return layUIResult;
    }

    @RequestMapping(value = "/news/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteNewsById(@PathVariable(name = "id")Integer id) {
        LayUIResult layUIResult = newsService.deleteNewsById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/news",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteNewsByIds(@RequestBody List<Integer> ids) {
        LayUIResult layUIResult = newsService.deleteNewsByIds(ids);
        return layUIResult;
    }

    /**
     * @Description: 更新置顶状态
     * @Param: [id]
     * @return: com.wu.common.utils.LayUIResult
     * @Date: 2020/2/28
     */
    @RequestMapping(value = "/news/sticky/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public LayUIResult updateStickyById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = newsService.updateStickyById(id);
        return layUIResult;
    }

    /**
     * @Description: 更新jie是否加精
     * @Param: [id]
     * @return: com.wu.common.utils.LayUIResult
     * @Date: 2020/2/28
     */
    @RequestMapping(value = "/news/boutique/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public LayUIResult updateBoutiqueById(@PathVariable (name = "id") Integer id) {
        LayUIResult layUIResult = newsService.updateBoutiqueById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/news/closed/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public LayUIResult updateClosedById(@PathVariable (name = "id") Integer id) {
        LayUIResult layUIResult = newsService.updateClosedById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/page/reply/replyList",method = RequestMethod.GET)
    public String showReplyListPage() {
        return "page/reply/replyList";
    }

    @RequestMapping(value = "/replies",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectReplys(Integer page,Integer limit) {
        LayUIResult layUIResult = replyService.selectReplys(page,limit);
        return layUIResult;
    }

    @RequestMapping(value = "/reply/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteReplyById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = replyService.deleteReplyById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/replies",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteReplyByIds(@RequestBody List<Integer> ids) {
        LayUIResult layUIResult = replyService.deleteReplyByIds(ids);
        return layUIResult;
    }
}
