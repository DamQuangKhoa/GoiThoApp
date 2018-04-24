package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yen on 4/24/2018.
 */

public class SaleEntity {
    @SerializedName("saleId")
    @Expose
    private String saleId;


    @SerializedName("saleContent")
    @Expose
    private String saleContent;


    @SerializedName("salePrice")
    @Expose
    private String salePrice;


    public String getSaleId() {
        return saleId;
    }

    public String getSaleContent() {
        return saleContent;
    }

    public String getSalePrice() {
        return salePrice;
    }
}
