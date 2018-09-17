package com.hptg.nhk.dao;

import java.util.List;

import com.hptg.nhk.entity.NewsList;

public interface CustomerDAO {

	public List<NewsList> getNewsList();

	public void addNews(NewsList news);

	public NewsList getNews(int theId);
	
}
