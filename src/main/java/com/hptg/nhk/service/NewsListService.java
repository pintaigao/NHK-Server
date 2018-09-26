package com.hptg.nhk.service;

import java.util.List;

import com.hptg.nhk.entity.NewsList;

public interface NewsListService {

	public List<NewsList> getNewsList();

	public List<NewsList> getNewsListWithSpecificDay(String today,String yesterday);

	public void updateNews(List<NewsList> newsList);

	public NewsList getNews(int theId);
	
}
