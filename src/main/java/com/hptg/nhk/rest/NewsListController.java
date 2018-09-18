package com.hptg.nhk.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hptg.nhk.entity.NewsList;
import com.hptg.nhk.service.NewsListService;
import com.hptg.nhk.utils.GetNewsDetail;

@RestController
@RequestMapping(value = "/api")
public class NewsListController {

    // autowire the CustomerService
    @Autowired
    private NewsListService customerService;

    // add mapping for GET /customers
    @GetMapping("/news")
    public List<NewsList> getNewsList() {
        return customerService.getNewsList();
    }

    
    @ResponseBody
    @GetMapping(value = "/news/{news_id}")
    public Map<String, String> getNewsDetail(@PathVariable String news_id) {
        String[] newsdetail = GetNewsDetail.getNewsDetail(news_id);
        Map<String, String> map = new HashMap<>();
        map.put("title", newsdetail[0]);
        map.put("article",newsdetail[2]);
        map.put("public_time",newsdetail[1]);
        return map;
    }


}


















