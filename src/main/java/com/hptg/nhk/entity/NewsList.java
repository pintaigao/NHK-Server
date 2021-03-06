package com.hptg.nhk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "news_list")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsList {

    @Id
    @Column(name = "news_id")
    private String news_id;

    @Column(name="title")
    private String title;

    @Column(name = "title_with_ruby")
    private String title_with_ruby;

    @Column(name = "news_image_url")
    private String news_web_image_uri;

    @Column(name = "public_date")
    private String news_prearranged_time;

    @Column(name = "news_url")
    private String news_web_url;

    @Lob
    @Column(name = "news_photo")
    private byte[] news_img;

    public NewsList() {
    }



    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_with_ruby() {
        return title_with_ruby;
    }

    public void setTitle_with_ruby(String title_with_ruby) {
        this.title_with_ruby = title_with_ruby;
    }

    public String getNews_web_image_uri() {
        if(news_web_image_uri.equals("")){
            return news_web_image_uri;
        }
        String url_clone = new String(news_web_image_uri);
        String checking = url_clone.substring(0,5);
        if(!checking.equals("https")){
            String[] urlarr = url_clone.split(".");
            System.out.println(url_clone);
            String prefixurl = "https://www3.nhk.or.jp/news/easy/" + urlarr[0] + "/" + url_clone;
            return prefixurl;
        }
        return news_web_image_uri;
    }

    public void setNews_web_image_uri(String news_web_image_uri) {
        this.news_web_image_uri = news_web_image_uri;
    }

    public String getNews_web_url() {
        return news_web_url;
    }

    public void setNews_web_url(String news_web_url) {
        this.news_web_url = news_web_url;
    }


    public String getNews_prearranged_time() {
        return news_prearranged_time;
    }

    public void setNews_prearranged_time(String news_prearranged_time) {
        String[] publicTime = news_prearranged_time.split(" ");
        this.news_prearranged_time = publicTime[0];
    }

    public byte[] getNews_img() {
        return news_img;
    }

    public void setNews_img(byte[] news_img) {
        this.news_img = news_img;
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "news_id='" + news_id + '\'' +
                ", title='" + title + '\'' +
                ", title_with_ruby='" + title_with_ruby + '\'' +
                ", news_web_image_uri='" + news_web_image_uri + '\'' +
                ", news_web_url='" + news_web_url + '\'' +
                ", news_publication_time='" + news_prearranged_time + '\'' +
                '}';
    }
}
