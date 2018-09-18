package com.hptg.nhk.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static com.hptg.nhk.utils.GetNewsListJSON.getURLContent;

public class GetNewsDetail {

    public static void GetNewsDetail(String newsId){

        String url = "https://www3.nhk.or.jp/news/easy/"+newsId+"/"+newsId+".html";
        String strJson = getURLContent(url);
        Document doc = Jsoup.parse(strJson);
        System.out.println(doc.body().getElementById("js-article-body"));
        Element title = doc.body().getElementById("#js-article-body");
    }

    public static void main(String[] args) {
        GetNewsDetail("k10011629211000");
    }
}
