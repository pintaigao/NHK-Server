package com.hptg.nhk.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.hptg.nhk.utils.UrlHandler.getURLContent;

public class GetNewsDetail {

    static Map<String, String> wordRubyContainer = new LinkedHashMap<>();
    static int count = 0;

    public static String[] getNewsDetail(String newsId) {
        String url = "https://www3.nhk.or.jp/news/easy/" + newsId + "/" + newsId + ".html";
        String strJson = getURLContent(url);
        Document doc = Jsoup.parse(strJson);
        String[] str = new String[3];
        str[0] = doc.body().getElementsByClass("article-main__title").toString().trim();
        str[1] = doc.body().getElementById("js-article-date").text().trim().substring(1, 14);
        str[2] = doc.body().getElementById("js-article-body").children().toString();
        handleContentRuby(doc.body().getElementById("js-article-body").children().get(0),doc.body().getElementById("js-article-body").children().get(0).childNodes());
        return str;
    }

    public static void handleContentATage(Element element){
        Elements childsInsideATag = element.children();

        if(childsInsideATag.get(0).nodeName() == "ruby"){
            String word = childsInsideATag.get(0).child(0).text();
            String ruby = childsInsideATag.get(0).child(1).text();
            wordRubyContainer.put(ruby,word);
        }else if(childsInsideATag.get(0).nodeName() == "span"){
            String word = childsInsideATag.get(0).child(0).text();
            wordRubyContainer.put("content" + count,word);
            count += 1;
        }
        if(childsInsideATag.get(1).nodeName() == "span"){
            String word = childsInsideATag.get(1).text();
            wordRubyContainer.put("content" + count,word);
            count += 1;
        }

    }


    public static void handleContentRuby(Element firstpara,List<Node> childlist) {

        /* Get all the items from this Father tag*/
        List<Node> listNode = childlist;

        /* Only get the list of the item that have rape in a tag */
        Elements firstparaContent = firstpara.children();

        for (Node n : listNode) {
            String currnetNodeNameFromNode  = n.nodeName();
            Element currentElement = firstparaContent.first() ;
            String currentNodeNameFromELement = currentElement.nodeName() == null?"no name":currentElement.nodeName();
            if (currnetNodeNameFromNode == currentNodeNameFromELement) {
                if (currnetNodeNameFromNode == "span") {
                    Elements InsideElement = currentElement.children();
                    if (InsideElement.isEmpty()) {
                        wordRubyContainer.put("content" + count, firstparaContent.first().text());
                        count += 1;
                        firstparaContent = firstparaContent.size() == 1? firstparaContent :firstparaContent.next() ;
                    } else {
                        handleContentRuby(currentElement,firstparaContent.first().childNodes());
                        firstparaContent = firstparaContent.size() == 1? firstparaContent :firstparaContent.next() ;
                    }
                } else if (n.nodeName() == "ruby") {
                    System.out.println("go here");
                    if (firstparaContent.first().childNode(0).nodeName() != "span") {
                        String word = firstparaContent.first().childNode(0).toString();
                        String ruby0 = firstparaContent.first().children().text();
                        wordRubyContainer.put(ruby0, word);
                        firstparaContent = firstparaContent.size() == 1? firstparaContent :firstparaContent.next() ;
                    } else {
                        handleContentRuby(currentElement,firstparaContent.first().childNodes());
                        firstparaContent = firstparaContent.size() == 1? firstparaContent :firstparaContent.next() ;
                    }

                } else if (n.nodeName() == "a"){
                    handleContentATage(firstparaContent.first());
                    firstparaContent = firstparaContent.size() == 1? firstparaContent :firstparaContent.next() ;
                }
            } else {
                wordRubyContainer.put("content" + count, n.toString());
                count += 1;
            }

        }

        System.out.println(wordRubyContainer);
    }

    public static void main(String[] args) {
        getNewsDetail("k10011579301000");
    }

}