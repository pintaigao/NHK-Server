package com.hptg.nhk.service;

import com.hptg.nhk.Model.NewsList;

import java.util.List;

public interface NewsListService {
    public List<NewsList> getNewsList();

    public void addNews(NewsList newsList);

    public NewsList getNews(int theId);

}
