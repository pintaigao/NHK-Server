package com.hptg.nhk.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;

import static com.hptg.nhk.utils.UrlHandler.getURLContent;

public class GetNewsDetail {

    public static String[] getNewsDetail(String newsId){
        String url = "https://www3.nhk.or.jp/news/easy/"+newsId+"/"+newsId+".html";
        String strJson = getURLContent(url);
        Document doc = Jsoup.parse(strJson);
        String[] str = new String[3];
        str[0] = doc.body().getElementsByClass("article-main__title").toString().trim();
        str[1] = doc.body().getElementById("js-article-date").text().trim().substring(1,14);
        str[2] = doc.body().getElementById("js-article-body").toString();
        System.out.println(str[0]);
        return str;
    }

    public static void main(String[] args) {
        getNewsDetail("k10011579301000");
    }

}
