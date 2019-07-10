package com.hptg.nhk.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hptg.nhk.entity.NewsList;
import com.hptg.nhk.service.NewsListService;
import com.hptg.nhk.utils.GetDate;
import com.hptg.nhk.utils.GetNewsDetail;
import com.hptg.nhk.utils.GetNewsList;

@RestController
@RequestMapping(value = "/api")
public class NewsListController {


    // autowire the CustomerService
    @Autowired
    private NewsListService newsListService;

    // add mapping for GET /customers
    @GetMapping("/news")
    public List<NewsList> getNewsList() {
        return newsListService.getNewsList();
    }


    /* Get the news detail */
    @ResponseBody
    @GetMapping(value = "/news/{news_id}")
    public Map<String, List<Map<String, String>>> getNewsDetail(@PathVariable String news_id) {
        GetNewsDetail g = new GetNewsDetail();
        g.getNewsDetail(news_id);
        List<Map<String, String>> title = g.getTitle();
        List<Map<String, String>> date = g.getPublicDate();
        List<Map<String, String>> artilelist = g.getArticlelist();
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        map.put("title", title);
        map.put("public_time",date);
        map.put("article",artilelist);
        return map;
    }

    /* update all the news *//*
    @GetMapping(value = "/update_all_news")
    public void updateAllNews(){
        List<NewsList> newsLists = GetNewsList.getNewsList();
        newsListService.updateNews(newsLists);
    }*/

    @GetMapping(value = "/daily_update_news")
    public boolean dailyUpdateNews(){
        /* Global Property of Date */
        String today = GetDate.getToday();
        /* Global Property of Yesterday */
        String yesterday = GetDate.getYesterday();
        List<NewsList> existingList = newsListService.getNewsListWithSpecificDay(today,yesterday);
        List<NewsList> newsLists = GetNewsList.updateNewsList(today,yesterday,existingList);
        System.out.println(newsLists.get(1).getTitle());
        newsListService.updateNews(newsLists);
        return true;
    }
}


















