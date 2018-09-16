package com.hptg.nhk.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExtractNewsJSONObject {

    public JSONObject ExtractNewsJSONObject(){
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
    }
}
