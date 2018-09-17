package com.hptg.nhk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hptg.nhk.entity.NewsList;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<NewsList> getNewsList() {
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and gt result list
		Query<NewsList> theQuery = currentSession.createQuery("from NewsList", NewsList.class);

		List<NewsList> newsLists = theQuery.getResultList();
		System.out.println(newsLists);
		// return the results
		return newsLists;
	}

	@Override
	public void addNews(NewsList news) {
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// save/update the customer
		currentSession.saveOrUpdate(news);
	}

	@Override
	public NewsList getNews(int theId) {
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		NewsList news = currentSession.get(NewsList.class,theId);
		return news;
	}

}











