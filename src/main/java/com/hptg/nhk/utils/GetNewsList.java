package com.hptg.nhk.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hptg.nhk.entity.NewsList;
import com.hptg.nhk.service.NewsListService;

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


    public static List<NewsList> getNewsList() {
        String strJson = getURLContent("https://www3.nhk.or.jp/news/easy/news-list.json");

        strJson = strJson.trim().substring(2, strJson.length());

        /*Store all the news into map with (date:this day's all news)*/

        Map<String, JSONArray> dailyNews = new HashMap();

        /*Date Key Set*/

        String[] newsDateArr;

        /* News List List<NewsList>*/
        List<NewsList> newsLists = new ArrayList<>();


/*//        Configure the Database
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(NewsList.class).buildSessionFactory();

//        Create session
        Session session = factory.getCurrentSession();*/

        try {
            JSONObject jsonObject = new JSONObject(strJson);

            newsDateArr = jsonObject.keySet().toArray(new String[jsonObject.length()]);
//            session.beginTransaction();
            for (String date : newsDateArr) {
                JSONArray jsonArr = (JSONArray) jsonObject.get(date);
                for (Object oj : jsonArr) {
                    ObjectMapper mapper = new ObjectMapper();
                    NewsList news = mapper.readValue(oj.toString(), NewsList.class);
                    /* Get News Image */
                    byte[] newImg = GetNewsImage.getImageFromNetByUrl(news.getNews_web_image_uri());
                    news.setNews_img(newImg);
                    newsLists.add(news);
//                    session.save(news);
                }
            }
//            session.getTransaction().commit();
//            session.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return newsLists;
    }


    public static List<NewsList> updateNewsList(String today, String yestoday, List<NewsList> existingList) {

        String strJson = getURLContent("https://www3.nhk.or.jp/news/easy/news-list.json");

        strJson = strJson.trim().substring(2, strJson.length());

        /* News List List<NewsList>*/
        List<NewsList> newsLists = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(strJson);
            JSONArray jsonArrToday = jsonObject.getJSONArray(today);
            System.out.println(jsonArrToday);
            for (Object oj : jsonArrToday) {
                boolean flag = true;
                ObjectMapper mapper = new ObjectMapper();
                NewsList news = mapper.readValue(oj.toString(), NewsList.class);
                String newsId = news.getNews_id();
                for (int i = 0; i < existingList.size(); i++) {
                    String existingNewsId = existingList.get(i).getNews_id();
                    if (newsId.equals(existingNewsId)) {
                        existingList.remove(i);
                        flag = false;
                    }
                }
                if (flag) {
                    byte[] newImg = GetNewsImage.getImageFromNetByUrl(news.getNews_web_image_uri());
                    news.setNews_img(newImg);
                    newsLists.add(news);
                }
            }

        } catch (JSONException | IOException e) {
            System.out.println("JSON Object Not Found");
        }


        try {
            JSONObject jsonObject = new JSONObject(strJson);
            JSONArray jsonArrYesterday = jsonObject.getJSONArray(yestoday);
            for (Object oj : jsonArrYesterday) {
                boolean flag = true;
                ObjectMapper mapper = new ObjectMapper();
                NewsList news = mapper.readValue(oj.toString(), NewsList.class);
                String newsId = news.getNews_id();
                for (int i = 0; i < existingList.size(); i++) {
                    String existingNewsId = existingList.get(i).getNews_id();
                    if (newsId.equals(existingNewsId)) {
                        flag = false;
                        existingList.remove(i);
                    }
                }
                if (flag) {
                    byte[] newImg = GetNewsImage.getImageFromNetByUrl(news.getNews_web_image_uri());
                    news.setNews_img(newImg);
                    newsLists.add(news);
                }
            }

        } catch (JSONException | IOException e) {
            System.out.println("JSON Object Not Found");
            return newsLists;
        }
        return newsLists;
    }

    /* Main for testing */
    public static void main(String[] args) {
        GetNewsList.getNewsList();
    }
}
