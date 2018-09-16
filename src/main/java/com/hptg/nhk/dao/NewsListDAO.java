package com.hptg.nhk.dao;

import com.hptg.nhk.Model.NewsList;

import java.util.List;

public interface NewsListDAO {
    public List<NewsList> getNewsList();

    public void addNews(NewsList news);

    public NewsList getNews(int theId);
}
