package com.hptg.nhk.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hptg.nhk.entity.NewsList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.hptg.nhk.utils.UrlHandler.getURLContent;

public class GetNewsList {


    public static List<NewsList> getNewsList(){
        String strJson = getURLContent("https://www3.nhk.or.jp/news/easy/news-list.json");

        strJson = strJson.trim().substring(2, strJson.length());

        /*Store all the news into map with (date:this day's all news)*/

        Map<String, JSONArray> dailyNews = new HashMap();

        /*Date Key Set*/

        String[] newsDateArr;

        /* News List List<NewsList>*/
        List<NewsList> newsLists = new ArrayList<>();


//        Configure the Database

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(NewsList.class).buildSessionFactory();

//        Create session

        Session session = factory.getCurrentSession();

        try {
            JSONObject jsonObject = new JSONObject(strJson);

            newsDateArr = jsonObject.keySet().toArray(new String[jsonObject.length()]);
            session.beginTransaction();
            for (String date : newsDateArr) {
                JSONArray jsonArr = (JSONArray) jsonObject.get(date);
                for (Object oj : jsonArr) {
                    ObjectMapper mapper = new ObjectMapper();
                    NewsList news = mapper.readValue(oj.toString(), NewsList.class);
                    byte[] newImg = GetNewsImage.getImageFromNetByUrl(news.getNews_web_image_uri());
                    news.setNews_img(newImg);
                    newsLists.add(news);
                    session.save(news);
                }
            }
            session.getTransaction().commit();
            session.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            factory.close();
        }
        return newsLists;
    }

    public static void main(String[] args) {
        GetNewsList.getNewsList();
    }
}
