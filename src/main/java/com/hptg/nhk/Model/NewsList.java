package com.hptg.nhk.Model;

import java.net.URL;
import java.util.Date;

public class NewsList {
    private String news_id;
    private String title;
    private String news_web_image_uri;
    private String news_web_url;
    private String news_publication_time;

    /* Useless Info */
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

    public String getNews_priority_number() {
        return news_priority_number;
    }

    public void setNews_priority_number(String news_priority_number) {
        this.news_priority_number = news_priority_number;
    }

    public String getNews_prearranged_time() {
        return news_prearranged_time;
    }

    public void setNews_prearranged_time(String news_prearranged_time) {
        this.news_prearranged_time = news_prearranged_time;
    }

    public String getTitle_with_ruby() {
        return title_with_ruby;
    }

    public void setTitle_with_ruby(String title_with_ruby) {
        this.title_with_ruby = title_with_ruby;
    }

    public boolean isNews_file_ver() {
        return news_file_ver;
    }

    public void setNews_file_ver(boolean news_file_ver) {
        this.news_file_ver = news_file_ver;
    }

    public String getNews_creation_time() {
        return news_creation_time;
    }

    public void setNews_creation_time(String news_creation_time) {
        this.news_creation_time = news_creation_time;
    }

    public String getNews_preview_time() {
        return news_preview_time;
    }

    public void setNews_preview_time(String news_preview_time) {
        this.news_preview_time = news_preview_time;
    }

    public boolean isNews_publication_status() {
        return news_publication_status;
    }

    public void setNews_publication_status(boolean news_publication_status) {
        this.news_publication_status = news_publication_status;
    }

    public boolean isHas_news_web_image() {
        return has_news_web_image;
    }

    public void setHas_news_web_image(boolean has_news_web_image) {
        this.has_news_web_image = has_news_web_image;
    }

    public boolean isHas_news_web_movie() {
        return has_news_web_movie;
    }

    public void setHas_news_web_movie(boolean has_news_web_movie) {
        this.has_news_web_movie = has_news_web_movie;
    }

    public boolean isHas_news_easy_image() {
        return has_news_easy_image;
    }

    public void setHas_news_easy_image(boolean has_news_easy_image) {
        this.has_news_easy_image = has_news_easy_image;
    }

    public boolean isHas_news_easy_movie() {
        return has_news_easy_movie;
    }

    public void setHas_news_easy_movie(boolean has_news_easy_movie) {
        this.has_news_easy_movie = has_news_easy_movie;
    }

    public boolean isHas_news_easy_voice() {
        return has_news_easy_voice;
    }

    public void setHas_news_easy_voice(boolean has_news_easy_voice) {
        this.has_news_easy_voice = has_news_easy_voice;
    }

    public String getNews_web_movie_uri() {
        return news_web_movie_uri;
    }

    public void setNews_web_movie_uri(String news_web_movie_uri) {
        this.news_web_movie_uri = news_web_movie_uri;
    }

    public String getNews_easy_image_uri() {
        return news_easy_image_uri;
    }

    public void setNews_easy_image_uri(String news_easy_image_uri) {
        this.news_easy_image_uri = news_easy_image_uri;
    }

    public String getNews_easy_movie_uri() {
        return news_easy_movie_uri;
    }

    public void setNews_easy_movie_uri(String news_easy_movie_uri) {
        this.news_easy_movie_uri = news_easy_movie_uri;
    }

    public String getNews_easy_voice_uri() {
        return news_easy_voice_uri;
    }

    public void setNews_easy_voice_uri(String news_easy_voice_uri) {
        this.news_easy_voice_uri = news_easy_voice_uri;
    }

    public boolean isNews_display_flag() {
        return news_display_flag;
    }

    public void setNews_display_flag(boolean news_display_flag) {
        this.news_display_flag = news_display_flag;
    }
}
