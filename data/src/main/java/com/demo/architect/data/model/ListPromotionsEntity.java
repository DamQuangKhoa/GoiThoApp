package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListPromotionsEntity {
    @SerializedName("listPromotion")
    @Expose
    private ArrayList<PromotionEntity> listPromotion;

    public ArrayList<PromotionEntity> getListPromotion() {
        return listPromotion;
    }
}
