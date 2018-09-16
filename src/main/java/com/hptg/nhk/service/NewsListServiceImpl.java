package com.hptg.nhk.service;

import com.hptg.nhk.Model.NewsList;
import com.hptg.nhk.dao.NewsListDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class NewsListServiceImpl implements NewsListService{

    @Autowired
    private NewsListDAO newsListDAO;

    @Override
    @Transactional
    public List<NewsList> getNewsList() {
        return newsListDAO.getNewsList();
    }

    @Override
    @Transactional
    public void addNews(NewsList newsList) {
        newsListDAO.addNews(newsList);
    }

    @Override
    @Transactional
    public NewsList getNews(int theId) {
        return newsListDAO.getNews(theId);
    }
}
