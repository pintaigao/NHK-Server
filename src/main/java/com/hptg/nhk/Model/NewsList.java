package com.hptg.nhk.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URL;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "News_List")
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

    @Column(name = "news_url")
    private String news_web_url;

    @Column(name = "public_date")
    private String news_publication_time;

    public NewsList() {
    }

    public NewsList(String news_id, String title, String news_web_image_uri, String news_web_url, String news_publication_time) {
        this.news_id = news_id;
        this.title = title;
        this.news_web_image_uri = news_web_image_uri;
        this.news_web_url = news_web_url;
        this.news_publication_time = news_publication_time;
    }

    /*Useless Info
    private String news_priority_number;
    private String news_prearranged_time;
    private String title_with_ruby;
    private boolean news_file_ver;
    private String news_creation_time;
    private String news_preview_time;
    private boolean news_publication_status;
    private boolean has_news_web_image;
    private boolean has_news_web_movie;
    private boolean has_news_easy_image;
    private boolean has_news_easy_movie;
    private boolean has_news_easy_voice;
    private String news_web_movie_uri;
    private String news_easy_image_uri;
    private String news_easy_movie_uri;
    private String news_easy_voice_uri;
    private boolean news_display_flag;
    */

    public String getNews_id() {
        System.out.println(news_id);
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

    public String getNews_publication_time() {
        System.out.println(news_publication_time);
        return news_publication_time;
    }

    public void setNews_publication_time(String news_publication_time) {
        String[] publicTime = news_publication_time.split(" ");
        this.news_publication_time = publicTime[0];
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "news_id='" + news_id + '\'' +
                ", title='" + title + '\'' +
                ", title_with_ruby='" + title_with_ruby + '\'' +
                ", news_web_image_uri='" + news_web_image_uri + '\'' +
                ", news_web_url='" + news_web_url + '\'' +
                ", news_publication_time='" + news_publication_time + '\'' +
                '}';
    }
}
