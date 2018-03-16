package com.demo.architect.data.model.offline;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Skull on 29/11/2017.
 */
@SuppressWarnings("serial")
public class DetailDiaryEntity implements Serializable {
    private ProductEntity product;
    private String action;
    private String note;
    private Date date;
    private String user;

    public DetailDiaryEntity() {
    }

    public DetailDiaryEntity(ProductEntity product, String action, String note, Date date, String user) {
        this.product = product;
        this.action = action;
        this.note = note;
        this.date = date;
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
