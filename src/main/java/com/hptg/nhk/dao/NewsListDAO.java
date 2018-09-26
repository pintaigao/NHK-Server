package com.hptg.nhk.dao;

import java.util.List;

import com.hptg.nhk.entity.NewsList;

public interface NewsListDAO {

	public List<NewsList> getNewsList();

	public List<NewsList> getNewsListWithSpecificDay(String today,String yesterday);

	public void updateNews(List<NewsList> newsLists);

	public NewsList getNews(int theId);
	
}
