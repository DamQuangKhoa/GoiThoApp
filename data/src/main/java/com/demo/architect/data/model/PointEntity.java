package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yen on 4/24/2018.
 */

public class PointEntity {
    @SerializedName("totalSuccess")
    @Expose
    private int totalSuccess;


    @SerializedName("totalMoney")
    @Expose
    private String totalMoney;


    @SerializedName("totalPoint")
    @Expose
    private int totalPoint;

    public int getTotalSuccess() {
        return totalSuccess;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public int getTotalPoint() {
        return totalPoint;
    }
}
