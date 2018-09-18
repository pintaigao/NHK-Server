package com.hptg.nhk.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;

import static com.hptg.nhk.utils.UrlHandler.getURLContent;

public class GetNewsDetail {

    public static String getNewsDetail(String newsId){
        String url = "https://www3.nhk.or.jp/news/easy/"+newsId+"/"+newsId+".html";
        String strJson = getURLContent(url);
        Document doc = Jsoup.parse(strJson);
        String str = null;
        str = doc.body().getElementById("js-article-body").toString();
        return str;
    }

    public static void main(String[] args) {
        getNewsDetail("k10011579301000");
    }

}
