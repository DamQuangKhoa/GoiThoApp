package com.demo.architect.data.model;

/**
 * Created by Yen on 3/27/2018.
 */

public class OrderEntity {
    private String content;
    private String timeEdit;
    private int status;
    private int point;
    private String reason;

    public OrderEntity() {
    }

    public OrderEntity(String content, String timeEdit, int status, int point, String reason) {
        this.content = content;
        this.timeEdit = timeEdit;
        this.status = status;
        this.point = point;
        this.reason = reason;
    }

    public String getContent() {
        return content;
    }

    public String getTimeEdit() {
        return timeEdit;
    }

    public int getStatus() {
        return status;
    }

    public int getPoint() {
        return point;
    }

    public String getReason() {
        return reason;
    }
}
