package com.unbelievable.uetsupport.objects;

/**
 * Created by huylv on 20/11/2015.
 */
public class News {
    String imageNews;
    String contentNews;

    public News() {
    }

    public News(String imageNews, String contentNews) {
        this.imageNews = imageNews;
        this.contentNews = contentNews;
    }

    public String getImageNews() {
        return imageNews;
    }

    public void setImageNews(String imageNews) {
        this.imageNews = imageNews;
    }

    public String getContentNews() {
        return contentNews;
    }

    public void setContentNews(String contentNews) {
        this.contentNews = contentNews;
    }
}
