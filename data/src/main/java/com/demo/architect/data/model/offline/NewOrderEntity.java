package com.demo.architect.data.model.offline;

import java.util.Date;

/**
 * Created by Skull on 25/03/2018.
 */

public class NewOrderEntity {
    private String title;
    private String address;
    private Date date;
    private boolean isSubcribe;

    public NewOrderEntity() {
    }

    public NewOrderEntity(String title, String address, Date date, boolean isSubcribe) {
        this.title = title;
        this.address = address;
        this.date = date;
        this.isSubcribe = isSubcribe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSubcribe() {
        return isSubcribe;
    }

    public void setSubcribe(boolean subcribe) {
        isSubcribe = subcribe;
    }
}
