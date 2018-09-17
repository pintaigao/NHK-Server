package com.hptg.nhk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hptg.nhk.dao.NewsListDAO;
import com.hptg.nhk.entity.NewsList;

@Service
public class NewsListServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private NewsListDAO customerDAO;

	@Override
	@Transactional
	public List<NewsList> getNewsList() {
		return customerDAO.getNewsList();
	}

	@Override
	@Transactional
	public void addNews(NewsList newsList) {
		customerDAO.addNews(newsList);
	}

	@Override
	@Transactional
	public NewsList getNews(int theId) {
		return customerDAO.getNews(theId);
	}
}





