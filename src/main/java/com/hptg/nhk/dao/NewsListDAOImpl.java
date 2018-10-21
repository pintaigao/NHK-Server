package com.hptg.nhk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hptg.nhk.entity.NewsList;

@Repository
public class NewsListDAOImpl implements NewsListDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<NewsList> getNewsList() {
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and gt result list
		Query<NewsList> theQuery = currentSession.createQuery("from NewsList order by news_prearranged_time desc ", NewsList.class);

		List<NewsList> newsLists = theQuery.getResultList();
		// return the results
		return newsLists;
	}

	@Override
	public List<NewsList> getNewsListWithSpecificDay(String today,String yesterday){
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// execute query and gt result list
		String query = "from NewsList where news_prearranged_time = :date1 or news_prearranged_time=:date2";
		System.out.println(today);
		System.out.println(yesterday);
		Query<NewsList> theQuery = currentSession.createQuery(query, NewsList.class).setParameter("date1",today).setParameter("date2",yesterday);

		List<NewsList> SpecificNewsLists = theQuery.getResultList();
		// return the results
		return SpecificNewsLists;
	}

	@Override
	public void updateNews(List<NewsList> newsLists) {
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		for(NewsList news : newsLists){
			// save/update the news
			currentSession.saveOrUpdate(news);
		}
	}

	@Override
	public NewsList getNews(int theId) {
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		NewsList news = currentSession.get(NewsList.class,theId);
		return news;
	}

}











