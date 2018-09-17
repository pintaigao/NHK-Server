package com.hptg.nhk.service;

import java.util.List;

import com.hptg.nhk.entity.NewsList;

public interface CustomerService {

	public List<NewsList> getNewsList();

	public void addNews(NewsList newsList);

	public NewsList getNews(int theId);
	
}
