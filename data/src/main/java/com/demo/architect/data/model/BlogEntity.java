package com.demo.architect.data.model;

/**
 * Created by Yen on 3/28/2018.
 */

public class BlogEntity {
    private String title;
    private String dateCreate;
    private int view;
    private int like;

    public BlogEntity() {
    }

    public BlogEntity(String title, String dateCreate, int view, int like) {
        this.title = title;
        this.dateCreate = dateCreate;
        this.view = view;
        this.like = like;
    }

    public String getTitle() {
        return title;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public int getView() {
        return view;
    }

    public int getLike() {
        return like;
    }
}
