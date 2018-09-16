package com.hptg.nhk.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hptg.nhk.Model.NewsList;
import com.hptg.nhk.service.NewsListService;
import com.hptg.nhk.service.NewsListServiceImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hptg.nhk.playground.GetNewsList.getURLContent;

public class Main {

    private static NewsListServiceImpl newsListService;

    public static void main(String[] args) {

        String strJson = getURLContent("https://www3.nhk.or.jp/news/easy/news-list.json?_=1537074548180") ;
        strJson = strJson.trim().substring(2,strJson.length());

        /* Store all the news into map with (date:this day's all news) */
        Map<String,JSONArray> dailyNews = new HashMap();

        /* Date Key Set */
        String[] newsDateArr;

        // Initiate specific day's news list
        List<JSONObject> specificDayNews = new ArrayList();

        JSONObject firstDayfirstNews = null;

        try {
            JSONObject jsonObject = new JSONObject(strJson);
            newsDateArr = jsonObject.keySet().toArray(new String[jsonObject.length()]);
            for(String date:newsDateArr){
                JSONArray jsonArr = (JSONArray) jsonObject.get(date);
                dailyNews.put(date,jsonArr);
            }

            // Get the Specific Day News, for now it is the first day, store all this day's news into List
            JSONArray firstDayNews = (JSONArray) jsonObject.get(newsDateArr[0]);
            for(Object jo: firstDayNews){
                specificDayNews.add((JSONObject)jo);
            }

            firstDayfirstNews = (JSONObject) firstDayNews.get(0);


        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println(e);
        }

        /*Store them into the model*/
        ObjectMapper mapper = new ObjectMapper();
        try {
            NewsList newsList = mapper.readValue(firstDayfirstNews.toString(),NewsList.class);
            newsList.getNews_id();

        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
