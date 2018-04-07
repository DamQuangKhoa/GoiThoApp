package com.demo.architect.data.model;

import java.io.Serializable;

/**
 * Created by Yen on 3/28/2018.
 */

public class PostEntity implements Serializable{
    private String title;
    private int image;
    private String dateCreate;
    private int view;
    private int like;

    public PostEntity() {
    }

    public PostEntity(String title, int image, String dateCreate, int view, int like) {
        this.title = title;
        this.image = image;
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

    public int getImage() {
        return image;
    }
}
