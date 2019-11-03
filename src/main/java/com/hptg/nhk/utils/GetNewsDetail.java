package com.hptg.nhk.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.hptg.nhk.utils.UrlHandler.getURLContent;

public class GetNewsDetail {

    private List<Map<String, String>> title = new LinkedList<>();
    private List<Map<String, String>> publicDate = new LinkedList<>();
    private List<Map<String, String>> articlelist = new LinkedList<>();


    public List<Map<String, String>> getArticlelist() {
        return articlelist;
    }

    public List<Map<String, String>> getTitle() {
        return title;
    }

    public List<Map<String, String>> getPublicDate() {
        return publicDate;
    }

    int count = 0;

    public void getNewsDetail(String newsId) {

        String url = "https://www3.nhk.or.jp/news/easy/" + newsId + "/" + newsId + ".html";
        String strJson = getURLContent(url);
        Document doc = Jsoup.parse(strJson);
        
        Map<String, String> titleRubyContainer = new LinkedHashMap<>();
        handleContentRuby(doc.body().getElementsByClass("article-main__title").get(0),doc.body().getElementsByClass("article-main__title").get(0).childNodes(),titleRubyContainer);
        title.add(titleRubyContainer);

        Map<String, String> dateContainer = new LinkedHashMap<>();
        dateContainer.put("publicDate", doc.body().getElementById("js-article-date").text().trim().substring(1, 14));
        publicDate.add(dateContainer);

        int childNum = doc.body().getElementById("js-article-body").children().size();
        for (int i = 0; i < childNum; i++) {
            Map<String, String> wordRubyContainer = new LinkedHashMap<>();
            handleContentRuby(doc.body().getElementById("js-article-body").children().get(i), doc.body().getElementById("js-article-body").children().get(i).childNodes(), wordRubyContainer);
            System.out.println(wordRubyContainer);
            articlelist.add(wordRubyContainer);
        }

    }

    private void handleContentATage(Element element, Map<String, String> wordRubyContainer) {
        Elements childsInsideATag = element.children();
        if (childsInsideATag.get(0).nodeName() == "ruby") {
            String word = childsInsideATag.get(0).child(0).text();
            String ruby = childsInsideATag.get(0).child(1).text();
            wordRubyContainer.put(ruby, word);
        } else if (childsInsideATag.get(0).nodeName() == "span") {
            Element el = childsInsideATag.get(0);


            if (el.childNodeSize() != 0) {
                wordRubyContainer.put("content" + count, childsInsideATag.get(0).text());
                count += 1;
            } else {
                String word = childsInsideATag.get(0).child(0).text();
                wordRubyContainer.put("content" + count, word);
                count += 1;
            }
        }

        if (childsInsideATag.size() > 1 && childsInsideATag.get(1).nodeName() == "span") {
            String word = childsInsideATag.get(1).text();
            wordRubyContainer.put("content" + count, word);
            count += 1;
        }

    }


    private void handleContentRuby(Element firstpara, List<Node> childlist, Map<String, String> wordRubyContainer) {

        /* Get all the items from this Father tag*/
        List<Node> listNode = childlist;

        /* Only get the list of the item that have rape in a tag */
        Elements firstparaContent = firstpara.children();

        for (Node n : listNode) {
            String currnetNodeNameFromNode = n.nodeName();
            Element currentElement = firstparaContent.first();
            String currentNodeNameFromELement = currentElement.nodeName() == null ? "no name" : currentElement.nodeName();
            if (currnetNodeNameFromNode == currentNodeNameFromELement) {
                if (currnetNodeNameFromNode == "span") {
                    Elements InsideElement = currentElement.children();
                    if (InsideElement.isEmpty()) {
                        wordRubyContainer.put("content" + count, firstparaContent.first().text());
                        count += 1;
                        firstparaContent = firstparaContent.size() == 1 ? firstparaContent : firstparaContent.next();
                    } else {
                        handleContentRuby(currentElement, firstparaContent.first().childNodes(), wordRubyContainer);
                        firstparaContent = firstparaContent.size() == 1 ? firstparaContent : firstparaContent.next();
                    }
                } else if (n.nodeName() == "ruby") {
                    if (firstparaContent.first().childNode(0).nodeName() != "span") {
                        String word = firstparaContent.first().childNode(0).toString();
                        String ruby0 = firstparaContent.first().children().text();
                        wordRubyContainer.put(ruby0, word);
                        firstparaContent = firstparaContent.size() == 1 ? firstparaContent : firstparaContent.next();
                    } else {
                        handleContentRuby(currentElement, firstparaContent.first().childNodes(), wordRubyContainer);
                        firstparaContent = firstparaContent.size() == 1 ? firstparaContent : firstparaContent.next();
                    }

                } else if (n.nodeName() == "a") {
                    handleContentATage(firstparaContent.first(), wordRubyContainer);
                    firstparaContent = firstparaContent.size() == 1 ? firstparaContent : firstparaContent.next();
                }
            } else {
                wordRubyContainer.put("content" + count, n.toString());
                count += 1;
            }

        }
    }

    public static void main(String[] args) {
        GetNewsDetail g = new GetNewsDetail();
        g.getNewsDetail("k10012156541000");
        System.out.println();
    }

}