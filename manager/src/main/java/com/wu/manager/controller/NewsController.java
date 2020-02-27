package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.service.NewsService;
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

    @RequestMapping("/page/news/{pageName}")
    public String showNewsPage(@PathVariable(name = "pageName") String pageName) {
        return "page/news/"+pageName;
    }

    @RequestMapping(value = "/news",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult  selectAllNews() {
        LayUIResult layUIResult = newsService.selectAllNews();
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
}
