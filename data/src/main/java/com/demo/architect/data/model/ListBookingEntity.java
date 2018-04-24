package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ListBookingEntity implements Serializable {

    @SerializedName("listOrder")
    @Expose
    private ArrayList<BookingEntity> listOrder;


    public ArrayList<BookingEntity> getListOrder() {
        return listOrder;
    }

    public void setListOrders(ArrayList<BookingEntity> listOrder) {
        this.listOrder = listOrder;
    }

}