package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListPromotionsEntity {
    @SerializedName("listPromotion")
    @Expose
    private List<PromotionEntity> listPromotion;

    public List<PromotionEntity> getListPromotion() {
        return listPromotion;
    }
}
