package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ListSaleEntity implements Serializable {

    @SerializedName("listSale")
    @Expose
    private ArrayList<SaleEntity> listSale;


    public ArrayList<SaleEntity> getListSale() {
        return listSale;
    }
}