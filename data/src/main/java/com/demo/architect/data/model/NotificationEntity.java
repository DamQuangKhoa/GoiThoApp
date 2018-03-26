package com.demo.architect.data.model;

/**
 * Created by Yen on 3/26/2018.
 */

public class NotificationEntity {
    private String title;
    private String content;
    private int type;

    public NotificationEntity() {
    }

    public NotificationEntity(String title, String content, int type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }
}
