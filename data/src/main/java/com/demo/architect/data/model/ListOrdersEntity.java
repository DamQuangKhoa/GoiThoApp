package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListOrdersEntity implements Serializable {
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("listOrders")
    @Expose
    private ArrayList<OrderEntity> listOrders;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<OrderEntity> getListOrders() {
        return listOrders;
    }

    public void setListOrders(ArrayList<OrderEntity> listOrders) {
        this.listOrders = listOrders;
    }
}