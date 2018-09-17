package com.hptg.nhk.controller;

import com.hptg.nhk.Model.NewsList;
import com.hptg.nhk.service.NewsListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsListController {

    @Autowired
    private NewsListService newsListService;

    @GetMapping("/n")
    public List<NewsList> getNewsList(){
        return newsListService.getNewsList();
    }
}
