package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionEntity {
    @SerializedName("promotionId")
    @Expose
    private String promotionId;

    @SerializedName("promotionName")
    @Expose
    private String promotionName;

    @SerializedName("promotionContent")
    @Expose
    private String promotionContent;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("promotionDate")
    @Expose
    private String promotionDate;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("rule")
    @Expose
    private String rule;


    public PromotionEntity() {
    }



    public String getPromotionId() {
        return promotionId;
    }

    public String getPromotionContent() {
        return promotionContent;
    }

    public String getPromotionDate() {
        return promotionDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRule() {
        return rule;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public String getPrice() {
        return price;
    }
}


