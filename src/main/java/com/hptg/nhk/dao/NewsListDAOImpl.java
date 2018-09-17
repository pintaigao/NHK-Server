package com.hptg.nhk.dao;

import com.hptg.nhk.Model.NewsList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsListDAOImpl implements NewsListDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<NewsList> getNewsList() {

        // get the hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query ...
        Query<NewsList> theQuery = currentSession.createQuery("from news_list order by public_date", NewsList.class);

        // execute query and gt result list
        List<NewsList> newsLists = theQuery.getResultList();

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
